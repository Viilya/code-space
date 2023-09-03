package com.ssafy.algorithmstudy.boj.boj1463;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1463_1로_만들기 {
    static final int MAX_N = 1000001;
    static int dp[] = new int [MAX_N];
    static int N;
    public static void main(String[] args) throws IOException {
        input();
        dynamicProgramming();
        printRes();
        closeIO();
    }

    public static void dynamicProgramming(){
        int currNum = 1;
        while(currNum != N ) {
            dp[currNum + 1] = Math.min(dp[currNum + 1], dp[currNum] + 1);
            if (currNum * 2 <= N) {
                dp[currNum * 2] = Math.min(dp[currNum * 2], dp[currNum] + 1);
            }
            if (currNum * 3 <= N) {
                dp[currNum * 3] = Math.min(dp[currNum * 3], dp[currNum] + 1);
            }
            currNum += 1;
        }
    }

    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        Arrays.fill(dp, MAX_N);
        dp[1] = 0;
    }

    public static void printRes() throws IOException {
        bw.write(String.valueOf(dp[N]));
    }

    public static void closeIO() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
