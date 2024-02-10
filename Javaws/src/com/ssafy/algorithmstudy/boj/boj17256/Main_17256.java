package com.ssafy.algorithmstudy.boj.boj17256;


import java.util.Scanner;

public class Main_17256 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[3];
        int arr2[]  = new int[3];
        for(int k = 0; k < 3 ; k++){
            arr[k] = sc.nextInt();
        }

        for(int k = 0 ; k < 3; k ++){
            arr2[k] = sc.nextInt();
        }

        System.out.println((arr2[0] - arr[2]) + " " + (arr2[1] / arr[1])+ " " + (arr2[2] - arr[0]) );

    }
}
