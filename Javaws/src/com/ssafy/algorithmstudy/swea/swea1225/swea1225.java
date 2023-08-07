package com.ssafy.algorithmstudy.swea.swea1225;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


public class swea1225 {

    public static int[] numbers;
    

    public static Deque<Integer> dq = new ArrayDeque<Integer>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        // test_case 1 ~ 10
        for (int test_case = 1; test_case <= 10; test_case++) {
            br.readLine();
            st = new StringTokenizer(br.readLine());

            // 숫자를 저장하는 배열 
            numbers = new int[8];
            int min = Integer.MAX_VALUE;
            int tmp;
            for (int k = 0; k < 8; k++) {
                numbers[k] = Integer.parseInt(st.nextToken());

                // 주어진 숫자들 중에서 최솟값을 계산
                min = Math.min(min, numbers[k]);
            }
            //
            // 최대로 돌 수 있는 싸이클 - 1 만큼 숫자들을 뺌
            tmp = min / 15 - 1;
            for (int k = 0; k < 8; k++) {
                numbers[k] -= 15 * tmp;
                // 이후 dq에 숫자들을 추가 
                dq.addLast(numbers[k]);
            }
            while (true) {
                if (cycle())
                    break;
            }

            // 출력 
            bw.write("#" + String.valueOf(test_case));
            for (int k = 0; k < 8; k++) {
                bw.write(" " + String.valueOf(dq.pollFirst()));
            }
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }
    /**
     * 싸이클 한번을 돌면서 숫자들을 빼며 0이하의 값이 나오는 암호가 있으면 true를 return 
     * @return
     */
    public static boolean cycle() {

        // 1~5 까지 돌면서 숫자에서 값을 빼고 뒤로 보내기 
        for (int k = 1; k <= 5; k++) {
            int number = dq.pollFirst() - k;
            // 뺀 숫자가 0 보다 작은 경우 
            if (number <= 0) {
                // 0을 마지막에 추가
                dq.addLast(0);
                return true;
            }
            dq.addLast(number);
        }
        return false;
    }
}


