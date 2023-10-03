package com.ssafy.algorithmstudy.swea.swea3307;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3307_최장증가부분수열 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }


/*
 */
    public static int doLIS(){
        int dp[] = new int[N];
        Arrays.fill(dp, 1);

        for(int k = 0; k < N-1 ; k ++){
            for(int s = k + 1 ; s< N; s++){
                if(seq[k] < seq[s] && dp[s] < dp[k] + 1){
                    dp[s] = dp[k] + 1;
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }


    public static int N;
    public static int seq[];
    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<= T; tc ++){
            N = Integer.parseInt(br.readLine());
            seq = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            printRes(tc, doLIS());
        }
    }

    public static void printRes(int tc, int max) throws IOException {
        bw.write("#" +tc + " " + max);
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
