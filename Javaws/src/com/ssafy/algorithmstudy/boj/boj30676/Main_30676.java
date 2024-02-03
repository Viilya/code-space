package com.ssafy.algorithmstudy.boj.boj30676;

import java.util.Scanner;

public class Main_30676 {
    public static void main(String args[]){
//        빨간색: 620nm 이상 780nm 이하
//        주황색: 590nm 이상 620nm 미만
//        노란색: 570nm 이상 590nm 미만
//        초록색: 495nm 이상 570nm 미만
//        파란색: 450nm 이상 495nm 미만
//        남색: 425nm 이상 450nm 미만
//        보라색: 380nm 이상 425nm 미만
//        별의 색을 출력한다.
//        빨간색이면 "Red",
//        주황색이면 "Orange",
//        노란색이면 "Yellow",
//        초록색이면 "Green",
//        파란색이면 "Blue",
//        남색이면 "Indigo",
//        보라색이면 "Violet"을 출력한다.
        Scanner sc = new Scanner(System.in);
        int color = sc.nextInt();
        if(color >= 620) {
            printColor("Red");
        }else if(color >= 590){
            printColor("Orange");
        }else if(color >= 570){
            printColor("Yellow");
        }else if(color >= 495){
            printColor("Green");
        }else if(color >= 450){
            printColor("Blue");
        }else if(color >= 425){
            printColor("Indigo");
        }else {
            printColor("Violet");
        }
    }

    public static void printColor(String msg){
        System.out.println(msg);
    }
}
