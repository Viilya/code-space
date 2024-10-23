package com.ssafy.algorithmstudy.boj.boj32405;

import java.io.*;
import java.util.Arrays;

/**
 * N 명 플레이어 -> 1명의 승자가 결정 (공격력 체력)
 * 체력이 0보다 큰 사람 한명만 남으면 종료
 * 1 2 3 4 순
 * 자기 제외 모든 플레이어 체력 공격력 만큼 감소
 *
 */

public class Main_32405_배틀로얄 {


    public static int N;
    public static int hp[];
    public static int atk[];
    public static int result = 0;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
        closeIO();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        hp = new int[N];
        atk = new int[N];

        atk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        hp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    }

    private static void solve() {
        int playerRemainCount = N;
        int roundCount = 0;
        int idx[] = new int[N];
        for(int k = 0 ; k < N ; k++ ){
            idx[k] = k;
        }
        int atkSum = 0;


        while(playerRemainCount != 1){
            int remain = 0;
            for(int k = 0 ; k < playerRemainCount; k ++){
                if(hp[k] > atkSum - atk[k] * roundCount){
                    atkSum += atk[k];
                    idx[remain] = idx[k];
                    hp[remain] = hp[k];
                    atk[remain++] = atk[k];
                }
            }
            roundCount ++;
            playerRemainCount = remain;
        }

        result = idx[0];
    }

    private static void output() throws IOException {
        bw.write((result+1) + "");
        bw.newLine();
    }

    private static void closeIO() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
}
