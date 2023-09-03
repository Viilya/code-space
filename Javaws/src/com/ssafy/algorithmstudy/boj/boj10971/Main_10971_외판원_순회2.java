package com.ssafy.algorithmstudy.boj.boj10971;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10971_외판원_순회2 {
    public static void main(String[] args) throws IOException {
        input();
        isVisited[0] = true;
        DFS(0, 0, 1);

        printRes();
        closeIO();
    }
    public static int  N ;
    public static int[][] map;
    public static boolean[] isVisited;
    public static int minRoute = Integer.MAX_VALUE;
    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        isVisited = new boolean[N];
        for(int k = 0 ; k < N ; k++){
            map[k] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }

    public static void DFS(int point, int sum, int count){
        if(count == N){
            if(map[point][0] != 0){
                minRoute = Math.min(minRoute, sum + map[point][0]);
            }
            return;
        }
        for(int k = 0 ; k < N ; k++){
            if(map[point][k] != 0 && !isVisited[k]){
                isVisited[k]= true;
                DFS(k, sum + map[point][k], count+1);
                isVisited[k]= false;
            }
        }
    }

    public static void printRes() throws IOException {
        bw.write(String.valueOf(minRoute));
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
