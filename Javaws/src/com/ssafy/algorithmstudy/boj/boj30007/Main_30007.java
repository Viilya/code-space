package com.ssafy.algorithmstudy.boj.boj30007;

import java.util.Scanner;

public class Main_30007 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int k = 0 ; k < t ; k ++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            System.out.println((a * (c-1) + b) + "");
        }
    }
}
