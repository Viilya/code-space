package com.ssafy.algorithmstudy.swea.swea5215;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_5215_햄버거_다이어트 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;
    public static int ingredientsCount; // 재료 개수 
    public static int caloriesLimit; // 칼로리 제한 
    public static int ingredients[][]; // 0 : 맛 , 1 : 칼로리 
    public static int maxTaste = 0; // 가장 높은 맛
    public static boolean[] isChosen;

    /**
     * 결과 출력
     * @param t
     * @throws IOException
     */
    public static void printRes(int t) throws IOException {
        bw.write("#" + t + " " + maxTaste);
        bw.newLine();
    }

    /**
     * 재료 조합 함수
     * @param cnt
     * @param tasteSum
     * @param caloriesSum
     * @param beforeingredients
     */
    public static void ingredientsCombination(int cnt, int tasteSum, int caloriesSum, int beforeingredients) {
        // 선택한 재료의 수가 다 찼거나, 전의 선택한 재료가 마지막 재료인 경우 전체 맛 합 계산
        if (cnt == ingredientsCount || beforeingredients == ingredientsCount - 1) {
            maxTaste = Math.max(maxTaste, tasteSum);
            return;
        }
        
        for (int k = beforeingredients + 1; k < ingredientsCount; k++) {
            if (!isChosen[k]) { // 아직 재료 선택을 하지 않은 경우 
                if (ingredients[k][1] + caloriesSum > caloriesLimit) { // 재료를 선택했을 때 칼로리 합이 넘은경우 지금까지 합 계산
                    maxTaste = Math.max(maxTaste, tasteSum);
                }else if (ingredients[k][1] + caloriesSum == caloriesLimit) { // 재료를 선택했을 때 칼로리 합과 같은 경우 더해서 합 계산 
                    maxTaste = Math.max(maxTaste, tasteSum + ingredients[k][0]);
                }else{ // 둘다 아닌 경우 다음 계산을 위해 함수 호출
                    isChosen[k] = true;
                    //System.out.println("bfi / k > " + beforeingredients + " " + k);
                    ingredientsCombination(cnt + 1, tasteSum + ingredients[k][0], caloriesSum + ingredients[k][1], k);
                    isChosen[k] = false;
                }
            }
        }
    }

    /**
     * input을 받은 후 조합 함수 호출
     * @throws IOException
     */
    public static void input() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            ingredientsCount = Integer.parseInt(st.nextToken());
            caloriesLimit = Integer.parseInt(st.nextToken());
            ingredients = new int[ingredientsCount][2];
            for (int k = 0; k < ingredientsCount; k++) {
                st = new StringTokenizer(br.readLine());
                ingredients[k][0] = Integer.parseInt(st.nextToken());
                ingredients[k][1] = Integer.parseInt(st.nextToken());
            }
            isChosen = new boolean[ingredientsCount];
            maxTaste = 0;
            ingredientsCombination(0, 0, 0, -1);
            printRes(test_case);
        }
    }
    public static void main(String args[]) throws IOException {
        input();
        bw.flush();
        bw.close();
        br.close();
    }
}

