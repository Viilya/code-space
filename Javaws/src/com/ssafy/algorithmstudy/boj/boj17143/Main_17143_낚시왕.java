package com.ssafy.algorithmstudy.boj.boj17143;

import java.io.*;
import java.util.StringTokenizer;

public class Main_17143_낚시왕 {

    // Shark의 정보 저장
    static class Shark{

        int speed;
        int dir;
        int size;

        public Shark(int speed, int dir, int size) {

            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        run();
        printRes();
        closeIO();
    }
    static int R, C, M;
    static Shark tank0[][]; // shark 위치를 포함할 수조 1
    static Shark tank1[][]; // shark 위치를 포함할 수조 2
    static int totalSize = 0; // 지금까지 잡은 shark 의 크기 정보
    static int[] dx= {-1, 1, 0, 0};
    static int[] dy= {0,0,1,-1};
    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = next();
        C = next();
        M = next();
        tank0 = new Shark[R][C];
        tank1 = new Shark[R][C];
        for(int k = 0 ; k < M ; k ++){
            st = new StringTokenizer(br.readLine());
            tank0[next() - 1][next() - 1] = new Shark(next(), next() - 1, next());
        }
    }
    public static int next(){ // integer parse
        return Integer.parseInt(st.nextToken());
    }

    public static void run(){ // 중복 상어를 제거하기 위해 2개의 수조를 준비해서 위치에 따라서 상어가 옮겨가는 형식으로 작성
        for(int currPos = 0 ; currPos < C; currPos ++){
            if(currPos%2==0){
                totalSize += catchAndMove(tank0, tank1, currPos); // 1 -> 0
            }else{
                totalSize += catchAndMove(tank1, tank0, currPos); // 0 -> 1
            }
        }
    }


    // 상어를 잡고, 옮기는 함수를 호출
    public static int catchAndMove(Shark[][] tankSrc, Shark[][] tankDest, int currPos){
        int size = catchShark(tankSrc, currPos); // 상어 잡기
        selectSharkAndMove(tankSrc, tankDest); // 잡은 후 상어가 이동하는 함수
        return size;
    }

    // 상어 잡기, 지금 위치에서 지표면에 가장 가까운 상어 포획
    public static int catchShark(Shark[][] tank, int position){
        for(int depth = 0 ; depth < R; depth++){
            if(tank[depth][position] != null){ // 위부터 탐색해서 상어를 발견하면 사이즈를 반환하고, 그 위치 상어를 지움
                int size = tank[depth][position].size;
                tank[depth][position] = null;
                return size;
            }
        }
        return 0;
    }

    // 모든 상어를 하나씩 확인해서, 반대 수조로 옮김
    public static void selectSharkAndMove(Shark[][] tankSrc, Shark[][] tankDest){
        for(int k = 0 ; k < R; k ++){
            for(int s= 0 ; s < C ; s++){
                if(tankSrc[k][s] != null){
                    Move(k, s, tankSrc[k][s], tankDest);
                    tankSrc[k][s] = null;
                }
            }
        }
    }
/*
    // 상어가 향한 방향으로 옮기면서 배열의 범위를 넘어가면 방향을 바꿔서 이동
    public static void Move(int x, int y, Shark shark, Shark[][] tankDest){
        int X = x;
        int Y = y;
        for(int k = 0 ; k < shark.speed; k++){
            X += dx[shark.dir]; // 방향으로 이동
            Y += dy[shark.dir];
            if(!isValid(X, Y)) { // 만약 이동한 방향이 유효하지 않으면 방향을 바꾸고 반대로 이동
                shark.dir ^= 1;
                X += dx[shark.dir] * 2;
                Y += dy[shark.dir] * 2;
            }
        }

        Eat(X, Y, shark, tankDest); // 이동을 다했으면 중복 상어를 제거
    }
*/
    public static void Move(int x, int y, Shark shark, Shark[][] tankDest) {
        int X = x + (dy[shark.dir] * shark.speed) % (2 * R);
        int Y = y + (dy[shark.dir] * shark.speed) % (2 * C);


        Eat(X, Y, shark, tankDest);
    }
    // 수조의 위치에 상어가 있는지 확인하고, 있으면 크기가 큰 상어가 작은 상어를 잡아먹음
    public static void Eat(int x, int y, Shark shark, Shark[][] tankDest) {
        if(tankDest[x][y] == null){ // 그 위치에 상어가 없으면 그냥 상어가 위치함
            tankDest[x][y] = shark;
            return;
        }
        if(shark.size > tankDest[x][y].size){ // 아니면 큰 상어가 살아남음
            tankDest[x][y] = shark;
        }
    }

    public static boolean isValid(int x, int y){
        if(x < 0 || x >= R || y < 0 || y >= C){
            return false;
        }
        return true;
    }

    public static void printRes() throws IOException {
        bw.write(String.valueOf(totalSize));
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
