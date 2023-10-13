package com.ssafy.algorithmstudy.boj.boj2661;

import java.io.*;

public class Main_2661_좋은수열 {
    public static void main(String[] args) throws IOException {
        input();

        makeGoodSeq();

        closeIO();
    }
    public static int N ;


    /**
     *
     * 1
     * 12
     * 121
     * 1213
     * 12131
     * 121312
     * 1213121
     * 12131231
     * 121312313
     * 1213123132
     * 12131231321
     */

    public static void makeGoodSeq() throws IOException {
        int seq[] = new int[N];
        int possibleNumber[][] = new int[N][4];
        seq[0] = 1;
        for(int k = 1 ; k < N ; k ++){
            possibleNumber[k][seq[k-1]] = 1;
            getNumber(seq, possibleNumber, k);
        }

        printRes(seq);

    }

    private static void getNumber(int[] seq, int[][] possibleNumber, int c) {
        boolean flag = false;
        for(int k = 1 ; k < 4; k ++){
            if(possibleNumber[c][k] == 0){
                if(isPossible(seq, possibleNumber, c, k)){
                    seq[c] = k;
                    flag = true;
                    break;
                }
            }
        }
        if(!flag){
            possibleNumber[c-1][seq[c-1]] = 1;
            possibleNumber[c][seq[c-1]] = 0;
            getNumber(seq, possibleNumber, c-1);
            possibleNumber[c][seq[c-1]] = 1;
            getNumber(seq, possibleNumber, c);
        }
    }

    private static boolean isPossible(int seq[], int PossibleNumber[][], int c, int tar) {

        for (int k = c; k >= 0; k--) {
            if (seq[k] == tar) {
                boolean flag = false;
                for (int s = 1; s < c - k; s++) {
                    if (k - s>= 0) {
                        if (seq[k - s] != seq[c - s]) {
                            flag = true;
                        }
                    } else {
                        flag = true;
                    }
                }

                if(!flag) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    public static void printRes(int seq[]) throws IOException {
        for(int k = 0 ; k < seq.length; k++){
            bw.write(String.valueOf(seq[k]));
        }
    }

    public static void closeIO() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

}

