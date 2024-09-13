package com.ssafy.algorithmstudy.boj.boj1202;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 보석 N개 (1 < N , K < 300,000)
 * 무개 M, 가격 V (0 < 1,000,000)
 * 가방 k, 최대 무게 C -> 1개의 보석만 (1 < C < 100,000,000)
 * -> DP...?
 * 훔칠 수 있는 보석의 최대 가격
 */


public class Main_1202_보석도둑 {

    static class Gems implements Comparable<Gems> {
        int weight;
        int price;

        public Gems(){}
        public Gems(int w, int p){
            this.weight = w;
            this.price = p;
        }

        @Override
        public int compareTo(Gems o) {
            if(o.price == this.price){
                return this.weight - o.weight;
            }
            return o.price - this.price;
        }

        @Override
        public String toString(){
            return "[" + this.price + ", " + this.weight + "]";
        }
    }


    public static int MAX_GEM = 300000;
    public static int MAX_BAG = 300000;
    public static int N, K;
    public static int bagWeight[];
    public static Gems gems[];

    public static void main(String [] args) throws IOException {
        input();
        solve();
        output();
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        gems = new Gems[N];
        bagWeight = new int[K];
        for(int k = 0 ; k < N ; k ++) {
            st = new StringTokenizer(br.readLine());
            gems[k] = new Gems(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        }
        for(int k = 0 ; k < K ; k ++){
            bagWeight[k] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(gems);
        Arrays.sort(bagWeight);
        System.out.println(Arrays.toString(gems));
    }

    public static void solve() {
        for(int k = 0; k < N ; k ++){

        }
    }

    public static void output() throws IOException {
        bw.write("");
        bw.flush();
        bw.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
