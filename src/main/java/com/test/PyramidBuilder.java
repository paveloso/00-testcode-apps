package com.test;

import sun.util.resources.cldr.ebu.CalendarData_ebu_KE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    public int[][] buildPyramid(List<Integer> input) throws CannotBuildPyramidException {



            //a list of accepted amount of integers in a list
            int startingCount = 3;
            int num = 3;
            List<Integer> amountList = new ArrayList<>();
            amountList.add(startingCount);

            for (int i = 0; i < 100; i++) {
                num = num + startingCount + i;
                amountList.add(num);
            }

            try {
                Collections.sort(input);
                input.toArray();
            } catch (Exception e) {
                throw new CannotBuildPyramidException("");
            } catch (OutOfMemoryError e) {
                throw new CannotBuildPyramidException("");
            }
            //checking if amount of an Integers is accepted
            int sizeOfInput = input.size();

            boolean numAccept = false;
            try {
                for (int i = 0; i < sizeOfInput; i++) {
                    int numInList = amountList.get(i);
                    if (sizeOfInput == numInList) {
                        numAccept = true;
                        break;
                    } else {
                        numAccept = false;
                    }
                }
            } catch (Exception e) {
                throw new CannotBuildPyramidException("");
            }



                //start building a 2D array

                int rowCount = amountList.indexOf(sizeOfInput) + 2; //rows count in final 2D array
                int colCount = rowCount + amountList.indexOf(sizeOfInput) + 1;//columns count in final 2D array
                int[][] pyramid = new int[rowCount][colCount];//creating 2D array

                int row = 0;
                int column = 0;
                int index = 0;
                int mid = colCount / 2;
                int secMid;
                int floatNum = 0;

                pyramid[row][mid] = input.get(index);
                for (int k = 0; k < rowCount && index < input.size() - 1; k++) {
                    index++;
                    row++;
                    floatNum = 1;
                    mid = mid - floatNum;
                    secMid = mid;
                    pyramid[row][mid] = input.get(index);
                    for (int i = 0; i < row; i++) {
                        index++;
                        secMid = secMid + 2;
                        pyramid[row][secMid] = input.get(index);
                    }
                }



        return pyramid;
    }
}
