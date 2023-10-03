package com.ssafy.algorithmstudy.boj.boj11564;

import java.io.*;
import java.util.StringTokenizer;

public class Main_11564_점프왕_최준민 {
    public static void main(String[] args) throws IOException {
        input();
        jump();
        printRes();
        closeIO();
    }
    static long K;
    static long A, B;
    static long jumpCount = 0;
    public static void jump(){
        long aCount = Math.abs(A) /K;
        long bCount = Math.abs(B) /K;
        if(A<=0 && B>=0) {
            jumpCount = aCount + bCount + 1;
        }else{
            long smallerLong = Math.min(Math.abs(A), Math.abs(B));
            if(smallerLong % K == 0){
                smallerLong = 1;
            }else{
                smallerLong = 0;
            }
            jumpCount = Math.abs(aCount - bCount) + smallerLong;
        }

    }


    public static void input() throws IOException {
        st= new StringTokenizer(br.readLine());
        K = Long.parseLong(st.nextToken());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
    }

    public static void printRes() throws IOException {
        bw.write(jumpCount + "");
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
