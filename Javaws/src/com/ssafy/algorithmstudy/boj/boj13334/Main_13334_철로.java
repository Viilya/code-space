package com.ssafy.algorithmstudy.boj.boj13334;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_13334_철로 {
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
            if(this.x == o.x){
                return this.y - o.y;
            }
            return this.x - o.x;
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
    }

    private static void solve() {

        for(int k = 0 ; k < N ; k ++){
            int result = 0;
            int start = arr[k].x;
            int end = start + D;
            int i = k;
            while(i < N){
                if(arr[i].x > end) break;
                if(arr[i].x <= end && arr[i].y <= end){
                    result ++;
                }
                i++;
            }
            ans = Math.max(ans, result);
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
