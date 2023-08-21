package com.ssafy.algorithmstudy.boj.boj1976;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N 개 도시
 * 여행경로가 가능한 것인지 알아보기
 * 중간에 다른 도시를 경유해서 갈 수 있다.
 * E
 * |
 * A - B - C
 * | /
 * D
 *
 * N < 200 / M 1000
 * i j 1/0
 */

public class Main_1976_여행_가자 {
    public static void main(String[] args) throws IOException {
        input();
        for (int k = 0; k < N; k++) {
            isVisited = new boolean[N];
            isVisited[k] = true;
            checkEachConnection(k);
        }
        printRes(isTravelRouteValid());
    }

    public static int N, M;
    public static int route[];
    public static int isConnected[][];
    public static boolean isVisited[];
    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        isConnected = new int [N][N];
        route = new int [M];
        for(int k = 0 ; k < N ; k++){
            isConnected[k] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        route = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static void checkEachConnection(int start){
        for(int k = 0 ; k < N ; k++){
            if(isConnected[start][k] == 1){

            }
        }

    }


    public static void printArr(){
        for(int k = 0 ; k< N  ;k++) {
            System.out.println(Arrays.toString(isConnected[k]));
        }
    }

    public static String isTravelRouteValid(){
        for(int k = 0 ; k < M-1 ; k++){
            if(isConnected[route[k] - 1][route[k+1] - 1] == 0){
                return "NO";
            }
        }
        return "YES";
    }

    public static void printRes(String res) throws IOException{
        bw.write(res);
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
