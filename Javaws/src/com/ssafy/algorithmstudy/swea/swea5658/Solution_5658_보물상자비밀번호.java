package com.ssafy.algorithmstudy.swea.swea5658;

import java.io.*;
import java.util.*;

public class Solution_5658_보물상자비밀번호 {
    public static void main(String[] args) throws IOException {
        input();

        closeIO();
    }
    public static int N, K;
    public static LinkedList<Character> arr[] = new LinkedList[4];
    public static TreeSet<String> set;

    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= T ; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            char line[] = br.readLine().toCharArray();
            for(int k = 0 ; k < 4 ; k++){
                arr[k] = new LinkedList<Character>();
                for(int s = 0 ; s< (N>>2) ; s++){
                    arr[k].addLast(line[(N>>2) * k + s]);
                }
            }
            set = new TreeSet<>();
            rotate();
            String numbers[] = set.toArray(new String[0]);

            String ansString = numbers[numbers.length - K];
            //System.out.println(ansString);

            int pow = 0;
            int ansInt =0;
            for(int k = ansString.length()-1 ; k >= 0; k--){
                if(ansString.charAt(k) <='9'){
                    ansInt += Math.pow(16, pow) * (ansString.charAt(k)- '0');
                }else{
                    ansInt += Math.pow(16, pow) * (ansString.charAt(k)- 'A' + 10);
                }
                pow += 1;
            }

            printRes(tc, ansInt);
        }
    }

    public static void rotate(){

        addHex();

        for(int k = 1 ; k < (N>>2); k++){
            for(int s = 0 ; s< 4; s++){
                arr[(s+1)%4].addFirst(arr[s%4].pollLast());
            }
            addHex();
        }
    }

    public static void addHex(){
        for(int k = 0 ; k < 4 ; k ++){
            StringBuilder sb = new StringBuilder();
            for(char c : arr[k]){
                sb.append(c);
            }
            set.add(sb.toString());
        }

    }

    public static void printRes(int tc, int res) throws IOException {
        bw.write("#" + tc + " " + res);
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
