package com.ssafy.algorithmstudy.boj.boj2239;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2239_스도쿠 {
    public static void main(String[] args) throws IOException {
        input();

        fillSudoku(0, 0);

        printRes();
        closeIO();
    }
    public static int colCount[] = new int[9];
    public static int rowCount[] = new int[9];
    public static int boxCount[][] = new int[3][3];
    public static int sudoku[][] = new int[9][9];
    public static int resultSudoku[][] = new int[9][9];
    public static boolean isSolved = false;

    public static void fillSudoku(int x, int y){

        boolean solveFlag = true;
        for(int k = 0 ; k < 9 ; k ++){
            if(((colCount[k]) ^ ((1<<10) - 2)) != 0){
                solveFlag = false;
            }
        }
        if(!isSolved && solveFlag){
//            System.out.println("Entered solve session");
            isSolved = true;
            for(int k = 0 ; k < 9 ; k ++){
                resultSudoku[k] = Arrays.copyOf(sudoku[k], 9);
            }
            return;
        }
        for(int k = 0 ; k < 9 ; k ++){
            for(int s = 0 ; s < 9 ; s++){
                if(sudoku[k][s] == 0){
                    for(int t = 1 ; t<= 9 ; t++){
                        if (checkValidNumber(k, s, t)) {
                            //System.out.println("k / s / t : " + k + " / " + s + " / " + t);
                            fillCount(k, s, t);
                            sudoku[k][s] = t;
                            fillSudoku(k, s);
                            removeCount(k, s, t);
                            sudoku[k][s] = 0;
                            if(isSolved) return;
                        }
                    }
                    return;
                }
            }
        }
    }

    public static boolean checkValidNumber(int x, int y, int t){
        int bit = 1<<t;
        if(((colCount[x] & bit) != bit ) && ((rowCount[y] & bit) != bit) && ((boxCount[x/3][y/3] & bit) != bit)){
            return true;
        }return false;
    }



    public static void input() throws IOException {
        for(int k = 0 ; k < 9 ; k ++){
            sudoku[k] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            for(int s = 0 ; s < 9 ; s++){
                if(sudoku[k][s] > 0){
                    fillCount(k, s, sudoku[k][s]);
                }
            }
        }

//        for(int k = 0 ; k < 9 ; k++){
//            System.out.println(Integer.toBinaryString(rowCount[k]));
//        }
//        System.out.println();
//        for(int k = 0 ; k < 9 ; k++){
//            System.out.println(Integer.toBinaryString(colCount[k]));
//        }
//        System.out.println();
//        for(int k = 0 ; k < 9 ; k++){
//            System.out.println(Integer.toBinaryString(boxCount[k/3][k%3]));
//        }
//        System.out.println();
    }

    public static void fillCount(int k, int s, int t){
        colCount[k] = (colCount[k] | (1<<t));
        rowCount[s] = (rowCount[s] | (1<<t));
        boxCount[k/3][s/3] = (boxCount[k/3][s/3] | (1<<t));
    }

    public static void removeCount(int k, int s, int t){
        colCount[k] = colCount[k] ^ (1<<t);
        rowCount[s] = rowCount[s] ^ (1<<t);
        boxCount[k/3][s/3] = boxCount[k/3][s/3] ^ (1<<t);
    }

    public static void printRes() throws IOException {
        for(int k = 0 ; k < 9 ; k ++){
            for(int s= 0 ; s< 9 ; s++){
                bw.write(String.valueOf(resultSudoku[k][s]));
            }
            bw.newLine();
        }
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
