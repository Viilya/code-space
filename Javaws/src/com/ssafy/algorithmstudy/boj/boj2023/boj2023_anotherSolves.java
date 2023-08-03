package com.ssafy.algorithmstudy.boj.boj2023;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj2023_anotherSolves {
    public static boolean[] isNotPrime;
    public static BufferedReader br;
    public static BufferedWriter bw;

    public static void main(String args[]) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int numbers[] = new int[n];
        isNotPrime = new boolean[(int) Math.sqrt(Math.pow(10, n + 1)) + 1];
        isNotPrime[1] = true;
        isNotPrime[0] = true;
        eratosNet(n);
        getDigits(0, n, numbers);
        //System.out.println(Arrays.toString(isNotPrime));
        bw.flush();
        bw.close();
        br.close();
        
    }

    public static void eratosNet(int n) {
        int len = isNotPrime.length;
        for (int k = 2; k < len; k++) {
            if (!isNotPrime[k]) {
                int s = 2;
                while (s * k < len) {
                    isNotPrime[k * s] = true;
                    s++;
                }
            }
        }
    }

    public static void getDigits(int currDigit, int n, int numbers[]) throws IOException {
        if (currDigit == n) {
            for (int k = 0; k < n; k++) {
                bw.write(String.valueOf(numbers[k]));
            }
            bw.newLine();
            return;
        }
        int num = 0;
        for (int k = 0; k < currDigit; k++) {
            num += Math.pow(10, currDigit - k) * numbers[k];
            //System.out.println("num bf get / currDigit > " + num + " " + currDigit);
        }
        for (int k = 0; k <= 9; k++) {
            if (isPrime(num + k)) {
                //System.out.println("prime pass number > " + num + k);
                numbers[currDigit] = k;
                getDigits(currDigit + 1, n, numbers);
            }

        }
    }

    public static boolean isPrime(int num) {
        int len = isNotPrime.length;
        if (num < len) {
            if (isNotPrime[num])
                return false;
            return true;
        }
        double tmp = Math.sqrt(num);
        for (int k = 2; k < tmp; k++) {
            if (isNotPrime[k] == false && num % k == 0)
                return false;
        }
        
        return true;        
    }
}