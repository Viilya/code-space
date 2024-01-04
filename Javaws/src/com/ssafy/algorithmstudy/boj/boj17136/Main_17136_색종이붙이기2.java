package com.ssafy.algorithmstudy.boj.boj17136;

import java.io.*;
import java.util.Arrays;

public class Main_17136_색종이붙이기2 {

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

        comb(0, 0);

    }

    public static void comb(int curr_x, int curr_y){

        for(int k = curr_x; k < 10 ; k ++) {
            for (int s = 0; s < 10; s++) {
                if (map[k][s] == 1) {
                    setSize(k, s);
                }
            }
        }

    }

    public static void setSize(int x, int y){
        for(int k = 5; k > 0 ; k --){
            if(checkGluable(x, y, k)){
                gluePaper(x, y, k);
                paper_count[k] --;
                comb(x, y);
                paper_count[k] ++;
                removePaper(x, y, k);
            }
        }
    }

    private static boolean checkGluable(int x, int y, int size) {
        if(x + size > 10 || y + size > 10) return false;
        if(paper_count[size] == 0) return false;
        for(int k = x ; k < x + size ; k ++){
            for(int s = y;  s < y + size; s++){
                if(map[k][s] == 0) return false;
            }
        }
        return true;
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

    public static void gluePaper(int x, int y, int size) {
        for(int k = x ; k < x + size ; k ++){
            for(int s = y;  s < y + size; s++) {
                map[k][s] = 0;
            }
        }
    }

    public static void removePaper(int x, int y, int size) {
        for(int k = x ; k < x + size ; k ++){
            for(int s = y;  s < y + size; s++) {
                map[k][s] = 1;
            }
        }
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
