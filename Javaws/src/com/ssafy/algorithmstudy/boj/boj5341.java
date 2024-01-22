package com.ssafy.algorithmstudy.boj;

import java.util.*;
public class boj5341{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        while(true){
            int a = sc.nextInt();
            if(a == 0) break;
            long t = a * (a+1) / 2;
            System.out.println(t + "");
        }
    }
}
