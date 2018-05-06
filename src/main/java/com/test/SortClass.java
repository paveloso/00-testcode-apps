package com.test;

import java.lang.reflect.Array;
import java.util.TreeSet;

// Insertion sort implementation

public class SortClass {

    public int[] sortTheArray(int[] array) {

        int indexOfMin = 0;
        int tempNum;
        int indexToSwap;
        int round = 0;

        while (round < array.length - 1) {

            int currentMin = array[round];

            for (int i = round; i < array.length; i++) {
                if (array[i] <= currentMin) {
                    currentMin = array[i];
                    indexOfMin = i;
                }
            }

            if (indexOfMin == round) {
                round++;
            } else {
                if (array[indexOfMin] == array[round]) {
                    round++;
                } else {

                    round++;

                    tempNum = array[round - 1];
                    array[round - 1] = array[indexOfMin];
                    array[indexOfMin] = tempNum;

                }

            }
        }

        return array;
    }
}
