package com.ssafy.algorithmstudy.boj.boj20187;

import java.io.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main_20187_색종이_접기 {
    public static int K;
    public static int[][] map;
    public static int mapLength;
    public static int H;
    public static int w, h;
    public static Deque<Character> dq = new ArrayDeque<Character>();
    public static void main(String args[]) throws IOException{
        input();
        closeIO();
    }

    public static void input() throws IOException{
        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        mapLength = (int)Math.pow(2, K);
        map = new int[mapLength][mapLength];
        w = mapLength / 2;
        h = mapLength / 2;
        for(int k = 0 ; k < 2 * K ; k ++) {
            dq.addLast(st.nextToken().charAt(0));
        }
        int flag = 0;
        int foldIdxDU = 0;
        int foldIdxLR = 0;
        for(int k = 0 ; k < 2*K; k++) {
            char tmp = dq.pollLast();

            System.out.print(tmp + " " );
            if(tmp == 'L'){
                if((flag / 2)== 0) {
                    flag = flag + 2;
                }
            }else if(tmp == 'D'){
                if((flag % 2) == 0) {
                    flag = flag + 1;
                    foldIdxDU = 1;
                }
            }else if(tmp == 'R'){
                if((flag / 2) == 0) {
                    flag = flag + 2;
                    foldIdxLR = 1;
                }
            }else if(tmp == 'U'){
                if((flag % 2) == 0) {
                    flag = flag + 1;
                }
            }
            if(flag == 3)
                break;
        }
        System.out.println("\n" + foldIdxDU + " " +  foldIdxLR);
        foldIdxDU = foldIdxDU;
        foldIdxLR = foldIdxLR;
        int hole = Integer.parseInt(br.readLine());
        printRes(foldIdxDU, foldIdxLR, hole);
        //System.out.println(hole);
    }

    /*
     0 1
     2 3

     1 0
     3 2

     2 3
     0 1

     3 2
     1 0



     0 1 2 3
     1 0 3 2
     2 3 0 1
     3 2 1 0
     */

    public static void printRes(int DU, int LR, int hole) throws IOException{
        for(int k = 0 ; k < mapLength ; k ++){
            for(int s = 0 ; s < mapLength ; s ++){
                int a = (s % 2);
                int b = (k % 2);
                printHole(a, b, DU, LR, hole);
            }
            bw.newLine();
        }
    }

    public static void printHole(int a, int b, int DU, int LR, int hole) throws IOException{
        int sum = ((b<<1) ^ (DU<<1)) ^ ((a ^ LR));
        bw.write(String.valueOf(hole ^ sum) + " ");
    }

    public static void closeIO() throws IOException{
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}

