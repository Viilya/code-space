package com.ssafy.algorithmstudy.boj.boj1493;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class boj1493 {
    static int length, width, height;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        length = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        int n = Integer.parseInt(br.readLine());
        Cubes[] cubes = new Cubes[n];     
        for (int k = 0; k < n; k++) {
            st = new StringTokenizer(br.readLine());
            cubes[k].setCube(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
    }
}
class Cubes {
    public int size;
    public int ea;

    public Cubes(){}
    public void setCube(int size, int ea) {
        this.size = size;
        this.ea = ea;
    }
}