package com.ssafy.algorithmstudy.boj.boj1149;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1149_RGB거리 {
    public static void main(String[] args) throws IOException {
        input();
        dp(0, 0, map[0][0]);
        dp(0, 1, map[0][1]);
        dp(0, 2, map[0][2]);
        printRes();
        closeIO();
    }
    public static int N;
    public static int map [][];
    public static int _dp[][];
    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][3];
        _dp = new int[N][3];
        for (int k = 0; k < N; k++) {
            st = new StringTokenizer(br.readLine());
            for (int s = 0; s < 3; s++) {
                map[k][s] = Integer.parseInt(st.nextToken());
                _dp[k][s] = Integer.MAX_VALUE;
            }
        }


    }
    public static void dp(int x, int y, int sum){
        if(_dp[x][y] > sum){
            _dp[x][y] = sum;
            if(x == N-1)
                return;
            for(int k = 0 ; k < 3;  k++){
                if(k != y){
                    dp(x+1, k, sum+map[x+1][k]);
                }
            }
        }
    }
    public static void printRes() throws IOException {
        bw.write(String.valueOf(Math.min(_dp[N-1][0], Math.min(_dp[N-1][1], _dp[N-1][2]))));
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
