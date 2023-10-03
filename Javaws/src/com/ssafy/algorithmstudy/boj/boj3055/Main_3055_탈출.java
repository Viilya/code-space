package com.ssafy.algorithmstudy.boj.boj3055;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * R행 C열
 * '.' 빈 곳
 * '*' 물
 * 'X' 돌
 * 'D' 비버의 굴
 * 'S' 고슴도치
 *
 * 매 분 고슴도치는 사방으로 이동
 * 물 도 비어있는 칸으로 확장
 * 물이 있는 칸, 인접해있는 비어있는 칸(한변 공유) 물이 차게 된다.
 * 물과 고슴도치는 물을 통과할 수 없음
 *
 */



public class Main_3055_탈출 {

    static class Point{
        int x;
        int y;
        int count ;

        public Point(int x, int y){
            this.x = x; this.y = y; this.count =0;
        }

        public Point(int x, int y, int count){

            this.x = x; this.y = y; this.count =count;
        }
    }

    public static void main(String[] args) throws IOException {
        input();

        doBFS();

        printRes();
        closeIO();
    }

    public static int R, C;
    public static char[][] map;
    public static char[][] nextMap;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int minLength = Integer.MAX_VALUE;
    public static Point SuperConductorDochi;

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map= new char[R][C];
        nextMap = new char[R][C];
        for(int k = 0 ; k < R ; k ++){
            map[k] = br.readLine().toCharArray();
            nextMap[k] = Arrays.copyOf(map[k], C);
            for(int s = 0 ; s< C; s++){
                if(map[k][s] == 'S'){
                    SuperConductorDochi = new Point(k, s);
                }
            }
        }
    }

    public static void doBFS(){
        Deque<Point> dq = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[R][C];
        dq.add(SuperConductorDochi);
        isVisited[SuperConductorDochi.x][SuperConductorDochi.y] = true;
        int currLevel = 0;
        getNextMap();
        while(!dq.isEmpty()){
            Point thisDochi = dq.poll();
            if(map[thisDochi.x][thisDochi.y] == 'D'){
                minLength = thisDochi.count;
                break;
            }
            if(currLevel != thisDochi.count){
                getNextMap();
                currLevel = thisDochi.count;
            }
            for(int k = 0 ; k < 4 ; k++){
                int X = thisDochi.x + dx[k];
                int Y = thisDochi.y + dy[k];
                if(isMovable(X, Y) && !isVisited[X][Y]){
                    isVisited[X][Y] = true;
                    dq.add(new Point(X, Y, thisDochi.count +1));
                }
            }
        }

    }

    public static void getNextMap(){
        for(int k = 0; k < R; k ++){
            map[k] = Arrays.copyOf(nextMap[k], C);
        }

        for(int k = 0 ; k < R; k ++){
            for(int s = 0 ; s < C; s++){
                if(map[k][s] == '*'){
                    for(int d = 0 ; d < 4 ; d ++){
                        int X = k + dx[d];
                        int Y = s + dy[d];
                        if(isWaterCanGo(X, Y)){
                            nextMap[X][Y] = '*';
                        }
                    }
                }
            }
        }
    }

    public static boolean isWaterCanGo(int x, int y){
        return x >= 0 && x < R && y >= 0 && y < C && map[x][y] != 'X' && map[x][y] != 'D';
    }

    public static boolean isMovable(int x, int y){
        return x >= 0 && x < R && y >= 0 && y < C && nextMap[x][y] != 'X' && nextMap[x][y] != '*';
    }

    public static void printRes() throws IOException {
        bw.write((minLength!=Integer.MAX_VALUE?minLength:"KAKTUS") + "");
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
