package com.ssafy.algorithmstudy.boj.boj1168;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class boj1168 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
    public static int N;
    public static int K;
    public static int segmentTree[];
    public static Deque<Integer> dq = new ArrayDeque<>();

    public static void printRes() throws IOException {
        bw.write("<");
        while (dq.size() > 1) {
            bw.write(String.valueOf(dq.pollFirst()) + ", ");
        }
        bw.write(String.valueOf(dq.pollFirst()));
        bw.write(">\n");

        bw.flush();
        bw.close();
        br.close();
    }

    public static void printTree() {
        for (int k = 1; k < N * 2; k *= 2) {
            for (int s = k; s < k * 2; s++) {
                System.out.print(segmentTree[s] + " ");
            }
            System.out.println();
        }
    }
    // @TODO : 누적합 자리 값 구하는 함수
    
    public static int getIdxWithSum(int n, int st, int end, int sum) {
        if (segmentTree[n] == sum && sum == 1 && st == end)
            return st;
        if (sum > segmentTree[n * 2]) 
            return getIdxWithSum(n * 2 + 1, (st + end) / 2 + 1, end, sum - segmentTree[n * 2]);
        else {
            if (segmentTree[n * 2] != 0) {
                return getIdxWithSum(n * 2, st, (st + end) / 2, sum);
            }
        }
        return -1;
    }

    // @TODO : 누적합 구하는 함수
    public static int getSum(int n, int st, int end, int t) {
        if (st <=t  && end <= t) {
            return segmentTree[n];
        }
        if (st<= t && t <= end) {
            return getSum(n * 2, st, (st + end) / 2, t) + getSum(n * 2 + 1, (st + end) / 2 + 1, end, t);
        }else
            return 0;                                                                                                                                                                                                                                                                                                                                                                                                                                              
    }

    public static void update(int n, int st, int end, int t, int diff) {
        if (st <= t && t <= end) {
            //System.out.println("update target t / n / st / end > " + t + " / " + n + " / " + st + " / " + end);
            segmentTree[n] += diff;
        }
        else
            return;
        if (st == end)
            return;
        int mid = (st + end) / 2;
        update(n * 2, st, mid, t, diff);
        update(n * 2 + 1, mid + 1, end, t, diff);
    }

    public static void getJosephus() {
        int fullsize = segmentTree[1];
        int currSum = K;
        int idx = 0;
        while (true) {
            idx = getIdxWithSum(1, 1, N, currSum);
            //System.out.println("idx, fullsize, currSum > " + idx + " / " + fullsize + " / " + currSum);
            dq.addLast(idx);
            update(1, 1, N, idx, -1);
            //printTree();
            fullsize = segmentTree[1];
            if (fullsize == 0)
                break;
            currSum = (getSum(1, 1, N, idx) + K) % (fullsize);
            if (currSum == 0)
                currSum = fullsize;
        }
    }

    public static int initSegmentTree(int node, int start, int end) {
        if (start == end)
            return segmentTree[node] = 1;
        int mid = (start + end) / 2;
        return segmentTree[node] = initSegmentTree(node * 2, start, mid) + initSegmentTree(node * 2 + 1, mid + 1, end);
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }
    public static void main(String args[]) throws IOException {
        input();
        segmentTree = new int[N * 4];
        initSegmentTree(1, 1, N);
        getJosephus();
        printRes();
        // Runtime.getRuntime().gc();
        // long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        // System.out.print(usedMemory + " bytes");
    }
    
    
}
