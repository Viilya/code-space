package com.ssafy.algorithmstudy.swea.swea1228;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class swea1228 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        for (int test_case = 1; test_case <= 10; test_case++) {
            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            LinkedList<Integer> list = new LinkedList<Integer>();
            int[] pw = new int[n];
            for (int k = 0; k < n; k++)
                list.add(Integer.parseInt(st.nextToken()));

            int t = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < t; k++) {
                st.nextToken();
                int startIdx = Integer.parseInt(st.nextToken());
                int correctionCount = Integer.parseInt(st.nextToken());
                for (int s = startIdx; s < startIdx + correctionCount; s++) {
                    list.add(s,Integer.parseInt(st.nextToken()));
                }
            }
            bw.write("#" + String.valueOf(test_case) + " ");
            for (int k = 0; k < 10; k++) {
                bw.write(String.valueOf(list.get(k)) + " ");
            }
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }
}

