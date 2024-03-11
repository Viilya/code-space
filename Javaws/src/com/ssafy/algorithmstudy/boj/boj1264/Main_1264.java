package com.ssafy.algorithmstudy.boj.boj1264;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1264 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }
    public static char m[] = {'a', 'e', 'i', 'o', 'u', 'A' ,'E', 'O', 'I', 'U'};

    private static void input() throws IOException {

        while(true){
            int COUNT = 0;
            char line[] = br.readLine().toCharArray();
            if(line[0] == '#') break;
            for(int k = 0 ; k < line.length; k++){
                for(int s = 0 ; s < 10 ; s++ ){
                    if(line[k] == m[s]){
                        COUNT += 1;
                        break;
                    }
                }
            }
            output(COUNT);
        }
    }


    private static void output(int COUNT) throws IOException {
        bw.write(COUNT + "");
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
