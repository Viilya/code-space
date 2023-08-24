package com.ssafy.algorithmstudy.boj.boj15961;

import java.io.*;
import java.util.StringTokenizer;

public class Main_15961_회전초밥 {
    public static void main(String[] args) throws IOException {
        input();
        countInitial();
        countAll();
        printRes();
        closeIO();
    }
    public static int N, D, K, c;
    public static int list[];
    public static int counts[];
    public static int maxCount = 0;
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        list = new int[N];
        counts = new int[D+1];

        for(int k = 0 ; k < N ; k ++){
            list[k] = Integer.parseInt(br.readLine());
        }
       // System.out.println(Arrays.toString(list));
    }
    public static void countInitial(){
        for(int k = 0 ; k < K ; k ++){
            counts[list[k]] += 1;
        }
        counts[c] += 1;
        maxCount = countGroup();
       // System.out.println(Arrays.toString(counts));
    }

    public static int countGroup() {
        int totalCount = 0;
        for(int k = 0 ; k < D+1 ; k++){
            if(counts[k] != 0){
                totalCount += 1;
            }
        }

        return totalCount;
    }
    public static void countAll(){
        int count = maxCount;
        for(int k = K ; k < N ; k ++){
            counts[list[k-K]] --;
            if(counts[list[k-K]] == 0){
                count -= 1;
            }
            if(counts[list[k]] == 0){
                count += 1;
            }
            counts[list[k]] ++;

            maxCount = Math.max(maxCount, count);
        }

        for(int k = 0 ; k < K ; k++){
            if(counts[list[k]] == 0){
                count += 1;
            }
            counts[list[k]] ++;


            counts[list[N - K + k]] --;
            if(counts[list[N- K + k]] == 0){
                count -= 1;
            }
            maxCount = Math.max(count, maxCount);
        }
    }

    public static void printRes() throws IOException {
        bw.write(String.valueOf(maxCount));
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
