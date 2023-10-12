package com.ssafy.algorithmstudy.swea.swea5607;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_5607_조합 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }
    public static final int MOD = 1234567891;
    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            long nFactorial;
            // r!(n-r)!
            long a;
            long factorial[] = new long[N+1];
            long res;
            factorial[1] = 1L;
            for(int k = 2 ; k <= N ; k ++){
                factorial[k] = (factorial[k-1] * k) % MOD;
            }
            nFactorial = factorial[N];
            a = (factorial[N-M] * factorial[M]) % MOD;

            long aPowerMod = divideAndConquer(a, MOD - 2);


            printRes(tc, (nFactorial * aPowerMod) % MOD);
        }
    }

    public static long divideAndConquer(long a, int pow){
        if(pow == 0)
            return 1;

        long halfPower = divideAndConquer(a, pow/2);
        long res = (halfPower * halfPower) % MOD;

        if(pow % 2 == 1)
            res = (res * a) % MOD;

        return res;
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
