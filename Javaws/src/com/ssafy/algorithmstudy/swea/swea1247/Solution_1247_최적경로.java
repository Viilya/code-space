package com.ssafy.algorithmstudy.swea.swea1247;

import java.util.Scanner;

public class Solution_1247_최적경로 {
    public static int dfs(int[][] map, int[] visited, int curr, int n){
        int nonvisitedFlag = 0;
        int temp = 0;
        for(int k =0;k<n;k++){
            if(visited[k] == 0){
                nonvisitedFlag += 1;
                temp = k;
            }
        }
        if(nonvisitedFlag == 1){
            return Math.abs(map[curr][0] - map[temp][0]) +
                    Math.abs(map[curr][1] - map[temp][1]) + Math.abs(map[temp][0] - map[1][0]) + Math.abs(map[temp][1] - map[1][1]);
        }
        int res = 10000;
        for(int k =0;k<n;k++){
            if(visited[k] == 0){
                int[] tempvisited = visited.clone();
                tempvisited[k] = 1;
                //System.out.println("#CP4");
                int sum = dfs(map, tempvisited, k, n);
                res = Math.min(res, sum + Math.abs(map[curr][0] - map[k][0]) + Math.abs(map[curr][1] - map[k][1]));
                //System.out.println("#CP5");
                tempvisited = null;
            }
        }
        //System.out.println("#CP6");
        return res;
    }
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T; T=sc.nextInt();
        for(int test_case = 1; test_case <= T; test_case++){
            int[][] map = new int[12][2];
            int n; n = sc.nextInt();
            for(int k=0;k<n+2;k++){
                int x; x = sc.nextInt();
                int y; y = sc.nextInt();
                map[k][0] = x; map[k][1] = y;
            }
            int res = 10000;
            int[] visited = {1,1,0,0,0,0,0,0,0,0,0,0};

            //for(int k = 0 ; k < n+2 ; k ++){
            //    System.out.println("[ " + map[k][0] + ", " + map[k][1] + " ]");
            //}
            res = Math.min(res, dfs(map, visited, 0, n+2));
            System.out.println("#" + test_case + " " + res);
        }
    }
}
