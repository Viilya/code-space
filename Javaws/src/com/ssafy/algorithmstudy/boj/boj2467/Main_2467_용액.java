package com.ssafy.algorithmstudy.boj.boj2467;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2467_용액 {
    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
        closeIO();
    }


    public static int N;
    public static int solution[];
    public static int start = 0;
    public static int end = 1;
    public static int res[] = {-1, -1};
    public static int min = Integer.MAX_VALUE;


    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        solution = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static void solve() {
        start = 0;
        end = N-1;
        res[0] = solution[0];
        res[1] = solution[N-1];
        min = Math.abs(res[0] + res[1]);

        while(start < end){
            int sum = solution[start] + solution[end];

//            System.out.println("start : " + start + " end : " + end);
//            System.out.println("        " + solution[start] + "   +   " + solution[end] + "   =   " + sum);
//            System.out.println("     res[0] : " +res[0] + " res[1] : " + res[1]);
            if(Math.abs(sum) < min){
                res[0] = solution[start];
                res[1] = solution[end];
                min = Math.abs(sum);
            }
            if(sum < 0){
                start += 1;
            }else{
                end -= 1;
            }
        }
    }

    private static void output() throws IOException {
        bw.write(res[0] + " " + res[1]);
        bw.newLine();
    }

    private static void closeIO() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
