package com.ssafy.algorithmstudy.boj.boj2841;

import java.io.*;
import java.util.*;


/**
 * 1~6개의 줄 P 개의 프랫
 * 여러개 프랫이면 가장 높은 프렛의 음
 * 프렛을 누르거나 떼는 걸 한번
 *
 * 음의 수 N / 프렛의 수 P
 */

class Main_2841_외계인의기타연주{
    public static int _N;
    public static int _P;
    public static Stack<Integer> _strings[] = new Stack[6];
    public static int _fingerMoveCount = 0;


    public static void main(String[] args) throws IOException {
        input();
        output();
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        _N = Integer.parseInt(st.nextToken());
        _P = Integer.parseInt(st.nextToken());
        for(int k = 0 ; k < 6 ; k++){
            _strings[k] = new Stack<Integer>();
        }
        for (int i = 0; i < _N; i++) {
            st = new StringTokenizer(br.readLine());
            int string = Integer.parseInt(st.nextToken());
            int plat = Integer.parseInt(st.nextToken());
            solve(string-1, plat-1);
        }
    }

    public static void solve(int string, int plat) throws IOException {
        while(true){
            if(_strings[string].isEmpty()){
                break;
            }

            if(plat < _strings[string].peek()){
                _strings[string].pop();
                _fingerMoveCount ++;
            }else if(plat == _strings[string].peek()) {
                _strings[string].pop();
                _fingerMoveCount --;
                break;
            }else{
                break;
            }
        }

        _fingerMoveCount ++;
        _strings[string].push(plat);

    }

    public static void output() throws IOException {
        bw.write("" + _fingerMoveCount);
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}