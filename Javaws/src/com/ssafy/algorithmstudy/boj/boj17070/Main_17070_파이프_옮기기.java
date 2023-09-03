package com.ssafy.algorithmstudy.boj.boj17070;

import java.io.*;
import java.util.StringTokenizer;

public class Main_17070_파이프_옮기기 {
    public static void main(String[] args) throws IOException {
        input();
        DP();
        printRes();
        closeIO();
    }
    public static int N, M;
    public static int map[][];
    public static PipeCount pipe[][];

    public static void DP(){
        for(int k = 0 ; k < N ; k ++){
            for (int s = 1 ; s < N ; s++){
                if(pipe[k][s].a != 0){
                    if(s+1 < N && map[k][s+1] == 0){
                        pipe[k][s+1].a += pipe[k][s].a;
                    }
                    if(k+1 < N && s+1 < N && map[k][s+1] == 0 && map[k+1][s] == 0 && map[k+1][s+1] == 0){
                        pipe[k+1][s+1].c += pipe[k][s].a;
                    }
                }
                if(pipe[k][s].b != 0){
                    if(k+1 < N && map[k+1][s] == 0){
                        pipe[k+1][s].b += pipe[k][s].b;
                    }
                    if(k+1 < N && s+1 < N && map[k][s+1] == 0 && map[k+1][s] == 0 && map[k+1][s+1] == 0){
                        pipe[k+1][s+1].c += pipe[k][s].b;
                    }
                }
                if(pipe[k][s].c != 0){
                    if(s+1 < N && map[k][s+1] == 0){
                        pipe[k][s+1].a += pipe[k][s].c;
                    }
                    if(k+1 < N &&map[k+1][s] == 0){
                        pipe[k+1][s].b += pipe[k][s].c;
                    }
                    if(k+1 < N && s+1 < N && map[k][s+1] == 0 && map[k+1][s] == 0 && map[k+1][s+1] == 0){
                        pipe[k+1][s+1].c += pipe[k][s].c;
                    }
                }

            }
        }
    }



    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        map =new int[N][N];
        pipe =new PipeCount[N][N];
        for(int k = 0 ; k < N ; k ++){
            st = new StringTokenizer(br.readLine());
            for(int  s= 0 ; s < N ; s++){
                map[k][s] = Integer.parseInt(st.nextToken());
                pipe[k][s]= new PipeCount();
            }
            pipe[0][1].a = 1;
        }

    }

    public static void printRes() throws IOException {
        bw.write(String.valueOf(pipe[N-1][N-1].sum()));
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

class PipeCount{
    public int a;
    public int b;
    public int c;

    public int sum(){
        return a+ b+ c;
    }
}