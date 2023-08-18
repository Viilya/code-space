package com.ssafy.algorithmstudy.boj.boj17135;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17135_캐슬디펜스 {
    public static void main(String[] args) throws IOException {
        input();
        setArchersLocationByCombination(0, -1, new int[3]);
        printRes();
    }

    public static int N, M, D;
    public static int [][] map; // 원본 맵
    public static int [][] movingMap; // 적들이 움직이면서 줄어드는 적을 저장할 맵 배열
    public static int maxRes = 0;

    public static void input() throws IOException{ // 입력부
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        movingMap = new int[N+1][M];
        for(int k = 0 ; k < N; k++){
            map[k] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }
    // 궁수들의 위치를 조합으로 만들어서 발사
    public static void setArchersLocationByCombination(int cnt, int bfCastleLocation, int[] arr){
        if(cnt == 3){ // 궁수 3명의 위치가 정해졌으면 defenceBegins 호출하여 count 구하기
            initMovingMap();
            maxRes = Math.max(maxRes , defenceBegins(arr));
            return;
        }
        for(int k = bfCastleLocation + 1 ; k < M ; k ++){ // 조합 만들기
            arr[cnt] = k;
            setArchersLocationByCombination(cnt +1 , k, arr);
        }
    }
    // movingMap을 초기화
    public static void initMovingMap(){
        for(int k = 0; k < N ; k ++){
            movingMap[k] = map[k].clone();
        }
    }

    // 궁수들의 저장 위치를 계속 부르면서 맵에 있는 적들을 하나씩 아래로 당겨줌
    public static int defenceBegins(int[] arr){
        int count = 0;
        for(int k = 0 ; k < N ; k ++) {
            count += findArchersAndShoot(arr);
            enemyMoves();
        }
        return count;
    }

    public static int findArchersAndShoot(int [] arr){ // 궁수들이 화살을 발사 하면, 적들의 위치를 기억하고 죽인 적들의 수를 반환
        int count = 0;
        int targets[][] = new int[3][2]; // 궁수들이 사격할 적들의 위치를 저장하는 함수
        for(int k = 0 ; k < 3;  k++){
            targets[k] = looseArrow(arr[k], targets); // 궁수별로 타겟 찾기
        }
        for(int k = 0 ; k < 3;  k++){ // 적들의 위치를 보고 적이 있으면 0으로 만든 후에 카운트 (중복 제거 도 가능!)
            if(targets[k][0] >= 0 && movingMap[targets[k][0]][targets[k][1]] == 1){
                movingMap[targets[k][0]][targets[k][1]] = 0;
                count += 1;
            }
        }
        return count;
    }
    public static int[] looseArrow(int archerPosition, int[][] targets){ // 적의 위치를 거리 별로 찾는 함수
        int[] tmp = {-1, -1};
        for(int k = 1; k <= D ; k ++) { // 적을 찾고, 적을 찾은 위치가 유효하면 그대로 반환 아니면 -1,-1을 반환
            tmp = findEnemy(archerPosition, k);
            if (tmp[0] != -1) {
                break;
            }
        }
        return tmp;
    }

    public static int[] findEnemy(int archerPosition, int distance){ // 적을 찾는 함수
        for(int k = distance-1 ; k >= -distance+1 ; k --){
            if(isEnemyExist(N  + (k>0?k:-k) - distance, archerPosition - k)){ // 왼쪽부터 거리만큼 떨어져 있는 적을 찾으
                return new int[] {N  + (k>0?k:-k) - distance, archerPosition - k}; // 적을 찾으면 그 위치를 반환
            }
        }
        return new int[] {-1, -1}; // 적이 없으면 -1, -1 을 반환
    }

    public static boolean isEnemyExist(int x, int y){ // 적이 있으면 true, 없으면 false
        if(x < 0 || x >= N  || y < 0 || y >= M || movingMap[x][y] == 0)
            return false;
        return true;
    }
    public static void enemyMoves(){ // movingMap 을 아래로 한칸 씩 밀어주는 함수
        for(int k = N-1 ; k >=1 ; k --){
            movingMap[k] = movingMap[k-1].clone();
        }
        movingMap[0] = new int[M];
    }

    public static void printRes() throws IOException{ // 출력 부
        bw.write(String.valueOf(maxRes));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
