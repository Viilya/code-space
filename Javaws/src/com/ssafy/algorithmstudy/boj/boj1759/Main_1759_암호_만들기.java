package com.ssafy.algorithmstudy.boj.boj1759;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * L개의 알파벳 소문자
 * 최소 한개의 모음(a, e, i, o, u), 최소 두개의 자음
 * 알파벳이 암호에서 증가하는 순서로 배열
 * abc 는 가능성, bac 는 그렇지 않다.
 * 사용했을 법한 문자의 종류는 C가지
 * C 개의 문자 -> 가능성 있는 암호들을 모두 구하는 프로그램
 */

// 도현왔다감
public class Main_1759_암호_만들기 {
    public static void main(String[] args) throws IOException {
        input();
        Comb(0, 0, 0, -1, new StringBuilder());
        printRes();
        closeIO();
    }
    public static int L, C;
    public static char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    public static char[] alphs;
    public static void input() throws IOException {

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        alphs = new char[C];
        st = new StringTokenizer(br.readLine());
        for(int k = 0 ; k < C ; k ++){
            alphs[k] = st.nextToken().charAt(0);
        }
        Arrays.sort(alphs);
    }

    public static void Comb(int cnt, int vowelCount, int consonantCount, int formalAlphs, StringBuilder stack) throws IOException{
        if(cnt == L && vowelCount >= 1 && consonantCount >= 2){
            String str = stack.toString();
            bw.write(stack.toString() );
            bw.newLine();
        }
        for(int k = formalAlphs+1 ; k < C; k ++){
            boolean isVowel = false;
            for(int s = 0 ; s < vowels.length; s ++){
                if(alphs[k] == vowels[s]){
                    isVowel = true;
                    Comb(cnt + 1, vowelCount + 1, consonantCount, k, stack.append(alphs[k]));
                    stack.deleteCharAt(cnt);
                }
            }
            if(!isVowel){
                Comb(cnt + 1, vowelCount, consonantCount +1, k, stack.append(alphs[k]));
                stack.deleteCharAt(cnt);
            }
        }
    }
    public static void printRes() throws IOException{
        bw.write("");
    }
    public static void closeIO() throws IOException{
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
