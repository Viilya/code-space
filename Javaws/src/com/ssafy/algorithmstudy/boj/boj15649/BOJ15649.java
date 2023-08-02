package com.ssafy.algorithmstudy.boj.boj15649;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ15649 {
	static int n, r;
	static int[] numbers;
	static boolean[] isSelected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		r = sc.nextInt();
		numbers = new int[r];
		isSelected = new boolean[n + 1];
		permutation(0);

	}

	public static void permutation(int cnt) {
		if (cnt == r) {
			for (int k = 0 ; k < numbers.length; k++) {
				System.out.print(numbers[k] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = 1; i <= n; ++i) {
			if (isSelected[i])
				continue;
			numbers[cnt] = i;
			isSelected[i] = true;
			permutation(cnt + 1);
			isSelected[i] = false;
		}
	}
}
