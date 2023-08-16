package com.ssafy.algorithmstudy.boj.boj6987;

import java.io.*;
import java.util.StringTokenizer;

public class Main_6987_월드컵{
    public static void main(String[] args) throws IOException{
        input();
    }

    public static Team[] count = new Team[6]; // 팀들의 승무패 정보를 저장하는 배열
    public static boolean isAvailable = false; // true 인 경우 성립이 되는 결과, false 인 경우 성립하지 않음
    public static void input() throws IOException {
        for(int k = 0 ; k < 4 ; k ++) { // 4개의 test case 입력
            st = new StringTokenizer(br.readLine());
            boolean isTotalGameValid = true;
            for(int s = 0 ; s < 6 ; s++){ // count[] 에 정보 저장
                count[s] = new Team(Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken()) );
                if(count[s].totalGame() != 5) // 각 팀이 정확히 다섯 경기를 치루지 않은 경우 그냥 성립하지 않음
                    isTotalGameValid = false;
            }

            if(isTotalGameValid)  // 여섯 팀이 모두 다섯 경기를 치뤘을 경우 계산!
                Run(0, 0);

            bw.write(String.valueOf(isAvailable?1:0) + " ");
            isAvailable = false;
        }
        bw.newLine();
        bw.close();
        br.close();
    }

    /**
     * 자신을 제외한 뒤의 팀에게 (승, 패) (무, 무) (패, 승) 을 교환한다고 가정하고, count[] 에서 해당하는 각 팀의 승,무,패를 하나씩 감소
     * 모든 팀에게 한 번씩 감소 시켰을 경우 다음 팀도 똑같은 과정 반복
     * 마지막 팀의 모든 승,무,패 가 0 인 경우 성립하다 보고 isAvailable을 true 로 변경
     * @param num 지금 보고있는 팀
     * @param bfTeam 전 단계에서 본 팀
     */
    public static void Run(int num, int bfTeam) {
        if(num == 5){ // 마지막 팀을 본 경우 승, 패, 무가 0인지 확인
            if(count[num].win == 0 && count[num].tie ==0 && count[num].lose == 0){
                isAvailable = true; // 0 인경우 성립 한다 기록
                return;
            }
        }

        if (count[num].win != 0 || count[num].tie != 0 || count[num].lose != 0) { // 지금 보고 있는 팀이 아직 모든 경기를 감소시키지 않은 경우
            if (count[num].win > 0 && count[bfTeam + 1].lose > 0) { // num 팀과 bfTeam+1 팀 간 승, 패 교환
                count[num].win -= 1;
                count[bfTeam + 1].lose -= 1;
                Run(num, bfTeam + 1); // 다음 팀과의 경기를 위해 run 호출
                count[bfTeam + 1].lose += 1;
                count[num].win += 1;
            }if (count[num].tie > 0 && count[bfTeam + 1].tie > 0) { // num 팀과 bfTeam+1 팀 간 무승부 교환
                count[num].tie -= 1;
                count[bfTeam + 1].tie -= 1;
                Run(num, bfTeam + 1);// 다음 팀과의 경기를 위해 run 호출
                count[bfTeam + 1].tie += 1;
                count[num].tie += 1;
            }if (count[num].lose > 0 && count[bfTeam + 1].win > 0) { // num 팀과 bfTeam+1 탐 간 패, 숭 교환
                count[num].lose -= 1;
                count[bfTeam + 1].win -= 1;
                Run(num, bfTeam + 1);// 다음 팀과의 경기를 위해 run 호출
                count[bfTeam + 1].win += 1;
                count[num].lose += 1;
            }
        }
        else{ // 지금 보고 있는 팀이 모두 경기를 치룬 경우 보고 있는 팀을 다음 팀으로 변경
            Run(num+1, num+1);
        }
    }

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
}

/**
 * 팀들의 승, 패, 무를 저장하는 클래스
 */
class Team {
    public int win = 0;
    public int tie = 0;
    public int lose = 0;

    public Team(int win, int tie, int lose){
        this.win = win;
        this.tie = tie;
        this.lose = lose;
    }

    public int totalGame(){
        return win + tie + lose;
    }
}
