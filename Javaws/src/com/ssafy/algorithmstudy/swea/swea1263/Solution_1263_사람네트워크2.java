package com.ssafy.algorithmstudy.swea.swea1263;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_1263_사람네트워크2 {
    public static void main(String[] args) throws IOException {
        input();

        closeIO();
    }
    public static int[][] map;
    public static int N;
    public static int minSum = Integer.MAX_VALUE;
    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            for(int k = 0 ; k < N ; k ++){
                for(int s = 0 ; s< N ; s ++){
                    map[k][s] = Integer.parseInt(st.nextToken());

                }
            }

            floydWarshall();
//            for(int k = 0 ; k < N ; k++){
//                System.out.println(Arrays.toString(map[k]));
//            }
//            System.out.println();
            printRes(tc);
        }
    }

    public static void floydWarshall(){
        for(int k = 0 ; k < N ; k++){
            for(int s = 0 ; s < N ; s++){
                if(k == s) continue;
                for(int i = 0 ; i < N ; i ++){
                    map[s][i] = Math.min(map[s][i], map[i][k] + map[k][s]);

                }
            }
        }
        minSum = Integer.MAX_VALUE;

        for(int k = 0 ; k < N ; k++){
            int sum = 0;
            for(int s= 0 ; s< N ; s++){
                if(k == s) continue;
                sum += map[k][s];
            }
            minSum = Math.min(minSum, sum);
        }
    }

    public static void printRes(int tc) throws IOException {
        bw.write("#" + tc + " " + minSum);
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
