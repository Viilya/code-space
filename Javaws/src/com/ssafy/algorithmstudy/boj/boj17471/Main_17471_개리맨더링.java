package com.ssafy.algorithmstudy.boj.boj17471;

import java.io.*;
import java.util.*;

public class Main_17471_개리맨더링 {
    public static void main(String[] args) throws IOException {
        input();
        makeGroup(0, 0);
        printRes();
        closeIO();
    }

    public static int population[];
    public static int graph[][];
    public static int N;
    public static boolean isVisited[] = new boolean[1024];
    public static int totalSum = 0;
    public static boolean isChanged = false;
    public static int minDiff = Integer.MAX_VALUE;
    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        population = new int[N];
        population = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        graph = new int[N][N];
        for(int k = 0 ; k < N ; k ++){
            totalSum += population[k];
            graph[k][k] = 1;
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            for(int s= 0 ; s < count ; s++){
                int end = Integer.parseInt(st.nextToken());
                graph[k][end-1] = 1;
            }
        }
    }

    public static void makeGroup(int curr, int cnt){
        if(cnt == N) {
            //System.out.println(Integer.toBinaryString(curr));
            cutAndCalc(curr);
            return;
        }
        makeGroup((curr | (1<<cnt)), cnt+1);
        makeGroup((curr), cnt+1);
    }
    public static void cutAndCalc(int curr) {

        if (isUnion(curr) && isUnion((1<<(N)) - curr - 1)) {
            int partPop = 0;
            for (int k = 0; k < N; k++) {
                if ((curr & (1 << k)) == (1<<k)) {
                    partPop += population[k];
                }
            }
            int diff = Math.abs(totalSum - 2*partPop);
            if(minDiff > diff){
                minDiff = diff;
                isChanged = true;
            }
        }
    }


    public static boolean isUnion(int curr){
        int[] union1 = new int[N];

        for(int k = 0 ; k < N ; k ++){
            union1[k] = k;
        }
        boolean flag = true;
        boolean isCityIn[] = new boolean[N];
        for(int k = 0 ; k < N ; k++){
            if((curr & (1<<k)) == (1<<k)){
                isCityIn[k] = true;
            }
        }
        int formal = -1;
        for(int k = 0 ; k < N ; k ++) {
            if (isCityIn[k]) {
                for (int s = 0; s < N; s++) {
                    if (graph[k][s] == 1 && isCityIn[s]) {
                        int a= findParent(union1, k);
                        int b= findParent(union1, s);
                        if(a>b){
                            union1[a] = b;
                            formal = b;
                        }else{
                            union1[b] = a;
                            formal = a;
                        }
                    }
                }
            }
        }
//        System.out.println(formal + " ");
        for(int k = 0 ; k < N ; k++){
            if(isCityIn[k] && formal != union1[k]){
                flag = false;
            }
        }
//        System.out.println(Arrays.toString(union));
//        System.out.println(flag + " ");
        return flag;
    }

    public static int findParent(int[] parent, int x){
        if(parent[x] == x)
            return x;
        else
            return parent[x] = findParent(parent, parent[x]);
    }

    public static void printRes() throws IOException {
        bw.write(String.valueOf(isChanged?minDiff:-1));
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
