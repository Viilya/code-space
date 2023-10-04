package com.ssafy.algorithmstudy.boj.boj17472;

import java.io.*;
import java.util.*;

public class Main_17472_다리만들기2 {
    public static void main(String[] args) throws IOException {
        input();

        labelIsland();
        findConstructableLand();
        Prim();

        printRes();
        closeIO();
    }

    public static int N, M;
    public static int map[][];
    public static int adjList[][]; // 인접리스트 행렬

    public static int dx[] = {-1, 0, 1, 0};
    public static int dy[] = {0, 1, 0 ,-1};
    public static int minLen = 0;

    /**
     * 섬을 숫자로 분류하여 저장하는 함수, '2'부터 시작해서 저장!
     */
    public static void labelIsland(){
        int label = 2;
        for(int k = 0 ; k < N; k ++){
            for(int s= 0 ; s< M ; s++){
                if(map[k][s] == 1){ // 새로 찾은 땅이면 labeling
                    landBFS(k, s, label);
                    label++;
                }
            }
        }

        adjList = new int[label-2][label-2];
    }

    /**
     * 새로 찾은 섬의 위치를 받아 그 점 부터 이어진 땅을 label로 바꿔준다.
     * @param a
     * @param b
     * @param label
     */
    public static void landBFS(int a, int b, int label){
        Point p = new Point(a, b);
        Deque<Point> dq = new ArrayDeque<>();
        dq.add(p);


        while(!dq.isEmpty()){
            Point c = dq.poll();
            map[c.x][c.y] = label;

            for(int k = 0 ; k < 4;  k++){
                int X = c.x + dx[k];
                int Y = c.y + dy[k];

                if(isNewLand(X, Y)){
                    dq.add(new Point(X, Y));
                    map[X][Y] = label;
                }
            }
        }
    }

    /**
     * 맵을 탐색해서 섬이 나올 경우
     * findBridge 탐색 시작
     */
    public static void findConstructableLand(){
        for(int k = 0 ; k < N ; k++){
            for(int s = 0 ; s < M ; s++){
                if(map[k][s] > 1){
                    findBridge(k, s);
                }
            }
        }
    }

    /**
     * 사방 탐색해서 바다인 경우 buildBridge를 통해 닿는 끝점을 알아 낸다.
     * 만약 받은 Point가 null인 경우 다리 건설 불가능
     * 받은 Point가 섬인 경우 adjlist에 추가
     * @param x
     * @param y
     */
    public static void findBridge(int x, int y){
        for(int k = 0 ; k < 4; k ++){
            int X = x + dx[k];
            int Y = y + dy[k];

            if(canConstructBridge(X, Y)){
                Point dest = buildBridge(x, y, k); // 끝점 포인트 받아오기
                if(dest != null){
                    int len = Math.abs(X-dest.x) + Math.abs(Y - dest.y); // 거리 계산
                    if(len < 2) continue; // 다리 길이가 2 이상인 경우만 확인
                    int land1 =  map[x][y]-2, land2 = map[dest.x][dest.y] -2;
                    if(adjList[ land1 ][ land2 ] == 0) adjList[land1][land2] = len;
                    else{
                        adjList[land1][land2] = Math.min(adjList[land1][land2], len); // 인접리스트에 추가
                    }
                }
            }
        }
    }

    /**
     * 다리 건설
     * 방향으로 쭉 움직여서 섬이 닿는 경우 point를 return
     * 아닌 경우 null을 return
     * @param x
     * @param y
     * @param dir
     * @return
     */
    public static Point buildBridge(int x, int y, int dir){
        int X = x + dx[dir];
        int Y = y + dy[dir];
        int length = 1;
        while(true){
            if(X < 0 || X>= N) return null;
            if(Y < 0 || Y>= M) return null;
            if(map[X][Y] == map[x][y]) return null;
            if(map[X][Y] != 0 && map[X][Y] != map[x][y]){
                return new Point(X, Y);
            }
            X += dx[dir];
            Y += dy[dir];
            length += 1;

        }
    }

    /**
     * 주어진 adjlist를 통해 prim 알고리즘으로 최소 간선 트리 제작
     */
    public static void Prim(){
        PriorityQueue<Point> pq= new PriorityQueue<>();
        int included = 1;
        int islandCount = 1;
        int adjLen = adjList.length;
        // Point를 시작, 끝 처럼 사용
        for(int k = 0 ; k < adjLen; k ++){
            if(adjList[0][k] != 0)
                pq.add(new Point( 0, k, adjList[0][k]));
        }

        while(islandCount < adjLen){
            if(pq.isEmpty()) {
                minLen = -1;
                return;
            }
            Point curr = pq.poll();
            if(((1<<curr.y) & included) != (1<<curr.y)){
                included = (included | (1<<curr.y));
                islandCount ++;
                minLen += curr.w;
                for(int k = 0 ; k < adjLen; k ++){
                    if(adjList[curr.y][k] != 0)
                        pq.add(new Point(curr.y, k, adjList[curr.y][k]));
                }
            }
        }
    }

    /**
     * Point
     */
    static class Point implements Comparable<Point> {
        public int x; // x
        public int y; // y
        public int w; // w(길이)

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        public Point(int x, int y, int w){
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.w, o.w);
        }
    }

    public static boolean isNewLand(int x, int y){
        if(x<0 || x>=N)
            return false;
        if(y<0 || y>=M)
            return false;
        if(map[x][y] != 1){
            return false;
        }
        return true;
    }

    public static boolean canConstructBridge(int x, int y){
        if( x < 0 || x >= N )
            return false;
        if( y< 0 || y>= M)
            return false;
        if(map[x][y] != 0)
            return false;
        return true;
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int k = 0 ;k < N ; k++){
            map[k] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }

    public static void printRes() throws IOException {

//        for(int k = 0 ; k < N ; k ++){
//            System.out.println(Arrays.toString(map[k]));
//        }
//        System.out.println();
//
        for(int k = 0 ; k < adjList.length; k++){
            System.out.println(Arrays.toString(adjList[k]));
        }
        bw.write( minLen + "");
        bw.newLine();
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
