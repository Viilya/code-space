package com.ssafy.algorithmstudy.boj.boj2565;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_2565_전깃줄 {
    public static void main(String[] args) throws IOException {
        input();

        determineLines();

        printRes();
        closeIO();
    }
    public static int N;
    public static int lineCount = 0;
    public static int maxStickCount = 500;
    public static Point lines[] = new Point[maxStickCount];
    public static int crossCount[];
    public static int count = 1;

    public static void determineLines() {
        int dp[] = new int[maxStickCount];
        Arrays.sort(lines, Comparator.comparingInt(o -> o.x));
        System.out.println(Arrays.toString(lines));

    }


    public static void input() throws IOException {
        lineCount = Integer.parseInt(br.readLine());
        crossCount = new int [lineCount];
        for(int k = 0 ; k< 500; k ++){
            lines[k] = new Point(1000, 0);
        }
        for(int k = 0; k < lineCount ; k ++){
            st = new StringTokenizer(br.readLine());
            lines[k] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        //System.out.println(Arrays.toString(crossCount));
    }

    public static void printRes() throws IOException {
        bw.write(count + "");
    }

    public static void closeIO() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
