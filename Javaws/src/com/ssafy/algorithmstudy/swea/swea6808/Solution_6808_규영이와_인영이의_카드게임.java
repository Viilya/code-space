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

    /**
     * 모든 카드의 경우의 수를 조합 한 후에 승패 여부 판단 
     * @param cnt
     * @param score01
     * @param score02
     */
    public static void getCombination(int cnt, int score01, int score02) {
        // 모든 카드 뽑았을 경우 비교해서 승패 판가름 
        if (cnt == 9) {
            if (score01 < score02)
                resultLose++;
            if (score02 < score01)
                resultWin++;
            return;
        }
        // 아직 뽑히지 않은 카드를 뽑아서 Permutation 함수 호출 
        for (int k = 0; k < 9; k++) {
            if (!counted[k]) {
                counted[k] = true;
                // 반대쪽 카드가 더 큰 경우 반대쪽 점수에 더한 후 Permutation 함수 호출, 아닌 경우는 반대로 호출
                if (card02[k] > card01[cnt]) {
                    getCombination(cnt + 1, score01, score02 + card01[cnt] + card02[k]);
                } else if(card02[k] < card01[cnt]){
                    getCombination(cnt + 1, score01 + card01[cnt] + card02[k], score02);
                }
                counted[k] = false;
            }
        }

    }
    /**
     * input 받고 조합 함수 호출 
     * @throws IOException
     */
    public static void inputAndRun() throws IOException{
        T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            // 결과값 초기화
            resultLose = 0;
            resultWin = 0;
            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < 9; k++) 
                card01[k] = Integer.parseInt(st.nextToken());
            int count = 0;
            // 반대 카드 배열 생성 
            for (int k = 1; k <= 18; k++) {
                boolean isContained = false;
                for (int s = 0; s < 9; s++) {
                    if (card01[s] == k) {
                        isContained = true;
                    }
                }
                if (!isContained)
                    card02[count++] = k;
            }
            // 조합 호출 
            getCombination(0, 0, 0);
            printRes(test_case);
        }
    }
    public static void main(String args[]) throws IOException {
        inputAndRun();
        bw.flush();
    }
}