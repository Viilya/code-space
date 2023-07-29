package com.ssafy.algorithmstudy.bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class BJ2527 {
    public static char checkRectRelationship(int[][] f, int[][] s) {
        if ((f[0][0] == s[1][0] && f[0][1] == s[1][0]) || (f[1][0] == s[0][0] && f[1][1] == s[0][1])
                || (f[0][0] == s[1][0] && f[1][1] == s[0][1]) || (f[1][0] == s[0][0] && f[0][1] == s[1][1]))
            return 'c';
        
        return 'a';
    }    
    public static void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("BJ2527i.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        int[][] firstRect = new int[2][2];
        int[][] secondRect = new int[2][2];
        for (int t = 0; t < 4; t++) { // four line
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int k = 0 ; k < 4;  k ++)
                firstRect[k/2][k%2] = Integer.parseInt(st.nextToken());
            for(int k = 0 ; k < 4;  k ++)
                secondRect[k/2][k%2] = Integer.parseInt(st.nextToken());
            
            char result = checkRectRelationship(firstRect, secondRect);
            System.out.println(result);
        }
    }
}
