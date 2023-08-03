package com.ssafy.algorithmstudy.boj.boj2961;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class boj2961_Combination {
    public static int mult[];
    public static int add[];
    public static boolean isSelected[];
    public static int n;
    public static int sub = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        mult = new int[n];
        add = new int[n];
        isSelected = new boolean[n];
        for (int k = 0; k < n; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            mult[k] = Integer.parseInt(st.nextToken());
            add[k] = Integer.parseInt(st.nextToken());
        }

        getSub(1, 0, 0, 0);
        bw.write(String.valueOf(sub));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
    
    public static void getSub(int multed, int added, int cnt, int selectedCount) {
        
        if (selectedCount != 0) {
            sub = Math.min(sub, Math.abs(multed - added));

        }
        if (cnt == n) {
                return;
        }
        isSelected[cnt] = true;
        getSub( multed * mult[cnt], added + add[cnt], cnt + 1, selectedCount +1 );
        isSelected[cnt] = false;
        getSub(multed, added, cnt + 1, selectedCount);
    }
}
