package com.ssafy.algorithmstudy.swea.swea1228;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.LinkedList;

public class swea1228 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        for (int test_case = 1; test_case <= 10; test_case++) {
            // 숫자 입력 
            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            LinkedList<Integer> list = new LinkedList<Integer>();
            for (int k = 0; k < n; k++) // linkedList에 저장
                list.add(Integer.parseInt(st.nextToken()));

            int t = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int k = 0; k < t; k++) {
                st.nextToken(); // 'I' 받기 
                int startIdx = Integer.parseInt(st.nextToken()); // 시작 idx 
                int correctionCount = Integer.parseInt(st.nextToken()); // 개수 
                for (int s = startIdx; s < startIdx + correctionCount; s++) {
                    list.add(s, Integer.parseInt(st.nextToken())); // list 의 위치에 숫자 끼워 넣기
                }
            }
            
            // 출력
            bw.write("#" + String.valueOf(test_case) + " ");
            for (int k = 0; k < 10; k++) {
                bw.write(String.valueOf(list.get(k)) + " ");
            }
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }
}

