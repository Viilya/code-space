package com.ssafy.algorithmstudy.boj.boj1786;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1786_찾기 {
    public static void main(String[] args) throws IOException {
        input();

        patternFind();
        findPattern();

        printRes();
        closeIO();
    }
    public static char[] str; // str 저장 배열
    public static char[] pattern; // 패턴 저장 배열
    public static int[] idx; // 패턴 idx 저장 배열

    public static int patternFoundCount = 0; // 패턴 찾은 횟수
    public static ArrayList<Integer> patternIdxList = new ArrayList<>();  // 패턴 찾은 idx

    /**
     * pattern에 인덱스 계산해서 idx 배열에 저장
     */
    public static void patternFind() {
        idx[0] = -1;
        for(int k = 1 ; k < pattern.length; k ++){
            int fail = idx[k-1];
            while((pattern[k] != pattern[fail + 1]) && (fail >= 0)) fail = idx[fail];
            if(pattern[k] == pattern[fail+1]) idx[k] = fail+1;
            else idx[k] = -1;
        }
    }


    /**
     * str 배열에 저장되어있는 문자열에서 KMP 알고리즘을 통해 패턴을 찾음
     * 이때, 패턴을 찾으면 ArrayList에 추가하고, 그 전 패턴으로 돌아감
     */
    public static void findPattern(){
        int patternIdx = 0;
        int strIdx = 0;
        int strLen = str.length;
        while(strIdx <= strLen){
            if(patternIdx == pattern.length){
                patternFoundCount ++;
                patternIdxList.add(strIdx);
                patternIdx = idx[patternIdx-1] + 1;
                continue;
            }
            if(strIdx == strLen) break;

             if(str[strIdx] == pattern[patternIdx]) { strIdx++; patternIdx++;}
             else if(patternIdx == 0) strIdx ++;
             else patternIdx = idx[patternIdx-1] + 1;
        }
    }

    public static void input() throws IOException {
        str = br.readLine().toCharArray();
        pattern = br.readLine().toCharArray();
        idx = new int[pattern.length];
    }

    public static void printRes() throws IOException {
        bw.write("" + patternFoundCount);
        bw.newLine();
        for(int idx : patternIdxList){
            bw.write((idx - pattern.length + 1) + " " );
        }
        bw.newLine();

        //System.out.println(Arrays.toString(idx));
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
