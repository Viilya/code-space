package com.ssafy.algorithmstudy.bj;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14889 {
    public static int _sumMin = Integer.MAX_VALUE;
    public static int _totalSum = 0;
    /**
     * teams 에 있는 인원들의 능력치를 계산하여 return
     * @param stats
     * @param teams
     * @param n
     * @return total Team Stats
     */
    public static int calcTotalStatDiff(int stats[][], boolean[] teams, int n) {
        int team1Sum = 0;
        int team2Sum = 0;
        /* k 인원이 팀에 속해 있는 경우 */
        for (int k = 0; k < n - 1; k++) {
            // 1팀 : true 인 팀들
            if (teams[k] == true) {
                /* k 보다 번호가 높은 인원들과의 스탯을 모두 더함 */
                for (int s = k + 1; s < n; s++) {
                    if (teams[s])
                        team1Sum += stats[k][s] + stats[s][k];
                }
            }
            // 2팀 : false 인 팀
            else {
                for (int s = k + 1; s < n; s++) {
                    if (!teams[s]) 
                        team2Sum += stats[k][s] + stats[s][k];
                }
            }
        }
        return Math.abs(team1Sum - team2Sum);
    }
    
    /**
     * dfs 로 돌면서 teams에 가진 값들을 기준으로 다른 팀값과의 차이를 계산
     * 직전 값과 비교해서 값이 커지면 종료, 직전 값을 min 값으로 계산
     * @param stats
     * @param teams
     * @param n
     * @param curr
     * @param bfDiff
     */
    public static void teamUp(int stats[][], boolean[] teams, int n, int curr, int teamMemberCount) {

        /* 상대팀과의 스탯 차이 계산 */
        int statDiff = calcTotalStatDiff(stats, teams, n);

        // System.out.println(" statDiff : " + statDiff);
        // System.out.println(" bfDiff : " +  bfDiff);
        
        /* 팀 숫자가 N/2 가 되면 전역변수 _sumMin 과 비교해서 결과 값 삽입, 함수 종료 */
        if (teamMemberCount == n / 2) {
            _sumMin = Math.min(_sumMin, statDiff);
            return;
        }
        /* 인원을 한명 더 추가해서 teamUp을 호출 */
        for (int k = curr + 1; k < n; k++) {
            // 넘겨주기 위한 새로운 팀 복사
            // 해당하는 멤버 추가
            teams[k] = true;
            // 함수 호출
            teamUp(stats, teams, n, k, teamMemberCount + 1);
            teams[k] = false;
        }
    }
    

    /**
     * Main function
     * Input, 함수 호출
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("BJ14889i.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        /* Input 받는 코드 */
        int n = Integer.parseInt(st.nextToken());
        /* stats 을 저장하는 배열 */
        int[][] stats = new int[n][n];

        /* input stats! */
        for (int k = 0; k < n; k++) {
            st = new StringTokenizer(bf.readLine());
            for (int s = 0; s < n; s++) {
                stats[k][s] = Integer.parseInt(st.nextToken());
                _totalSum += stats[k][s];
            }
        }
        boolean[] teams = new boolean[n];

        /* 0번째 인원 추가 */
        teams[0] = true;

        teamUp(stats, teams, n, 0,  1);
        System.out.println(_sumMin);
    }
    
}