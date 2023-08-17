package com.ssafy.algorithmstudy.swea.swea5644;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_5644_무선충전 {
    public static void main(String[] args) throws IOException {
        input();
        bw.flush();
        bw.close();
        br.close();
    }
    public static int M, A;
    public static int[] aMoves;
    public static int[] bMoves;
    public static PriorityQueue<Coverage> [][] pq = new PriorityQueue[10][10];
    public static int[] dy = {0, 0, 1, 0, -1};
    public static int[] dx = {0, -1, 0, 1, 0};
    public static void input() throws IOException{
        System.setIn(new FileInputStream("src/com/ssafy/algorithmstudy/swea/swea5644/input01.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1 ; tc <= T; tc ++){
            st = new StringTokenizer(br.readLine());

            // M, A 입력
            M = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken());

            // a , b 움직임 입력
            aMoves = new int[M];
            bMoves = new int[M];
            aMoves = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            bMoves = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            // pq 초기화
            initPQ();

            // 타워 입력 받기
            for(int k = 0 ; k < A; k ++){
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                int cover = Integer.parseInt(st.nextToken());
                int power = Integer.parseInt(st.nextToken());

                // 각각의 pq에 power 추가
                addChargeArea(x-1, y-1, cover, power, k+1);
            }

            // 충전
            printRes(tc, Charge());
        }
    }

    /**
     * pq 배열 생성
     */
    public static void initPQ(){
        for(int k = 0 ; k < 10 ; k ++){
            for(int s = 0 ;s < 10 ; s++){
                pq[k][s] = new PriorityQueue<Coverage>();

            }
        }
    }

    /**
     * 각각의 타워를 입력 받아 pq 배열에 충전 값 추가
     * @param x
     * @param y
     * @param cover
     * @param power
     * @param idx
     */
    public static void addChargeArea(int x, int y, int cover, int power, int idx){
        for(int k = 0 ; k < 10;  k++){
            for(int s = 0 ;s < 10 ; s++) {
                if(Math.abs(k - x) + Math.abs(s - y) <= cover) {pq[k][s].add(new Coverage(idx, power));}
            }
        }
    }

    /**
     * 움직임마다 호출하여 충전 받을 수 있는 값을 모두 더함
     * @return
     */
    public static int Charge(){
        int sum =0 ;
        int aX = 0, bX = 9;
        int aY = 0, bY = 9;
        sum += addCharge(aX, aY, bX, bY);
        for(int k = 0 ; k < M ; k ++){
            // 주어진 좌표 한칸 움직이기
            aX += dx[aMoves[k]];
            bX += dx[bMoves[k]];
            aY += dy[aMoves[k]];
            bY += dy[bMoves[k]];
            int tmp = addCharge(aX, aY, bX, bY); // 그 좌표에서 충전 최댓값 합에 더하기
            //System.out.println(String.valueOf(tmp));
            sum += tmp;
        }

        return sum;
    }

    /**
     * 그 좌표에서 충전 받을 수 있는 값을 계산하여 반환
     * @param aX
     * @param aY
     * @param bX
     * @param bY
     * @return
     */
    public static int addCharge(int aX, int aY, int bX, int bY){
        int aPower = 0, bPower = 0;
        int aTower = 0, bTower = 0;
        // 그 자리에 타워가 있으면 파워 추가
        if(!pq[aX][aY].isEmpty()){
            aPower = pq[aX][aY].peek().power;
            aTower = pq[aX][aY].peek().tower;
        }
        if(!pq[bX][bY].isEmpty()){
            bPower = pq[bX][bY].peek().power;
            bTower = pq[bX][bY].peek().tower;
        }
        //System.out.println(" A / B > " + aPower + " "+ aTower + " / " + bPower+ " " + bTower);
        if(aTower == bTower && aTower != 0 && bTower != 0){ // 타워가 서로 겹칠 경우

            int aNextPower = 0;
            int bNextPower = 0;
            // 각각의 두 번째로 파워가 큰 타워를 비교
            Coverage tmpA = pq[aX][aY].poll();
            if(pq[aX][aY].size() > 0) {
                aNextPower = pq[aX][aY].peek().power;
            }
            Coverage tmpB = pq[bX][bY].poll();
            if(pq[bX][bY].size() > 0){
                bNextPower = pq[bX][bY].peek().power;
            }
            //System.out.println(aX + " " + aY + " " + bX + " " + bY);
            if(tmpA!=null)pq[aX][aY].add(tmpA);
            if(tmpB!=null)pq[bX][bY].add(tmpB);
            //System.out.println("aNP / bNP > " + aNextPower + " " + bNextPower);
            // 두번째로 파워가 큰 타워가 있는 쪽의 타워를 받아서 return
            return (aNextPower > bNextPower)?bPower+aNextPower:aPower+bNextPower;
        }

        return aPower + bPower;
    }

    public static void printRes(int tc, int sum) throws IOException{
        bw.write("#" + tc + " " + sum);
        bw.newLine();
    }

    public static BufferedReader br;
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}

/**
 * 그 위치에서의 타워와 타워의 번호를 저장하는 클래스
 */
class Coverage implements Comparable<Coverage>{
    int tower;
    int power;


    public Coverage(int tower, int power){
        this.tower = tower;
        this.power = power;
    }
    @Override
    public int compareTo(Coverage o) {
        return this.power > o.power?-1:1;
    }
    @Override
    public String toString(){
        return String.valueOf(power);
    }
}
