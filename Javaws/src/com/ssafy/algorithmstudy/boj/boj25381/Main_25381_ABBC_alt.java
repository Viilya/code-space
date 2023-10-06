package com.ssafy.algorithmstudy.boj.boj25381;

import java.io.*;
import java.util.StringTokenizer;

public class Main_25381_ABBC_alt {
    public static void main(String[] args) throws IOException {
        input();

        removeC();
        removeA();

        //System.out.println(sb);

        printRes();
        closeIO();
    }
    public static String str;
    public static StringBuilder sb;
    public static int count = 0;

    public static void removeC(){
        int strLen = sb.length();
        for(int k = 0; k <strLen ; k++){
            if(sb.charAt(k) == 'B'){
                for(int s = k+1; s < strLen; s++){
                    if(sb.charAt(s) == 'C'){
                        sb.deleteCharAt(s);
                        sb.deleteCharAt(k);
                        k -= 1;
                        strLen -= 2;
                        count ++;
                        break;
                    }
                }
            }
        }
    }

    public static void removeA(){
        int strLen = sb.length();
        for(int k = 0; k <strLen ; k++){
            if(sb.charAt(k) == 'A'){
                for(int s = k+1; s < strLen; s++){
                    if(sb.charAt(s) == 'B'){
                        sb.deleteCharAt(s);
                        sb.deleteCharAt(k);
                        k -= 1;
                        strLen -= 2;
                        count ++;
                        break;
                    }
                }
            }
        }
    }

    public static void input() throws IOException {
        str = br.readLine();
        sb = new StringBuilder().append(str);
    }

    public static void printRes() throws IOException {
        bw.write(count + "");
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
