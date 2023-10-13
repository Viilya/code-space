package com.ssafy.algorithmstudy.boj.boj10999;

import java.io.*;
import java.util.StringTokenizer;

public class Main_10999_구간합구하기2 {
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N, M, K;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
//        long tree[] = new long[N*4];
        long numbers[] = new long[N];
        node tree[] = new node[N*4];
        for(int k = 0 ; k < N ; k ++){
           numbers[k] = Long.parseLong(br.readLine());
           for(int s = 0 ; s < 4; s ++){
               tree[ 4 * k + s] = new node();
           }
        }

        init(tree, 0, N-1, 1, numbers);

//        System.out.println(Arrays.toString(tree));
        for(int k = 0 ; k < M; k ++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());

            if(a == 1){

                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                Long d = Long.parseLong(st.nextToken());
                update(tree, 1, 0, N-1, b-1, c-1, d);
                numbers[b-1] = c;
//                System.out.println(Arrays.toString(tree));

            }else if(a == 2){

                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                prLongRes(getSum(tree, 1, 0, N-1, b-1, (int)(c-1)));
            }
        }
    }

    private static long init(node tree[], int start, int end, int n, long numbers[]) {
        if(start == end) {
            return tree[n].num = numbers[start];
        }
        else if(start > end){
            return 0L;
        }
        return tree[n].num = init(tree, start, (start + end) / 2, n * 2, numbers)
                    + init(tree, (start+end) / 2 + 1 , end, n * 2 +1 , numbers);
    }

    private static void update(node tree[], int n, int start, int end, int left, int right, long diff){

        if(tree[n].lazy != 0){
            tree[n].num += (end-start+1) * tree[n].lazy;
            if(start!=end){
                tree[n*2].lazy += tree[n].lazy;
                tree[n*2 + 1].lazy += tree[n].lazy;
            }
            tree[n].lazy = 0;
        }

        if(start > right || end < left){
            return;
        }
        if(left <= start && end <= right){
            tree[n].num += (end - start + 1) * diff;
            if(start != end){
                tree[n*2].lazy += diff;
                tree[n*2+1].lazy += diff;
            }
            return;
        }
        update(tree, n*2, start, (start + end) / 2, left, right, diff);
        update(tree, n*2+1, (start+end)/2 +1, end, left, right, diff);
        tree[n].num = tree[n*2].num + tree[n*2+1].num;
    }

    private static long getSum(node tree[], int n, int start, int end, int left, int right){
        if(tree[n].lazy != 0){
            tree[n].num += (end-start+1) * tree[n].lazy;
            if(start!=end){
                tree[n*2].lazy += tree[n].lazy;
                tree[n*2 + 1].lazy += tree[n].lazy;
            }
            tree[n].lazy = 0;
        }
        if(left <= start && end <= right){
            return tree[n].num;
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

    static class node{
        long num;
        long lazy = 0;

        public node(int num){
            this.num = num;
        }
        public node(){}
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
