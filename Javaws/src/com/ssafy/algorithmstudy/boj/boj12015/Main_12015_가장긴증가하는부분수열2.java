package com.ssafy.algorithmstudy.boj.boj12015;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_12015_가장긴증가하는부분수열2 {

    public static void main(String args[]) throws IOException {
        input();

    }

    public static int N;
    public static int arr[];
    public static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        int last = 0;
        for(int k = 0 ; k < N  ; k ++){
            int a = Integer.parseInt(st.nextToken());
            if(k == 0) {
                arr[last] = a;
                continue;
            }

            if(arr[last] < a ){
                last += 1;
                arr[last] = a;
            }
            else{
                int idx = search(a, last);
                arr[idx] = a;
            }
//            System.out.println(Arrays.toString(arr));
        }
        output(last);
    }

    public static int search(int a, int last){
        int start = 0;
        int end= last;
        while(start < end){
            int mid = start + (end - start) / 2;
            if(arr[mid] >= a){
                end = mid;
            }else{
                start = mid + 1;
            }
        }

        return end;
    }



    public static void output(int last) throws IOException{
        bw.write((last+1) + "");
        bw.flush();
        bw.close();
        br.close();
    }



    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static StringTokenizer st;

}