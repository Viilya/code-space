package com.ssafy.algorithmstudy.swea.swea1873;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_1873_상호의_배틀필드 {
    public static void main(String[] args) throws IOException {
        input();
        bw.flush();
        bw.close();
        br.close();
    }

    public static int H, W, N;
    public static char[][] map;
    public static char[] inputs;
    public static Tank tank = new Tank();
    public static int dx[] = {-1, 1, 0 ,0}; // UP DOWN LEFT RIGHT
    public static int dy[] = {0, 0, -1, 1};
    public static void input() throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            for(int k = 0 ; k < H ; k ++){
                map[k] = br.readLine().toCharArray();
                for(int s = 0 ; s < W ; s++){
                    setInitialTank(k, s);
                }
            }
            N = Integer.parseInt(br.readLine());
            inputs = new char[N];
            inputs = br.readLine().toCharArray();

            runGame();
            printRes(tc);
        }
    }
    public static void setInitialTank(int k, int s){
        if(map[k][s] == '^'){
            tank.x = k;tank.y = s;tank.dir = 0;
        }if(map[k][s] == 'v'){
            tank.x = k;tank.y = s;tank.dir = 1;
        }if(map[k][s] == '<'){
            tank.x = k;tank.y = s;tank.dir = 2;
        }if(map[k][s] == '>'){
            tank.x = k;tank.y = s;tank.dir = 3;
        }
    }
    public static void runGame() throws IOException{
        for(int k = 0 ; k < N; k ++){
            //printRes();
            //bw.newLine();
            if(inputs[k] == 'U'){
                move(0);
            }if(inputs[k] == 'D'){
                move(1);
            }if(inputs[k] == 'L'){
                move(2);
            }if(inputs[k] == 'R'){
                move(3);
            }if(inputs[k] == 'S'){
                shoot();
            }
        }
    }

    public static void move(int dir){
        tank.dir = dir;
        if(isTankMoveable(tank.x + dx[dir], tank.y + dy[dir])){
            map[tank.x][tank.y] = '.';
            tank.x += dx[dir];
            tank.y += dy[dir];
        }
        tankMapChange(tank.x, tank.y, tank.dir);
    }
    public static void tankMapChange(int x, int y, int dir){
        if(dir == 0){
            map[x][y] = '^';
        }else if(dir == 1){
            map[x][y] = 'v';
        }
        else if(dir == 2){
            map[x][y] = '<';
        }
        else if(dir == 3){
            map[x][y] = '>';
        }

    }
    public static void shoot() throws IOException{
        int shellDir = tank.dir;
        int shellX = tank.x;
        int shellY = tank.y;
        //bw.write("Shoot x, y, dir > " + shellX + " " + shellY + " " + shellDir);
        //bw.newLine();
        while(true){
            shellX += dx[shellDir];
            shellY += dy[shellDir];
            if(isShellOutOfBounds(shellX, shellY)){
                break;
            }
            if(isShellMeetsWall(shellX, shellY)){
                if(map[shellX][shellY] == '*')
                    map[shellX][shellY] = '.';
                break;
            }
        }
    }


    public static boolean isTankMoveable(int x, int y){
        if(x<0 || x>=H || y<0 || y>=W || map[x][y] == '-' || map[x][y] =='*' || map[x][y] == '#'){
            return false;
        }return true;
    }
    public static boolean isShellOutOfBounds(int x, int y){
        if(x<0 || x>= H || y < 0 || y>= W ){
            return true;
        }return false;
    }
    public static boolean isShellMeetsWall(int x, int y){
        if(map[x][y] == '*' || map[x][y] == '#'){
            return true;
        }return false;
    }
    public static void printRes(int tc) throws IOException{
        bw.write("#" + tc + " ");
        for(int k = 0; k <H; k ++){
            for(int s = 0 ;s < W ; s++){
                bw.write(map[k][s]);
            }
            bw.newLine();
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}

class Tank{
    int dir = 0;
    int x = 0;
    int y = 0;
}