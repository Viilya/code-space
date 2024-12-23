package com.ssafy.algorithmstudy.boj.boj1717;


import java.io.*;
import java.util.StringTokenizer;

/**
 * n+1 개의 집합
 * 0, 1, 2 ... ㅜ
 * 합집합 연산과 같은 집합에 포함되어 있는지를 확인하는 연산을 수행하려 한다.
 *
 * n m
 * 0 a b -> 합집합
 * 1 a b -> 확인하는 연산
 */

public class Main_1717_집합의표현 {

    public static int _N;
    public static int _M;
    public static int _union[];

    public static boolean isSameSet(int a, int b){

        return false;
    }


    // 두 집합을 합치는 함수
    public static void union(int a, int b){
        int aRoot = findParent(a);
        int bRoot = findParent(b);

        if(aRoot < bRoot){
            _union[bRoot] = aRoot;
        }else{
            _union[aRoot] = bRoot;
        }
    }

    // 부모를 찾는 함수
    public static int findParent(int a){
        if(_union[a] == a) return a;
        return _union[a] = findParent(_union[a]);
    }

    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine());

        _N = toInt(st.nextToken());
        _M = toInt(st.nextToken());

        _union = new int[_N + 1];
        for(int k = 0 ; k < _N+1 ; k ++){
            _union[k] = k;
        }


        for(int k = 0 ; k < _M ; k ++ ) {
            st = new StringTokenizer(br.readLine());
            int command = toInt(st.nextToken());
            int a = toInt(st.nextToken());
            int b = toInt(st.nextToken());


            if(command == 0){
                union(a, b);
//                System.out.println(Arrays.toString(_union));
            }
            if(command == 1){
                output(findParent(a)==findParent(b));
            }
        }
    }

    public static int toInt(String token){
        return Integer.parseInt(token);
    }
    public static void output(boolean res) throws IOException{
        bw.write(res?"YES":"NO");
        bw.newLine();
    }

    public static void closeIO() throws IOException{
        bw.flush();
        bw.close();
        br.close();
    }
    public static void main(String args[]) throws IOException {
        input();
        closeIO();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
