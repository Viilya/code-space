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
            for(int k = 0 ; k < N ; k++){
                map[k] = Integer.parseInt(st.nextToken());
                initMax = Math.max(initMax, map[k]);
            }
            for(int k = 0 ; k < N ; k ++) {
                sub = initMax - map[k];
                twoCount += sub/2;
                if(sub%2 == 1){
                    oneCount += 1;
                }
            }

            while(true){
                if(twoCount - oneCount <= 1) break;
                twoCount -= 1;
                oneCount += 2;
            }

            count = twoCount * 2 + ((oneCount - twoCount > 0)?(oneCount - twoCount - 1) * 2 + 1:0);



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
