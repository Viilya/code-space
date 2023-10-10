package com.ssafy.algorithmstudy.boj.boj16953;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16953_AArrowB {
    public static void main(String[] args) throws IOException {
        input();
        printRes();
        closeIO();
    }

    public static int ans = -2;

    public static void input() throws IOException {
        st=  new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        bfs(N, M);

    }
    static class Point{
        int num;

        public Point(int num, int count) {
            this.num = num;
            this.count = count;
        }

        int count;


    }
    private static void bfs(int n, int m) {
        Queue<Point> q = new ArrayDeque<>();
        int half = m/2;
        q.add(new Point(n, 0));
        while(!q.isEmpty()){
            Point c = q.poll();
            //System.out.println(c.num + " "  + c.count);

            if(c.num == m){
                ans = c.count;
                return;
            }
            if(c.num > half) continue;

            q.add(new Point(c.num * 2, c.count + 1));
            q.add(new Point(c.num * 10 + 1, c.count + 1));

        }


    }

    public static void printRes() throws IOException {
        bw.write((ans+1) + "");
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
