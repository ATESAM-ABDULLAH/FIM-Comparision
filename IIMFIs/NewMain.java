/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IIMFIsAlgorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lenovo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        String File="../connect.dat";
        double min_Novelty=0.5;
        double min_Support;

        // Test 1
        AlgoIIMFIs algoIIMFIs1=new AlgoIIMFIs();
        List<PD_IMFIs> P1 = new ArrayList<>();
        min_Support=0.50;

        algoIIMFIs1.runAlgorithm(File, P1, false, min_Support, min_Novelty);
        algoIIMFIs1.printStats();

        // Test 2
        AlgoIIMFIs algoIIMFIs2=new AlgoIIMFIs();
        List<PD_IMFIs> P2 = new ArrayList<>();
        min_Support=0.70;

        algoIIMFIs2.runAlgorithm(File, P2, false, min_Support, min_Novelty);
        algoIIMFIs2.printStats();

        // Test 1
        AlgoIIMFIs algoIIMFIs3=new AlgoIIMFIs();
        List<PD_IMFIs> P3 = new ArrayList<>();
        min_Support=0.70;

        algoIIMFIs3.runAlgorithm(File, P3, false, min_Support, min_Novelty);
        algoIIMFIs3.printStats();

    }
}
