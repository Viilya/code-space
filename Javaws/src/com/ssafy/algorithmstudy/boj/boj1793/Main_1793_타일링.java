package com.ssafy.algorithmstudy.boj.boj1793;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;
public class Main_1793_타일링 {


    public static void main(String[] args) throws IOException {
        input();

        closeIO();
    }
    public static int N;
    public static BigInteger dp[];

    public static void DP(){
        for(int k = 0 ; k < N ; k++){
            dp[k+1] = dp[k+1].add(dp[k]);
            dp[k+2] = dp[k+2].add(dp[k].multiply(new BigInteger("2")));
        }
    }

    public static void input() throws IOException {
        while(true) {
            String str = br.readLine();
            if(str==null) break;
            if(str.equals("")) break;
            N = Integer.parseInt(str);
            dp = new BigInteger[N + 2];
            for(int k = 0 ; k < N+2 ; k++){
                dp[k] = new BigInteger("0");
            }
            dp[0] = BigInteger.ONE;
            DP();
            printRes();
        }
    }

    public static void printRes() throws IOException {
        bw.write(String.valueOf(dp[N]));
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
