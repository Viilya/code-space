package com.ssafy.algorithmstudy.swea.swea5656;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }

    public static int N, W, H;
    public static int map[][];
    public static int dx[] = {-1, 0, 1, 0};
    public static int dy[] = {0, 1, 0, -1};
    public static int minCount;

    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<= T ; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            for(int k = 0 ; k < H ; k ++) {
                map[k] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            minCount = Integer.MAX_VALUE;
            int seq[] = new int[N];
            getSeq(0, seq);

            printRes(tc);
        }
    }

    public static void getSeq(int count, int seq[]){
        if(count == N){
//            System.out.println(Arrays.toString(seq));
            int newMap[][] = new int[H][W];
            for(int k = 0 ; k < H ; k++){
                newMap[k] = Arrays.copyOf(map[k] , W);
            }
            bombSeq(newMap, seq);
            countRemainBlock(newMap);
//            for(int k = 0 ; k < H ; k++) {
//                System.out.println(Arrays.toString(newMap[k]));
//            }
//            System.out.println();
            return;
        }
        for(int k = 0 ; k < W; k ++){
            seq[count] = k;
            getSeq(count +1, seq);
        }
    }


    public static void bombSeq(int newMap[][], int seq[]){
        for(int k = 0 ; k < N ; k ++){
            dropBomb(newMap, seq[k]);
        }
    }

    public static void dropBomb(int newMap[][], int w){
        for(int k = 0 ; k < H; k ++){
            if(newMap[k][w] != 0){
                detonate(newMap, k, w);
                break;
            }
        }
        pullDown(newMap);
    }

    public static void detonate(int newMap[][], int x, int y){
        int power = newMap[x][y];
        newMap[x][y] = 0;
        for(int k = 0 ; k < 4;  k++){
            for(int s = 1; s < power; s++){
                int X = x + dx[k] * s;
                int Y = y + dy[k] * s;
                if(!isValid(X,Y)) break;
                if(newMap[X][Y] > 1){
                    detonate(newMap, X, Y);
                }
                newMap[X][Y] = 0;
            }
        }
    }

    public static void pullDown(int newMap[][]){
        for(int k = 0 ; k < W ; k ++){
            Deque<Integer> q = new ArrayDeque<>();
            for(int s=0;s<H;s++) {
                if(newMap[s][k]== 0) continue;
                q.addFirst(newMap[s][k]);
            }

            for(int s=H-1;s>=0;s--){
                if(q.isEmpty()) {
                    newMap[s][k] = 0;
                    continue;
                }
                newMap[s][k] = q.pollFirst();
            }
        }
    }

    private static void countRemainBlock(int[][] newMap) {
        int count = 0;
        for(int k = 0 ; k < H ; k++){
            for(int s= 0 ;s < W ; s++){
                if(newMap[k][s] == 0) continue;
                count ++;
            }
        }
        minCount = Math.min(minCount, count);
    }


    public static boolean isValid(int x, int y){
        if(x<0 || x>=H) return false;
        if(y<0 || y>=W) return false;
        return true;
    }

    public static void printRes(int tc) throws IOException {
        bw.write("#" + tc + " " + minCount);
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
