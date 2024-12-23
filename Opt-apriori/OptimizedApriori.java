package apriori;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OptimizedApriori {

    protected int k;
    protected int totalCandidateCount = 0;
    protected long startTimestamp;
    protected long endTimestamp;
    private int itemsetCount;
    private int databaseSize;
    private int minsupRelative;
    private int maxPatternLength = 10000;
    private List<int[]> database = null;
    protected Itemsets patterns = null;
    BufferedWriter writer = null;

    public OptimizedApriori() {}

    public Itemsets runAlgorithm(double minsup, String input, String output) throws IOException {
        if (output == null) {
            writer = null;
            patterns = new Itemsets("FREQUENT ITEMSETS");
        } else {
            writer = new BufferedWriter(new FileWriter(output));
            patterns = null;
        }

        startTimestamp = System.currentTimeMillis();
        itemsetCount = 0;
        totalCandidateCount = 0;
        MemoryLogger.getInstance().reset();

        databaseSize = 0;
        int[] itemCounts = new int[10000]; // Assume max item ID is 9999 for simplicity
        database = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(input));
        reader.lines().filter(line -> !line.isEmpty() && line.charAt(0) != '#' && line.charAt(0) != '%')
                .forEach(line -> {
                    String[] tokens = line.split(" ");
                    int[] transaction = Arrays.stream(tokens).mapToInt(Integer::parseInt).toArray();
                    database.add(transaction);
                    databaseSize++;
                    for (int item : transaction) {
                        itemCounts[item]++;
                    }
                });
        reader.close();

        this.minsupRelative = (int) Math.ceil(minsup * databaseSize);

        int[] frequent1 = IntStream.range(0, itemCounts.length)
                .filter(i -> itemCounts[i] >= minsupRelative)
                .toArray();

        Arrays.sort(frequent1);

        for (int item : frequent1) {
            saveItemsetToFile(item, itemCounts[item]);
        }

        if (frequent1.length == 0 || maxPatternLength <= 1) {
            endTimestamp = System.currentTimeMillis();
            MemoryLogger.getInstance().checkMemory();
            if (writer != null) {
                writer.close();
            }
            return patterns;
        }

        totalCandidateCount += frequent1.length;

        List<Itemset> level = null;
        k = 2;
        do {
            MemoryLogger.getInstance().checkMemory();

            List<Itemset> candidatesK;
            if (k == 2) {
                candidatesK = generateCandidate2(frequent1);
            } else {
                candidatesK = generateCandidateSizeK(level);
            }

            totalCandidateCount += candidatesK.size();

            for (int[] transaction : database) {
                if (transaction.length < k) continue;
                for (Itemset candidate : candidatesK) {
                    if (isSubset(transaction, candidate.itemset)) {
                        candidate.support++;
                    }
                }
            }

            level = new ArrayList<>();
            if (k < maxPatternLength + 1) {
                for (Itemset candidate : candidatesK) {
                    if (candidate.getAbsoluteSupport() >= minsupRelative) {
                        level.add(candidate);
                        saveItemset(candidate);
                    }
                }
            }
            k++;
        } while (!level.isEmpty());

        endTimestamp = System.currentTimeMillis();
        MemoryLogger.getInstance().checkMemory();

        if (writer != null) {
            writer.close();
        }

        return patterns;
    }

    private boolean isSubset(int[] transaction, int[] candidate) {
        int i = 0, j = 0;
        while (i < transaction.length && j < candidate.length) {
            if (transaction[i] < candidate[j]) {
                i++;
            } else if (transaction[i] == candidate[j]) {
                i++;
                j++;
            } else {
                return false;
            }
        }
        return j == candidate.length;
    }

    private List<Itemset> generateCandidate2(int[] frequent1) {
        List<Itemset> candidates = new ArrayList<>();
        for (int i = 0; i < frequent1.length; i++) {
            for (int j = i + 1; j < frequent1.length; j++) {
                candidates.add(new Itemset(new int[]{frequent1[i], frequent1[j]}));
            }
        }
        return candidates;
    }

    protected List<Itemset> generateCandidateSizeK(List<Itemset> levelK_1) {
        List<Itemset> candidates = new ArrayList<>();
        for (int i = 0; i < levelK_1.size(); i++) {
            int[] itemset1 = levelK_1.get(i).itemset;
            for (int j = i + 1; j < levelK_1.size(); j++) {
                int[] itemset2 = levelK_1.get(j).itemset;
                if (Arrays.equals(Arrays.copyOf(itemset1, itemset1.length - 1),
                        Arrays.copyOf(itemset2, itemset2.length - 1))) {
                    int[] newItemset = Arrays.copyOf(itemset1, itemset1.length + 1);
                    newItemset[newItemset.length - 1] = itemset2[itemset2.length - 1];
                    candidates.add(new Itemset(newItemset));
                }
            }
        }
        return candidates;
    }

    void saveItemset(Itemset itemset) throws IOException {
        itemsetCount++;
        if (writer != null) {
            writer.write(itemset.toString() + " #SUP: " + itemset.getAbsoluteSupport());
            writer.newLine();
        } else {
            patterns.addItemset(itemset, itemset.size());
        }
    }

    void saveItemsetToFile(int item, int support) throws IOException {
        itemsetCount++;
        if (writer != null) {
            writer.write(item + " #SUP: " + support);
            writer.newLine();
        } else {
            Itemset itemset = new Itemset(item);
            itemset.setAbsoluteSupport(support);
            patterns.addItemset(itemset, 1);
        }
    }

    public void printStats() {
        System.out.println("=============  APRIORI - STATS =============");
        System.out.println(" Candidates count : " + totalCandidateCount);
        System.out.println(" The algorithm stopped at size " + (k - 1));
        System.out.println(" Frequent itemsets count : " + itemsetCount);
        System.out.println(" Maximum memory usage : " + MemoryLogger.getInstance().getMaxMemory() + " mb");
        System.out.println(" Total time ~ " + (endTimestamp - startTimestamp) + " ms");
        System.out.println("===================================================");
    }

    public void setMaximumPatternLength(int length) {
        maxPatternLength = length;
    }
}
