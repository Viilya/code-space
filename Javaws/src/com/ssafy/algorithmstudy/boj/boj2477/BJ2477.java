package com.ssafy.algorithmstudy.boj.boj2477;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2477 {
    public static void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("BJ2477i.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        boolean[] isTwoEdgeDirection = new boolean[4];
        int[][] map = new int[4][4]; //
        int[] trueIdx = new int[2];
        int n;
        int dir,  len;
        int res = 0;
        int tmp = 0;
        n = Integer.parseInt(st.nextToken());
        for (int k = 0; k < 6; k++) {
            st = new StringTokenizer(bf.readLine());
            dir = Integer.parseInt(st.nextToken()) - 1;
            len = Integer.parseInt(st.nextToken());
            if (map[dir][0] == 0) {
                map[dir][0] = len;
                map[dir][2] = k;
            } else {
                isTwoEdgeDirection[dir] = true;
                if (k - map[dir][2] != 2) {
                    map[dir][1] = map[dir][0];
                    map[dir][0] = len;
                } else {
                    map[dir][1] = len;
                }
            }
        }
        res = 1;
        int flag = 0;
        for (int k = 0; k < 4; k++) {
            if (!isTwoEdgeDirection[k])
                res *= map[k][0];
            if (isTwoEdgeDirection[k]) {
                trueIdx[flag++] = k;
            }
        }
        //System.out.println(trueIdx[0] + " " + trueIdx[1]);
        //System.out.println(map[0][0] + " " + map[0][1] + " " + map[2][0] + " " + map[2][1]);
        tmp = map[ trueIdx[0] ][ (trueIdx[0] + trueIdx[1]) % 2 ] * map[ trueIdx[1] ][ (trueIdx[0] + trueIdx[1]) % 2 ^ 1 ];
        //System.out.println(res + " " + tmp);

        System.out.println((res - tmp) * n);
    }
}
