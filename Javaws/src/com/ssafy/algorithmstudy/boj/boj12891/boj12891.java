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
        int count[] = new int[5];
        int matchCount[] = new int[5];
        int res = 0;

        for (int k = 1; k < 5; k++) 
            count[k] = Integer.parseInt(st.nextToken());
        for (int k = 0; k < m; k++) 
            matchCount[returnIdx(line.charAt(k))] += 1;
        

        for (int k = m; k < n; k++) {

            if (isValid(count, matchCount))
                res += 1;

            char tmp = line.charAt(k);
            if(returnIdx(tmp) != 0)
                matchCount[returnIdx(tmp)] += 1;

            tmp = line.charAt(k - m);
            if(returnIdx(tmp)!= 0)
                matchCount[returnIdx(tmp)] -= 1;
            // System.out.println(thingy);
            // System.out.println(Arrays.toString(matchCount));
            // System.out.println(Arrays.toString(count));
        }
        
        if(isValid(count, matchCount))
            res += 1;
        System.out.println(res);
    }
    
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

    public static boolean isValid(int[] count, int[] matchCount) {
        for (int k = 0; k < count.length; k++) {
            if(count[k] > matchCount[k])
                return false;
        }
        return true;
    }
}