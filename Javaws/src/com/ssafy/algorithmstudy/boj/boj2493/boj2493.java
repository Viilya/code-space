package com.ssafy.algorithmstudy.boj.boj2493;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class boj2493 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;

    public static int[] line;
    public static int[] res;
    public static Deque<int[]> dq = new ArrayDeque<int[]>();
    public static int n;
    
    public static void main(String args[]) throws IOException {
        input();
        for (int k = 0; k < n; k++) {
            System.out.print(res[k] + " ");
        }
        System.out.println();
    }
    public static void input() throws NumberFormatException, IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        line = new int[n];
        res = new int[n];
        for (int k = 0; k < n; k++) {
            line[k] = Integer.parseInt(st.nextToken());
            int[] tower = { k, line[k] };
            if (dq.isEmpty()) {
                dq.add(tower);
                res[k] = 0;
            }
            else {
                if (dq.peekLast()[1] < tower[1] || line[k - 1] > tower[1]) {
                    for (int s = dq.peekLast()[0] + 1; s < k; k++) {
                        res[s] = dq.peekLast()[0];
                    }
                    while (true) {
                        if (dq.isEmpty()) {
                            res[k] = 0;
                            break;
                        }
                        if (dq.peekLast()[1] >= tower[1]) {
                            res[k] = dq.peekLast()[0] + 1;
                            break;
                        }
                        dq.pollLast();
                    }
                    dq.addLast(tower);  
                }
            }
        }
    }
}
