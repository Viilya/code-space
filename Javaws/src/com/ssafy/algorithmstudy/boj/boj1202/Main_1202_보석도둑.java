package com.ssafy.algorithmstudy.boj.boj1202;

import java.io.*;
import java.util.*;

/**
 * 보석 N개 (1 < N , K < 300,000)
 * 무개 M, 가격 V (0 < 1,000,000)
 * 가방 k, 최대 무게 C -> 1개의 보석만 (1 < C < 100,000,000)
 * -> DP...?
 * 훔칠 수 있는 보석의 최대 가격
 */


public class Main_1202_보석도둑 {

    static class Gem implements Comparable<Gem> {
        int weight;
        int value;

        public Gem(){}
        public Gem(int w, int p){
            this.weight = w;
            this.value = p;
        }

        @Override
        public int compareTo(Gem o) {
            if(o.weight == this.weight){
                return o.value - this.value;
            }
            return this.weight - o.weight;
        }

        @Override
        public String toString(){
            return "[" + this.value + ", " + this.weight + "]";
        }
    }


    public static int MAX_GEM = 300000;
    public static int MAX_BAG = 300000;
    public static int N, K; // N : 보석 개수  K : 가방 개수
    public static int bagList[]; // 가방 무게 저장
    public static Gem gemList[]; // 보석 저장
    public static Queue<Integer> tempbagList = new LinkedList<>();
    public static long valueSum = 0;

    public static void main(String [] args) throws IOException {
        input();
        solve();
        output();
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        gemList = new Gem[N];
        bagList = new int[K];
        for(int k = 0 ; k < N ; k ++) {
            st = new StringTokenizer(br.readLine());
            gemList[k] = new Gem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        }
        for(int k = 0 ; k < K ; k ++){
            bagList[k] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(gemList);
        Arrays.sort(bagList);
//        System.out.println(Arrays.toString(gemList));
    }

    public static void solve() {


        PriorityQueue<Gem> availableGems = new PriorityQueue<Gem>((Comparator.comparingInt(o -> -o.value)));
        int gemIndex = 0;
        
        
        // bagList 순회
        for(int k = 0; k < K ; k ++){
            
            
            // k 번째 가방에 넣을 수 있는 보석 전부 pq에 저장 
            while(gemIndex < N && gemList[gemIndex].weight <= bagList[k]){

                // pq에 저장 후 index 1 증가
                availableGems.add(gemList[gemIndex++]);
            }

//            System.out.println(availableGems.toString());
            // gems 저장 pq null 확인
            if(!availableGems.isEmpty()){
                valueSum += availableGems.poll().value;
            }
        }
    }

    public static void output() throws IOException {
        bw.write("" + valueSum);
        bw.flush();
        bw.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
