package com.ssafy.algorithmstudy.swea.swea1861;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

    
public class swea1861 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
    public static int N;
    public static int[] dx = { 0, 1, 0, -1 };
    public static int[] dy = { 1, 0, -1, 0 };
    public static Queue<int[]> q = new ArrayDeque<>();
    public static int[][] resMap;

    public static void printRes(int t, int[][] map) throws IOException {
        int minMap = Integer.MAX_VALUE;
        int maxRes = 0;
        for (int k = 0; k < N; k++) {
            for (int s = 0; s < N; s++) {
                if (maxRes < resMap[k][s]) {
                    maxRes = resMap[k][s];
                    minMap = map[k][s];
                } else if (maxRes == resMap[k][s] && minMap > map[k][s]) {
                    minMap = map[k][s];
                }
            }
        }
        bw.write("#" + String.valueOf(t) + " " + String.valueOf(minMap) + " " + String.valueOf(maxRes));
        bw.newLine();
    }

    public static boolean isValid(int [][]map, int k, int s, int val){
        if(k < 0 || k >= N || s < 0 || s >= N || map[k][s] - val != 1)
            return false;
        return true;
    }
    
    public static int dfs(int[][] map, int k, int s) {
        
        int sum = 1;
        int flag = 0;
        for (int i = 0; i < 4; i++) {
            if (isValid(map, k + dx[i], s + dy[i], map[k][s])) {
                //System.out.println("k / s > " + k + " " + s);
                if (resMap[k + dx[i]][s + dy[i]] != 0)
                    sum += resMap[k + dx[i]][s + dy[i]];
                else
                    sum += dfs(map, k + dx[i], s + dy[i]);
                flag = 1;
            }
        }
        resMap[k][s] = sum;
        return sum;
    }

    /**
     * input session
     * @throws IOException
     */
    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];
            resMap = new int[N][N];
            for (int k = 0; k < N; k++) {
                st = new StringTokenizer(br.readLine());
                for (int s = 0; s < N; s++) {
                    map[k][s] = Integer.parseInt(st.nextToken());
                }
            }
            for (int k = 0; k < N; k++) {
                for (int s = 0; s < N; s++) {
                    if (resMap[k][s] == 0) {
                        dfs(map, k, s);
                    }
                }
            }

            printRes(test_case, map);
        }
    }

    public static void main(String args[]) throws IOException {
        input();
        bw.flush();
        bw.close();
        br.close();
    }
}
