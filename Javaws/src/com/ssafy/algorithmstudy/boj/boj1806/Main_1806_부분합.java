package com.ssafy.algorithmstudy.boj.boj1806;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1806_부분합 {

    public static void main(String args[]) throws IOException {
        input();
    }

    public static void input() throws IOException {
        int N, S;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        int arr[] = new int[N];

        st = new StringTokenizer(br.readLine());
        int currSum = 0;
        int length = 0;
        int res = 100001;
        int firstIdx = 0;
        for(int k = 0 ; k < N ; k ++){
            arr[k] = Integer.parseInt(st.nextToken());
            length += 1;
            currSum += arr[k];
            if(currSum >= S){

                while(true) {
                    res = Math.min(length, res);
                    length -= 1;
                    currSum -= arr[firstIdx];
                    firstIdx++;
                    if(currSum < S){
                        break;
                    }
                }
            }
        }
        bw.write("" + ((res==100001)?0:res));
        bw.flush();
    }



    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


}
