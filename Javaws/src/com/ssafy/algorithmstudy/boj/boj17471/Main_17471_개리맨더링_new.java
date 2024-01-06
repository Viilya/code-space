package com.ssafy.algorithmstudy.boj.boj17471;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_17471_개리맨더링_new {
    public static void main(String args[]) throws IOException{
        input();
        solve();
        output();
        closeIO();
    }


    public static int n;
    public static int population[];
    public static int adjArr[][];
    public static int result = Integer.MAX_VALUE;
    public static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = getInteger(st.nextToken());
        population = new int[n];
        adjArr = new int[n][n];

        population = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
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
        for(int k = 0 ; k < n ; k ++) {
            boolean visited[] = new boolean[n];
            visited[k] = true;
            bfs(k, visited);
        }
    }

    private static void bfs( int currPosition, boolean visited[]){
        Deque<Integer> dq = new ArrayDeque<>();

        dq.offerLast(currPosition);

        while(!dq.isEmpty()){
            int curr = dq.pollFirst();
            visited[curr] = true;
            calcPopulationGap(visited);
            for(int k = 0 ; k < n ; k ++){
                if(!visited[k] && adjArr[curr][k] == 1){
                    dq.offerLast(k);
                }
            }
        }

    }

    private static void calcPopulationGap(boolean visited[]){
        int opposite  = -1;
        int sum1 = 0;
        int sum2 = 0;
        for(int k = 0 ; k < n ; k ++){
            if(visited[k]) sum1 += population[k];
            else {opposite = k;}
        }
        if(opposite == -1){
            result = Math.min(result, sum1);
            return;
        }
        boolean visitedCopy[] = Arrays.copyOf(visited, n);
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(opposite);
        visitedCopy[opposite]= true;
        while(!dq.isEmpty()){
            int curr = dq.pollFirst();
            sum2 += population[curr];
            for(int k = 0 ; k < n ; k ++){
                if(adjArr[curr][k] == 1 && !visitedCopy[k]){
                    dq.addLast(k);
                    visitedCopy[k] = true;
                }
            }
        }

        int checkedCityCount = 0;
        for(int k = 0 ; k < n ; k ++){
            if(visitedCopy[k]) checkedCityCount ++;
        }

        if(checkedCityCount == n ){
            System.out.println(Arrays.toString(visited));
            System.out.println(sum1 + " " + sum2 + " = " + Math.abs(sum1 - sum2));
            result = Math.min(result, Math.abs(sum1 - sum2));
        }

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
