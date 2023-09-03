package com.ssafy.algorithmstudy.ssafy.ws1;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_타일_채우기 {
    public static void main(String[] args) throws IOException {
        input();
        DP();
        printRes();
        closeIO();
    }
    public static int N;
    public static int dp[];

    public static void DP(){
        for(int k = 0 ; k < N ; k++){

        }
    }

    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        dp =new int[N+1];
    }

    public static void printRes() throws IOException {
        bw.write("");
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
