package com.ssafy.algorithmstudy.boj.boj17281;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17281_야구 {
    public static void main(String[] args) throws IOException {

        input();

        // 조합 함수 초기화
        int[] order = new int[8];
        for(int k = 0 ; k < 8 ; k++) order[k] = k+1;

        // 조합
        setOrderAndPlay(order);
        printRes();
        closeIO();
    }
    public static int N;
    // 이닝에 따른 선수들의 실적 저장 배열
    public static int innings [][];
    public static int res = 0;

    /**
     * 입력부
     * @throws IOException
     */
    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        innings = new int[N][9];
        for(int k = 0 ; k < N ; k++){
            innings[k] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

    }

    static int[] newOrder = new int[9];

    /**
     * NextPermutation 을 통해 생성한 후, 0번째 선수를 4번째 자리에 집어 넣은 새로운 배열을 생성하고 게임 점수 계산
     * @param arr
     */
    public static void setOrderAndPlay(int[] arr){
        while(true) {
            int i = 7;
            // 아래 idx에서 더 작은 숫자가 있을 때 까지
            while( i > 0 && arr[i-1] >= arr[i])
                i --;
            if(i == 0) // 더 작은 숫자가 아래 idx에 없으면 끝
                break;
            int j = 7;
            while(arr[i-1] >= arr[j]) //아래 idx에 더 큰 숫자의 idx를 찾음
                j--;
            swap(i -1, j, arr); // 교대

            int k = 7;
            while(i < k)
                swap(i ++ , k --, arr); // 정렬

            System.arraycopy(arr, 0, newOrder, 0, 3);
            System.arraycopy(arr, 3, newOrder, 4, 5);
            playGame(newOrder);

        }
    }
    /*
    next Permutation을 위한 swap 함수
     */
    static void swap(int i, int j, int[] arr) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 주어진 순서에 따라 이닝을 지나면서 점수를 계산, out 3번 나온 경우 다음 이닝으로 진행, 아니면 점수 계산
     * @param order
     */
    public static void playGame(int[] order){
        int inning = 0;
        int totalScore = 0;
        int currHitter = 0;
        int base[] = new int[4];
        while(inning < N){
            // base 초기화
            for(int k = 0 ; k < 4; k++) base[k] = 0;
            int outCount = 0;
            while(outCount < 3){ // out을 3번 하기 전까지
                // 지금 서있는 타자가 만들어낼 점수 저장
                int currHitterScore = innings[inning][order[currHitter]];
                if(currHitterScore != 0) { // 점수를 만들어 내면 base 상태에 따라 점수를 계산하여 저장
                    totalScore += calcScore(base, currHitterScore);
                }else{ // 아니면 out 한번
                    outCount ++;
                } // 다음 타자 순서를 불러오고, 9 이상이면 다시 0 부터 초기화
                currHitter = (currHitter+1)%9;
            }
            inning ++; // 아웃 다 당하고 나오면 다음 이닝으로

        }
        res = Math.max(res, totalScore);
    }

    /**
     * 점수를 얻었을 때 얼마나 공을 멀리 날렸나에 따라 점수를 계산하고, 그만큼 base 배열을 변화 시킴
     * @param base
     * @param run
     * @return
     */
    public static int calcScore(int[] base, int run){
        int thisScore = 0;

        if(run == 4){ // 홈런인 경우 모든 배열을 0으로 초기화 하고 1이 있던 만큼 점수에 추가
            for(int k = 0 ; k < 4 ; k++){
                thisScore += base[k];
                base[k] = 0;
            }
            return thisScore + 1;
        }
        base[0] = 1; // 홈에 1을 추가하고 낸 점수 만큼 배열을 밀어줌
        thisScore += base[3];
        push(base);
        for(int k = 0 ; k < run - 1; k++){
            base[0] = 0;
            thisScore += base[3];
            push(base);
        }
        return thisScore;
    }

    /**
     * 배열을 밀어주는 함수
     * @param base
     */
    public static void push(int[] base){
        for(int k = 3 ; k >= 1;  k--){
            base[k] = base[k-1];
        }
        base[0] = 0;
    }

    public static void printRes() throws IOException {
        bw.write(String.valueOf(res));
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
