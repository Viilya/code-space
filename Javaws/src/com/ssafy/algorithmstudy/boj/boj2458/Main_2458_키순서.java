package com.ssafy.algorithmstudy.boj.boj2458;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2458_키순서 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }


    public static int N , M;
    public static int adjArr[][];
    public static int orderCount = 0;
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjArr = new int[N][N];
        for(int k = 0 ; k < M ; k++){
            st = new StringTokenizer(br.readLine());
            adjArr[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
        }
        findOrder();
        printRes();

    }

    public static void findOrder(){
        for(int k = 0 ; k < N ; k ++){
            int countU = bfsUp(k);
            int countD = bfsDown(k);
            if(countU + countD + 1 == N)
                orderCount += 1;
        }
    }

    public static int bfsUp(int start){
        boolean isVisited[] = new boolean[N];
        int count = 0;
        Queue<Integer> q = new ArrayDeque<>();
        isVisited[start] = true;
        for(int k = 0 ; k < N ; k++){
            if(adjArr[start][k] == 1){
                q.add(k);
                isVisited[k] = true;
                count ++;
            }
        }

        while(!q.isEmpty()){
            int next = q.poll();
            for(int k = 0 ; k < N ; k++){
                if(adjArr[next][k] == 1 && !isVisited[k]){
                    q.add(k);
                    isVisited[k] = true;
                    count ++;
                }
            }
        }
        return count;
    }
    public static int bfsDown(int start){
        boolean isVisited[] = new boolean[N];
        int count = 0;
        Queue<Integer> q = new ArrayDeque<>();
        isVisited[start] = true;
        for(int k = 0 ; k < N ; k++){
            if(adjArr[k][start] == 1){
                q.add(k);
                isVisited[k] = true;
                count ++;
            }
        }

        while(!q.isEmpty()){
            int next = q.poll();
            for(int k = 0 ; k < N ; k++){
                if(adjArr[k][next] == 1 && !isVisited[k]){
                    q.add(k);
                    isVisited[k] = true;
                    count ++;
                }
            }
        }
        return count;
    }

    public static void printRes() throws IOException {
        bw.write("" + orderCount);
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
