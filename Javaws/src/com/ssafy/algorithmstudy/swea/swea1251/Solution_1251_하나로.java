package com.ssafy.algorithmstudy.swea.swea1251;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_1251_하나로 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }
    public static int N ;
    public static double E;
    public static int islands[][];
    public static long res = 0;
    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= 10 ; tc++){
            N = Integer.parseInt(br.readLine());
            islands = new int[N][2];
            for(int s = 0 ;s < 2; s ++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    islands[k][s] = Integer.parseInt(st.nextToken());
                }
            }
            E = Double.parseDouble(br.readLine());
            res = 0;
            Prim();
            printRes(tc);
        }
    }

    public static void Prim(){
        boolean []  isVisited = new boolean[N];
        isVisited[0] = true;
        int edgeCount = 0;
        PriorityQueue<Edge> q = new PriorityQueue<Edge>();
        for(int k = 1 ; k < N ; k++){
            q.add(new Edge(k, dist(0, k)));
        }
        while(edgeCount < N && !q.isEmpty()){
            Edge thisE = q.poll();
            if(isVisited[thisE.end])
                continue;
            //System.out.println(Arrays.toString(isVisited));
            //System.out.println("thisE > " + thisE.end + " / " + thisE.weight);
            edgeCount++;
            res += thisE.weight;
            isVisited[thisE.end]= true;
            for(int k = 0 ; k < N ; k ++){
                if(!isVisited[k]){
                    q.add(new Edge(k, dist(thisE.end, k)));
                }
            }
        }
    }

    public static long dist(int a, int b){
        return (long) (Math.pow(islands[a][0] - islands[b][0], 2) + Math.pow(islands[a][1] - islands[b][1], 2));
    }

    public static void printRes(int tc) throws IOException {
        bw.write("#" + tc + " " + Math.round(res * E));
        bw.newLine();
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

class Edge implements Comparable<Edge>{
    int end;
    long weight;

    public Edge(int end, long weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return Long.compare(this.weight, o.weight);
    }
}