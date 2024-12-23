package apriori;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
/**
 * Example of how to use APRIORI algorithm from the source code.
 * @author Philippe Fournier-Viger (Copyright 2008)
 */
public class Main{

	public static void main(String [] arg) throws IOException{

		String input = "../chess.dat";
		String output = null;  
		double minsup;
				
		// Test 1
		minsup = 0.5;
		AlgoApriori algo1 = new AlgoApriori();
		algo1.runAlgorithm(minsup, input, output);
		algo1.printStats();

		// Test 2
		minsup = 0.7;
		AlgoApriori algo2 = new AlgoApriori();
		algo2.runAlgorithm(minsup, input, output);
		algo2.printStats();

		// Test 2
		minsup = 0.9;
		AlgoApriori algo3 = new AlgoApriori();
		algo3.runAlgorithm(minsup, input, output);
		algo3.printStats();
	}
}
