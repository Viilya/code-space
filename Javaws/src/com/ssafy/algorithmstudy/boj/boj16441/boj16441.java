package com.ssafy.algorithmstudy.boj.boj16441;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj16441 {    
    public static char[][] map;
    public static int[] dx = { -1, 0, 1, 0 };
    public static int[] dy = { 0, 1, 0, -1 };
    static boolean[][] visited;
    /**
     * 입력, 출력 부
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("Javaws/src/com/ssafy/algorithmstudy/boj/boj16441/input.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        List<int[]> wolf = new ArrayList();
        for (int k = 0; k < n; k++) {
            map[k] = bf.readLine().toString().toCharArray();
            for (int s = 0; s < m; s++) {
                if (map[k][s]== 'W') {
                    int[] tmp = { k, s };
                    // 늑대 위치 저장 
                    wolf.add(tmp);
                } else if (map[k][s] == '.') {
                    // 초기에 모든 초원을 P로 바꿈
                    map[k][s] =  'P';
                }
            }
        }
        bf.close();
        // printMap(n, m);
        // System.out.println();
        visited = new boolean[n][m];
        for (int k = 0; k < wolf.size(); k++) {
            // 늑대가 지나간 곳 체크
            // 늑대 위치를 하나씩 받아서
            dq.add(wolf.get(k));
            // bfs 실행
            wolfMoves( n, m);
        }
        printMap(n, m);
        Runtime.getRuntime().gc();
        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.print(usedMemory + " bytes");
    }
    
    static Queue<int[]> dq = new LinkedList<int[]>();
    /**
     * BFS 로 늑대가 갈 수 있는 위치를 모두 체크, 변환
     * @param n
     * @param m
     */
    public static void wolfMoves( int n, int m) {
        if (dq.isEmpty()) {
            return;
        }
        
        int[] wolfLoc = dq.poll();
        //System.out.println("WolfLoc > " + wolfLoc[0] + " " + wolfLoc[1]);
        visited[wolfLoc[0]][wolfLoc[1]] = true;
        // 늑대 위치 저장
        for (int k = 0; k < 4; k++) {
            // 상하 좌우 순서대로 탐색
            int x = wolfLoc[0] + dx[k];
            int y = wolfLoc[1] + dy[k];
            if (isValid(n, m, x, y)) { // 이동 가능한 좌표면
                // 그 좌표의 지형 저장
                char tmp = map[x][y];
                int[] loc = { x, y };
                if ((tmp == 'P' || tmp == '.') && !visited[x][y]) { // 깡 초원일 때 P를 .으로 바꿈 
                    visited[x][y] = true; // 지나간 곳 표시 
                    map[x][y] = '.';
                    dq.add(loc); // BFS
                } else if (tmp == 'W' && !visited[x][y]) { // 늑대일 때는 BFS
                    visited[x][y] = true; // 지나간 곳 표시
                    dq.add(loc); // BFS
                } else if (tmp == '+') { // 빙판인 경우 별도의 함수로 넘김
                    moveAndAdd(x, y, k);
                }
            }
        }
        // 재귀로 호출
        wolfMoves( n, m);
    }

    
    public static boolean isValid(int n, int m, int x, int y) {
        if (map[x][y] == '#' || x < 0 || x>= n || y<0 || y>= m) {
            return false;
        }
        return true;
    }
    /**
     * 이동 하던 방향으로 계속 이동하여서 더이상 이동하지 못할 때 BFS에 추가
     * @param x
     * @param y
     * @param dir
     */
    public static void moveAndAdd(int x, int y, int dir) {
        // 초기 위치 저장 
        int X = x;
        int Y = y;
        while (true) {
            // 빙판이 아닌 지형을 만날 때 까지 이동하던 방향으로 계속 이동 
            X += dx[dir];
            Y += dy[dir];
            // 벽을 만나면 벽 만나기 직전 지형에서 멈춤 
            if (map[X][Y] == '#') {
                int[] tmp = { X - dx[dir], Y - dy[dir] };
                // 이미 들렸던 빙판이면 추가 안함                      
                if (visited[X - dx[dir]][Y - dy[dir]])
                    break;
                // dq 추가 
                dq.add(tmp);
                visited[X-dx[dir]][Y-dy[dir]] = true;
                break;
            } else if (map[X][Y] == '.' || map[X][Y] == 'P' ) { // 초원을 만나면 멈추고 . 으로 바꿈
                int[] tmp = { X, Y };
                // 이미 들렸던 초원이면 아무것도 안함
                if (visited[X][Y])
                    break;
                // 초원으로 바꿔줌
                map[X][Y] = '.';
                visited[x][y] = true;
                // dq
                dq.add(tmp);
                break;
            } else if (map[X][Y] == 'W') { // 늑대를 만나면 멈추고 BFS 추가
                int[] tmp = { X, Y };
                // 이미 들렸던 초원이면 아무것도 안함
                if (visited[X][Y])
                    break;   
                visited[x][y] = true;
                // dq 
                dq.add(tmp);
                break;
            }
        }
    }
    
    public static void printMap(int n, int m) {
        for (int k = 0; k < n; k++) {
            for (int s = 0; s < m; s++) {
                System.out.print(map[k][s]);
            } 
            System.out.println();
        }
    }
}