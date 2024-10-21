package com.ssafy.algorithmstudy.boj.boj5427;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int time;
    int w;
    int h;

    @Override
    public String toString() {
        return "Node{" +
                "time=" + time +
                ", w=" + w +
                ", h=" + h +
                '}';
    }

    public Node(int time, int h, int w) {
        this.time = time;
        this.w = w;
        this.h = h;
    }
}

public class Main_5427_불 {
    private static int W, H;
    private static char _map[][] = new char[1000][1000];
    private static int dh[] = {-1, 0, 1, 0};
    private static int dw[] = {0, 1, 0, -1};
    private static Queue<Node> _fire = new ArrayDeque<Node>();
    public static void main(String[] args) throws IOException {
        input();
        closeIO();
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        int T = toInt(st.nextToken());

        for(int k = 0 ; k < T ; k ++){
            st = new StringTokenizer(br.readLine());
            W = toInt(st.nextToken());
            H = toInt(st.nextToken());
            _fire.clear();
            int initialX = 0, initialY = 0;
            for(int s = 0 ; s < H ; s ++){
                String line = br.readLine();
                for(int i = 0 ; i < W ; i ++){
                    char c = line.charAt(i);
                    if(c== '@') {
                        initialX = s;
                        initialY = i;
                    }
                    if(c== '*'){
                        _fire.add(new Node(0, s, i));
                    }
                    _map[s][i] = c;
                }
            }

            output(solve(initialX, initialY));
        }
    }

    private static int toInt(String str){
        return Integer.parseInt(str);
    }

    private static int solve(int initX, int initY) throws IOException {
        Queue<Node> queue = new ArrayDeque<Node>();
        boolean isVisted[][] = new boolean[H][W];
        isVisted[initX][initY] = true;
        int currentTime = 0;
        queue.add(new Node(0, initX, initY));
        while(!queue.isEmpty()){
            Node currentNode = queue.poll();


            if(currentNode.time != currentTime){
                spreadFire(currentTime);
                currentTime = currentNode.time;
            }

            if(_map[currentNode.h][currentNode.w] == '*') continue;

            for(int k = 0 ; k < 4 ; k ++){
                int nexth = currentNode.h + dh[k];
                int nextw = currentNode.w + dw[k];

                if(isEscapable(nexth, nextw)){
//                    System.out.println(nexth + " " + nextw);
                    return(currentTime + 1);
                }
                if(isAvailable(nexth, nextw) && !isVisted[nexth][nextw]){
                    queue.add(new Node(currentNode.time + 1, nexth, nextw));
                    isVisted[nexth][nextw] = true;
                }

            }



            
            
        }
        return -1;
    }

    public static void spreadFire(int time){
        while(!_fire.isEmpty()){
            if(_fire.peek().time != time){
                return;
            }

            Node currFire = _fire.poll();

            for(int i = 0 ; i < 4 ; i ++){
                int nextFireH = currFire.h + dh[i];
                int nextFireW = currFire.w + dw[i];
                if(isSpreadable(nextFireH, nextFireW)){
                    _map[nextFireH][nextFireW] = '*';
                    _fire.add(new Node(currFire.time +1, nextFireH, nextFireW));
                }
            }
        }


    }
    public static boolean isSpreadable(int h, int w){
        return h >= 0 && h < H && w >= 0 && w < W && (_map[h][w] == '.' || _map[h][w] == '@');
    }
    public static boolean isAvailable(int h, int w){
        return _map[h][w] != '*' && _map[h][w] != '#';
    }

    public static boolean isEscapable(int h, int w){
        return h < 0 || h >= H || w < 0 || w >= W;
    }


    private static void output(int result) throws IOException {
        bw.write((result==-1)?"IMPOSSIBLE":result + "");
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
