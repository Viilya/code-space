package com.ssafy.algorithmstudy.boj.boj2961;

import java.io.IOException;
import java.util.Scanner;

public class boj2961 {
    public static int mult[];
    public static int add[];
    public static int n;
    public static int sub = Integer.MAX_VALUE;
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        mult = new int[n];
        add = new int[n];
        for (int k = 0; k < n; k++) {
            mult[k] = sc.nextInt();
            add[k] = sc.nextInt();
        }

        getSub(mult, add, 1, 0, 0, 0);
        System.out.println(sub);
    }
    
    public static void getSub(int[] mult, int[] add, int multed, int added, int cnt, int start) {
        
        if (cnt != 0) {
            sub = Math.min(sub, Math.abs(multed - added));
            if (cnt == n+1) {
                return;
            }
        }

        for (int k = start; k < n; k++) {
            getSub(mult, add, multed * mult[k], added + add[k], cnt + 1, k+1);
        }
    }
}
