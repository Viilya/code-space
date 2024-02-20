package com.ssafy.algorithmstudy.boj.boj14606;

import java.io.*;

public class Main_14606_피자Small {
    public static void main(String args[]) throws IOException {
        input();
        solve();
        output();
        bw.close();
        br.close();
    }

    public static long N ;
    public static long RESULT = 0;

    public static void input() throws IOException{
        N = Long.parseLong(br.readLine());
    }

    public static void solve() {

        divideAndConquer(N);
        divideAndConquer(N);

    }

    public static void divideAndConquer(long n){
        long a = n/2;
        long b = n - a;

        RESULT += a * b;

        if(a > 1) divideAndConquer(a);
        if(b > 1) divideAndConquer(b);
    }

    public static void output() throws IOException{
        bw.write(RESULT + "");
        bw.flush();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
}


/*


5
2 3 / 6
1 1 1 2 / 2 + 1
1 1 / 1



8
4 4 / 16
2 2 2 2 / 8
1 1 1 1 1 1 1 1 / 4

 */