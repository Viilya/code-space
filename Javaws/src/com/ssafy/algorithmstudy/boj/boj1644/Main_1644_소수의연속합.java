package com.ssafy.algorithmstudy.boj.boj1644;

import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main_1644_소수의연속합 {
    public static void main(String args[]) throws IOException {
        long beforeTime = System.currentTimeMillis(); // 코드 실행 전에 시간 받아오기

        Runtime.getRuntime().gc();
        input();
        solve();

        long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println(usedMemory/1024 + " Kilobytes");
        long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
        long diffTime = afterTime - beforeTime; // 두 개의 실행 시간
        System.out.println("실행 시간(ms): " + diffTime); // 세컨드(초 단위 변환)
        output();
        closeIO();

    }

    public static int N;

    public static int RESULT = 0;
    public static int ERATOS[];
    public static ArrayList<Integer> primes = new ArrayList<Integer>();

    public static void input() throws IOException{
        N = Integer.parseInt(br.readLine());

        ERATOS = new int[N+1];
        for(int k = 2 ; k < (N+1); k ++){
            if(ERATOS[k] == 0){
                for(int s = k * 2 ; s < N+1 ; s += k){
                    ERATOS[s] = 1;
                }
                primes.add(k);
            }
        }
    }

    public static void solve(){

        Queue <Integer> qIdx = new ArrayDeque<>();
        Queue <Integer> qLeft = new ArrayDeque<>();

        for(int k = 0 ; k < primes.size(); k ++){
            qIdx.offer(k);
            qLeft.offer(N - primes.get(k));
        }
        while(!qLeft.isEmpty()){
            int currIdx = qIdx.poll();
            int currLeft = qLeft.poll();
//            System.out.println(primes.get(currIdx) + " : " + currLeft);

            if(currLeft == 0){
                RESULT += 1;
                continue;
            }

            if(currIdx == 0){
                continue;
            }

            if(currLeft < primes.get(currIdx - 1)){
                continue;
            }



            qIdx.offer(currIdx - 1);
            qLeft.offer(currLeft - primes.get(currIdx -1));



        }
    }

    public static void output() throws IOException{
        bw.write("" + RESULT);
        bw.flush();
    }

    public static void closeIO() throws IOException{
        bw.close();
        br.close();
    }



    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


}
