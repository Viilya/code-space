package com.ssafy.algorithmstudy.boj.boj17144;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17144_미세먼지안녕 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }

    public static int cleaner;
    public static int dx[] = {-1, 0, 1, 0};
    public static int dy[] = {0, 1, 0, -1};

    public static void input() throws IOException {
        int R, C, T;
        st= new StringTokenizer(br.readLine());


        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        int map[][] = new int[R][C];

        for(int k = 0 ; k < R ; k++) {
            map[k] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if(map[k][0] == -1){
                cleaner = k; // 클리너 아래 좌표 저장
            }
        }
        sequence(R, C, T, map); // 1번, 2번 과정 진행
        printRes(R, C, map); // 결과 출력
    }


    // 테스트 횟수 만큼 과정 진행
    public static void sequence(int R, int C, int T, int map[][]){

        for(int k = 0 ; k < T; k ++){
            spreadSeq(R, C, map);
            cleanSeq(R, C, map);
        }
    }

    /**
     * 미세먼지 퍼지는 과정 1번 진행
     * @param r
     * @param c
     * @param map
     */
    private static void spreadSeq(int r, int c, int[][] map) {
        int newMap[][] = new int[r][c]; // 퍼진 미세먼지를 기록하기 위한 새로운 맵 생성

        for(int k = 0 ; k < r; k ++){
            for(int s = 0 ; s < c ; s++){
                if(map[k][s] <= 0) continue;
                spread(r, c, map, newMap, k, s); // map에서 발견한 미세먼지마다 퍼트리기
            }
        }

        for(int k = 0 ; k < r; k ++){
            map[k] = Arrays.copyOf(newMap[k], c); // 다 퍼트리면 map에 newMap 복사
        }

        map[cleaner][0] = -1;
        map[cleaner - 1][0] = -1; // 클리어 위치는 -1로 만들기(보험용)
    }

    /**
     * 한 지점의 미세먼지 퍼트리기
     * @param r
     * @param c
     * @param map
     * @param newMap
     * @param x
     * @param y
     */
    private static void spread(int r, int c, int[][] map, int[][] newMap, int x, int y) {

        int total = map[x][y] ;
        int partTotal = total / 5;
        for(int k = 0 ; k < 4 ; k ++){ // 4방 탐색 후 가능한 위치면 퍼트리기
            int X = x + dx[k];
            int Y = y + dy[k];
            if(isValid(r, c, X, Y, map)){
                newMap[X][Y] += partTotal;
                total -= partTotal;
            }
        }
        newMap[x][y] += total;
    }

    /**
     * 배열 돌리기를 하며 청소하기
     * @param r
     * @param c
     * @param map
     */
    private static void cleanSeq(int r, int c, int[][] map){
        int upd[] = {0,1,2,3}; // 위쪽 공기 흐름 방향 순서
        int downd[] = {2,1,0,3}; // 아래쪽 공기 흐름 방향 순서
        clean(c, map, upd, cleaner - 2, 0, 0, cleaner-1); // 위쪽 청소
        clean(c, map, downd, cleaner + 1, 0, cleaner, r-1); // 아래쪽 청소
        map[cleaner][1]= 0;
        map[cleaner - 1][1] = 0;
    }


    private static void clean(int c, int[][] map, int dir[], int a, int b, int r1, int r2) {
        int x = a;
        int y = b;
        for(int k = 0 ; k < 4; k ++){  // 4방 따라가며 청소
            int X, Y;
            while(true){
                X = x + dx[dir[k]];
                Y = y + dy[dir[k]];
                if(!isCleanable(r1, r2, c, X, Y, map)) break;
                map[x][y] = map[X][Y];  // 당기면서 덮어 쓰기
                x = X;
                y = Y;
            }
        }
    }

    private static boolean isCleanable(int r1, int r2, int c, int x, int y, int[][] map){
        if(x < r1 || x > r2) return false;
        if(y < 0 || y>= c) return false;
        if(map[x][y] == -1) return false;
        return true;
    }

    private static boolean isValid(int r, int c, int x, int y, int[][] map) {
        if(x < 0 || x>= r) return false;
        if(y < 0 || y>= c) return false;
        if(map[x][y] == -1) return false;
        return true;
    }

    public static void printRes(int r, int c, int map[][]) throws IOException {
        int sum = 0;
        for(int k = 0 ; k < r; k++){
            for(int s = 0 ; s< c; s++){
                if(map[k][s] >0) sum += map[k][s];
            }
        }

        bw.write(sum + "");
        bw.newLine();
    }

    public static void closeIO() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    public static void printMap(int r, int c, int map[][]){
        for(int k = 0; k < r; k ++){
            System.out.println(Arrays.toString(map[k]));
        }
    }


    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
