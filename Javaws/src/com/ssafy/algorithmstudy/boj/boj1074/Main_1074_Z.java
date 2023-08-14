package com.ssafy.algorithmstudy.boj.boj1074;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1074_Z {
    public static int N, r, c;
    public static int count = 0;
    public static void main(String args[])throws IOException{
        input();
        runZ(0, 0, (int)Math.pow(2, N));
        printRes();
    }
    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
    }

    public static void runZ(int x, int y, int size){
        if(size == 1){
            return;
        }
        if(r < x + size / 2 && c < y + size / 2){
            runZ(x, y, size/2);
        }else if( r < x + size / 2){
            count += (size / 2) * (size / 2);
            runZ(x, y+size/2, size/2);
        }else if( c < y + size / 2){
            count += (size / 2) * (size / 2) * 2;
            runZ(x + size/2, y, size/2);
        }else{
            count += (size / 2) * (size / 2) * 3;
            runZ(x + size/2, y + size/2, size/2);
        }
    }

    public static void printRes() throws IOException{
        bw.write(String.valueOf(count));
        bw.newLine();
        bw.close();
        br.close();
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
