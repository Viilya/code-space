package com.ssafy.algorithmstudy.boj.boj15652;

import java.io.*;
import java.util.StringTokenizer;

public class Main_15652_N과M4 {

    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int seq[] = new int[M];

        perm(N, M, seq, 0, 1);
    }

    public static void perm(int N, int M, int seq[], int count, int curr) throws IOException {
        if(count == M){
            for(int e : seq){
                bw.write(e + " ");
            }
            bw.newLine();
            return;
        }

        for(int k = curr ; k <= N ; k++){
            seq[count] = k;
            perm(N, M, seq, count+1, k);
        }
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
