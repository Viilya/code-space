package com.ssafy.algorithmstudy.swea.swea9229;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class swea9229 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
    public static int n, m;
    public static int[] snacks;

    public static int getSnack() {
        int res = -1;
        for (int k = 0; k < n-1; k++) {
            for (int s = k+1; s < n; s++) {
                int w = snacks[k] + snacks[s];
                if (w <= m) {
                    res = Math.max(w, res);
                }
            }
        }
        return res;
    }


    public static void printRes(int t, int res) throws IOException{
        bw.write("#" + String.valueOf(t) + " " + String.valueOf(res));
        bw.newLine();
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= t; test_case++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            snacks = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < n; k++) 
                snacks[k] = Integer.parseInt(st.nextToken());
            int res = getSnack();
            printRes(test_case, res);
        }
    }

    public static void main(String args[]) throws IOException {
        input();
        bw.flush();
        bw.close();
        br.close();

    }

    
}
