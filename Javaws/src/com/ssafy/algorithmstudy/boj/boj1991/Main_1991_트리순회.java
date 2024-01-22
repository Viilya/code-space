package com.ssafy.algorithmstudy.boj.boj1991;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_1991_트리순회 {
    public static void main(String[] args) throws IOException {
        input();
        solve();
        closeIO();
    }
    private static int N;
    private static ArrayList<Integer> arr[];
    public static StringBuilder sb = new StringBuilder();
    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new ArrayList[N];
        for(int k = 0 ; k < N ; k ++){
            arr[k] = new ArrayList<>();
        }
        for(int k = 0 ; k < N ; k ++) {
            st = new StringTokenizer(br.readLine());

            int parent = (st.nextToken().charAt(0) - 'A');
            char leftChildChar = st.nextToken().charAt(0);
            char rightChildChar = st.nextToken().charAt(0);
            int leftChild = leftChildChar == '.' ? -1 : leftChildChar - 'A';
            int rightChild = rightChildChar == '.' ? -1 : rightChildChar - 'A';

            arr[parent].add(leftChild);
            arr[parent].add(rightChild);
        }
    }

    private static void solve() throws IOException{
        preorder(0);
        output();
        sb = new StringBuilder();
        inorder(0);
        output();
        sb = new StringBuilder();
        postorder(0);
        output();
    }



    public static void preorder(int curr) throws IOException{
        int left = arr[curr].get(0);
        int right = arr[curr].get(1);

        sb.append((char)(curr + 'A'));
        if(left != -1){
            preorder(left);
        }
        if(right != -1){
            preorder(right);
        }
    }
    public static void inorder(int curr) throws IOException{
        int left = arr[curr].get(0);
        int right = arr[curr].get(1);
        if(left != -1){
            inorder(left);
        }
        sb.append((char)(curr + 'A'));
        if(right != -1){
            inorder(right);
        }
    }
    public static void postorder(int curr) throws IOException{
        int left = arr[curr].get(0);
        int right = arr[curr].get(1);

        if(left != -1){
            postorder(left);
        }
        if(right != -1){
            postorder(right);
        }
        sb.append((char)(curr + 'A'));
    }
    private static void output() throws IOException {
        bw.write(sb.toString());
        bw.newLine();
    }

    private static void closeIO() throws IOException {
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
