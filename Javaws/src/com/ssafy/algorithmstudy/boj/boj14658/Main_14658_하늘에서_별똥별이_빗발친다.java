package com.ssafy.algorithmstudy.boj.boj14658;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_14658_하늘에서_별똥별이_빗발친다 {
    public static void main(String[] args) throws IOException {
        input();
        setTramplin();
        printRes();
        closeIO();
    }
    public static int N, M, L, K;
    public static Coor stars[];
    public static int maxGuardedStars = 0;

    public static void setTramplin(){
        for(int k = 0 ; k < K ; k ++){
            countStarsX(stars[k]);
        }
    }
    public static void countStarsX(Coor star){
        int limitX = star.x + L;
        Coor Group[] = new Coor[K];
        Group[0] = star;
        int count = 0;
        for(int k = 0 ; k < K; k ++) {
            if(stars[k].x >= star.x && stars[k].x <= limitX) {
                Group[count++] = stars[k];
            }
        }
        Coor starGroup[] = new Coor[count];
        for(int k = 0 ; k < count ; k ++){
            starGroup[k] = Group[k];
        }
        Arrays.sort(starGroup, Comparator.comparingInt(o -> o.y));
        countStarsY(starGroup, count);
    }

    public static void countStarsY(Coor[] starGroup, int size){
        for(int k = 0 ; k < size; k ++){
            int thisCount = 0;
            for(int s = k ; s < size; s++){
                if(starGroup[s].y <= starGroup[k].y + L ){
                    thisCount += 1;
                }
            }
            maxGuardedStars = Math.max(thisCount, maxGuardedStars);
        }
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        stars = new Coor[K];
        for(int k = 0; k < K; k ++){
            st= new StringTokenizer(br.readLine());
            stars[k] = new Coor(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(stars);
    }

    public static void printRes() throws IOException {
        bw.write(String.valueOf(K - maxGuardedStars));
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

class Coor implements Comparable<Coor> {
    int x;
    int y;

    public Coor(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Corr{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public int compareTo(Coor o) {
        if(x > o.x){
            return 1;
        }
        else if(x < o.x){
            return -1;
        }
        else{
            if(y > o.y){
                return 1;
            }else{
                return -1;
            }
        }
    }


}