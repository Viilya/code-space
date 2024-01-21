package com.ssafy.algorithmstudy.boj.boj11725;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_11725_트리의부모찾기 {
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
        closeIO();
    }


    private static ArrayList<Integer> E[];
    private static int parent[];

    private static int N;
    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        E = new ArrayList[N+1];
        parent = new int[N+1];

        for(int k = 0 ; k < N+1 ; k ++){
            E[k] = new ArrayList<>();
        }

        for(int k = 0 ; k < N-1 ; k ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b =  Integer.parseInt(st.nextToken());
            addTree(a, b);
        }
    }

    private static void addTree(int a, int b){
        E[a].add(b);
        E[b].add(a);
    }

    private static void solve() {
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(1);
        parent[1] = 1;
        while(!dq.isEmpty()){
            int curr = dq.pollFirst();
            //System.out.println("curr : " + curr);
            int len = E[curr].size();
            for(int k = 0 ; k < len ; k++){
                int child =  E[curr].get(k);
                if(parent[ child ] == 0){
                    dq.offerLast(child);
                    parent[child] = curr;
                }
            }
        }
    }

    private static void output() throws IOException {
        for(int k = 2 ; k < N+1 ; k++) {
            bw.write(parent[k] + "");
            bw.newLine();
        }
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
