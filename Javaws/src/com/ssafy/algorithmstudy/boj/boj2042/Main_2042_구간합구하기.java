package com.ssafy.algorithmstudy.boj.boj2042;

import java.io.*;
import java.util.StringTokenizer;

public class Main_2042_구간합구하기 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N, M, K;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
        long tree[] = new long[N*4];
        long numbers[] = new long[N];

        for(int k = 0 ; k < N ; k ++){
           numbers[k] = Long.parseLong(br.readLine());
        }

        init(tree, 0, N-1, 1, numbers);

        //System.out.println(Arrays.toString(tree));
        for(int k = 0 ; k < M; k ++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Long c = Long.parseLong(st.nextToken());

            if(a == 1){

                update(tree, 1, 0, N-1, b-1, numbers[b-1] - c);
                numbers[b-1] = c;
               // System.out.println(Arrays.toString(tree));

            }else if(a == 2){
                prLongRes(getSum(tree, 1, 0, N-1, b-1, (int)(c-1)));
            }
        }
    }

    private static Long init(long tree[], int start, int end, int n, long numbers[]) {
        if(start == end) {
            return tree[n] = numbers[start];
        }
        else if(start > end){
            return 0L;
        }
        return tree[n] = init(tree, start, (start + end) / 2, n * 2, numbers)
                    + init(tree, (start+end) / 2 + 1 , end, n * 2 +1 , numbers);
    }

    private static void update(long tree[], int n, int start, int end, int idx, long diff){
        if(start > idx || end < idx){
            return;
        }
        tree[n] -= diff;

        if(start == end) {
            return;
        }
        update(tree, n*2, start, (start + end) / 2, idx, diff);
        update(tree, n*2+1, (start+end)/2 +1, end, idx, diff);
    }

    private static long getSum(long tree[], int n, int start, int end, int left, int right){
        if(left <= start && end <= right){
            return tree[n];
        }
        if(right <  start || end < left){
            return 0L;
        }
        return getSum(tree, n*2, start, (start + end) / 2, left, right)
                + getSum(tree, n*2+1, (start+end)/2 +1, end, left, right);
    }

    public static void prLongRes(long sum) throws IOException {
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
