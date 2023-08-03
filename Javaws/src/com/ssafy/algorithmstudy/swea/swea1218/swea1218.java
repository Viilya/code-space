package com.ssafy.algorithmstudy.swea.swea1218;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class swea1218 {
    public static Deque<Character> dq = new ArrayDeque<Character>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        

        for (int test_case = 1; test_case <= 10; test_case++) {
            int n = Integer.parseInt(br.readLine());
            String brackets = br.readLine();
            //System.out.println(brackets);
            bw.write("#" + String.valueOf(test_case) + " ");
            dq.clear();
            if (isValidBracket(brackets, n)) {
                bw.write("1");
            } else {
                bw.write("0");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();

    }

    public static boolean isValidBracket(String brackets, int n) {
        for (int k = 0; k < n; k++) {
            char c = brackets.charAt(k);
            char t;
            switch (getBracketIdx(c)) {
                case 0:
                case 1:
                case 2:
                case 3:
                    dq.addLast(c);
                    break;
                case 4:
                    t = dq.pollLast();
                    if (t != '[' || t == '\0')
                        return false;
                    break;
                case 5:
                    t = dq.pollLast();
                    if (t != '{' || t == '\0')
                        return false;
                    break;
                case 6:
                    t = dq.pollLast();
                    if (t != '(' || t == '\0')
                        return false;
                    break;
                case 7:
                    t = dq.pollLast();
                    if (t != '<' || t == '\0')
                        return false;
                    break;
            }
        }
        return true;        
    }
    public static int getBracketIdx(char bracket){
        if(bracket == '[')
            return 0;
        if(bracket == '{')
            return 1;
        if(bracket == '(')
            return 2;
        if (bracket == '<')
            return 3;
        if(bracket == ']')
            return 4;
        if(bracket == '}')
            return 5;
        if(bracket == ')')
            return 6;
        if(bracket == '>')
            return 7;
        else 
            return -1;
    }
}
