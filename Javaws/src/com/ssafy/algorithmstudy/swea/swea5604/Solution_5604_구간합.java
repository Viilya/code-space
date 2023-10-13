package com.ssafy.algorithmstudy.swea.swea5604;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution_5604_구간합 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }

    static HashMap<Long, Long> map = new HashMap<>();
    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0 ; tc < T ; tc++){
            st= new StringTokenizer(br.readLine());
            long start_ = Long.parseLong(st.nextToken());
            long start = cutBigChunk((start_==0)?0:start_-1);
            long end = cutBigChunk(Long.parseLong(st.nextToken()));
            printRes(tc + 1, end - start);
        }
    }

    public static long cutBigChunk(long a){
        long pow = 1;
        if(map.containsKey(a)) return map.get(a);
        while(true){
            if(pow == 0){
                System.out.println(a + " " + pow);
            }
            if(a / pow == 0) {
                pow /= 10;
                break;
            }
            pow *= 10;
        }
        if(a >= 10){
            long val = cutBigChunk( a - 1 - a%pow) + cutSmallChunk(a, pow);
            map.put(a, val);
            return val;
        }
        else{
            return a * (a+1) / 2;
        }
    }

    public static long cutSmallChunk(long a, long pow){
        return a / pow * (a % pow + 1) + cutBigChunk(a % pow);
    }

    public static void printRes(int tc, long res) throws IOException {
        bw.write("#" + tc + " " + res);
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
