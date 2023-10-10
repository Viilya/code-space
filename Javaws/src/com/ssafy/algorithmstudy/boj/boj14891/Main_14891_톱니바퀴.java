package com.ssafy.algorithmstudy.boj.boj14891;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_14891_톱니바퀴 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }


    public static void input() throws IOException {
        LinkedList<Integer> magnets[] = new LinkedList[4];
        for(int k = 0 ; k < 4 ; k ++){
            magnets[k] = new LinkedList<>();
            for(int s = 0 ; s < 8 ; s++){
                magnets[k].add(br.read() - '0');
            }
            br.readLine();
        }

        int totalRotate = Integer.parseInt(br.readLine());
        for(int k = 0 ; k < totalRotate; k++) {
            st = new StringTokenizer(br.readLine());

            int wheel = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            rotateWheel(magnets, wheel-1, dir, 0);
        }



//            System.out.println();
        printRes(calcScore(magnets));


    }


    private static void rotateWheel(LinkedList<Integer>[] magnets, int wheel, int dir, int mask) {
        mask = (mask|(1<<wheel));
        if(wheel - 1 >= 0 && (mask & (1<<(wheel-1))) != (1<<(wheel-1)) ){
            if(magnets[wheel-1].get(2) + magnets[wheel].get(6) == 1){
                rotateWheel(magnets, wheel - 1, (dir==-1?1:-1), mask);
            }
        }
        if(wheel + 1 < 4 && (mask & (1<<(wheel+1))) != (1<<(wheel+1)) ){
            if(magnets[wheel].get(2) + magnets[wheel+1].get(6) == 1){
                rotateWheel(magnets, wheel + 1, (dir==-1?1:-1), mask);
            }
        }

        if(dir == 1){
            magnets[wheel].addFirst(magnets[wheel].pollLast());
        }else{
            magnets[wheel].addLast(magnets[wheel].pollFirst());
        }
    }

    private static int calcScore(LinkedList<Integer>[] magnets) {
        int sum = 0;
        for(int k = 0 ; k < 4;  k++){
            sum += (magnets[k].get(0)==1)?Math.pow(2, k):0;
        }
        return sum;
    }


    public static void printRes(int totalScore) throws IOException {
        bw.write("" + totalScore);
        bw.newLine();
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
