package com.ssafy.algorithmstudy.boj.boj25381;

import java.io.*;
import java.util.StringTokenizer;

public class Main_25381_ABBC_alt2 {
    public static void main(String[] args) throws IOException {
        input();

        remove();

        //System.out.println(sb);

        printRes();
        closeIO();
    }
    public static char str[];
    public static int abCount = 0;
    public static int bcCount = 0;

    public static void remove(){
        int bCount = 0;
        int aCount = 0;
        int strlen = str.length;
        for(int k = 0 ; k < strlen ; k++){
            if(str[k] == 'B'){
                bCount ++;
                if(aCount > 0){
                    aCount --;
                    bCount --;
                    abCount ++;
                }
            }
            else if(str[k] == 'A'){
                aCount ++;
            }
            else if(str[k] == 'C'){
                if(bCount > 0){
                    bCount --;
                    bcCount ++;
                }
                else if(bCount == 0 && abCount > 0){
                    aCount ++;
                    abCount --;
                    bcCount ++;
                }

            }
        }
    }

    public static void input() throws IOException {
        str = br.readLine().toCharArray();
    }

    public static void printRes() throws IOException {
        bw.write( (abCount + bcCount) + "");
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
