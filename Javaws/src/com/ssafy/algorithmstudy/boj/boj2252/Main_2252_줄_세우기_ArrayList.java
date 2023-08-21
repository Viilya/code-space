package com.ssafy.algorithmstudy.boj.boj2252;

import java.io.*;
import java.util.*;

public class Main_2252_줄_세우기_ArrayList {
    public static void main(String[] args) throws IOException {
        input();
        getResultAndPrint();
    }

    public static int N, M;
    public static ArrayList<Integer>[] graph;
    public static int [] graphLevel;
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        graphLevel = new int[N+1];

        // 그래프 초기화
        for(int k = 0 ; k < N+1 ; k ++){
            graph[k] = new ArrayList<>();
        }

        // 시작 지점 ArrayList 에 end 간선 추가, end 에는 level ++
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

        // 초기에 level 이 제로인 정점들을 모두 dq에 추가
        addLevelZero();
        while(!dq.isEmpty()){
            // dq에서 정점을 빼내고 정점이 가르키는 모든 점들의 level을 -1
            int currEdge = dq.pollFirst();
            bw.write(currEdge + " ");
            for(int k = 0 ; k<graph[currEdge].size() ;k ++){
                int idx = graph[currEdge].get(k);
                graphLevel[idx] --;
                if(graphLevel[idx] == 0){ // 만약 감소시킨 level이 0이면 dq에 추가
                    dq.addLast(idx);
                    graphLevel[idx] = -1;
                }
            }
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

