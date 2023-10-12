package com.ssafy.algorithmstudy.swea.swea14510;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_14510_나무높이 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }

    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T ; tc++){
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int map[] = new int[N];
            int initMax = 0;
            int sub= 0;
            int count =  0;
            int oneCount = 0;
            int twoCount = 0;
            for(int k = 0 ; k < N ; k++){ // max 나무 찾기
                map[k] = Integer.parseInt(st.nextToken());
                initMax = Math.max(initMax, map[k]);
            }
            for(int k = 0 ; k < N ; k ++) { // max 나무와의 차이를 계산
                sub = initMax - map[k];
                twoCount += sub/2; // 모든 나무가 물의 양 2를 기본으로 사용
                if(sub%2 == 1){  // 홀수인 경우 물의 양이 1인 경우는 한번 사용
                    oneCount += 1;
                }
            }

            while(true){ // 물의 양을 2로 주는 경우와 1로 주는 경우의 차이가 최대한 적게 균형
                if(twoCount - oneCount <= 1) break;
                twoCount -= 1;
                oneCount += 2;
            }

            count = twoCount * 2 + ((oneCount - twoCount > 0)?(oneCount - twoCount - 1) * 2 + 1:0); // 경우 계산



            printRes(tc, count);
        }
    }

    public static void printRes(int tc, int count) throws IOException {
        bw.write("#" + tc + " " + count);
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
