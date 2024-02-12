package com.ssafy.algorithmstudy.boj.boj25372;

import java.io.*;
import java.util.StringTokenizer;

public class Main_25372 {
    public static void main(String[] args) throws IOException {
        input();
        output();
        closeIO();
    }

    private static void input() throws IOException {
        int N = Integer.parseInt(br.readLine());
        for(int k = 0 ; k < N ; k ++){
            String str = br.readLine();
            if(str.length() >= 6 && str.length() <= 9){
                System.out.println("yes");
            }else{
                System.out.println("no");
            }
        }
    }


    private static void output() throws IOException {
        bw.write("");
        bw.newLine();
    }

    private static void closeIO() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
