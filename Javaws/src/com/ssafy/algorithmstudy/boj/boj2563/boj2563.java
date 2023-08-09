package com.ssafy.algorithmstudy.boj.boj2563;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj2563 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
    public static int N;
    public static int[][] map = new int[100][100];
    public static void main(String args[]) throws IOException {
        input();
        printRes();
    }

    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int k = 0; k < N; k++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            paint(a, b);
        }
    }

    public static void paint(int a, int b) {
        for (int k = a; k < a + 10; k++) {
            for (int s = b; s < b + 10; s++) {
                map[k][s] = 1;
            }
        }
    }

    public static void printRes() throws IOException {
        int sum = 0;
        for (int k = 0; k < 100; k++) {
            for (int s = 0; s < 100; s++) {
                sum += map[k][s] == 1 ? 1 : 0;
            }
        }
        bw.write(String.valueOf(sum));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
