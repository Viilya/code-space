package com.ssafy.algorithmstudy.boj.boj1197;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1197_최소스패닝트리 {
    public static void main(String args[]) throws IOException {
        input();
        solve();
        output();
    }

    static class Node implements Comparable<Node> {
        int from;
        int to;
        int weight;

        public Node (int f, int t, int w){
            this.from = f;
            this.to = t;
            this.weight = w;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    public static int VERTEX;
    public static int EDGE;

    public static ArrayList<Node> GRAPH[];
    public static int PARENTS[];
    public static int RESULT = 0;
    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        VERTEX = Integer.parseInt(st.nextToken());
        EDGE = Integer.parseInt(st.nextToken());
        GRAPH = new ArrayList[VERTEX + 1];
        PARENTS = new int[VERTEX + 1];

        // 정점 기반 초기 실행
        for(int k = 0 ; k < VERTEX + 1; k ++) {
            PARENTS[k] = k;
            GRAPH[k] = new ArrayList<Node>();
        }
        // 간선 입력 받기
        for(int k = 0 ; k < EDGE ; k ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            GRAPH[a].add(new Node(a, b, c));
            GRAPH[b].add(new Node(b, a, c));
        }


    }

    public static void solve(){
        PriorityQueue<Node> pq = new PriorityQueue<Node>();

        int edgeCount = 0;
        // 0 인 지점 추가
        for(int k = 0 ; k < GRAPH[1].size(); k++){
            pq.add(GRAPH[1].get(k));
        }
//        System.out.println("while entered.");
        while(!pq.isEmpty()){
            Node curr = pq.poll();
//            System.out.println("what is Happening");
//            System.out.println(curr.toString());
            if(union(curr.from, curr.to)){
                edgeCount += 1;
                RESULT += curr.weight;
                int size = GRAPH[curr.to].size();
                for(int k = 0 ; k < size; k ++){
                    pq.add(GRAPH[curr.to].get(k));
                }
            }
            if(edgeCount == VERTEX - 1){
                break;
            }
        }
    }


    public static boolean union(int a, int b){
        int aP = find(a);
        int bP = find(b);

        if(aP == bP){
            return false;
        }

        if(aP > bP){
            PARENTS[a] = bP;
        }else{
            PARENTS[b] = aP;
        }

        return true;
    }

    public static int find(int child){
        if(PARENTS[child] == child){
            return child;
        }else{
            return PARENTS[child] = find(PARENTS[child]);
        }
    }


    public static void output() throws IOException{
        bw.write("" + RESULT);
        bw.flush();
        bw.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;

}
