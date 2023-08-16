package com.ssafy.algorithmstudy.boj.boj3109;


import java.io.*;
import java.util.StringTokenizer;

public class Main_3109_빵집 {
    public static void main(String[] args) throws IOException{
        input();
    }
    public static char[][] map;
    public static int R, C;
    public static boolean[][] isVisited;
    public static int[] dx = {-1, 0, 1};
    public static int[] dy = {1, 1, 1};
    public static int maxRes = 0;

    public static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        isVisited = new boolean[R][C];
        for(int k = 0 ; k< R ; k ++){
            map[k] = br.readLine().toCharArray().clone();
            isVisited[k][0] = true;
            for(int s = 0 ; s < C ; s++){
                if(map[k][s] == 'x') // 벽인 경우 미리 방문했다 가정
                    isVisited[k][s] = true;
            }
        }
        for(int k = 0; k < R ; k++) { // 모든 빵집에 대해 하나씩 DFS
            if(connectPipe(k, 0)) // 끝에 도달 한 경우 최대 결과에 1 추가
                maxRes += 1;
        }
        bw.write(String.valueOf(maxRes));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }

    /**
     * DFS, (x, currLevel) 의 좌표에서 우상, 우, 우하의 경우를 호출하여 재귀로 DFS 호출
     * 끝 부분에 도달한 경우 true를 반환하여 그대로 함수 종료
     * @param x
     * @param currLevel
     * @return
     */
    public static boolean connectPipe(int x, int currLevel){
        // 끝에 도달 할 수 있는 경우 true를 반환
        if(currLevel == C-1){
            return true;
        }
        for(int k = 0 ; k < 3 ; k++){ // 우상, 우, 우하의 경우를 고려
            int X = x + dx[k];
            int Y = currLevel + dy[k];
            if(isValid(X, Y)){ // 접근 가능한 좌표인 경우 실행
                isVisited[X][Y] = true;
                if(connectPipe(X, Y)) { // 접근으로 바꾸고 다음 dfs를 실행하여 dfs의 결과가 true인 경우 상위로 true를 반환
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 접근 불가능한 좌표이거나, 이미 방문한 경우 false, 아니면 true를 반환
     * @param x
     * @param y
     * @return
     */
    public static boolean isValid(int x, int y){
        if(x < 0 || x >= R || y < 0 || y >= C || isVisited[x][y]) return false;
        return true;
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}
