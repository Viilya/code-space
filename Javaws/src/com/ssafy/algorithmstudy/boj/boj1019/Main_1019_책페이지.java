package com.ssafy.algorithmstudy.boj.boj1019;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1019_책페이지 {
    public static void main(String[] args) throws IOException {
        input();
        printRes();
        closeIO();
    }

    public static int count[] = new int [10];
    public static void input() throws IOException {
            st= new StringTokenizer(br.readLine());
            cutBigChunk(Integer.parseInt(st.nextToken()));
            count[0] --;
    }

    public static void cutBigChunk(int a){
        int pow = 1;
        while(true){
            if(a / pow == 0) {
                pow /= 10;
                break;
            }
            pow *= 10;
        }
        if(a >= 10){
            cutBigChunk( a - 1 - a%pow);
            cutSmallChunk(a, pow);
        }
        else{
            for(int k = 1 ; k <= a; k++){
                count[k] ++;
            }
        }
    }

    public static void cutSmallChunk(int a, int pow){
        count[a / pow] += (a % pow + 1);
        count[0] += ((a % pow) / (pow/10)) + 1 ;
        System.out.println(String.valueOf(((a % pow) / (pow/10)) + 1 ));
        cutBigChunk(a % pow);
    }

    public static void printRes() throws IOException {
        bw.write(Arrays.toString(count));
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
