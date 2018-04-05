package com.test;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class Calculator {

    private boolean TryPeek(double num) {
        if (num != 0)
            return true;
        else {
            return false;
        }
    }

    private boolean IsInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean IsDbl(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int priorityCheck(String str) {
        if (str.equals("+") || str.equals("-") || str.equals("(") || str.equals(")"))
            return 1;
        else
            return 2;
    }

    public String evaluate(String s) throws EmptyStackException, NullPointerException {

        try {
            if (s == null) {
                return null;
            }

            char[] chaA = s.toCharArray();
            //int arraLength = chaA.length;
            //System.out.println(chaA.length);

//        for (char c : chaA)
//            System.out.println(c);

            // making list of String out of input parameter

            ArrayList<String> strL = new ArrayList<String>();
            String temp = null;
            String tempF;
            String tempT = null;
            int count = 0;
            int nCount = 0;
            int dotCount = 0;
            int restrictedItems = 0;
            int openBrackets = 0;
            int closedBrackets = 0;
            boolean wrongBrackets = false;

            for (char c : chaA) {
                String tS = Character.toString(c);
                if (openBrackets < closedBrackets) {
                    wrongBrackets = true;
                }
                if (tS.equals("(")) {
                    openBrackets++;
                }
                if (tS.equals(")")) {
                    closedBrackets++;
                }

                if (tS.equals(",") || tS.equals("")) {
                    restrictedItems++;
                    break;
                }
                if (tS.equals("(") || tS.equals(")")) {
                    nCount = 0;
                }
                if (IsInt(tS) == true || tS.equals(".")) {
                    if (IsInt(tS) == false) {
                        dotCount++;
                        if (dotCount > 1) {
                            break;
                        }
                    }
                    nCount = 0;
                    if (count == 1) {
                        tempT = temp + tS;
                    } else if (count > 1) {
                        tempT = tempT + tS;
                    }
                    temp = tS;
                    count++;


                } else if (!IsInt(tS) && nCount == 0) {
                    if (count > 1) {
                        strL.add(tempT);
                        tempT = null;
                        dotCount = 0;
                    }
                    if (tS.equals("(") || tS.equals(")")) {
                        nCount = nCount;
                    } else {
                        nCount++;
                    }
                    if (count == 1) {
                        strL.add(temp);
                    }
                    count = 0;
                    temp = tS;
                    strL.add(temp);
                } else
                    break;
            }
            if (count > 0 && nCount == 0) {
                if (tempT == null) {
                    strL.add(temp);
                } else {
                    strL.add(tempT);
                    tempT = null;
                }
            }

            if (dotCount > 1 || restrictedItems > 0 || wrongBrackets == true || openBrackets != closedBrackets) {
                return null;
            }

//        for (char ch : chaA)
//            System.out.println(ch);
//
//        for (String st : strL)
//            System.out.println(st);

            // sorting incoming list for numbers and operators

            Stack<Double> stackNum = new Stack<Double>();
            Stack<String> stackOp = new Stack<String>();
            double last;
            double secondLast;
            double res;
            double tempRes;
            String finRes;

            for (int i = 0; i < strL.size(); i++) {
                String tempS = strL.get(i);
                if (IsDbl(tempS)) {
                    double tempD = Double.parseDouble(tempS);
                    stackNum.push(tempD);
                } else if (tempS.equals(")")) {
                    if (stackOp.peek().equals("(")) {
                        break;
                    } else {
                        last = stackNum.pop();
                        secondLast = stackNum.pop();
                        if (stackOp.peek().equals("+")) {
                            tempRes = secondLast + last;
                            stackOp.pop();
                            stackNum.push(tempRes);
                            if (stackOp.peek().equals("(")) {
                                stackOp.pop();
                                last = stackNum.pop();
                                secondLast = stackNum.pop();
                                if (stackOp.peek().equals("+")) {
                                    tempRes = secondLast + last;
                                    stackOp.pop();
                                    stackNum.push(tempRes);
                                } else if (stackOp.peek().equals("-")) {
                                    tempRes = secondLast - last;
                                    stackOp.pop();
                                    stackNum.push(tempRes);
                                } else if (stackOp.peek().equals("*")) {
                                    tempRes = secondLast * last;
                                    stackOp.pop();
                                    stackNum.push(tempRes);
                                } else {
                                    tempRes = secondLast / last;
                                    stackOp.pop();
                                    stackNum.push(tempRes);
                                }
                            }
                        } else if (stackOp.peek().equals("-")) {
                            tempRes = secondLast - last;
                            stackOp.pop();
                            stackNum.push(tempRes);
                        } else if (stackOp.peek().equals("*")) {
                            tempRes = secondLast * last;
                            stackOp.pop();
                            stackNum.push(tempRes);
                        } else {
                            tempRes = secondLast / last;
                            stackOp.pop();
                            stackNum.push(tempRes);
                        }
                    }
                } else if (tempS.equals("(")) {
                    stackOp.push(tempS);
                } else if (!IsDbl(tempS)) {
                    if (stackOp.empty()) {
                        stackOp.push(tempS);
                    } else {
                        if (priorityCheck(tempS) == priorityCheck(stackOp.peek()) && !stackOp.peek().equals("(") && !stackOp.peek().equals(")")) {
                            last = stackNum.pop();
                            secondLast = stackNum.pop();
                            if (stackOp.peek().equals("+")) {
                                tempRes = secondLast + last;
                                stackOp.pop();
                                stackNum.push(tempRes);
                                stackOp.push(tempS);
                            } else if (stackOp.peek().equals("-")) {
                                tempRes = secondLast - last;
                                stackOp.pop();
                                stackNum.push(tempRes);
                                stackOp.push(tempS);
                            } else if (stackOp.peek().equals("*")) {
                                tempRes = secondLast * last;
                                stackOp.pop();
                                stackNum.push(tempRes);
                                stackOp.push(tempS);
                            } else {
                                tempRes = secondLast / last;
                                stackOp.pop();
                                stackNum.push(tempRes);
                                stackOp.push(tempS);
                            }
                        } else if (priorityCheck(tempS) > priorityCheck(stackOp.peek()) || stackOp.peek().equals("(")
                                || stackOp.peek().equals(")")) {
                            stackOp.push(tempS);
                        } else {
                            last = stackNum.pop();
                            secondLast = stackNum.pop();
                            if (stackOp.peek().equals("+")) {
                                tempRes = secondLast + last;
                                stackOp.pop();
                                stackNum.push(tempRes);
                                stackOp.push(tempS);
                            } else if (stackOp.peek().equals("-")) {
                                tempRes = secondLast - last;
                                stackOp.pop();
                                stackNum.push(tempRes);
                                stackOp.push(tempS);
                            } else if (stackOp.peek().equals("*")) {
                                tempRes = secondLast * last;
                                stackOp.pop();
                                stackNum.push(tempRes);
                                stackOp.push(tempS);
                            } else {
                                tempRes = secondLast / last;
                                stackOp.pop();
                                stackNum.push(tempRes);
                                stackOp.push(tempS);
                            }
                        }
                    }
                }
            }



            // clearing up the stacks

            if (stackOp.empty()) {
                res = stackNum.pop();
            } else {
                if (stackOp.empty()) {
                    res = stackNum.pop();
                } else {
                    while (!stackOp.empty()) {
                        last = stackNum.pop();
                        secondLast = stackNum.pop();
                        if (stackOp.peek().equals("+")) {
                            tempRes = secondLast + last;
                            stackOp.pop();
                            stackNum.push(tempRes);
                        } else if (stackOp.peek().equals("-")) {
                            tempRes = secondLast - last;
                            stackOp.pop();
                            stackNum.push(tempRes);
                        } else if (stackOp.peek().equals("*")) {
                            tempRes = secondLast * last;
                            stackOp.pop();
                            stackNum.push(tempRes);
                        } else {
                            tempRes = secondLast / last;
                            stackOp.pop();
                            stackNum.push(tempRes);
                        }
                    }
                    res = stackNum.pop();
                }
            }

            if (res % 1 == 0) {
                int in = (int) res;
                finRes = Integer.toString(in);
            } else {
                finRes = Double.toString(res);
            }

//            for (Double d : stackNum)
//                System.out.println(d);
//
//            for (String st : stackOp)
//                System.out.println(st);

            return finRes;
        } catch (EmptyStackException e) {
            return null;
        }
    }
}
