package com.ssafy.algorithmstudy.boj.boj9205;

import java.awt.*;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * t <= 50
 * 출발 상근이네 집 -> 한 박스 20개 -> 50m 에 1병 씩
 * 맥주를 파는 편의점이 있다. 빈병을 버리고 새 맥주 병을 살 수 있다. 박스에 들어있는 맥주는 20병을 못넘음
 * 50미터를 가기 전에 맥주 한 병을 마셔야한다.
 * 편의점, 상근이네 집, 페스티벌의 좌표
 * 편의점 <= 100
 *
 * 1000 미터 이상이면 못감!
 */




public class Main_9205_맥주마시면서걸어가기 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }
    public static int N;
    public static Point[] points;
    public static boolean isConnected[][];
    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < T ; t++){
            int storeCount = Integer.parseInt(br.readLine());
            N = storeCount +2;
            points = new Point[N];
            for(int k = 0 ; k < N ; k++){
                st = new StringTokenizer(br.readLine());

                points[k] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            isConnected = new boolean[N][N];
            for(int k = 0 ; k < N;  k++){
                for(int  s= 0 ;s < N ; s++){
                    if(Math.abs(points[k].x - points[s].x) + Math.abs(points[k].y - points[s].y) <= 1000 && k!=s){
                        isConnected[k][s] = true;
                        isConnected[s][k] = true;
                    }
                }
            }

            printRes(bfs()?"happy":"sad");
        }

    }

    public static boolean bfs(){
        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(0);
        boolean isVisited[] = new boolean[N];
        isVisited[0] = true;
        while(!dq.isEmpty()){
            int p = dq.poll();

            if(p == N-1){
                return true;
            }

            for(int k = 0 ; k < N ; k++){
                if(isConnected[p][k] && !isVisited[k]){
                    dq.add(k);
                    isVisited[k] = true;
                }
            }
        }
        return false;
    }

    public static void printRes(String ans) throws IOException {
        bw.write(ans);
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
