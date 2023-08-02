package com.ssafy.algorithmstudy.boj.boj15650;

import java.util.Scanner;

public class BOJ15650 {
	public static int[] numbers;
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		numbers = new int[m];
		
		permutation(n, m, 0, 1);
		
	}
	public static void permutation(int n, int m, int cnt, int start) {
		if(cnt == m) {
			for(int k = 0 ; k < m ; k ++) 
				System.out.print(numbers[k] + " ");
			System.out.println();
			return;
		}
		for(int k = start ; k <= n ; k++) {
			numbers[cnt] = k;
			permutation(n, m, cnt +1, k + 1);
		}
	}
}
