package com.ssafy.algorithmstudy.boj.boj16926;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class boj19626 {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringTokenizer st;
    public static int N, M, R;
    public static int map[][];
    public static Deque<Integer> dq = new ArrayDeque<>();
    public static int[] dx = { 0, 1, 0, -1 };
    public static int[] dy = { 1, 0, -1, 0 };
    public static int groupNum;
    
    public static void main(String args[]) throws IOException {
        input();
        makeGroup();
        printRes();
    }

    public static void input() throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int k = 0; k < N; k++) {
            st = new StringTokenizer(br.readLine());
            for (int s = 0; s < M; s++) {
                map[k][s] = Integer.parseInt(st.nextToken());
            }
        }
        groupNum = (Math.min(N, M) + 1) / 2;
    }
    
    public static void makeGroup() {
        for (int k = 0; k < groupNum; k++) {
            addGroup(k);
            //System.out.println(Arrays.toString(dq.toArray()));
            rotateGroup();
            addMap(k);
            dq.clear();
        }
    }

    public static void addGroup(int groupNum) {
        int x = groupNum;
        int y = groupNum;
        int d = 0;
        while (true) {
            if (isValid(groupNum, x, y)) {
                //System.out.println("x | y | gn | mapxy > " + x + " " + y + " " + groupNum);
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
        int rotateIt = R % dq.size();
        for (int k = 0; k < rotateIt; k++) 
            dq.addLast(dq.pollFirst());
    }
    
    public static void addMap(int groupNum) {
        int x = groupNum;
        int y = groupNum;
        int d = 0;
        while (true) {
            if (dq.isEmpty())
                break;
            if (isValid(groupNum, x, y)) {
                System.out.println("x | y | gn | mapxy > " + x + " " + y + " " + groupNum);
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

    public static boolean isValid(int groupNum, int x, int y) {
        if (x < groupNum || x >= N - groupNum || y < groupNum || y >= M - groupNum)
            return false;
        return true;
    }
    
    public static void printRes() throws IOException{
        for (int k = 0; k < N; k++) {
            for (int s = 0; s < M; s++) {
                bw.write(String.valueOf(map[k][s]) + " ");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
