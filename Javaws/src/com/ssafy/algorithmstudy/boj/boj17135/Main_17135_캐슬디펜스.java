package com.ssafy.algorithmstudy.boj.boj17135;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17135_캐슬디펜스 {
    public static void main(String[] args) throws IOException {
        input();
        runSimulation();
        printRes();
    }

    public static int N, M, D;
    public static int [][] map;
    public static int [][] movingMap;
    public static int maxRes = 0;

    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        movingMap = new int[N+1][M];
        for(int k = 0 ; k < N; k++){
            map[k] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            //System.out.println(Arrays.toString(map[k]));
        }
    }

    public static void runSimulation(){
        initMovingMap();
        setArchersLocationByCombination(0, -1, new int[3]);

    }
    public static void initMovingMap(){
        for(int k = 0; k < N ; k ++){
            movingMap[k] = map[k].clone();
        }
    }

    public static void setArchersLocationByCombination(int cnt, int bfCastleLocation, int[] arr){
        if(cnt == 3){
            initMovingMap();
            maxRes = Math.max(maxRes , defenceBegins(arr));
            return;
        }
        for(int k = bfCastleLocation + 1 ; k < M ; k ++){
            arr[cnt] = k;
            setArchersLocationByCombination(cnt +1 , k, arr);
        }
    }

    public static int defenceBegins(int[] arr){
        int count = 0;
        for(int k = 0 ; k < N ; k ++) {
//            System.out.println(Arrays.toString(arr));
//            for (int s = 0; s < N; s++) {
//                System.out.println(Arrays.toString(movingMap[s]));
//            }
            count += findArchersAndShoot(arr);
            enemyMoves();
            //System.out.println("enemy Moves");
        }
        return count;
    }

    public static int findArchersAndShoot(int [] arr){
        int count = 0;
        int targets[][] = new int[3][2];
        for(int k = 0 ; k < 3;  k++){
            targets[k] = looseArrow(arr[k], targets);
        }
        for(int k = 0 ; k < 3;  k++){
            if(targets[k][0] >= 0 && movingMap[targets[k][0]][targets[k][1]] == 1){
                movingMap[targets[k][0]][targets[k][1]] = 0;
                count += 1;
            }
        }
        return count;
    }
    public static int[] looseArrow(int archerPosition, int[][] targets){
        int[] tmp = {-1, -1};
        for(int k = 1; k <= D ; k ++) {
            tmp = findEnemy(archerPosition, k);
            //System.out.println();
            if (tmp[0] != -1) {
                break;
            }
        }
        return tmp;
    }

    public static int[] findEnemy(int archerPosition, int distance){
        for(int k = distance-1 ; k >= -distance+1 ; k --){
            //System.out.println((N  + (k>0?k:-k) - distance) + " " + (archerPosition- k));
            if(isEnemyExist(N  + (k>0?k:-k) - distance, archerPosition - k)){
                return new int[] {N  + (k>0?k:-k) - distance, archerPosition - k};
            }
        }
        return new int[] {-1, -1};
    }

    public static boolean isEnemyExist(int x, int y){
        if(x < 0 || x >= N  || y < 0 || y >= M || movingMap[x][y] == 0)
            return false;
        return true;
    }
    public static void enemyMoves(){
        for(int k = N-1 ; k >=1 ; k --){
            movingMap[k] = movingMap[k-1].clone();
        }
        movingMap[0] = new int[M];
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
