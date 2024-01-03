package com.ssafy.algorithmstudy.boj.boj16637;
import java.io.*;

public class Main_16637_괄호추가하기 {

    public static void main(String args[]) throws IOException {
        input();
        solve();
        output();
        ioclose();
    }

    private static void output() throws IOException {
        bw.write("" + max_result);
        bw.flush();
    }

    private static void ioclose() throws IOException{
        bw.close();
        br.close();
    }
    public static int bracket_result[];
    public static String line;
    public static int max_result = Integer.MIN_VALUE;
    public static int line_len=0;

    private static void input() throws IOException{
        String n = br.readLine();
        line = br.readLine();
    }

    public static void solve(){
        line_len = line.length() - 1;
        bracket_result = new int[line_len / 2 + 1];
        for(int k = 0 ; k < line_len-1; k=k+2){
            int a = charToInt(line.charAt(k));
            int b = charToInt(line.charAt(k+2));
            bracket_result[k/2] = getBracketResult(a, b, line.charAt(k+1));
        }

        brute(0, charToInt(line.charAt(0)));
    }

    public static void brute(int curr_idx, int res){
        if(curr_idx >= line_len){
            max_result = Math.max(res, max_result);
            return;
        }
        brute(curr_idx + 2, getBracketResult(res, charToInt(line.charAt(curr_idx+2)), line.charAt(curr_idx+1)));
        if(curr_idx < line_len - 2) {
            brute(curr_idx + 4, getBracketResult(res, bracket_result[curr_idx / 2 + 1], line.charAt(curr_idx+1)));
        }
    }

    private static int charToInt(char num){
        return (int)num - (int)'0';
    }

    private static int getBracketResult(int a, int b, char op){
        if(op == '+'){
            return a + b;
        }else if(op =='-'){
            return a - b;
        }else{
            return a * b;
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

}