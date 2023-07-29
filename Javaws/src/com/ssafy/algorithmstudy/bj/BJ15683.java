package com.ssafy.algorithmstudy.bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

public class BJ15683 {
    public static Stack<Integer> stack = new Stack<>();
    public static int maxCount = Integer.MAX_VALUE;
    public static int[] dx = { -1, 0, 1, 0 };
    public static int[] dy = { 0, 1, 0, -1 };
    public static int[][] dc = {
        {},
        {0},
        {0, 2},
        {0, 1},
        {0, 1, 3},
        {0, 1, 2, 3},
    };
    public static int[] rotationIteration = { 0, 4, 2, 4, 4, 1 };

    public static boolean outOfBoundAndFindWall(int[][] newMap, int N, int M, int curX, int curY) {
        if (curX < 0 || curX >= N)
            return true;
        if(curY < 0 || curY >= M)
            return true;
        if (newMap[curX][curY] == 6)
            return true;
        return false;
    }

    public static void cameraCheckArea(int[][] newMap, int N, int M, int cameraX, int cameraY, int cameraNumber,
            int cameraDir, int dr) {
        int x = cameraX;
        int y = cameraY;
        while (true) {
            x += dx[(cameraDir + dr) % 4];
            y += dy[(cameraDir + dr) % 4];
            if (outOfBoundAndFindWall(newMap, N, M, x, y)) 
                break;
            if(newMap[x][y] == 0)
                newMap[x][y] = 9;
        }
    }

    public static void arrayCopy(int[][] arr1, int[][] arr2, int N, int M) {
        for (int k = 0; k < N; k++) {
            for (int s = 0; s < M; s++)
                arr1[k][s] = arr2[k][s];
        }
    }
    
    public static int countNoCameraZone(int[][] map, int N, int M) {
        int count = 0;
        for (int k = 0; k < N; k++) {
            for (int s = 0; s < M; s++) {
                if (map[k][s] == 0)
                    count++;
            }
        }
        return count;
    }

    public static void cameraDFS(int[][] map, int[][] cameraLoc, int cameraCount, int N, int M, int currCameraLoc) {

        if (cameraCount == currCameraLoc) {
            int count = countNoCameraZone(map, N, M);
            maxCount = Math.min(maxCount, count);
            return;
        }

        int cameraX = cameraLoc[currCameraLoc][0];
        int cameraY = cameraLoc[currCameraLoc][1];
        int cameraNumber = map[cameraX][cameraY];
        
        for (int dr = 0; dr < rotationIteration[cameraNumber]; dr++) {
            int[][] newMap = new int[N][M];
            arrayCopy(newMap, map, N, M);
            for (int cameraDir : dc[cameraNumber]) {
                cameraCheckArea(newMap, N, M, cameraX, cameraY, cameraNumber, cameraDir, dr);
            }
            cameraDFS(newMap, cameraLoc, cameraCount, N, M, currCameraLoc + 1);
        }
    }

    
    public static void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("BJ15683i.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int[][] cameraLoc = new int[8][2];
        int cameraCount = 0;
        for (int k = 0; k < N; k++) {
            st = new StringTokenizer(bf.readLine());
            for (int s = 0; s < M; s++) {
                map[k][s] = Integer.parseInt(st.nextToken());
                if (map[k][s] != 0 && map[k][s] != 6) {
                    cameraLoc[cameraCount][0] = k;
                    cameraLoc[cameraCount++][1] = s;
                }
            }
        }
        cameraDFS(map, cameraLoc, cameraCount, N, M, 0);
        System.out.println(maxCount);
    }
}
