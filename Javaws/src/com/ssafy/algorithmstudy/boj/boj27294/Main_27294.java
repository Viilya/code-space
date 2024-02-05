package com.ssafy.algorithmstudy.boj.boj27294;

import java.util.Scanner;

public class Main_27294 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int time = sc.nextInt();
        int sake = sc.nextInt();
        if(time >= 12 && time <= 16 && sake == 0){
            System.out.println("320");
        }else{
            System.out.println("280");
        }
    }
}
