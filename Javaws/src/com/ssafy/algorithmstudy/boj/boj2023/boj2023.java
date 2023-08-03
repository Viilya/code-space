package com.ssafy.algorithmstudy.boj.boj2023;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj2023 {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static void main(String args[]) throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int numbers[] = new int[n];
        getDigits(0, n, numbers);
        bw.flush();
        bw.close();
        br.close();
    }

    public static void getDigits(int currDigit, int n, int numbers[]) throws IOException {
        if(currDigit == n){
            for(int k = 0 ; k < n; k ++){
                bw.write(String.valueOf(numbers[k]));
            }
            bw.newLine();
            return;
        }
        int num = 0;
        for (int k = 0; k < currDigit ; k++) {
            num += Math.pow(10, currDigit - k) * numbers[k];
            //System.out.println("num bf get / currDigit > " + num + " " + currDigit);
        }
        for (int k = 0; k <= 9; k++) {
            if (isPrice(num+k)) {
                numbers[currDigit] = k;
                getDigits(currDigit + 1, n, numbers);
            }

        }

    }

    public static boolean isPrice(int num) {
        //System.out.println(num);
        if (num == 1 || num == 0) {
            return false;
        }
        for (int k = 2; k <= Math.sqrt(num); k++) {
            if (num % k == 0) {
                return false;
            }
        }
        return true;        
    }
}
