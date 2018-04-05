package com.test;

import java.lang.reflect.Array;
import java.util.*;

public class Subsequence {

    private boolean bool = true;

    private boolean IsEmpty (List l) {
        if (l.isEmpty()){
            return true;
        } else
            return false;
    }

    public boolean find(List x, List y) {

        boolean bExc = false;



        Exception exc = new NullPointerException();

        if (x == null || y == null) {
            throw new IllegalArgumentException();
        }


        int xSize = x.size();
        int ySize = y.size();



        int i = 0;
        int j = 0;

        if (!x.equals(y) && x.size() != 0) {
            try {
                while (i < x.size()) {
                    if (x.get(i) == y.get(j) && i < xSize && j < ySize) {
                        i++;
                        j++;
                        bool = true;
                    } else if (i < xSize && j < ySize && x.get(i) != y.get(j)) {
                        j++;
                        bool = false;
                    } else {

                    }
                }
            } catch (IndexOutOfBoundsException e) {
                return false;
            }
        } else {
            return true;
        }


        if (xSize > ySize) {
            bool = false;
        }

        if (bExc) {
            bool = true;
        }

        boolean IsOrNot = IsEmpty(x);
        if (IsOrNot && !y.isEmpty())
            bool = true;


        return bool;
    }

}
