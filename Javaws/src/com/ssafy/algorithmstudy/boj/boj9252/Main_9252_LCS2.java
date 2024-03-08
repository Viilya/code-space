package com.ssafy.algorithmstudy.boj.boj9252;

import java.io.*;
import java.util.StringTokenizer;

public class Main_9252_LCS2 {
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
        closeIO();
    }
    private static char FIRST_STRING [];
    private static char SECOND_STRING[];
    private static int dp[][];
    private static int RESULT = 0;
    private static StringBuilder sb = new StringBuilder("");
    private static void input() throws IOException {
        FIRST_STRING = br.readLine().toCharArray();
        SECOND_STRING = br.readLine().toCharArray();
        dp = new int[1001][1001];
    }

    private static void solve() {
       for(int k = 0 ; k < FIRST_STRING.length; k++){
           for(int s = 0 ; s < SECOND_STRING.length; s++){
               if(FIRST_STRING[k] == SECOND_STRING[s]){
                   dp[k+1][s+1] = dp[k][s] + 1;
                   continue;
               }

               dp[k+1][s+1] = Math.max(dp[k][s+1], dp[k+1][s]);
           }
       }
       int x = FIRST_STRING.length;
       int y = SECOND_STRING.length;
       RESULT = dp[x][y];
       while(x != 0 && y!=0){


           if(dp[x-1][y] == dp[x][y]){
               x -= 1;
               continue;
           }
           if(dp[x][y-1] == dp[x][y]){
               y -=1;
               continue;
           }

           if(FIRST_STRING[x-1] == SECOND_STRING[y-1]) {
               sb.append(FIRST_STRING[x - 1]);
           }
           x -=1;
           y -=1;
       }
    }

    private static void output() throws IOException {
        String ans = sb.reverse().toString();
        bw.write(RESULT + ((ans.length() != 0)?("\n" + ans):""));
        bw.newLine();
    }

    private static void closeIO() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
