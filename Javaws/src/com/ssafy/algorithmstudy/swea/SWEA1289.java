package com.ssafy.algorithmstudy.swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
public class SWEA1289 {
    public static void ChangeMemory(int[] dM, int k, int target) {
        for (int t = k; t < dM.length; t++) {
            dM[t] = target;
        }
    }

    public static void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("SWEA1289i.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bf.readLine());

        for (int test_case = 1; test_case <= t; test_case++) {
            String st = bf.readLine();
            int[] memory = new int[50];
            for (int k = 0; k < st.length(); k++) 
                memory[k] = st.charAt(k) - '0';
            int[] dumpMemory = new int[st.length()];
            int count = 0;
            for (int k = 0; k < st.length(); k++) {
                if (dumpMemory[k] != memory[k]) {
                    ChangeMemory(dumpMemory, k, memory[k]);
                    count++;
                }
            }
            // System.out.println(st);
            // for (int k = 0; k < dumpMemory.length; k++) {
            //     System.out.print(dumpMemory[k]);
            // }
            // System.out.println();
            System.out.println("#" + test_case + " " + count);
            
            

        }

    }
}
