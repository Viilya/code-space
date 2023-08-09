package com.ssafy.algorithmstudy.boj.boj11286;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj11286Abandoned {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int N;
    
    /**
     * input session
     * @throws IOException
     */
    public static void inputAndRun() throws IOException {
        N = Integer.parseInt(br.readLine());
        Heap heap = new Heap(N);
        for (int k = 0; k < N; k++) {
            int command = Integer.parseInt(br.readLine());
            if (command == 0) {
                bw.write(String.valueOf(heap.pop()));
                bw.newLine();
            } else {
                heap.insert(command);
            }
            heap.printTable();
        }
    }
    public static void main(String args[]) throws IOException {
        inputAndRun();
        bw.flush();
        bw.close();
        br.close();
    }
}

class Node {
    public int value;
    public int absValue;

    public Node(int value) {
        this.value = value;
        this.absValue = Math.abs(value);
    }
}

class Heap {
    Node[] data;
    int size = 0;

    public Heap(int N) {
        data = new Node[N * 2 + 2];
    }

    public void insert(int value) {
        Node newNode = new Node(value);
        data[++size] = newNode;
        sortUp(size);
    }

    public int pop() {
        if (size == 0) {
            return 0;
        }
        int res = data[1].value;
        data[1] = data[size];
        data[size] = null;
        size--;
        sortDown(1);
        return res;
    }

    public void swap(int idx1, int idx2) {
        Node tmp = data[idx1];
        data[idx1] = data[idx2];
        data[idx2] = tmp;
    }

    public void sortUp(int idx) {
        if (idx == 1) {
            return;
        }
        if (data[idx / 2].absValue > data[idx].absValue) {
            swap(idx / 2, idx);
            sortUp(idx / 2);
        } else if(data[idx/2].absValue == data[idx].absValue){
            if (data[idx / 2].value > data[idx].value) {
                swap(idx / 2, idx);
                sortUp(idx / 2);
            }
        }
        else{
            return;
        }
    }
    
    public void sortDown(int idx) {
        //System.out.println("idx > " + idx);
        if (data[idx * 2] == null && data[idx * 2 + 1] == null)
            return;
        if (data[idx * 2+1] == null) {
            if (data[idx * 2].absValue < data[idx].absValue) {
                swap(idx * 2, idx);
                sortDown(idx*2);
            }else if(data[idx*2].absValue == data[idx].absValue){
                if (data[idx * 2].value < data[idx].value) {
                    swap(idx * 2, idx);
                    sortDown(idx * 2);
                }
            }
        } else {
            int childMinAbs = Math.min(data[idx * 2].absValue, data[idx * 2 + 1].absValue);
            if (childMinAbs == data[idx].absValue) {
                if (data[idx * 2].absValue > data[idx * 2 + 1].absValue && data[idx].value > data[idx * 2 + 1].value) {
                    swap(idx, idx * 2 + 1);
                    sortDown(idx * 2 +1);
                } else if(data[idx *2].absValue < data[idx * 2].absValue && data[idx].value > data[idx *2].value){
                    swap(idx, idx * 2+1);
                    sortDown(idx * 2+1); 
                }
            }
            else if (childMinAbs < data[idx].absValue) {
                if (data[idx * 2].absValue > data[idx *2 +1].absValue) {
                    swap(idx, idx * 2 + 1);
                    sortDown(idx * 2 + 1); 
                } else {
                    swap(idx, idx * 2);
                    sortDown(idx * 2);  
                }
            }
        }
    }

    public int size() {
        return size;
    }
    
    public void printTable(){
        for (int k = 1; k <= size; k ++) {
            System.out.print(data[k].value + " ");
        }
        System.out.println();
        System.out.println();
    }
}