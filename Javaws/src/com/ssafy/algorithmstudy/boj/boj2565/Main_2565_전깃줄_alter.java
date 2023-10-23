package com.ssafy.algorithmstudy.boj.boj2565;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2565_전깃줄_alter {
    public static void main(String[] args) throws IOException {
        input();
        dp = new int[crossSum];
        Arrays.fill(dp, 100);
        dp[crossSum -1] = 0;
        determineLines(crossCount, crossSum - 1);

        printRes();
        closeIO();
    }
    public static int N;
    public static int lineCount = 0;
    public static int maxStickCount = 500;
    public static Point lines[] = new Point[maxStickCount];
    public static int crossCount[];
    public static int dp[];
    public static int crossSum = 0;

    public static void determineLines(int[] arrCount, int totalCount){


        int count = dp[totalCount];
        for(int k = 0 ; k < lineCount ; k++){
            if(arrCount[k] != 0 ){
                int newArr[] = Arrays.copyOf(arrCount, lineCount);
                int newCount = removeLine(newArr, k);

                if(dp[newCount] > count + 1){
                    dp[newCount] = count + 1;
                    determineLines(newArr, newCount);
                }
//                System.out.println(Arrays.toString(newArr));
//                System.out.println(newCount + " ");
//                System.out.println(count + "");
            }
        }
    }

    private static int removeLine(int[] arrCount, int k) {
        int sum=0;
        arrCount[k] = 0;
        for(int s = 0 ; s < lineCount ; s ++){
            if(arrCount[s] != 0) {
                if (lines[k].x < lines[s].x && lines[k].y > lines[s].y) {
                    arrCount[s]--;
                } else if (lines[k].x > lines[s].x && lines[k].y < lines[s].y) {
                    arrCount[s]--;
                }
            }
            sum += arrCount[s];
        }
        return sum;
    }


    public static void input() throws IOException {
        lineCount = Integer.parseInt(br.readLine());
        crossCount = new int [lineCount];

        for(int k = 0; k < lineCount ; k ++){
            st = new StringTokenizer(br.readLine());
            lines[k] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            for(int s = 0 ; s < k ; s ++){
                if(lines[k].x < lines[s].x && lines[k].y > lines[s].y){
                    crossCount[k] ++;
                    crossCount[s] ++;
                }else if(lines[k].x > lines[s].x && lines[k].y < lines[s].y){
                    crossCount[k] ++;
                    crossCount[s] ++;
                }
            }
        }

        crossSum = Arrays.stream(crossCount).sum();
    }

    public static void printRes() throws IOException {
//        System.out.println(Arrays.toString(dp));
        bw.write( dp[0] + "");
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
