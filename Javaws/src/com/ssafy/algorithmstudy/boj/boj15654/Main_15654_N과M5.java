package com.ssafy.algorithmstudy.boj.boj15654;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15654_N과M5 {
    public static void main(String[] args) throws IOException {
        input();
        printRes();
        closeIO();
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int seq[] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int target[] = new int[M];
        perm(N, M, seq, target, 0, 0);
    }

    private static void perm(int N, int M, int[] seq, int[] target, int count, int mask) throws IOException{
        if(count == M){
            for(int e: target){
                bw.write(e + " ");
            }
            bw.newLine();
            return;
        }
        for(int k = 0 ; k < N ; k++){
            if(((1<<k) & mask) != (1<<k)) {
                mask = (1<<k) | mask;
                target[count] = seq[k];
                perm(N, M, seq, target, count + 1,mask);
                mask = (1<<k) ^ mask;
            }
        }
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
