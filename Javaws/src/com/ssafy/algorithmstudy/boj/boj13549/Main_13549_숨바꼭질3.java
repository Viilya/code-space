package com.ssafy.algorithmstudy.boj.boj13549;

import java.awt.*;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_13549_숨바꼭질3 {
    public static void main(String args[]) throws IOException {
        input();
        solve();
        output();
    }

    public static int N;
    public static int K;

    public static void input() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }


    public static int dp[] = new int[200002];
    public static void solve(){
        int max = Math.max(N, K);
        Arrays.fill(dp, 10000000);
        Deque<Point> dq = new ArrayDeque<>();
        dq.add(new Point(N, 0));
        dp[N] = 0;
        while(!dq.isEmpty()){
            Point curr = dq.pollFirst();
            int multTemp = curr.x * 2;
            if(curr.x == K) {
                break;
            }
            //System.out.println("curr : " + curr);
            while(multTemp < 2 * max){
                if(dp[multTemp] > curr.y) {
                    dq.offerLast(new Point(multTemp, curr.y));
                    dp[multTemp] = curr.y;
                    multTemp *= 2;
                }else{
                    break;
                }
            }
            if(curr.x+1  < (2 * max) && dp[curr.x+1] > curr.y + 1) {
                dq.offerLast(new Point(curr.x + 1, curr.y + 1));
                dp[curr.x + 1] = curr.y + 1;
            }
            if(curr.x-1  >= 0 && dp[curr.x-1] > curr.y + 1) {
                dq.offerLast(new Point(curr.x - 1, curr.y + 1));
                dp[curr.x - 1] = curr.y + 1;
            }
        }

    }

    public static void output() throws IOException{
        bw.write(dp[K] + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
}

