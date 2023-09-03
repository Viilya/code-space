
package com.ssafy.algorithmstudy.ssafy.ws1;

        import java.io.*;
        import java.util.StringTokenizer;

public class Solution_벽칠하기 {
    public static void main(String[] args) throws IOException {
        input();
        DP();
        printRes();
        closeIO();
    }
    public static int N;
    public static int dp[][];

    public static void DP(){
        for(int k = 0 ; k < N - 1; k++){
            dp[k+1][0] = dp[k][0] + dp[k][1];
            dp[k+1][1] = dp[k][0];
        }
    }


    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new int[N][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
    }

    public static void printRes() throws IOException {
        bw.write(String.valueOf(dp[N-1][0] + dp[N-1][1]));
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
