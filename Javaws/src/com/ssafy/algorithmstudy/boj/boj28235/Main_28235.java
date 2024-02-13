package com.ssafy.algorithmstudy.boj.boj28235;

import java.io.*;
import java.util.StringTokenizer;

public class Main_28235 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }

    private static void input() throws IOException {
        String str = br.readLine();
        if(str.equals("SONGDO")){
            output("HIGHSCHOOL");
        }else if(str.equals("CODE")) {
            output("MASTER");
        }else if(str.equals("2023")){
            output("0611");
        }else{
            output("CONTEST");
        }
    }


    private static void output(String msg) throws IOException {
        bw.write(msg + "");
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
