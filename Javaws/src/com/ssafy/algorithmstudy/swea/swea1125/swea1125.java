package com.ssafy.algorithmstudy.swea.swea1125;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


public class swea1125 {
    public static int[] numbers;
    public static int[] numbersIdx;

    public static Deque<Integer> dq = new ArrayDeque<Integer>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        for (int test_case = 1; test_case <= 10; test_case++) {
            br.readLine();
            st = new StringTokenizer(br.readLine());
            numbers = new int[8];
            numbersIdx = new int[8];
            int min = Integer.MAX_VALUE;
            int tmp;
            for (int k = 0; k < 8; k++) {
                numbersIdx[k] = k;
                numbers[k] = Integer.parseInt(st.nextToken());
                min = Math.min(min, numbers[k]);
            }
            tmp = min / 15 - 1;
            for (int k = 0; k < 8; k++) {
                numbers[k] -= 15 * tmp;
                dq.addLast(numbers[k]);
            }
            while (true) {
                if (cycle())
                    break;
            }
            bw.write("#" + String.valueOf(test_case));
            for (int k = 0; k < 8; k++) {
                bw.write(" " + String.valueOf(dq.pollFirst()));
            }
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static boolean cycle() {
        for (int k = 1; k <= 5; k++) {
            int number = dq.pollFirst() - k;
            if (number <= 0) {
                dq.addLast(0);
                return true;
            }
            dq.addLast(number);
        }
        return false;
    }
}
