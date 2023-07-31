package com.ssafy.algorithmstudy.boj.boj1592;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BJ1592 {
    public static void main(String args[]) throws IOException {
        int[] circle;
        int n, m, l;
        int currLoc = 0;
        int cnt = 0;
        System.setIn(new FileInputStream("BJ1592i.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        circle = new int[n];

        while (true) {
            circle[currLoc]++;
            if (circle[currLoc] == m)
                break;
            if (circle[currLoc] % 2 == 1) {
                currLoc += l;
            } else {
                currLoc -= l;
            }
            if (currLoc < 0) {
                currLoc = n + currLoc;
            }
            if (currLoc >= n) {
                currLoc = currLoc - n;
            }
            cnt++;
        }
        System.out.println(cnt);
    }
}
