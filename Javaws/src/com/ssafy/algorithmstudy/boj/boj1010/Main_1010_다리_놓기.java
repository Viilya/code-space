package com.ssafy.algorithmstudy.boj.boj1010;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1010_다리_놓기 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }

    public static int dp[][] = new int[31][31];
    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int k = 0 ; k < T ; k++){
            st= new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            printRes(connectDP(M, N));
        }
    }

    public static int connectDP(int M, int N){
        if(dp[M][N] != 0) return dp[M][N];
        if(N == 0 || M == 1 || N == M) {
            return dp[M][N] = 1;
        }
        return dp[M][N] = connectDP(M-1, N-1) + connectDP(M-1, N);
    }


    public static void printRes(int res) throws IOException {
        bw.write(String.valueOf(res));
        bw.newLine();
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
