package com.ssafy.algorithmstudy.boj.boj12908;

import java.io.*;
import java.util.StringTokenizer;

public class Main_12908_텔레포트3 {
    public static void main(String args[]) throws IOException {
        input();

        Comb(0,  S, 0);

        printResult();


    }

    public static int dx[] = {-1, 0, 1, 0};
    public static int dy[] = {0, 1, 0, -1};
    public static long minDistance = 0;


    public static Point S ;
    public static Point E ;
    public static Teleport tp[] = new Teleport[3];

    private static void Comb(int count, Point curr, long dist){
        if(count >= 3) {
            long finalDist = dist + getDistance(curr.x, curr.y , E.x, E.y);
            minDistance = Math.min(finalDist, minDistance);
            return;
        }
        for(int k = 0 ; k < 3; k++){

            long thisDistance1 = dist + getDistance(curr.x, curr.y, tp[k].x1, tp[k].y1) +
                    getDistance(E.x, E.y, tp[k].x2, tp[k].y2) + 10;

            if(thisDistance1 < minDistance) {
                minDistance = thisDistance1;
            }

            Comb(count + 1, new Point(tp[k].x2, tp[k].y2),
                    dist + getDistance(curr.x, curr.y, tp[k].x1, tp[k].y1) + 10);


            long thisDistance2 = dist + getDistance(curr.x, curr.y, tp[k].x2, tp[k].y2) +
                    getDistance(E.x, E.y, tp[k].x1, tp[k].y1) + 10;

            if(thisDistance2 < minDistance) {
                minDistance = thisDistance2;
            }

            Comb(count + 1, new Point(tp[k].x1, tp[k].y1),
                    dist + getDistance(curr.x, curr.y, tp[k].x2, tp[k].y2) + 10 );

            //System.out.println("count = " + count + " Point x, y : " + curr.x + ", " + curr.y);
            //System.out.println("k = " + k + " dist1 : " + thisDistance1 + " dist2 : " + thisDistance2);
        }
    }

    private static int getDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        E = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        for(int k = 0 ; k < 3; k ++){
            st = new StringTokenizer(br.readLine());
            tp[k] = new Teleport(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        minDistance = Math.abs(S.x - E.x) + Math.abs(S.y - E.y);
        br.close();

    }
    private static void printResult() throws IOException{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write("" + minDistance);
        bw.newLine();
        bw.flush();
        bw.close();
    }

    static class Point{
        int x, y;

        public Point(int x, int y){
            this.x = x; this.y = y;;
        }
    }

    static class Teleport{
        int x1;
        int y1;
        int x2;
        int y2;
        public Teleport(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }


    }
}
