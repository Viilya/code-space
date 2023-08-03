package com.ssafy.algorithmstudy.boj.boj12891;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj12891 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder line = new StringBuilder("");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        line.append(br.readLine());

        st = new StringTokenizer(br.readLine());
        // 기준 염기서열을 저장하는 배열 
        int count[] = new int[5];
        // 잘라낸 DNA에서의 염기 서열을 저장할 배열
        int matchCount[] = new int[5];
        int res = 0;

        for (int k = 1; k < 5; k++) 
            count[k] = Integer.parseInt(st.nextToken());
        // 잘라낸 염기 서열 확인 
        for (int k = 0; k < m; k++) 
            matchCount[returnIdx(line.charAt(k))] += 1;
        

        for (int k = m; k < n; k++) {
            // 염기 서열이 조건에 잘 맞는지 확인하고 맞으면 결과값 + 1
            if (isValid(count, matchCount))
                res += 1;
            // 뒤로 한칸씩 당기면서 추가한 char의 해당하는 index + 1
            char tmp = line.charAt(k);
            if(returnIdx(tmp) != 0)
                matchCount[returnIdx(tmp)] += 1;
            // 뒤로 한칸씩 밀면서 빠지는 char의 해당하는 index - 1
            tmp = line.charAt(k - m);
            if(returnIdx(tmp)!= 0)
                matchCount[returnIdx(tmp)] -= 1;
        }
        
        if(isValid(count, matchCount))
            res += 1;
        System.out.println(res);
    }
    
    /**
     * 받은 DNA 염기의 index를 반환하는 함수
     * @param c
     * @return
     */
    public static int returnIdx(char c) {
        if (c == 'A') {
            return 1;
        } else if (c == 'C') {
            return 2;
        } else if (c == 'G') {
            return 3;
        } else if (c == 'T') {
            return 4;
        }
        return 0;
    }

    // 염기 서열을 모두 포함하고 있는지 확인하는 함수
    public static boolean isValid(int[] count, int[] matchCount) {
        for (int k = 0; k < count.length; k++) {
            if(count[k] > matchCount[k])
                return false;
        }
        return true;
    }
}