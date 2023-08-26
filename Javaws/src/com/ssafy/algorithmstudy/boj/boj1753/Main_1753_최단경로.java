package com.ssafy.algorithmstudy.boj.boj1753;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1753_최단경로 {
    final static int INF = Integer.MAX_VALUE;
    static class Node{
        int ver, weight;
        Node next;

        public Node(int ver, int weight, Node next) {
            this.ver = ver;
            this.weight = weight;
            this.next = next;
        }
    }
    public static void main(String[] args) throws IOException {
        input();
        printRes();
        closeIO();
    }

    public static int V, E, startV;
    static Node[] adjList;
    static int[] dist;
    static boolean[] visited;
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        startV = Integer.parseInt(br.readLine());
        adjList = new Node[V];
        dist = new int[V];
        visited = new boolean[V];
        for(int k = 0 ; k < E ; k ++){
            st= new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, weight, adjList[from]);
        }
        Arrays.fill(dist, INF);


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
