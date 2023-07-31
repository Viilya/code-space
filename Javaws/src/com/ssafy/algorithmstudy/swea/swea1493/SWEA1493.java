package com.ssafy.algorithmstudy.swea.swea1493;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1493 {
    public static void main(String args[]) throws IOException {
        //System.setIn(new FileInputStream("SWEA1493i.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int t = Integer.parseInt(st.nextToken());
        for (int test_case = 0; test_case < t; test_case++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            int stage1 = findStage(a);
            int stage2 = findStage(b);
            int numADiff = stage1 * (stage1 + 1) / 2 - a;
            int numBDiff = stage2 * (stage2 + 1) / 2 - b;
            int y = 2 + numADiff + numBDiff;
            int x = stage1 * (stage1 + 1) / 2 + stage2 * (stage2 + 1) / 2 - numADiff;
            
        }
    }
    
    public static int findStage(int a) {

        int n = 1;
        while (true) {
            if(a < n * (n+1) / 2)
                return n - 1;
            n++;
        }
    }
}
