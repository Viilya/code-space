package com.ssafy.algorithmstudy.boj.boj1197;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_1197_최소스패닝트리 {
    public static void main(String args[]) throws IOException {
        input();
    }

    static class Node{
        int vertex;
        int weight;

        public Node (int v, int w){
            this.vertex = v;
            this.weight = w;
        }
    }
    public static int VERTEX;
    public static int EDGE;

    public static LinkedList<Node> GRAPH[] ;
    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        VERTEX = Integer.parseInt(st.nextToken());
        EDGE = Integer.parseInt(st.nextToken());

        GRAPH = new LinkedList[VERTEX];

        for(int k = 0; k < VERTEX; k ++){
            GRAPH[k] = new LinkedList<Node>();
        }

    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;

}
