package com.ssafy.algorithmstudy.boj.boj1992;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1992_쿼드트리 {
    public static void main(String[] args) throws IOException {
         input();
         divideAndPrint(0,0,N);
         printRes();

    }

    public static void printRes() throws IOException{
        bw.flush();
        bw.newLine();
        bw.close();
        br.close();
    }
    public static int N;
    public static int Map[][];
    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        Map = new int[N][N];
        for(int k = 0 ; k < N;k++){
            for(int s = 0 ; s< N ; s++){
                Map[k][s] = br.read() -'0';
            }
            br.read();
        }
    }
    public static void divideAndPrint(int x, int y, int len) throws IOException{
        if(isFull(x, y, len)) {
            bw.write(String.valueOf(Map[x][y]));
        }else{
            bw.write('(');
            divideAndPrint(x, y, len / 2);
            divideAndPrint(x, y+len/2, len / 2);
            divideAndPrint(x+len/2, y, len / 2);
            divideAndPrint(x+len/2, y+len/2, len / 2);
            bw.write(')');
        }
    }

    public static boolean isFull(int x, int y ,int len){
        for(int k = x ; k < x + len ; k++){
            for(int s = y ; s < y + len ; s++)
                if(Map[x][y] != Map[k][s]) return false;
        }
        return true;
    }



    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
