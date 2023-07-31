package com.ssafy.algorithmstudy.swea.swea1289;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
public class SWEA1289 {

    public static void main(String args[]) throws IOException {
        //System.setIn(new FileInputStream("SWEA1289i.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());
        // test case
        for (int test_case = 1; test_case <= t; test_case++) {
            // 한 줄 읽기 
            String st = bf.readLine();
            // count 변수 
            int count = 0;
            // 비교 메모리를 저장하는 변수
            char tmp = '0';
            // 입력받은 메모리와 비교하면서 tmp 와 다르면 tmp를 그 메모리 값으로 갱신하면서 count += 1 실행
            for (int k = 0; k < st.length(); k++) {
                if (tmp != st.charAt(k)) {
                    tmp = st.charAt(k);
                    count++;
                }
            }
            // 출력부
            System.out.println("#" + test_case + " " + count);
        }

    }
}
