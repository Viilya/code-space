package com.ssafy.algorithmstudy.boj.boj1987;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1987_알파벳 {
    public static void main(String[] args) throws IOException {
        input();
        alphabet[map[0][0] -'A'] = true;
        dfs(0, 0, 1);
        printRes();
    }
    public static int R, C;
    public static char[][] map;
    public static boolean[] alphabet = new boolean[26];
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};
    public static int maxRes = 0;
    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        for(int k = 0 ; k < R ; k ++){
            map[k] = br.readLine().toCharArray();
        }
    }


    public static void dfs(int x, int y, int count){
        boolean noNextDFS = true;
        for(int d = 0 ; d < 4; d ++){
            int X = x + dx[d];
            int Y = y + dy[d];
            if(isMovable(X,Y)){
                alphabet[map[X][Y] -'A'] = true;
                dfs(X, Y, count+1);
                alphabet[map[X][Y] -'A'] = false;
                noNextDFS = false;
            }
        }
        if(noNextDFS){
            maxRes = Math.max(maxRes, count);
        }
    }

    public static boolean isMovable(int x, int y){
        if( x < 0 || x >= R || y < 0 || y >= C || alphabet[map[x][y] - 'A']){
            return false;
        }
        return true;
    }

    public static void printRes() throws IOException{
        bw.write(String.valueOf(maxRes));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
