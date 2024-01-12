package com.ssafy.algorithmstudy.boj.boj15989;

import java.io.*;

public class Main_15989_123더하기4 {

    public static int dp[][] = new int[10001][4];
    public static void main(String args[]) throws IOException{
        initDp();
        inputAndSolve();
        closeIO();
    }


    public static void initDp(){
        dp[1][1] = 1;
        dp[2][2] = 1;
        dp[3][3] = 1;
        for(int k = 0; k < 10001; k ++){
            for(int s = 1 ; s <= 3; s ++){
                if(k+s < 10001) {
                    dp[k + s][s] += dp[k][1];
                }
            }
            for(int s = 2 ; s <= 3 ; s++){
                if(k+s < 10001) {
                    dp[k+s][s] += dp[k][2];
                }
            }
            if(k+3<10001){
                dp[k+3][3] += dp[k][3];
            }
        }
    }
    public static void inputAndSolve() throws IOException {
        int T = Integer.parseInt(br.readLine());



        for(int k = 0 ; k < T ; k ++){
            int n = Integer.parseInt(br.readLine());
            output(dp[n][1] + dp[n][2] + dp[n][3]);
        }
    }

    public static void output(int n) throws IOException{
        bw.write(n + "");
        bw.newLine();
    }

    public static void closeIO() throws IOException{
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

}
