package com.ssafy.algorithmstudy.boj.boj4485;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_4485_녹색옷입은애가젤다지{
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }


    public static int N;
    public static int map[][];
    public static int dx[] = {-1, 0, 1, 0};
    public static int dy[] = {0, 1, 0, -1};
    public static int minLen = 0;


    public static void input() throws IOException {

        int problemCount = 1;
        while(true){
            N = Integer.parseInt(br.readLine());
            if(N == 0) break; // 0이 나오면 프로그램 종료
            map = new int  [N][N];
            for(int k = 0 ; k < N ; k++){
                map[k] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            printRes(problemCount++, dijkstra()); // 결과 출력 함수

        }
    }

    public static int dijkstra(){
        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean isVisited[][] = new boolean[N][N];
        int minDist[][] = new int[N][N];
        for(int k = 0 ; k < N ; k ++) {
            Arrays.fill(minDist[k], Integer.MAX_VALUE);
        }

        // 0,0 부터 시작
        minDist[0][0] = 0;
        isVisited[0][0] = true;
        pq.add(new Point(0,0,map[0][0]));

        while(!pq.isEmpty()){ // pq가 다 빌때 까지
            Point c = pq.poll();
            for(int k = 0 ; k < 4; k ++){ // 4방 탐색
                int X = dx[k] + c.x;
                int Y = dy[k] + c.y;

                if(isValid(X, Y) && !isVisited[X][Y]){ // 유효한 좌표이고 아직 방문하지 않은 좌표이면
                    if(minDist[X][Y] > map[X][Y] + c.l){ // 최소값이 지금 값보다 크다면 갱신하고 큐에 추가
                        minDist[X][Y] = map[X][Y] + c.l;
                        pq.add(new Point(X, Y, minDist[X][Y]));
                        isVisited[X][Y] = true;
                    }
                }
            }
        }
        return minDist[N-1][N-1];
    }

    static class Point implements Comparable<Point> {
        int x, y, l;
        public Point(int x, int y, int l) {
            this.x = x;
            this.y = y;
            this.l = l;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.l, o.l);
        }
    }


    public static boolean isValid(int x, int y){
        if(x<0 || x>=N) return false;
        if(y<0 || y>=N) return false;
        return true;
    }

    public static void printRes(int p, int r) throws IOException {
        bw.write("Problem " + p + ": " + r);
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