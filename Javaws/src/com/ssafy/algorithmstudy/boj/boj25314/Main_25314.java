package com.ssafy.algorithmstudy.boj.boj25314;

import java.util.Scanner;

public class Main_25314 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int longCount = a / 4 + ((a%4==0)?0:1);
        for(int k = 0; k  < longCount ; k ++){
            System.out.print("long ");
        }
        System.out.println("int");
    }
}
