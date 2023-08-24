package com.ssafy.algorithmstudy.swea.swea3124;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_3124_최소스패닝트리 {
    public static void main(String[] args) throws IOException {
        input();

        closeIO();
    }

    public static int V, E;
    public static PriorityQueue<Edge>[] graph;
    public static boolean[] isVisited;
    public static long weightSum = 0;
    public static int vCount = 0;
    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T ; tc ++){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            graph = new PriorityQueue[V + 1];
            isVisited = new boolean[V+1];
            for(int k = 0 ; k < V + 1 ; k++){
                graph[k] = new PriorityQueue<Edge>();
            }
            for(int k = 0 ; k < E ; k ++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph[start].add(new Edge(end, weight));
                graph[end].add(new Edge(start, weight));
            }
            vCount = 0;
            weightSum = 0;
            isVisited[1] = true;
            prim();
            printRes(tc);
        }
    }



    public static void prim(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int curr = 1;
        addPq(curr, pq);
        do{
            Edge thisEdge = pq.poll();
            if(!isVisited[thisEdge.end]){
                isVisited[thisEdge.end] = true;
                curr = thisEdge.end;
                addPq(curr, pq);
                weightSum += thisEdge.weight;
                vCount ++;
            }
        }while(vCount < V-1 && !pq.isEmpty());
    }

    public static void addPq(int curr, PriorityQueue<Edge> pq){
        int size = graph[curr].size();
        for(int k = 0 ; k < size ; k ++){
            pq.add(graph[curr].poll());
        }
    }

    public static void printRes(int tc) throws IOException{
        bw.write("#"+ tc + " " + weightSum);
        bw.newLine();
    }

    public static void closeIO() throws IOException{
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}

class Edge implements Comparable<Edge>{
    int end;
    int weight;

    public Edge() {
    }

    public Edge(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }


    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}