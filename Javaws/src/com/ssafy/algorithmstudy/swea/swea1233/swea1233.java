package com.ssafy.algorithmstudy.swea.swea1233;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class swea1233 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;

    public static void printRes(int test_case, int res) throws IOException {
        bw.write("#" + String.valueOf(test_case) + " " + String.valueOf(res));
        bw.newLine();
    }

    public static void inputAndSolve() throws IOException{
        for (int test_case = 1; test_case <= 10; test_case++) {
            int n = Integer.parseInt(br.readLine());
            int result = 1;
            for (int k = 0; k < n; k++) {
                st = new StringTokenizer(br.readLine());
                int node = Integer.parseInt(st.nextToken());
                String str = st.nextToken();
                if (str.charAt(0) >= '0' && str.charAt(0) <= '9'
                        && st.hasMoreTokens()) 
                    result = 0;
                // if (str.charAt(0) )
                }
            printRes(test_case, result);
        }
        
    }

    public static void main(String args[]) throws IOException {
        inputAndSolve();
        bw.flush();
        bw.close();
        br.close();
    }
}
