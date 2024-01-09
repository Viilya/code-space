package com.ssafy.algorithmstudy.boj.boj16120;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 */
public class Main_16120_PPAP {

    public static void main(String args[]) throws Exception{
        input();
    }

    private static void print(String res) throws Exception{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        System.out.println(res);
        if(res.equals("P")){
            bw.write("PPAP");
        }else{
            bw.write("NP");
        }
        bw.flush();
        bw.close();
    }

    private static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Deque<Character> dq = new ArrayDeque<>();

        for(int k = str.length() - 1 ; k >= 0; k--) {
            dq.offerLast(str.charAt(k));
            StringBuilder sb = new StringBuilder();
            if(dq.size() >= 4){
                for(int s = 0 ; s< 4; s++){
                    sb.append(dq.pollLast());
                }
                if(sb.toString().equals("PPAP")){
                    dq.offerLast('P');
                }else{
                    for(int s= 3 ; s >= 0 ; s --){
                        dq.offerLast(sb.charAt(s));
                    }
                }
            }
//            System.out.println(sb.toString());
//            System.out.println(" dq : " + dq.toString());

        }
        br.close();
        StringBuilder sb = new StringBuilder();
        int size = dq.size();
        for(int k = 0 ; k < size; k++){
            sb.append(dq.pollFirst());
        }
//        System.out.println(sb.toString());
        print(sb.toString());
    }
}
