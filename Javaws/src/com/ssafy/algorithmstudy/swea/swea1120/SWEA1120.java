package com.ssafy.algorithmstudy.swea.swea1120;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.FileInputStream;


/**
 * 1 : N, 2 : S / Up : N , Down : S
 */
public class SWEA1120 {
    public static void checkFallingMagnets(int[][] map, int n) {
        for (int s = 0; s < n; s++) {
            boolean foundN = false;
            boolean foundS = false;
            for (int k = 0; k < n; k++) {
                if (!foundN) { // fall S til found N
                    if (map[s][k] == 2)
                        map[s][k] = 0;
                    if (map[s][k] == 1)
                        foundN = true;
                }
                if (!foundS) { // fall N til found S
                    if (map[s][n - k - 1] == 1)
                        map[s][n - k - 1] = 0;
                    if (map[s][n - k - 1] == 2)
                        foundS = true;
                }
            }
        }
    }
    

    public static int checkConflictMagnets(int[][] map, int n) {
        int count = 0;
        for (int k = 0; k < n; k++) {
            for (int s = 0; s < n; s++) {
                if (map[k][s] == 1) {
                    
                    count++;
                    for (int i = s; i < n; i++) {
                        if (map[k][i] == 2) {
                            for (int t = i; t < n; t++) {
                                i = t;
                                if (map[k][t] == 1) {
                                    s = t - 1;
                                    break;
                                }
                            }
                            s = i - 1;
                            break;
                            
                        }
                    }
                }
            }
        }
        return count;
    }
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input01.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n;
         //StringTokenizer인자값에 입력 문자열 넣음; //두번째 호출
        for (int test_case = 1; test_case <= 10; test_case++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            n = Integer.parseInt(st.nextToken());
            int[][] map = new int[n][n];
            int result = 0;

            // input line
            for (int k = 0; k < n; k++) {
                st = new StringTokenizer(bf.readLine());
                for (int s = 0; s < n; s++)
                    map[s][k] = Integer.parseInt(st.nextToken());
            }

            // check map
            // for (int k = 0; k < n; k++) {
            //     for (int s = 0; s < n; s++) {
            //         System.out.print(map[k][s]);
            //     }
            //     System.out.println();
            // }
            // System.out.println();
            

            
            checkFallingMagnets(map, n);
            result = checkConflictMagnets(map, n);
            

            // for (int k = 0; k < n; k++) {
            //     for (int s = 0; s < n; s++) {
            //         System.out.print(map[k][s]);
            //     }
            //     System.out.println();
            // }
            
            System.out.println("#" + test_case + " " + result);
        }
    }
}