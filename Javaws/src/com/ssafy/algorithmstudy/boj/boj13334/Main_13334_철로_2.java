package com.ssafy.algorithmstudy.boj.boj13334;

import java.io.*;
import java.util.*;

public class Main_13334_철로_2 {
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
        closeIO();
    }

    static class Line implements Comparable<Line> {
        int x;
        int y;
        public Line(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Line o) {
            if(this.y == o.y){
                return this.x - o.x;
            }
            return this.y - o.y;
        }

        @Override
        public String toString() {
            return "Line{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
    static int N;
    static int D;
    static Line arr[];
    static int ans = 0;
    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new Line[N];
        for(int k = 0 ; k < N ; k ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[k] = new Line(Math.min(a, b), Math.max(a, b));
        }
        D = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));
    }

    private static void solve() {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int k = 0 ; k < N ; k ++){
            if(arr[k].y - D <= arr[k].x){
                q.add(arr[k].x);
            }
            if(!q.isEmpty()){
                for(int s = 0 ; s < q.size(); s++){
                    if(q.peek()>= arr[k].y - D){
                        break;
                    }

                    q.poll();
                    s--;
                }
            }
//            System.out.println(k + " : " + q.toString());
            ans = Math.max(ans, q.size());
        }
    }

    private static void output() throws IOException {
        bw.write("" + ans);
        bw.newLine();
    }

    private static void closeIO() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
