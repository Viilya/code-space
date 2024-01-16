package com.ssafy.algorithmstudy.boj.boj12919;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_12919_A와B2 {

    public static void main(String[] args) throws IOException {
        input();
        solve();
        closeIO();
    }

    static class Node {
        boolean bDiff;
        StringBuilder str;

        public Node( StringBuilder str, boolean bDiff) {
            this.bDiff = bDiff;
            this.str = str;
        }
    }

    public static StringBuilder sb1 = new StringBuilder();
    public static StringBuilder sb2 = new StringBuilder();
    public static int bDiffCount = 0;

    private static void input() throws IOException{
        sb1.append(br.readLine());
        sb2.append(br.readLine());
    }

    private static void solve() throws IOException{
        for(int k = 0 ; k < sb2.length(); k++){
            if(sb2.charAt(k) == 'B') bDiffCount += 1;
        }
        for(int k = 0 ; k < sb1.length(); k++){
            if(sb1.charAt(k) == 'B') bDiffCount -= 1;
        }

        //System.out.println("bDiffCount : "+ bDiffCount);

        output(checkAvailableByBFS());

    }

    private static boolean checkAvailableByBFS() {

        Deque<Node> dq = new ArrayDeque<>();

        dq.offerLast(new Node(sb1, bDiffCount%2==1));

        while(!dq.isEmpty()){
            Node curr = dq.pollFirst();
            //System.out.println(curr.str + " / curr.b : " + curr.bDiff);
            if(sb2.toString().contentEquals(curr.str)) {
                return true;
            }
            StringBuilder tmp = new StringBuilder(curr.str);
            if (curr.bDiff) {
                tmp.reverse();
            }

            if(sb2.indexOf(tmp.toString()) != -1){
                if(curr.bDiff) {
                    tmp.reverse();
                }
                dq.offerLast(new Node(tmp.append("A"), curr.bDiff));
                dq.offerLast(new Node(new StringBuilder(curr.str.append("B").reverse()), !curr.bDiff));
            }
        }

        return false;
    }

    private static void output(boolean result) throws IOException{
        bw.write(result?"1":"0");
        bw.newLine();
    }
    private static void closeIO() throws IOException{
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;


}
