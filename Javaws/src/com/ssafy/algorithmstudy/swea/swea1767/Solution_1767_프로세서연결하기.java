package com.ssafy.algorithmstudy.swea.swea1767;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_1767_프로세서연결하기 {
    public static void main(String[] args) throws IOException {
        input();

        closeIO();
    }

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static int map[][] ;
    public static int N;
    public static int minLen = 0;
    public static int maxCore = 0;
    public static ArrayList<Core> arr;
    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T ; tc++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            arr = new ArrayList<>();
            for(int k = 0; k < N ; k ++){
                st = new StringTokenizer(br.readLine());
                for(int s = 0 ; s< N ; s ++){
                    map[k][s] = Integer.parseInt(st.nextToken());
                    if(map[k][s] == 1){
                        arr.add(new Core(k, s));
                    }
                }
            }
            maxCore = 0;
            minLen = Integer.MAX_VALUE;
            makeLine(0, 0, 0);
            printRes(tc);
        }
    }

    public static void makeLine(int cnt, int lineLen, int coreCount){
        if(cnt == arr.size()){
            if(maxCore < coreCount){
                maxCore = coreCount;
                minLen = lineLen;
            }else if(maxCore == coreCount && minLen > lineLen){
                minLen = lineLen;
            }
            return;
        }
        if(isBoundary(arr.get(cnt))){
            makeLine(cnt + 1, lineLen, coreCount + 1);
        }else{
            for(int d = 0 ; d< 4; d ++){
                int len = connect(arr.get(cnt), d);
                if(len != 0) {
                    makeLine(cnt + 1, lineLen + len, coreCount + 1);
                    cut(arr.get(cnt), d);

                }
            }
            makeLine(cnt + 1, lineLen, coreCount);
        }
    }

    public static boolean isBoundary(Core c){
        if(c.x == 0 || c.x == N-1 || c.y == 0 || c.y == N-1)
            return true;
        return false;
    }

    public static int connect(Core c, int d){
        int count=0;
        int X = c.x;
        int Y = c.y;
        while(true){
            X += dx[d];
            Y += dy[d];
            if(X<0 || X>=N || Y <0 || Y>=N){
                return count;
            }
            if(map[X][Y] != 0) {
                X -= dx[d];
                Y -= dy[d];
                while(true){
                    if(map[X][Y] == 1)
                        break;
                    map[X][Y] = 0;
                    X -= dx[d];
                    Y -= dy[d];
                }
                return 0;
            }else{
                map[X][Y] = 2;
                count += 1;
            }
        }

    }

    public static void cut(Core c, int d){
        int X = c.x;
        int Y = c.y;
        while(true) {
            X += dx[d];
            Y += dy[d];
            if (X < 0 || X >= N || Y < 0 || Y >= N) {
                break;
            }
            map[X][Y] = 0;
        }
    }
    public static void printRes(int tc) throws IOException {
        bw.write("#" + tc + " " + minLen);
        bw.newLine();
    }

    public static void closeIO() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    static class Core{
        int x, y;

        public Core(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}

