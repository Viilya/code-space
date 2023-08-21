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

public class Main2_FloydWarshall {
    public static void main(String[] args) throws IOException {
        input();
        checkEachConnection();
        printRes(isTravelRouteValid());
    }

    public static int N, M;
    public static int route[];
    public static int isConnected[][];
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
    public static int nextInt(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }

    private static void checkEachConnection(){
        for(int k = 0 ; k < N ; k++){
            isConnected[k][k] = 1;
        }


        for (int k = 0; k < N; k++) //가운데 노드
            for (int s = 0; s < N; s++) //시작 노드
                for (int i = 0; i < N; i++) //마지막 노드
                    if (isConnected[s][k] == 1 && isConnected[k][i] == 1) {
                        isConnected[s][i] = 1;
                        isConnected[i][s] = 1;
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
