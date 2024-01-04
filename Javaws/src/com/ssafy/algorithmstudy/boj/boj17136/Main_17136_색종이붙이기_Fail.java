package com.ssafy.algorithmstudy.boj.boj17136;

import java.io.*;
import java.util.Arrays;

public class Main_17136_색종이붙이기_Fail {

    public static void main(String args[]) throws IOException {
        input();
        solve();
        output();
        IOclose();
    }

    public static int map[][] = new int [10][10];
    public static int result_count = 0;
    public static int paper_count[] = {0, 5,5,5,5,5};

    public static void input() throws IOException {
        for(int k = 0 ; k < 10 ; k ++){
            map[k] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }

    public static void solve() {
        for(int k = 5 ; k > 0; k --){
            gluePaper(k); // k is paperSize
        }
        checkValidity();
    }

    public static void printMap(){
        System.out.println(result_count + "");
        for(int k = 0 ; k < 10 ; k ++){
            for(int s= 0 ; s< 10 ; s++){
                System.out.print(map[k][s] + " " );
            }
            System.out.println();
        }
    }

    public static void gluePaper(int size){
        for(int k = 0 ; k<  10 ; k ++ ){
            for (int s = 0 ; s < 10 ; s++){
                if(map[k][s] == 1){
                    checkGluable(k, s, size);
                }
                printMap();
                if(paper_count[size] == 0){
                    return;
                }
            }
        }
    }

    public static void checkGluable(int x, int y, int size){

        for(int k = x ; k< x + size ; k ++ ){
            for (int s = y ; s < y + size ; s++){
                if(isBoundaryNotValid(k, s) || map[k][s] == 0){
                    return;
                }
            }
        }
        for(int k = x ; k<  x + size ; k ++ ){
            for (int s = y ; s < y + size ; s++){
                map[k][s] = 0;
            }
        }
        result_count += 1;
        paper_count[size] --;
    }

    public static boolean isBoundaryNotValid(int x, int y){
        if(x >= 10 || y >= 10) return true;
        return false;
    }

    public static void checkValidity() {
        for(int k = 0 ; k < 10; k ++){
            for(int s = 0 ; s< 10 ; s++){
                if(map[k][s] == 1){
                    result_count = -1;
                    return;
                }
            }
        }
    }

    public static void output() throws IOException{
        bw.write(result_count + "");
        bw.flush();
    }

    public static void IOclose() throws IOException {
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
}
