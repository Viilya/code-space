package com.ssafy.algorithmstudy.boj.boj17406;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_17406_배열_돌리기_4 {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringTokenizer st;
    public static int N, M, R;
    public static int Map[][];
    public static Deque<Integer> dq = new ArrayDeque<>();
    public static int[] dx = { 0, 1, 0, -1 };
    public static int[] dy = { 1, 0, -1, 0 };
    public static int groupNum;
    public static int rotateIdx[][];
    public static boolean isChosen[];
    public static int T;
    public static int minSum = Integer.MAX_VALUE;
    public static void main(String args[]) throws IOException {
        input();
        printRes();
    }

    public static void input() throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        Map = new int[N][M];
        for (int k = 0; k < N; k++) {
            st = new StringTokenizer(br.readLine());
            for (int s = 0; s < M; s++) {
                Map[k][s] = Integer.parseInt(st.nextToken());
            }
        }
        rotateIdx = new int[T][3];
        isChosen = new boolean[T];
        for (int k = 0; k < T; k++) {
            st = new StringTokenizer(br.readLine());
            rotateIdx[k][0] = Integer.parseInt(st.nextToken());
            rotateIdx[k][1] = Integer.parseInt(st.nextToken());
            rotateIdx[k][2] = Integer.parseInt(st.nextToken());
        }
        permutationTurn(Map, 0);
    }
    
    public static void permutationTurn(int[][] map, int cnt) {
        if (cnt == T) {
            System.out.println("\ncnt > " + cnt);
            printMap(map);
            getMinSum(map);
        }
        //System.out.println("T / cnt > " + T + " " + cnt);
        for (int k = 0; k < T; k++) {
            if (!isChosen[k]) {
                isChosen[k] = true;
                int newMap[][] = new int[N][M];
                for (int s = 0; s < N; s++) {
                    for (int i = 0; i < M; i++) {
                        newMap[s][i] = map[s][i];
                    }
                }
                //System.out.println("before makeGroup");
                makeGroup(newMap, k);
                permutationTurn(newMap, cnt + 1);
                isChosen[k] = false;
            }
        }
    }

    public static void makeGroup(int[][] map, int idx) {
        int groupNum = rotateIdx[idx][2];
        for (int k = 0; k < groupNum; k++) {
            addGroup(map, k, rotateIdx[idx][0], rotateIdx[idx][1], rotateIdx[idx][2]);
            //System.out.println(Arrays.toString(dq.toArray()));
            rotateGroup();
            addMap(map, k, rotateIdx[idx][0], rotateIdx[idx][1], rotateIdx[idx][2]);
            dq.clear();
        }
    }

    public static void addGroup(int[][] map, int groupNum, int r, int c, int s) {
        int x = r - s + groupNum - 1;
        int y = c - s + groupNum - 1;
        int d = 0;
        while (true) {
            if (isValid(groupNum, x, y, r, c, s)) {
                
                dq.addLast(map[x][y]);
            } else {
                x -= dx[d];
                y -= dy[d];
                d++;
            }
            if (d == 4)
                break;
            x += dx[d];
            y += dy[d];
        }
        dq.pollLast();
    }

    public static void rotateGroup() {
        dq.addFirst(dq.pollLast());
    }
    
    public static void addMap(int[][] map, int groupNum, int r, int c, int s) {
        int x = r - s + groupNum - 1;
        int y = c - s + groupNum - 1;
        int d = 0;
        while (true) {
            if (dq.isEmpty())
                break;
            if (isValid(groupNum, x, y, r, c, s)) {
                
                map[x][y]= dq.pollFirst();
            } else {
                x -= dx[d];
                y -= dy[d];
                d++;
            }
            if (d == 4)
                break;
            x += dx[d];
            y += dy[d];
        }
        dq.pollLast(); 
    }

    public static boolean isValid(int groupNum, int x, int y, int r, int c, int s) {
        if (x < r - s + groupNum - 1|| x >= r + s - groupNum || y < c - s + groupNum - 1 || y >= c + s - groupNum)
            return false;
        return true;
    }

    public static void getMinSum(int[][] map) {
        for (int k = 0; k < N; k++) {
            int sum = 0;
            for (int s = 0; s < M; s++) {
                sum += map[k][s];
            }
            minSum = Math.min(sum, minSum);
        }
    }

    public static void printMap(int[][] map) {
        for (int k = 0; k < N; k++) {
            for (int s = 0; s < M; s++) {
                System.out.print(String.valueOf(map[k][s]) + " ");
            }
            System.out.println();
        }
    }
    public static void printRes() throws IOException {
        bw.write(String.valueOf(minSum));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
