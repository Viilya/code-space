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

    // 각 탑의 높이를 저장
    public static int[] line;
    // 결과 값 
    public static int[] res;
    // 높은 탑들을 저장하는 배열 
    public static Deque<int[]> dq = new ArrayDeque<int[]>();
    public static int n;
    
    public static void main(String args[]) throws IOException {
        input();
        // System.out.println(Arrays.stream(res).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        for (int k = 0; k < n; k++) {
            bw.write(String.valueOf(res[k]) + " ");
        }
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    /**
     * Inpust and Calc
     * @throws IOException
     */
    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        line = new int[n];
        res = new int[n];

        // get int and run
        for (int k = 0; k < n; k++) {
            line[k] = Integer.parseInt(st.nextToken());
            // 0 : idx , 1: height
            int[] tower = { k, line[k] };

            if (dq.isEmpty()) {
                dq.add(tower);
                res[k] = 0;
            }
            else {
                // 높이가 높았다가 낮아지거나, 바로 전 스택의 탑의 높이보다 높아질 때
                if (dq.peekLast()[1] < tower[1] || line[k - 1] > tower[1]) {
                    // 지금 탑 이전 까지 스택의 가장 위의 idx로 발사
                    for (int s = dq.peekLast()[0] + 1; s < k; k++) {
                        res[s] = dq.peekLast()[0];
                    }
                    // 스택에서 지금 탑 보다 높은 탑이 나올 때 까지 pop
                    while (true) {
                        // 스택이 비어있으면 지금까지 나온 탑중 가장 높은 탑
                        if (dq.isEmpty()) {
                            res[k] = 0;
                            break;
                        }
                        // 높은 탑이 나오면 그 탑으로 발사 
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
