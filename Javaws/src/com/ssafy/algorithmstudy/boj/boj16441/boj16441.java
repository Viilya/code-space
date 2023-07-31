package com.ssafy.algorithmstudy.boj.boj16441;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class boj16441 {    
    public static StringBuilder[] map;
    public static boolean[][] availMap;
    public static int[] dx = { -1, 0, 1, 0 };
    public static int[] dy = { 0, 1, 0, -1 };
    
    public static void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("Javaws/src/com/ssafy/algorithmstudy/boj/boj16441/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new StringBuilder[n];

        List<int[]> wolf = new ArrayList();
        StringBuilder a = new StringBuilder();
        for (int k = 0; k < n; k++) {
            map[k] = new StringBuilder(bf.readLine());
            for (int s = 0; s < m; s++) {
                if (map[k].charAt(s) == 'W') {
                    int[] tmp = { k, s };
                    wolf.add(tmp);
                } else if (map[k].charAt(s) == '.') {
                    map[k].setCharAt(s, 'P');
                }
            }
        }
        // printMap(n, m);
        // System.out.println();
        int[] tmp = new int[2];
        for (int k = 0; k < wolf.size(); k++) {
            boolean[][] visited = new boolean[n][m];
            dq.addLast(wolf.get(k));
            wolfMoves(visited, n, m);
        }
        printMap(n, m);
    }
    
    static Deque<int[]> dq = new ArrayDeque<int[]>();

    public static void wolfMoves(boolean[][] visited, int n, int m) {
        if (dq.isEmpty()) {
            return;
        }
        int[] wolfLoc = dq.pollFirst();
        int wolfX = wolfLoc[0];
        int wolfY = wolfLoc[1];
        for (int k = 0; k < 4; k++) {
            int x = wolfX + dx[k];
            int y = wolfY + dy[k]; 
            if (isValid(n, m, x, y)) {
                char tmp = map[x].charAt(y);
                int[] loc = { x, y };
                // System.out.println("x, y : "+ x+ " " +y);
                // printMap(n, m);
                if ((tmp == 'P' || tmp == '.') && !visited[x][y]) {
                    visited[x][y] = true;
                    map[x].setCharAt(y, '.');
                    dq.addLast(loc);
                } else if (tmp == 'W' && !visited[x][y]) {
                    visited[x][y] = true;
                    dq.addLast(loc);
                } else if (tmp == '+') {
                    moveAndAdd(visited, x, y, k);
                }
                
            }
        }
        wolfMoves(visited, n, m);
    }

    
    public static boolean isValid(int n, int m, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= n || map[x].charAt(y) == '#') {
            return false;
        }
        return true;
    }
    
    public static void moveAndAdd(boolean[][] visited, int x, int y, int dir) {
        int X = x;
        int Y = y;
        while (true) {
            X += dx[dir];
            Y += dy[dir];
            if (map[X].charAt(Y) == '#') {
                int[] tmp = { X - dx[dir], Y - dy[dir] };                           
                if (visited[X - dx[dir]][Y - dy[dir]])
                    break;   
                dq.addLast(tmp);
                visited[X-dx[dir]][Y-dy[dir]] = true;
                break;
            } else if (map[X].charAt(Y) == '.' || map[X].charAt(Y) == 'P' ) {
                int[] tmp = { X, Y };
                if (visited[X - dx[dir]][Y - dy[dir]])
                    break;   
                map[X].setCharAt(Y, '.');
                visited[x][y] = true;
                dq.addLast(tmp);
                break;
            } else if (map[X].charAt(Y) == 'W') {
                int[] tmp = { X, Y };
                if (visited[X - dx[dir]][Y - dy[dir]])
                    break;   
                visited[x][y] = true;
                dq.addLast(tmp);
                break;
            }
        }
    }
    
    public static void printMap(int n, int m) {
        for (int k = 0; k < n; k++) {
            for (int s = 0; s < m; s++) {
                System.out.print(map[k].charAt(s));
            } 
            System.out.println();
        }
    }
}