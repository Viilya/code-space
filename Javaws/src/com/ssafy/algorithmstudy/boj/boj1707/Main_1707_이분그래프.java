package com.ssafy.algorithmstudy.boj.boj1707;

import java.io.*;
import java.util.*;

public class Main_1707_이분그래프 {
    public static void main(String args[]) throws IOException {
        inputAndSolve();
        ioClose();
    }

    static class Node{
        int v;
        int color;

        public Node(int v, int color){
            this.v = v;
            this.color = color;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "v=" + v +
                    ", color=" + color +
                    '}';
        }
    }

    public static ArrayList<Integer> adjArr[];
    public static int E;
    public static int V;

    public static void inputAndSolve() throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int k = 0 ; k < T; k ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            adjArr = new ArrayList[V];
            for(int s = 0 ; s < V ; s ++){
                adjArr[s] = new ArrayList<Integer>();
            }
            for(int s = 0 ; s < E ; s ++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adjArr[a-1].add(b-1);
                adjArr[b-1].add(a-1);
            }

            //System.out.println(Arrays.toString(adjArr));

            output(solve()?"YES":"NO");
        }
    }

    public static boolean solve(){
        Deque<Node> dq = new ArrayDeque<>();
        int vortexColor[] = new int[V];
        for(int k = 0 ; k < V ; k ++) {
            if(vortexColor[k] == 0){
                dq.offerLast(new Node(k, 1));

                vortexColor[k] = 1;
                while(!dq.isEmpty()){
//                    System.out.println(Arrays.toString(dq.toArray()));
//                    System.out.println(Arrays.toString(vortexColor));
                    Node currNode = dq.pollFirst();

//                    System.out.println(adjArr[currNode.v].size() + "");
                    for(int s = 0 ; s < adjArr[currNode.v].size() ; s ++){
                        int color = currNode.color==1?2:1;

                        if(vortexColor[adjArr[currNode.v].get(s)] == 0){
                            dq.offerLast(new Node(adjArr[currNode.v].get(s), color));
                            vortexColor[adjArr[currNode.v].get(s)] = color;
                        }

                        if(vortexColor[adjArr[currNode.v].get(s)] == currNode.color){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void output(String result) throws IOException{
        bw.write(result);
        bw.newLine();
    }

    public static void ioClose() throws IOException{
        bw.flush();
        bw.close();
        br.close();
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
}
