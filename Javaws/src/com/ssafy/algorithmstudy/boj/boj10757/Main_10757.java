package com.ssafy.algorithmstudy.boj.boj10757;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main_10757 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BigInteger b1 = new BigInteger(st.nextToken());
        BigInteger b2 = new BigInteger(st.nextToken());
        b1 = b1.add(b2);
        System.out.println(b1);
    }
}
