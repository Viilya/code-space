package com.ssafy.algorithmstudy.boj.boj1244;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BJ1244 {
    public static int switchChange(int currSwitch){
        if (currSwitch == 1)
            return 0;
        return 1;
    }

    public static void changeSwitch(int[] switches, int gender, int number) {
        int n = switches.length;
        if (gender == 1) {
            for (int k = 1; k < n; k++) {
                if (k % number == 0) {
                    switches[k] = switchChange(switches[k]);
                }
            }
        } else {
            switches[number] = switchChange(switches[number]);
            for (int k = 1; k < n; k++) {
                if (number - k < 1 || k + number >= n)
                    break;
                if (switches[k + number] == switches[number - k]) {
                    switches[k + number] = switchChange(switches[k + number]);
                    switches[number - k] = switches[k + number];
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input02.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] switches;
        int studentsNumber;
        int n;

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        switches = new int[n+1];
        for (int k = 1; k <= n; k++) 
            switches[k] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        studentsNumber = Integer.parseInt(st.nextToken());
        for (int k = 0; k < studentsNumber; k++) {
            st = new StringTokenizer(bf.readLine());
            changeSwitch(switches, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())); // switch number
        }
        for (int k = 1; k <= n; k++) {
            if(k == n || k % 20 == 0)
                System.out.println(switches[k]);
            else {
                System.out.print(switches[k] + " ");
            }
        }
    }
}