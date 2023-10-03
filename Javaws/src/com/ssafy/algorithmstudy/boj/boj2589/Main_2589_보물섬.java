package com.ssafy.algorithmstudy.boj.boj2589;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_2589_보물섬 {
    private static int s;

    public static void main(String[] args) throws IOException {
        input();

        bfsEveryCorrdinate();

        printRes();
        closeIO();
    }

    static class Coor{
        int x;
        int y;
        int count;
        public Coor(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }


    private static int N, M;
    private static char map[][];

    private static boolean isVisited[][];
    private static boolean isBFSed[][];
    private static int maxHour = 0;
    private static int dx[] = {-1, 0, 1, 0};
    private static int dy[] = {0, 1, 0, -1};

    private static void bfsEveryCorrdinate() {
        isBFSed = new boolean[N][M];
        for (int k = 0; k < N; k++) {
            for (int s = 0; s < M; s++) {
                if (map[k][s] == 'L' && !isBFSed[k][s]){
                    isVisited = new boolean[N][M];
                    bfs(k, s);
                }
            }
        }
    }

    private static void bfs(int x, int y){
        Deque<Coor> dq = new ArrayDeque<>();
        dq.offerLast(new Coor(x, y, 0));

        while(!dq.isEmpty()){
            Coor currCoor = dq.pollFirst();
            isVisited[currCoor.x][currCoor.y] = true;
            boolean isMovable = false;

            for(int k = 0; k < 4; k ++){
                int X = currCoor.x + dx[k];
                int Y = currCoor.y + dy[k];
                if(isValidCoordinate(X, Y)){
                    dq.offerLast(new Coor(X, Y, currCoor.count+1));
                    isVisited[X][Y] = true;
                    isMovable = true;
                }
            }
            if(isMovable) isBFSed[currCoor.x][currCoor.y] = true;
            else{maxHour = Math.max(currCoor.count, maxHour);}

        }
    }





    private static boolean isValidCoordinate(int x, int y){
        if(x<0||x>=N||y<0||y>=M||map[x][y]=='W'||isVisited[x][y])return false;
        return true;
    }



    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for(int k = 0 ; k < N ; k ++){
            map[k] = br.readLine().toCharArray();
        }
    }

    public static void printRes() throws IOException {
        bw.write(maxHour + "");
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

