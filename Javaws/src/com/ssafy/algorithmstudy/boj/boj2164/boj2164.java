package com.ssafy.algorithmstudy.boj.boj2164;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class boj2164 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Deque<Integer> dq = new ArrayDeque<>();

        for (int k = 1; k <= n; k++) {
            dq.addLast(k);
        }
        while (true) {
            if (dq.peekFirst() == dq.peekLast())
                break;
            dq.pop();
            int tmp = dq.pollFirst();
            dq.addLast(tmp);
            System.out.println(dq.toString());
        }
        System.out.println(dq.peekFirst());
        sc.close();
    }
}
