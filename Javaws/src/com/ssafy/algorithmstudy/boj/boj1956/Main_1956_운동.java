package com.ssafy.algorithmstudy.boj.boj1956;

import java.io.*;
import java.util.StringTokenizer;

/**
 * V 개의 마을, E 개의 도로 (일방 통행)
 * 1~V 번 번호
 * 운동하기 위한 경로
 * 사이클 찾기
 * 사이클을 이루는 도로의 길이의 합이 최소가 되도록 한다.
 * 왕복하는 경우도 사이클에 포함됨
 *
 * 2 <= V <= 400 / 0 <= E <= V(V-1) / a -> b . c
 */
public class Main_1956_운동 {

    public static int V;
    public static int E;
    public static int map[][];
    public static boolean visited[];
    public static int minCost = 1000000000;
    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        V = toInt(st.nextToken());
        E = toInt(st.nextToken());
        map = new int[V][V];
        visited = new boolean[V];
        for(int k = 0 ; k < E; k ++){
            st = new StringTokenizer(br.readLine());
            map[toInt(st.nextToken()) - 1][toInt(st.nextToken()) - 1] = toInt(st.nextToken());
        }
    }

    private static int toInt(String str) {
        return Integer.parseInt(str);
    }

    private static void solve() {
        int d[][] = new int [V][V];
        for(int k = 0 ; k < V ; k ++) {
            for(int s= 0 ; s < V ; s++){
                if(s == k){
                    d[k][s] = 1000000000;
                }else{
                    if(map[k][s] == 0){
                        d[k][s] = 1000000000;
                    }else{
                        d[k][s] = map[k][s];
                    }
                }
            }
        }
        for(int k = 0 ; k < V; k ++){
            for(int s= 0 ; s< V ; s ++){
                for(int i = 0 ; i < V;  i ++){
                    d[s][i] = Math.min(d[s][i], d[s][k] + d[k][i]);
                }
            }
        }
        for(int k = 0 ; k < V; k ++){
            minCost = Math.min(minCost, d[k][k]);
        }
    }


    private static void output() throws IOException {
        bw.write("" + (minCost==1000000000?-1:minCost));
        bw.newLine();
    }

    private static void closeIO() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
        closeIO();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
