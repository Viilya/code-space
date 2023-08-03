package com.ssafy.algorithmstudy.boj.boj2023;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj2023 {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static void main(String args[]) throws NumberFormatException, IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        // 숫자의 자릿수를 저장할 배열 
        int numbers[] = new int[n];

        // 함수 호출 
        getDigits(0, n, numbers);
        bw.flush();
        bw.close();
        br.close();
    }
    /**
     * 재귀함수로, 소수를 판별하고 숫자를 한자리 씩 추가하는 함수 
     * @param currDigit
     * @param n
     * @param numbers
     * @throws IOException
     */
    public static void getDigits(int currDigit, int n, int numbers[]) throws IOException {
        // 숫자의 길이가 n 일 때 숫자를 계산하여 출력 후 함수 종료 
        if(currDigit == n){
            for(int k = 0 ; k < n; k ++){
                bw.write(String.valueOf(numbers[k]));
            }
            bw.newLine();
            return;
        }
        int num = 0;
        // 현재 가지고 있는 숫자 * 10 한 값을 저장 
        for (int k = 0; k < currDigit ; k++) {
            num += Math.pow(10, currDigit - k) * numbers[k];
            //System.out.println("num bf get / currDigit > " + num + " " + currDigit);
        }
        for (int k = 0; k <= 9; k++) {
            // 0~9 까지 현재 가지고 있는 숫자에 뒤로 추가하여 소수 여부를 판별 
            if (isPrice(num + k)) {
                // 소수인 경우 추가하고, 재귀 함수로 호출 
                numbers[currDigit] = k;
                getDigits(currDigit + 1, n, numbers);
            }

        }

    }
    /**
     * 받은 값이 소수인지를 판별하는 함수 
     * @param num
     * @return
     */
    public static boolean isPrice(int num) {
        //System.out.println(num);
        // 1이나 0 인 경우 소수 아님 
        if (num == 1 || num == 0) {
            return false;
        }
        // num의 root 값 
        double sqrt = Math.sqrt(num);
        for (int k = 2; k <= sqrt; k++) {
            // num root 값 까지 나눴을 때 나눠 떨어지면 소수가 아님 
            if (num % k == 0) {
                return false;
            }
        }
        return true;        
    }
}
