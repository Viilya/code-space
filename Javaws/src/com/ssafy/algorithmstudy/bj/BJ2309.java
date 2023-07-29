package com.ssafy.algorithmstudy.bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
public class BJ2309 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("BJ2309i.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] numArr = new int[9];
        int totalSum = 0;
        int fake1 = -1, fake2 = -1;
        for (int k = 0; k < 9; k++) {
            numArr[k] = Integer.parseInt(bf.readLine());
            totalSum += numArr[k];
        }
        for (int k = 0; k < 9; k++) {
            for (int s = k + 1; s < 9; s++) {
                if (totalSum - numArr[k] - numArr[s] == 100) {
                    fake1 = numArr[k];
                    fake2 = numArr[s];
                    break;
                }
            }
        }
        Arrays.sort(numArr);
        for (int k = 0; k < 9; k++) {
            if (numArr[k] != fake1 && numArr[k] != fake2) {
                System.out.print(numArr[k] + " ");
            }
        }
        System.out.println();
    }

}
