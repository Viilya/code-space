package com.ssafy.algorithmstudy.boj.boj11659;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11659 {
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int line[] = new int[n+1];
		
		st= new StringTokenizer(br.readLine());
		int sum = 0;
		for(int k = 1 ; k <= n ; k++) {
			sum += Integer.parseInt(st.nextToken());
			line[k] = sum;
		}
		
		for(int i = 0 ; i < t;  i++) {
			int res = 0;
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			System.out.println(line[b] - line[a-1]);
			
		}
		
	}
}
 
