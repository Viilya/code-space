package com.ssafy.algorithmstudy.swea.swea1238;

import java.io.*;
import java.util.*;

public class Solution_1238_Contact {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }
    public static final int MAX_MAN = 100;

    public static int inputCount;
    public static int startV;
    public static int[][] map;
    public static boolean[] isVisited;

    public static Deque<int[]> dq = new ArrayDeque<>();
    public static int res;
    public static void input() throws IOException {
        for(int tc = 1; tc <= 10; tc++){
            map = new int[MAX_MAN][MAX_MAN];
            isVisited = new boolean[MAX_MAN];

            st = new StringTokenizer(br.readLine());
            inputCount = Integer.parseInt(st.nextToken());
            startV = Integer.parseInt(st.nextToken());

            inputCount /= 2;
            st = new StringTokenizer(br.readLine());
            for(int k = 0 ; k < inputCount ; k ++){
                map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
            }
            isVisited[startV] = true;
            dq.add(new int[] {startV-1, 1});

            bfs();

            printRes(tc);
        }
    }

    public static void bfs(){
        PriorityQueue<Integer> pq= new PriorityQueue<Integer>(Collections.reverseOrder());
        int currLevel = 0;
        do{
            int[] curr = dq.pollFirst();
            if(currLevel < curr[1]){
                pq.clear();
                currLevel = curr[1];
            }
            pq.add(curr[0]);

            for(int k = 0 ; k < MAX_MAN; k++){
                if(map[curr[0]][k] == 1 && !isVisited[k]){
                    isVisited[k] = true;
                    //System.out.println("curr / k > " + curr + " " + k);
                    dq.offerLast(new int[] {k, curr[1] + 1});
                }
            }
        }while(!dq.isEmpty());

        res = pq.poll();

    }

    public static void printRes(int tc) throws IOException {
        bw.write("#" + tc + " " + (res+1));
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

