package com.ssafy.algorithmstudy.boj.boj11658;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11658_구간합구하기3 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N, M, K;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int tree[][] = new int[N*4][N*4];
        int numbers[][] = new int[N][N];

        for(int k = 0 ; k < N ; k ++){
           numbers[k] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            init(tree, k, 0,N-1, 1, numbers);
        }



        //System.out.println(Arrays.toString(tree));
        for(int k = 0 ; k < M; k ++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());

            if (a == 0) {

                int b = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                int d = Integer.parseInt(st.nextToken());
                update(tree, b, 1, 0, N - 1, c, numbers[b][c] - d);
                numbers[b][c] = d;
                // System.out.println(Arrays.toString(tree));

            } else if (a == 1) {

                int b = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                int d = Integer.parseInt(st.nextToken()) - 1;
                int e = Integer.parseInt(st.nextToken()) - 1;
                int sum = 0;
                for (int s = b; s <= d; s++) {
                    sum += getSum(tree, s, 1, 0, N - 1, c, e);
                }
                printRes(sum);
            }
//            for (int t = 0; t < N; t++) {
//                System.out.println(Arrays.toString(tree[t]));
//            }
//            System.out.println(
//            );
        }
    }

    private static int init(int tree[][], int line, int start, int end, int n, int numbers[][]) {
        if(start == end) {
            return tree[line][n] = numbers[line][start];
        }
        else if(start > end){
            return 0;
        }
        return tree[line][n] = init(tree, line, start, (start + end) / 2, n * 2, numbers)
                    + init(tree, line, (start+end) / 2 + 1 , end, n * 2 +1 , numbers);
    }

    private static void update(int tree[][], int line, int n, int start, int end, int idx, int diff){
        if(start > idx || end < idx){
            return;
        }
        tree[line][n] -= diff;

        if(start == end) {
            return;
        }
        update(tree, line, n*2, start, (start + end) / 2, idx, diff);
        update(tree, line, n*2+1, (start+end)/2 +1, end, idx, diff);
    }

    private static int getSum(int tree[][], int line, int n, int start, int end, int left, int right){
        if(left <= start && end <= right){
            return tree[line][n];
        }
        if(right <  start || end < left){
            return 0;
        }
        return getSum(tree, line, n*2, start, (start + end) / 2, left, right)
                + getSum(tree, line, n*2+1, (start+end)/2 +1, end, left, right);
    }

    public static void printRes(int sum) throws IOException {
        bw.write(sum + "");
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
