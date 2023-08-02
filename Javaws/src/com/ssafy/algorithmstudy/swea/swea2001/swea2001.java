package com.ssafy.algorithmstudy.swea.swea2001;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea2001 {
    public static void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("Javaws/src/com/ssafy/algorithmstudy/swea/swea2001/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        for(int test_case = 1; test_case <= t ; test_case ++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int [][] map = new int[n][n];
            for ( int k = 0 ; k < n ; k ++){
                st = new StringTokenizer(br.readLine());
                for (int s= 0 ; s< n ; s ++){
                    map[k][s] = Integer.parseInt(st.nextToken());
                }
            }
            int max = 0;
            for(int k = 0 ; k <= n - m ; k ++){
                for ( int s = 0 ; s <= n - m ; s ++){
                    max = Math.max(getMax(map, n, m, k, s), max);
                }
            }
            System.out.println("#" + test_case + " " + max);
            
        }
        br.close();
    }

    public static int getMax(int[][] map, int n, int m, int x, int y) {
        int sum = 0;
        for (int k = x; k < x + m; k++) {
            for (int s = y; s < y + m; s++) {
                sum += map[k][s];
            }
        }
        return sum;    
    }
}
