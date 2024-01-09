package com.ssafy.algorithmstudy.boj.boj17471;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_17471_개리맨더링_union {
    public static void main(String args[]) throws IOException{
        input();
        solve();
        output();
        closeIO();
    }


    public static int n;
    public static int population[];
    public static int totalPopulation = 0;
    public static int adjArr[][];
    public static int result = Integer.MAX_VALUE;
    public static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = getInteger(st.nextToken());
        population = new int[n];
        adjArr = new int[n][n];

        population = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int k = 0 ; k < n ; k++){
            totalPopulation += population[k];
        }
        for(int k = 0 ; k < n ; k ++){
            st = new StringTokenizer(br.readLine());
            int count = getInteger(st.nextToken());
            for(int s = 0 ; s < count ; s++){
                adjArr[k][getInteger(st.nextToken()) - 1] = 1;
            }
        }

       // printArr(); // check Input works properly
    }

    public static int getInteger(String num) {
        return Integer.parseInt(num);
    }

    public static void printArr(){
        System.out.println(" n : " + n);
        System.out.println(" pop : " + Arrays.toString(population));
        System.out.println(" adjArr --");
        for(int k = 0 ; k < n ; k ++){
            System.out.println(Arrays.toString(adjArr[k]));
        }
    }

    private static void solve() {
        getCombination(0, 0);
    }

    private static void getCombination(int count, int store){
        if(count == n){
            calcSumDiff(store);
        }else{
            getCombination(count + 1, store);
            getCombination(count + 1, (store | (1 << count)));
        }
    }

    public static int count = 0;
    private static void calcSumDiff(int store){
        if(checkUnionable(store) && checkUnionable((1<<(n+1)) - store - 1)){
            int sum = 0;
            for(int k = 0 ; k < n ; k++){
                if(((1<<k) & store) == (1<<k)){
                    sum += population[k];
                }
            }
            result = Math.min(result, Math.abs(totalPopulation - sum - sum));
        }
    }

    private static boolean checkUnionable(int store){
        int parent[] = new int[n];
        boolean isVisited[] = new boolean[n];
        for(int k = 0 ; k < n ; k++ ){
            parent[k] = k;
        }
        Deque<Integer> dq = new ArrayDeque<>();
        int firstElement = 0;
        for(int k = 0 ; k < n ; k ++){
            if((store & (1<<k))== (1<<k)){
                firstElement = k;
                dq.offerLast(k);
                break;
            }
        }

        while(!dq.isEmpty()){
            int currCity = dq.pollFirst();
            for(int k = 0 ; k < n ; k++){
                if(adjArr[currCity][k] == 1 && ((store & (1<<k))== (1<<k)) && !isVisited[k]){
                    isVisited[k] = true;
                    dq.offerLast(k);
                    union(parent, currCity, k);
                }
            }
        }

        for(int k = 0 ; k < n ; k++){
            if( ((store & (1<<k))== (1<<k)) && parent[k] != firstElement){
                return false;
            }
        }
        return true;
    }

    private static void union(int parent[], int a, int b){
        if(findParent(parent, a) < findParent(parent, b)){
            parent[b] = parent[a];} else{
                    parent[a] = parent[b];}
    }

    private static int findParent(int parent[], int child){
        if(parent[child] == child){
            return child;
        }
        return parent[child] = findParent(parent, parent[child]);
    }

    private static void output() throws IOException{
        bw.write((result==Integer.MAX_VALUE?-1:result)+ "");
        bw.flush();
    }

    private static void closeIO() throws IOException{
        bw.close();
        br.close();
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
}
