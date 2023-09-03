package com.ssafy.algorithmstudy.ssafy.ws1;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_막대칠하기 {
    public static void main(String[] args) throws IOException {
        input();
        DP();
        printRes();
        closeIO();
    }
    public static int N;
    public static int dp[];

    public static void DP(){
        for(int k = 1 ; k < N; k++){
            dp[k+1] += dp[k] * 2;
            dp[k+2] += dp[k];
        }
    }


    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new int[N+2];
        dp[1] = 2;
        dp[2] = 1;
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