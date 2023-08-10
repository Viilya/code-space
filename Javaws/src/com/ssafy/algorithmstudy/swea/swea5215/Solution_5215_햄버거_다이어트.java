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
    public static int ingredientsCount;
    public static int caloriesLimit;
    public static int ingredients[][];
    public static int maxTaste = 0;
    public static boolean[] isChosen;

    public static void printRes(int t) throws IOException {
        bw.write("#" + t + " " + maxTaste);
        bw.newLine();
    }

    public static void ingredientsCombination(int cnt, int tasteSum, int caloriesSum, int beforeingredients) {
        if (cnt == ingredientsCount || beforeingredients == ingredientsCount-1) {
            maxTaste = Math.max(maxTaste, tasteSum);
            return;
        }
        for (int k = beforeingredients + 1; k < ingredientsCount; k++) {
            if (!isChosen[k]) {
                if (ingredients[k][1] + caloriesSum > caloriesLimit) {
                    maxTaste = Math.max(maxTaste, tasteSum);
                }else if (ingredients[k][1] + caloriesSum == caloriesLimit) {
                    maxTaste = Math.max(maxTaste, tasteSum + ingredients[k][0]);
                }else{
                    isChosen[k] = true;
                    //System.out.println("bfi / k > " + beforeingredients + " " + k);
                    ingredientsCombination(cnt + 1, tasteSum + ingredients[k][0], caloriesSum + ingredients[k][1], k);
                    isChosen[k] = false;
                }
            }
        }
    }

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

