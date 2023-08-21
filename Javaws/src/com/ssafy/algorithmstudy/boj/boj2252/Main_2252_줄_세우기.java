package com.ssafy.algorithmstudy.boj.boj2252;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_2252_줄_세우기 {
    public static void main(String[] args) throws IOException {
        input();
        getResultAndPrint();
    }

    public static int N, M;
    public static LinkedList<Integer>[] graph;
    public static int [] graphLevel;
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new LinkedList[N+1];
        graphLevel = new int[N+1];
        for(int k = 0 ; k < N+1 ; k ++){
            graph[k] = new LinkedList<>();
        }
        for(int k = 0 ; k < M ; k ++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
            graphLevel[end] ++;
        }
    }
    public static Deque<Integer> dq = new ArrayDeque<>();
    public static void getResultAndPrint() throws IOException{
        addLevelZero();
        while(!dq.isEmpty()){
            int currEdge = dq.pollFirst();
            bw.write(currEdge + " ");
            for(int k = 0 ; k<graph[currEdge].size() ;k ++){
                graphLevel[graph[currEdge].get(k)] --;
            }
            addLevelZero();
        }
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    public static void addLevelZero(){
        for(int k = 1 ; k <= N ; k++){
            if(graphLevel[k] == 0){
                dq.addLast(k);
                graphLevel[k] = -1;
            }
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}

