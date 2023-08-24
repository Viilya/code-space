package com.ssafy.algorithmstudy.boj.boj1697;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_1697_숨바꼭질 {
    public static final int MAX_N = 200001;
    public static Deque<int[]> dq = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        input();
        dq.addLast(new int[] {N, 0});
        printRes(teleport());
    }


    public static int N, K;
    public static boolean isVisited[] = new boolean[MAX_N];
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    public static int teleport(){
        // 큐가 빌 때 까지 돌기
        while(!dq.isEmpty()){
            int curr[] = dq.pollFirst();

            // 동생의 위치면 종료
            if(curr[0] == K){
                return curr[1];
            }
            // 이미 방문했던 거리면 종료 (cnt가 더 높을 거이기 때문에)
            if(isVisited[curr[0]]){
                continue;
            }
            isVisited[curr[0]] = true; // 방문 처리
            dq.addLast(new int[] {curr[0] + 1, curr[1] + 1}); // + 1 가기
            if(curr[0] > 0) {
                dq.addLast(new int[]{curr[0] - 1, curr[1] + 1}); // -1 가기
            }
            if(Math.abs(curr[0] - K) > Math.abs((curr[0] << 1) - K)){ // *2 했을 때 거리가 더 멀어지면 수행하지 않음
                dq.addLast(new int[] {curr[0] << 1, curr[1] + 1});
            }
        }
        return 0;
    }

    public static void printRes(int cnt) throws IOException{
        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
//유니온 파인드
//        / 힙
/// 크루스칼
/// 프림