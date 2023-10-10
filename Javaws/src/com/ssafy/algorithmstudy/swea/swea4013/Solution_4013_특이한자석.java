package com.ssafy.algorithmstudy.swea.swea4013;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_4013_특이한자석 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }


    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<= T ; tc++){
            LinkedList<Integer> magnets[] = new LinkedList[4];
            int totalRotate = Integer.parseInt(br.readLine());
            int totalScore = 0;
            for(int k = 0 ; k < 4 ; k ++){
                magnets[k] = new LinkedList<>();
                st = new StringTokenizer(br.readLine());
                for(int s = 0 ; s < 8 ; s++){
                    magnets[k].add(Integer.parseInt(st.nextToken()));
                }
            }
//            for(int k = 0 ; k < 4 ; k ++){
//                System.out.println(magnets[k].toString());
//            }
//            System.out.println();
            for(int k = 0 ; k < totalRotate; k++) {
                st = new StringTokenizer(br.readLine());

                int wheel = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());

                rotateWheel(magnets, wheel-1, dir, 0);
//                for(int s = 0 ; s < 4 ; s++){
//                    System.out.println(magnets[s].toString());
//                }
//                System.out.println();
            }



//            System.out.println();
            printRes(tc, calcScore(magnets));

        }
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


    public static void printRes(int tc, int totalScore) throws IOException {
        bw.write("#" + tc + " " + totalScore);
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
