package com.ssafy.algorithmstudy.boj.boj11660;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11660 {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		// 그 좌표 값 까지의 합을 저장하는 2차원 배열 생성 
		// 이때 0~n 까지 n+1 개수 만큼 행과 열 생성
		int mapSum[][] = new int [n+1][n+1];
		for(int k = 1 ; k <= n ; k ++) {
			st = new StringTokenizer(br.readLine());
			for(int s= 1 ;s <= n ; s++)  
				// 좌표 기준 한칸 위, 한칸 왼쪽 값을 더하고, 좌표 대각선왼쪽 위 값을 뺀 후 입력받은 값을 더하면 좌표까지의 합이 나온다.
				mapSum[k][s] = mapSum[k-1][s] + mapSum[k][s-1] - mapSum[k-1][s-1] + Integer.parseInt(st.nextToken());
			
		}
		/**
		 * 입력부, 2 개의 좌표를 받고 결과값을 계산
		 */
		for (int k = 0 ; k < m ; k ++) {
			st = new StringTokenizer(br.readLine());
			// 입력 받기 
			int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
			// 큰 좌표까지의 합과, 작은 좌표 a, b 와 큰 좌표 y, x 순으로 뺄셈 한수, 작은 좌표 a-1, b-1 을 더함  
			System.out.println(mapSum[x][y] - mapSum[a-1][y] - mapSum[x][b-1] + mapSum[a-1][b-1]);
		}	
	}
}
