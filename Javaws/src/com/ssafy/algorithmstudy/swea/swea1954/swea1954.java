package com.ssafy.algorithmstudy.swea.swea1954;

import java.io.IOException;
import java.util.Scanner;

public class swea1954 {

    public static int[] dx = { 0, 1, 0, -1 };
    public static int[] dy = { 1, 0, -1, 0 };
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int test_case = 1; test_case <= t; test_case++){
            int n = sc.nextInt();
            int[][] map = new int[n][n];
            int d = 0;
            int x = 0, y = 0;
            int count = 1;
            for (int k = 0; k < n * n; k++) {
                if (!isValid(map, n, x, y)){
                    x -= dx[d];
                    y -= dy[d];
                    d += 1;
                    d %= 4;
                    x += dx[d];
                    y += dy[d];
                }
                map[x][y] = count;
                x += dx[d];
                y += dy[d];    
                count ++;
            }
            System.out.println("#" + test_case);

            for (int k = 0; k < n; k++) {
                for (int s = 0; s < n; s++) {
                    System.out.print(map[k][s] + " ");
                }
                System.out.println();
            }
            sc.close();
        }
        
    }

    public static boolean isValid(int[][] map, int n, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= n || map[x][y] != 0) 
            return false;
        return true;
    }
}
