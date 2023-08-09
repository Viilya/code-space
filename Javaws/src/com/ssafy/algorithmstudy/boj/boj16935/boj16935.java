package com.ssafy.algorithmstudy.boj.boj16935;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj16935 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
    public static int N, M, T;
    public static int[][] map;
    public static int[] commands;

    public static void printRes() throws IOException{
        for (int k = 0; k < N; k++) {
            for (int s = 0; s < M; s++) {
                bw.write(String.valueOf(map[k][s]) + " ");
            }
            bw.newLine();
        }
        bw.flush();
    }
    
    /**
     * 1
     */
    public static void reverseUpDown() {
        // 
        int[] upMap = new int[M];// tmp line
        for (int k = 0; k < N / 2; k++) {
            // 한줄 미리 저장 후 위 아래 바꾸기
            upMap = Arrays.copyOf(map[k], M);
            map[k] = Arrays.copyOf(map[N - k - 1], M);
            map[N - k - 1] = Arrays.copyOf(upMap, M);
        }
    }

    /**
     * 2
     */
    public static void reverseLeftRight() {
        // 한 줄 마다 좌 우 바꾸기
        for (int k = 0; k < N; k++) {
            for (int s = 0; s < M/2; s++) {
                int tmp = map[k][s];
                map[k][s] = map[k][M - s - 1];
                map[k][M - s - 1] = tmp;
            }
        }
    }
    
    /**
     * 3
     */
    public static void rotateRight() {
        // 새로 M, N 만든 후에 map에 숫자들을 오른쪽으로 돌려 새로 만든 배열에 저장후 다시 선언해서 데이터 저장 
        int[][] rRMap = new int[M][N];
        for (int k = 0; k < N; k++) {
            for (int s = 0; s < M; s++) {
                rRMap[s][N - k - 1] = map[k][s];
            }
        }
        int tmp = M;
        M = N;
        N = tmp;
        map = new int[N][M];
        for (int k = 0; k < N; k++) {
            map[k] = Arrays.copyOf(rRMap[k], M);
        }
    }

    /**
     * 4
     */
    public static void rotateLeft() {
        // 새로 M, N 만든 후에 map에 숫자들을 왼쪽으로 돌려 새로 만든 배열에 저장후 다시 선언해서 데이터 저장 
        int[][] lRMap = new int[M][N];
        for (int k = 0; k < N; k++) {
            for (int s = 0; s < M; s++) {
                lRMap[M-s-1][k] = map[k][s];
            }
        }
        int tmp = M;
        M = N;
        N = tmp;
        map = new int[N][M];
        for (int k = 0; k < N; k++) {
            map[k] = Arrays.copyOf(lRMap[k], M);
        }
    }
    
    /**
     * 5
     */
    public static void divideAndRotateRight() {
        // 전체 맵을 4 등분 한 후에 오른쪽으로 돌려서 다시 저장
        int[]map01 = new int[M / 2];
        for (int k = 0; k < N/2; k++) {
            map01 = Arrays.copyOfRange(map[k], 0, M / 2);
            for (int s = 0; s < M / 2; s++) {
                map[k][s] = map[k+N/2][s];
                map[k+N/2][s] = map[k+N/2][s+M/2];
                map[k + N / 2][s + M / 2] = map[k][s+M/2];
                map[k][s + M / 2] = map01[s];
            }
        }
    }

    /**
     * 6
     */
    public static void divideAndRotateLeft() {
        // 전체 맵을 4 등분 한 후에 오른쪽으로 돌려서 다시 저장{
        int[] map01 = new int[M / 2];
        for (int k = 0; k < N/2; k++) {
            map01 = Arrays.copyOfRange(map[k], 0, M / 2);
            for (int s = 0; s < M / 2; s++) {
                map[k][s] = map[k][s+M/2];
                map[k][s + M / 2] = map[k+N/2][s+M/2];
                map[k + N / 2][s+M/2] = map[k+N/2][s];
                map[k + N / 2][s] = map01[s];
            }
        }
    }
    
    /**
     * get commands one by one and call functions
     */
    public static void getCommandAndRun() {
        for(int k = 0 ; k < T ; k ++){
            switch (commands[k]) {
                case 1:
                    reverseUpDown();
                    break; 
                case 2:
                    reverseLeftRight();
                    break;
                case 3:
                    rotateRight();
                    break;
                case 4:
                    rotateLeft();
                    break;
                case 5:
                    divideAndRotateRight();
                    break;
                case 6:
                    divideAndRotateLeft();
                    break;
            }
        }
    }

    /**
     * input session
     * @throws IOException
     */
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        commands = new int[T];
        for (int k = 0; k < N; k++) {
            st = new StringTokenizer(br.readLine());
            for (int s = 0; s < M; s++)
                map[k][s] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int k = 0; k < T; k++) 
            commands[k] = Integer.parseInt(st.nextToken());
        
    }

    public static void main(String args[]) throws IOException {
        input();
        getCommandAndRun();
        printRes();
    }
}
