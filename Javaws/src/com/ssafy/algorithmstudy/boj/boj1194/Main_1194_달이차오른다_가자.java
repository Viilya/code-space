package com.ssafy.algorithmstudy.boj.boj1194;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_1194_달이차오른다_가자 {





    public static void main(String[] args) throws IOException {
        input();

        BFS(start);

        printRes();
        closeIO();
    }

    private static int N, M;
    private static char map[][];
    private static Point start;

    private static int dx[] = {-1, 0, 1, 0};
    private static int dy[] = {0, 1, 0, -1};
    private static int minPath = Integer.MAX_VALUE;
    public static void BFS(Point point){
//        System.out.println("BFS Started");
        Deque<Point> dq = new ArrayDeque<>();
        dq.add(point);
        boolean isVisited[][][] = new boolean[N][M][1<<8];
        isVisited[point.x][point.y][point.keyPocket] = true;
        while(!dq.isEmpty()){
            Point p = dq.poll();
            //System.out.println("count , x, y : " + p.count + ", " + p.x + ", " + p.y);
            //System.out.println(Integer.toBinaryString(p.keyPocket));
            if(p.count >= minPath) continue;
            for(int k = 0 ; k < 4 ; k++){

                int X = p.x + dx[k];
                int Y = p.y + dy[k];
                if(isMovable(X, Y) && !isVisited[X][Y][p.keyPocket]){
                    //System.out.println("go in to next level : " + X + " " + Y + " " + map[X][Y]);
                    isVisited[X][Y][p.keyPocket] = true;
                    if(isDoor(X, Y)) {
                        //System.out.println("door true");
                        if(isOpenable(X, Y, p.keyPocket)){
                            dq.add(new Point(p.count + 1, p.keyPocket, X, Y));
                        }
                    }
                    else if(isKey(X, Y)) {
                        //System.out.println("key true");
                        if ((p.keyPocket & (1<<(map[X][Y] - 'a'))) != (1<<(map[X][Y] - 'a'))) {
                            dq.add(new Point(p.count +1, p.keyPocket | (1<<(map[X][Y] - 'a')), X, Y));
                            isVisited[X][Y][p.keyPocket | (1<<(map[X][Y] - 'a'))] = true;
                        } else {
                            dq.add(new Point(p.count + 1, p.keyPocket, X, Y));
                        }
                    }
                    else if(map[X][Y] == '1'){
                        minPath = Math.min(minPath, p.count + 1);
                        return;
                    }else{
                        //System.out.println("empty true");
                        dq.add(new Point(p.count + 1, p.keyPocket, X, Y));
                    }
                }
            }
        }
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];


        for(int k= 0 ; k < N ; k ++)
            map[k] = br.readLine().toCharArray();

        for(int k = 0 ; k < N ; k ++){
            for(int s = 0 ; s < M ; s++){
                if(map[k][s] == '0')
                    start = new Point(0, 0, k, s);
            }
        }
    }


    public static boolean isMovable(int x, int y){
        if(x<0 || x >= N || y < 0 || y >= M || map[x][y] == '#') return false;
        return true;
    }

    public static boolean isDoor(int x, int y){
        if(map[x][y] >= 'A' && map[x][y] <= 'F') return true;
        return false;
    }

    public static boolean isKey(int x, int y){
        if(map[x][y] >= 'a' && map[x][y] <= 'f') return true;
        return false;
    }

    public static boolean isOpenable(int x, int y, int keyPocket){
        if(((1<<(map[x][y] -'A')) & keyPocket) == (1<<(map[x][y] -'A'))) return true;
        return false;
    }
    public static void printRes() throws IOException {
        bw.write((minPath!=Integer.MAX_VALUE?minPath:-1) +  "");
//        Runtime.getRuntime().gc();
//        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
//        System.out.println(usedMemory + " bytes");
    }

    public static void closeIO() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
class Point{
    int x;
    int y;
    int count;
    int keyPocket;
    public Point(){};

    public Point(int count, int keyPocket, int x, int y){
        this.x = x; this.y = y; this.keyPocket = keyPocket; this.count = count;
    }
}