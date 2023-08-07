package com.ssafy.algorithmstudy.boj.boj20167;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj20167 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // N, K saves
    public static int N, K;
    // int[] saves the food energy
    public static int map[];
    // int[] saves dp
    public static long dp[];
    // add of all ecdysis energy
    public static long ecdysisCount = 0;
    public static long maxdp = 0;
    public static void main(String args[]) throws IOException {
        // input function
        input();
        // dp function 
        dp();
        // print result
        printResult();
    }
    /**
     * get inputs
     * @throws IOException
     */
    public static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N];
        dp = new long[N];
        st = new StringTokenizer(br.readLine());
        
        for (int k = 0; k < N; k++) {
            map[k] = Integer.parseInt(st.nextToken());
        }
    }
    
    /**
     * call eats for all 0~N-1 of map
     */
    public static void dp() {
        for (int k = 0; k < N; k++) {
            eat(k);
        }
    }

    /**
     * start eating from startpoints til get statisfaction
     * @param startPoint
     */
    public static void eat(int startPoint) {
        long food = 0;
//        int maxdp = 0;

        // get formal max dp
        // for (int k = 0; k < startPoint; k++) 
        //     maxdp = Math.max(maxdp, dp[k]);
        if (startPoint > 0) {
            maxdp = Math.max(maxdp, dp[startPoint - 1]);
        }

        // eating from startpoints, check addDp(check all foods bigger than k)
        for (int k = startPoint; k < N; k++) {
            food += map[k];
            if(addDp(maxdp, food, k)) break; // if bigger, break
        }
    }
    
    /**
     * if food >= K , check if it is bigger than formal dp[k], and add 
     * @param ecdysisEnergy
     * @param food
     * @param k
     * @return
     */
    public static boolean addDp(long ecdysisEnergy, long food, int k) {
        if (food >= K || k == N - 1) {
            dp[k] = Math.max(dp[k], ecdysisEnergy + (food-K>=0?food - K:0));
            return true;
        }
        return false;
    }

    /**
     * prints the result of the problems
     * @throws IOException
     */
    public static void printResult() throws IOException {
        bw.write(String.valueOf(dp[N - 1]));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
