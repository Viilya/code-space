package com.ssafy.algorithmstudy.boj.boj2845;

import java.util.Scanner;

public class Main_2845_Something {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int P = sc.nextInt();
        int arr = N * P;
        for(int k =0 ; k < 5; k ++){
            System.out.print(sc.nextInt() - arr + " ");
        }
    }
}
