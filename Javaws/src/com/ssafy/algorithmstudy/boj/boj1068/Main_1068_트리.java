package com.ssafy.algorithmstudy.boj.boj1068;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 트리 리프노트 자식개수 0
 * 노드 하나를 지우고 남은 트리에서 리프 노드의 개수를 구하는 프로그램
 * 노드를 지우면 노드와 모든 노드 자손이 트리에서 제거
 *
 * 개수가 N
 * 0 ~ N-1 (각 노드의 부모 , 없으면 루트 -1)
 * 지울 노드 번호
 *
 *
 */
public class Main_1068_트리 {
    static class Node{
        Node parent;

        @Override
        public String toString() {
            return "{" +
                    ", childList=" + childList +
                    ", isDeleted=" + isDeleted +
                    '}';
        }

        Deque<Integer> childList;
        boolean isDeleted = false;

        public Node() {
            childList = new ArrayDeque<>();
        }
    }
    public static Node tree[];
    public static int N;
    public static int result = 0 ;
    public static int rootNodeIdx = 0;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
        closeIO();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        tree = new Node[N];

        for(int k = 0 ; k < N ; k ++){
            tree[k] = new Node();
        }

        st = new StringTokenizer(br.readLine());
        for(int k = 0 ; k < N ; k ++) {
            int parent = Integer.parseInt(st.nextToken());
            if(parent!=-1){
                tree[k].parent = tree[parent];
                tree[parent].childList.add(k);
            }else{
                rootNodeIdx = k;
            }
        }
//        System.out.println(Arrays.toString(tree));


        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            int deleteNode = Integer.parseInt(st.nextToken());

            Node parentNode = tree[deleteNode].parent;
            tree[deleteNode].isDeleted = true;
            if(parentNode == null) continue;
            parentNode.childList.remove(deleteNode);

        }
//        System.out.println(Arrays.toString(tree));
    }

    private static void solve() {
        Deque<Integer> q = new ArrayDeque<>();
//        System.out.println(Arrays.toString(tree));

        q.add(rootNodeIdx);
        while(!q.isEmpty()){
            int curr = q.poll();
            Node currNode = tree[curr];
//            System.out.println(currNode);
            if(currNode.isDeleted) continue;
            if(currNode.childList.isEmpty()){
                result ++; continue;
            }
            while(!currNode.childList.isEmpty()){
                q.add(currNode.childList.poll());
            }
        }

    }

    private static void output() throws IOException {
        bw.write("" + result);
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
