package com.ssafy.algorithmstudy.swea.swea6808;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_6808_규영이와_인영이의_카드게임 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
    public static int T;
    public static int[] card01 = new int[9];
    public static int[] card02 = new int[9];
    public static boolean[] counted = new boolean[9];
    public static int resultWin = 0;
    public static int resultLose = 0;

    public static void printRes(int test_case) throws IOException{
        bw.write("#" + test_case + " " + resultWin + " " + resultLose);
        bw.newLine();
    }

    public static void getCombination(int cnt, int score01, int score02) {
        if (cnt == 9) {
            if (score01 < score02)
                resultLose++;
            if (score02 < score01)
                resultWin++;
            return;
        }
        for (int k = 0; k < 9; k++) {
            if (!counted[k]) {
                counted[k] = true;
                if (card02[k] > card01[cnt]) {
                    getCombination(cnt + 1, score01, score02 + card01[cnt] + card02[k]);
                } else if(card02[k] < card01[cnt]){
                    getCombination(cnt + 1, score01 + card01[cnt] + card02[k], score02);
                } else {
                    getCombination(cnt + 1, score01, score02);
                }
                counted[k] = false;
            }
        }

    }

    public static void inputAndRun() throws IOException{
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            resultLose = 0;
            resultWin = 0;
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < 9; k++) 
                card01[k] = Integer.parseInt(st.nextToken());
            int count = 0;
            for (int k = 1; k <= 18; k++) {
                boolean isContained = false;
                for (int s = 0; s < 9; s++) {
                    if (card01[s] == k) {
                        isContained = true;
                    }
                }
                if(!isContained)
                    card02[count++] = k;
            }
            getCombination(0, 0, 0);
            printRes(test_case);
        }
    }
    public static void main(String args[]) throws IOException {
        inputAndRun();
        bw.flush();
    }
}