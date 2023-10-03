package com.ssafy.algorithmstudy.boj.boj14502;

import java.io.*;
import java.util.*;

public class Main_14502_연구소_2 {

    public static void main(String[] args) throws IOException {
        input();
        buildWallAndBFS(map, 0, 0, 0);
        printRes();
        closeIO();
    }
    private static int N, M;
    private static int map[][];
    private static boolean isVisited[][];
    private static int maxSafeArea = 0;
    private static int safeAreaCount = 0;
    private static int dx[] = {-1, 0, 1, 0};
    private static int dy[] = {0, 1, 0, -1};

    private static ArrayList<Point> viruses = new ArrayList<>();
    private static void buildWallAndBFS(int map[][], int count, int currX, int currY){
        if(count >= 3){ // 벽을 3개 다 놓은 경우 BFS
            BFS(map);
            return;
        }
        for(int k = currX ; k < N ; k ++){
            if(k != currX) {  // x 좌표가 같지 않은 경우 처음부터 쭉 돌기
                for (int s = 0; s < M; s++) {
                    if (map[k][s] == 0) {
                        map[k][s] = 1;
                        buildWallAndBFS(map, count + 1, k, s);
                        map[k][s] = 0;
                    }
                }
            }
            else if(k == currX){ // x 좌표가 같은 경우 방금 전 놓았던 벽의 위치부터 놓기
                for(int s = currY ; s < M ; s++){
                    if (map[k][s] == 0) {
                        map[k][s] = 1;
                        buildWallAndBFS(map, count + 1, k, s);
                        map[k][s] = 0;
                    }
                }
            }
        }
    }

    private static void BFS(int map[][]){

        int count = safeAreaCount - 3;
        isVisited = new boolean[N][M];
        for(int k = 0 ; k < viruses.size(); k ++){ // 모든 바이러스 위치를 탐색
            Point virus = viruses.get(k);

            Queue<Point> q = new ArrayDeque<>();
            q.add(virus);
            while(!q.isEmpty()){ // 바이러스 BFS
                Point currVirus = q.poll();

                for(int s = 0 ; s < 4; s ++){ // 4방 탐색하며 BFS
                    int X = currVirus.x + dx[s];
                    int Y = currVirus.y + dy[s];
                    if(isSafe(X, Y, map) && !isVisited[X][Y]){
                        q.add(new Point(X, Y));
                        isVisited[X][Y] = true; // 빈 공간인 경우 2로 채우고 count 줄이기
                        count--;
                    }
                }
            }
        }
        maxSafeArea = Math.max(count, maxSafeArea); // max 값 비교
    }


    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int k = 0 ; k < N ; k++){
            st = new StringTokenizer(br.readLine());
            for(int s = 0 ; s < M ; s++){
                 map[k][s] = Integer.parseInt(st.nextToken());
                 if(map[k][s] == 2){ // 미리 바이러스의 위치를 ArrayList에 저장
                     viruses.add(new Point(k, s));
                 }
                 if(map[k][s] == 0){ // 미리 빈 공간을 카운트
                     safeAreaCount ++;
                 }
            }
        }
    }

    // 바이러스를 퍼뜨릴 수 있는 위치인 경우 true를 반환, 아니면 false
    public static boolean isSafe(int x, int y, int[][] map){
        if(x<0||x>=N||y<0||y>=M||map[x][y]!=0) return false;
        return true;
    }


    public static void printRes() throws IOException {
        bw.write(maxSafeArea + "");
    }

    public static void closeIO() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }
    static class Point{
        int x, y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}