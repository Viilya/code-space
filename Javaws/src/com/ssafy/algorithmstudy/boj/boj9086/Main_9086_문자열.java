package com.ssafy.algorithmstudy.boj.boj9086;

import java.io.*;
import java.util.StringTokenizer;

public class Main_9086_문자열 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }

    private static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());

        for(int k = 0 ; k < T ; k ++){
            String s = br.readLine();
            output(s.charAt(0), s.charAt(s.length() - 1));
        }
    }

    private static void output(char a, char b) throws IOException {
        bw.write(a +""+ b);
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
