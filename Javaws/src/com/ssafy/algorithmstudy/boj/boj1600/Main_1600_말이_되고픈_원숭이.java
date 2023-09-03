package com.ssafy.algorithmstudy.boj.boj1600;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1600_말이_되고픈_원숭이{
    public static void main(String[] args) throws IOException {
        input();
        DPBFS();
        printRes();
        closeIO();
    }
    public static int W, H, K;
    public static int map[][];
    public static int dp[][];
    public static int dx[] = {1, 0, -1, 0};
    public static int dy[] = {0, 1, 0, -1};
    public static int dHx[] = { 1, 2, 2, 1, -1, -2, -2, -1};
    public static int dHy[] = {2, 1, -1, -2, 2, 1, -1, -2};

    static class Moves{
        int x;
        int y;
        int k;
        int count;

        public Moves(int x, int y, int k, int count){
            this.x = x;
            this.y = y;
            this.k = k;
            this.count = count;
        }
    }

    public static void DPBFS(){
        Queue<Moves> q = new ArrayDeque<>();
        q.offer(new Moves(0, 0, 0, 0));
        boolean isVisited[][][] = new boolean[H][W][K+1];
        isVisited[0][0][0] = true;
        while(!q.isEmpty()){
            Moves move = q.poll();
            dp[move.x][move.y]= move.count;
            if(move.x == H-1 && move.y == W-1) break;
            if(move.k < K){
                for(int k = 0 ; k < 8 ; k ++) {
                    int X = move.x + dHx[k];
                    int Y = move.y + dHy[k];
                    if (isValid(X, Y) && !isVisited[X][Y][move.k+1]) {
                        isVisited[X][Y][move.k+1] = true;
                        dp[X][Y] = move.count+1;
                        q.offer(new Moves(X, Y, move.k+1, move.count + 1));
                    }
                }
            }
            for (int k = 0; k < 4; k++) {
                int X = move.x + dx[k];
                int Y = move.y + dy[k];
                if(isValid(X, Y) && !isVisited[X][Y][move.k]){
                    isVisited[X][Y][move.k] = true;
                    dp[X][Y] = move.count+1;
                    q.offer(new Moves(X, Y, move.k, move.count + 1));
                }
            }
        }
    }


    public static boolean isValid(int x, int y){
        if(x< 0 || x>= H || y< 0 || y>= W || map[x][y] == 1){
            return false;
        }
        return true;
    }



    public static void input() throws IOException {
        K = Integer.parseInt(br.readLine());
        st= new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        dp = new int[H][W];
        for(int k = 0 ; k < H ; k ++){
            st = new StringTokenizer(br.readLine());
            for(int s = 0 ; s < W ; s++){
                map[k][s] = Integer.parseInt(st.nextToken());
            }
        }
        for(int k = 0; k < H ; k++) {
            for(int s= 0 ; s< W ; s++){
                dp[k][s] = Integer.MAX_VALUE;
            }
        }
    }

    public static void printRes() throws IOException {
        bw.write(String.valueOf(dp[H-1][W-1]!=Integer.MAX_VALUE?dp[H-1][W-1]:-1));
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
