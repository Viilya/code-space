package com.ssafy.algorithmstudy.boj.boj27959;

import java.util.Scanner;

public class Main_27959 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt() * 100;
        int b = sc.nextInt();
        if(a>=b){
            System.out.println("Yes");

        }else {
            System.out.println("No");
        }
    }
}
