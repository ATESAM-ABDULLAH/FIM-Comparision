/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IIMFIsAlgorithm;

import java.util.List;

/**
 *
 * @author Hussein
 */
class IMFIs {
    Integer[] IMFIs;
    int Support;
     IMFIs(Integer item) {
        IMFIs = new Integer[]{item};
    }
     IMFIs(Integer[] items) {
        this.IMFIs = items;
    }
     IMFIs(List<Integer> IMFIs, int support) {
        this.IMFIs = new Integer[IMFIs.size()];
        int i = 0;
        for (int item : IMFIs) {
            this.IMFIs[i++] = item;
        }
        this.Support = support;
    }
     int getAbsoluteSupport() {
        return Support;
    }
     int size() {
        return IMFIs.length;
    }
     int get(int position) {
        return IMFIs[position];
    }
     void setAbsoluteSupport(int support) {
        this.Support = support;
    }
}