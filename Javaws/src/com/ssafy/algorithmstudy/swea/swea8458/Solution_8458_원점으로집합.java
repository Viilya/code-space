package com.ssafy.algorithmstudy.swea.swea8458;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_8458_원점으로집합 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }

    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= T ; tc++){
            int N = Integer.parseInt(br.readLine());
            int dist[] = new int[N];
            int result = 0;
            int flag = -1;
            int maxDist = 0;
            for(int k = 0 ; k < N ; k ++){
                st = new StringTokenizer(br.readLine());
                dist[k] = Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(Integer.parseInt(st.nextToken()));
                maxDist = Math.max(maxDist, dist[k]);
                if(flag == -1) flag = dist[k] % 2;
                else{
                    if(flag != dist[k] % 2){
                        result = -1;
                    }
                }
            }

            if(result != -1) {
                result = 0;
                int sum = 0;
                while(true){
                    sum += result;
                    if(sum >= maxDist) {
                        boolean isAllEven = true;
                        for (int k = 0; k < N; k++) {
                            if((sum - dist[k]) % 2 != 0){
                                isAllEven = false;
                            }
                        }
                        if(isAllEven) {
                            break;
                        }

                    }
                    result ++;
                }
            }
            printRes(tc, result);

        }
    }
    public static void printRes(int tc, int count) throws IOException {
        bw.write("#" + tc + " " + count);
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
