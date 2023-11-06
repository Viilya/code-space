package com.ssafy.algorithmstudy.boj.boj1744;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class Main_1744_수묶기 {
    public static void main(String args[]) throws IOException{
        input();
        solve();
        output();
    }

    static int N;
    static ArrayList<Integer> arr = new ArrayList<>();
    static int res = 0;

    public static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int k = 0 ; k < N ; k ++)
            arr.add(Integer.parseInt(br.readLine()));

        arr.sort(Comparator.comparingInt(c -> -c));
        LinkedList<Integer> arr = new LinkedList<>();

//        System.out.println(arr.toString());
    }

    public static void solve() {
        int k;
        int endPoint = 0;

        for (k = 0; k < N; k++) {
            if (arr.get(k) > 0) {
                if (k + 1 < N && arr.get(k + 1) > 1) {
                    res += arr.get(k) * arr.get(k++ + 1);
                }// 뒤에 수가 양수면 더하기 -> 다음 인덱스
                else {
                    res += arr.get(k);
                }// 아니면 그냥 여기까지만 더하기
            }
            else {
                endPoint = k;
                break;
            }  // 음수면 음수 지점 기록하고 빠져나오기

        }
        for (k = N - 1; k >= endPoint; k--) { // 맨 끝 부터 곱하기 (양수 반대)
            if (arr.get(k) <= 0) {
                if (k - 1 >= 0 && arr.get(k - 1) <= 0) {
                    res += arr.get(k) * arr.get(k-- - 1);
                }
                else {
                    res += arr.get(k);
                }
            }
        }
    }

    private static void output() {
        System.out.println("" + res);

    }
}
