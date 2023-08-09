package com.ssafy.algorithmstudy.boj.boj11286;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

class Number implements Comparable<Number> {
    public int absNum;
    public int num;

    public Number(int num) {
        this.absNum = Math.abs(num);
        this.num = num;
    }

    @Override
    public int compareTo(Number o) {
        if (absNum > o.absNum)
            return 1;
        else if (absNum < o.absNum)
            return -1;
        else {
            if (num > o.num)
                return 1;
            else if (num < o.num)
                return -1;

            return 0;
        }
    }
}
    
public class boj11286 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    public static PriorityQueue<Number> priorityQueueLowest = new PriorityQueue<>();

    
    /**
     * input session
     * @throws IOException
     */
    public static void inputAndRun() throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int k = 0; k < N; k++) {
            int command = Integer.parseInt(br.readLine());
            if (command != 0) {
                priorityQueueLowest.add(new Number(command));
            }
            else {
                if (priorityQueueLowest.isEmpty()) {
                    bw.write(String.valueOf(0));
                } else {
                    bw.write(String.valueOf(priorityQueueLowest.poll().num));
                }
                bw.newLine();
            }

        }
    }

    public static void main(String args[]) throws IOException {
        inputAndRun();
        bw.flush();
        bw.close();
        br.close();
    }
}
