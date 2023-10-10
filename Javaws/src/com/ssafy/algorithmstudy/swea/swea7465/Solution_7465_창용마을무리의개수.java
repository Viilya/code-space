package com.ssafy.algorithmstudy.swea.swea7465;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_7465_창용마을무리의개수 {

    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }

    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<= T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            boolean isVisited[] = new boolean[N];
            int adj[][] = new int[N][N];

            for(int k =0 ; k < M ; k ++){
                st = new StringTokenizer(br.readLine());
                int a=  Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                adj[a][b] = 1;
                adj[b][a] = 1;
            }
            int groupCount = 0;
            for(int k = 0 ; k < N ; k++){
                if(!isVisited[k]) {
                    groupCount++;
                    bfs(adj, isVisited, N, M, k);
                }
            }

            printRes(tc, groupCount);
        }

    }

    private static void bfs(int[][] adj, boolean[] isVisited, int n, int m, int st) {
        Queue<Integer> q = new ArrayDeque<>();

        q.add(st);

        while(!q.isEmpty()){
            int c = q.poll();
            for(int k = 0 ; k < n ; k ++){
                if(adj[c][k] == 1 && !isVisited[k]){
                    isVisited[k] = true;
                    q.add(k);
                }
            }
        }
    }

    public static void printRes(int tc, int res) throws IOException {
        bw.write("#" + tc+ " " + res);
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
