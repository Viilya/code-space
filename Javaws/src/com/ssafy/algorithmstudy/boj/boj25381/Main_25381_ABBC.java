package com.ssafy.algorithmstudy.boj.boj25381;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_25381_ABBC {
    public static void main(String[] args) throws IOException {
        input();

        makeCount();


        System.out.println(aCount + " / " + cCount);
        System.out.println(Arrays.toString(bCount));
        calcCount();

        printRes();
        closeIO();
    }

    private static void calcCount() {
        if(bCount[0] > aCount){
            maxCount += aCount;
            aCount = 0;
        }else{
            aCount -= bCount[0];
            maxCount += bCount[0];
        }
        if(bCount[1] > cCount){
            maxCount += cCount;
            cCount = 0;
        }else{
            cCount -= bCount[1];
            maxCount += bCount[1];
        }

        if(bCount[2] > aCount + cCount){
            maxCount += aCount + cCount;
        }else{
            maxCount += bCount[2];
        }

    }

    public static String str;
    public static StringBuilder sb;
    public static int maxCount = 0;
    public static int acCount[];

    public static int aCount = 0;
    public static int cCount = 0;
    public static int bCount[] = new int[3]; // a, c, ac
    public static void makeCount(){
        boolean flag = false;
        for(int k = 0 ; k < sb.length() ; k ++){
            if(str.charAt(k) == 'A'){
                aCount ++;
                if(flag) continue;
                for(int s= k + 1 ; s<sb.length() ; s++){
                    if(str.charAt(s) == 'B'){
                        acCount[s] = acCount[s] | 1;
                    }
                }
                flag = true;
            }
        }
        flag = false;
        for(int k = sb.length() - 1 ; k >= 0 ; k --){
            if(str.charAt(k) == 'C') {
                cCount++;
                if (flag) continue;
                for (int s = k - 1; s >= 0; s--) {
                    if (str.charAt(s) == 'B') {
                        acCount[s] = acCount[s] | 2;
                    }
                }
                flag = true;
            }
        }

        for(int k = 0 ; k < sb.length(); k++){
            if(acCount[k] > 0) {
                bCount[acCount[k] - 1] ++;
            }
        }
    }


    public static void input() throws IOException {
        str = br.readLine();
        sb = new StringBuilder().append(str);
        acCount = new int[sb.length()];
    }

    public static void printRes() throws IOException {
        bw.write(maxCount + "");
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
