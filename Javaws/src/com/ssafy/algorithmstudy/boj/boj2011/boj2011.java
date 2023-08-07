package com.ssafy.algorithmstudy.boj.boj2011;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj2011 {
    public static String _line;
    public static BufferedWriter bw;
    public static BufferedReader br;
    public static int result = 0;
    public static int[] dp;
    public static void main(String args[]) throws IOException {
        input();
        runDp();
        printRes();
    }

    public static void input() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        _line = br.readLine();
    }

    public static void runDp() {
        int len = _line.length();
        dp = new int[len];
        if (_line.charAt(0) == '0')
            return;
        else
            dp[0] = 1;
        for (int k = 1; k < len; k++) {
            if (!checkDp(k))
                return;
        }
        result = dp[len - 1];
    }

    public static boolean checkDp(int idx) {
        int num = Integer.parseInt(String.valueOf(_line.charAt(idx)));
        int bfNum = Integer.parseInt(String.valueOf(_line.charAt(idx - 1)));
        //System.out.println("bfNum / num > " + bfNum + " " + num );
        if (num == 0) {
            
            if (bfNum == 1 || bfNum == 2) {
                if (idx == _line.length() - 1) {
                    dp[idx] = dp[idx - 2];
                    return true;
                }
                dp[idx] = dp[idx-1];
            } else {
                return false;
            }
        } else {
            int tmp = bfNum * 10 + num;
            if (tmp >= 10 && tmp <= 26) {
                if (idx < 2) {
                    dp[idx] = dp[idx - 1] + 1;
                    return true;
                }
                dp[idx] = dp[idx - 1] + dp[idx - 2];
            } else 
                dp[idx] = dp[idx - 1];
        }
        return true;
    }    
    public static void printRes() throws IOException{
        bw.write(String.valueOf(result % 1000000));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
