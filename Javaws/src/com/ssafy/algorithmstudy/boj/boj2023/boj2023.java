package com.ssafy.algorithmstudy.boj.boj2023;

import java.util.Scanner;

public class boj2023 {
    boolean[] primes = new boolean[10000];
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int numbers[] = new int[n];
        getDigits(0, n, numbers);
    }

    public static void getDigits(int currDigit, int n, int numbers[]) {
        if(currDigit == n){
            for(int k = 0 ; k < n; k ++){
                System.out.print(numbers[k]);
            }
            System.out.println();
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
