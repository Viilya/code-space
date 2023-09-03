package com.ssafy.algorithmstudy.boj.boj20058;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_20058_마법사_상어와_파이어스톰 {
    public static void main(String[] args) throws IOException {
        input();
        // 회전 하고 녹이기
        runLevels();
        // 전체 얼음 합이랑 얼음 덩어리 개수 구하기
        getIceSum();

        printRes();
        closeIO();
    }
    public static int powN;
    public static int N;
    public static int Q;
    public static int [][] map;
    public static int [] levels;
    public static int iceSum = 0;
    public static int iceMax = 0;
    public static int [][] iceBerg;
    static int dx[] = {0, -1, 0, 1};
    static int dy[] = {1, 0 ,-1, 0};
    public static void runLevels(){
        for(int level : levels){
            if(level == 0) continue;
            int powL = 1<<level;
            // level 만큼 돌리기(재귀로)
            cutIce(0, 0, powN, powL);
            // 얼음 녹이기
            meltIce();
        }
    }

    public static void cutIce(int x, int y, int n, int l){
        if(n == l){
            rotateIce(x, y, l);
        }else{
            // 4분할 해서 자르기
            cutIce(x, y, n/2, l);
            cutIce(x + n/2, y, n/2, l);
            cutIce(x, y+ n/2, n/2, l);
            cutIce(x+n/2, y+n/2, n/2, l);
        }
    }

    public static void rotateIce(int x, int y, int l){
        int rMap[][] = new int[l][l];
         // rMap 에 돌린 배열 넣고
        for(int k = x; k < x + l ; k ++){
            for(int s= y ; s < y + l ; s ++){
                rMap[s - y][x + l - k - 1] = map[k][s];
            }
        }
        // map 에 배열 다시 넣기
        for(int k = x; k < x + l ; k ++){
            for(int s = y; s < y + l ; s++){
                map[k][s] = rMap[k - x][s - y];
            }
        }
    }

    // 얼음 녹이는 함수
    public static void meltIce(){

        boolean [][] isReduce = new boolean[powN][powN];
        // 한 지점 사방 탐색 하고 사방 얼음이 3개 없으면 줄일지 말지 불린 배열에 저장
        for(int k = 0 ; k < powN ; k ++){
            for(int s= 0; s < powN ; s++){
                isReduce[k][s] = checkIce4Dir(k, s);
            }
        }
        // 불린 배열 저장한거 토대로 1씩 감소
        for(int k = 0 ; k < powN ; k ++){
            for(int s= 0 ;s < powN ; s++){
                map[k][s] -= isReduce[k][s]?1:0;
            }
        }
    }

    public static boolean checkIce4Dir(int x, int y){
        if(map[x][y] == 0) return false; // 0이면 셀필요 없음
        int iceCount = 0;
        // 사방 탐색해서 얼음 잇는 개수 카운트
        for(int k = 0 ; k < 4; k ++){
            int X = x + dx[k];
            int Y = y + dy[k];

            if(X < 0 || X >= powN)
                continue;
            if(Y < 0 || Y >= powN)
                continue;
            if(map[X][Y] == 0)
                continue;

            iceCount ++;
        }
        // 3개 미만이면 감소
        if(iceCount < 3) return true;
        return false; // 아니면 유지
    }


    // 모든 얼음 개수 구하기 + bfs로 얼음 덩어리 탐색
    public static void getIceSum(){
        iceBerg = new int[powN][powN];
        int iceBergCount =0;
        for(int  k = 0 ; k < powN ; k++){
            for(int s = 0 ; s< powN ; s++){
                if(iceBerg[k][s] == 0 && map[k][s] != 0){ // 탐색 아직 안했거나, 얼음 빙하가 있으면
                    getIceBerg(k, s, ++iceBergCount); // 얼음 덩어리 묶기
                }
                iceSum += map[k][s];
            }
        }
    }

    public static void getIceBerg(int x, int y, int count){
        Queue<int[]> q = new ArrayDeque<>();
        int iceCount = 0;
        q.add(new int[] {x, y});
        while(!q.isEmpty()){ // BFS로 사방 탐색해서 아직 탐색 하지 않았고 빙하가 존재하면 확장
            int[] curr = q.poll();
            for(int k = 0 ; k < 4; k ++){
                int X = curr[0] + dx[k];
                int Y = curr[1] + dy[k];

                if(X < 0 || X >= powN)
                    continue;
                if(Y < 0 || Y >= powN)
                    continue;
                if(map[X][Y] == 0)
                    continue;
                if(iceBerg[X][Y] != 0)
                    continue;

                iceBerg[X][Y] = count;
                iceCount += 1;
                q.add(new int[] {X, Y});
            }
        }
        iceMax = Math.max(iceMax, iceCount);
    }


    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        powN = 1 << N;
        map = new int[powN][powN];
        levels = new int[Q];


        for(int k = 0 ; k < powN ; k ++){
            map[k] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        levels = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public static void printRes() throws IOException {
        bw.write(String.valueOf(iceSum));
        bw.newLine();
        bw.write(String.valueOf(iceMax));
        bw.newLine();
    }

    public static void closeIO() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    public static void printMap(){
        for(int k = 0 ; k < powN; k ++){
            System.out.println(Arrays.toString(map[k]));
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
