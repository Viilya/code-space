package com.ssafy.algorithmstudy.boj.boj15964;

import java.util.Scanner;

public class Main_15964 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        long ans = (long)(a + b) * (long)(a-b);

        System.out.println(ans + "");


    }
}
