# Comparison of Apriori Algorithm vs IIMFI for Frequent Itemset Mining and Optimization Strategies

This repository contains implementations and comparisons of the Apriori algorithm and the Incremental Interesting Maximal Frequent Itemset (IIMFI) algorithm for frequent itemset mining. The project explores optimization strategies for both algorithms.

## Repository Structure

- `Apriori/`: Contains the implementation of the Apriori algorithm.
- `IIMFIs/`: Contains the implementation of the IIMFI algorithm.
- `Opt-apriori/`: Contains the optimized version of the Apriori algorithm.
- `accidents.dat`, `chess.dat`, `connect.dat`: Dataset files used for testing.
- `optimization.md`: Documentation on optimization strategies applied.
- `Project-Report.pdf`: Detailed report of the project.
- `results-new.txt`, `results-old.txt`: Output results from algorithm executions.
- `TopN-data.sh`: Script related to data processing.

## Usage

1. **Apriori Algorithm**: Navigate to the `Apriori/` directory and compile the Java files. Execute `Main.java` to run the algorithm.
2. **IIMFI Algorithm**: Navigate to the `IIMFIs/` directory and compile the Java files. Execute `NewMain.java` to run the algorithm.
3. **Optimized Apriori**: Navigate to the `Opt-apriori/` directory and compile the Java files. Execute `OptimizedApriori.java` to run the optimized version.

Ensure that the dataset files are in the appropriate directories before execution.

## Datasets

The datasets used in this project include:

- `accidents.dat`: Accident data.
- `chess.dat`: Chess game data.
- `connect.dat`: Connection data.

These datasets are used to evaluate the performance of the algorithms.

## Report

For a comprehensive understanding of the project, including methodology, results, and analysis, refer to the [Project Report](Project-Report.pdf).

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
