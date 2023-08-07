package com.ssafy.algorithmstudy.boj.boj1158;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class boj1158 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N, K;
    public static Deque<Integer> dq = new ArrayDeque<Integer>();
    public static Deque<Integer> res = new ArrayDeque<Integer>();
    public static void main(String args[]) throws IOException {
        input();
        printRes();
    }

    public static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        makeNumList();
        Josephus();
    }

    public static void makeNumList() {
        for (int k = 1; k <= N; k++) {
            dq.addLast(k);
        }
    }

    public static void Josephus() {
        while (true) {
            for (int k = 0; k < K - 1; k++) {
                int tmp = dq.pollFirst();
                dq.addLast(tmp);
            }
            res.addLast(dq.pollFirst());
            if(dq.isEmpty())
                break;
        }
    }

    public static void printRes() throws IOException {
        bw.write("<");
        for (int k = 0; k < N-1; k++) {
            bw.write(String.valueOf(res.pollFirst()) + ", ");
        }
        bw.write(String.valueOf(res.pollFirst()) + ">\n");
        bw.flush();
        br.close();
        bw.close();
    }
}