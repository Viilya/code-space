////////////////////////////////////////////////////////////
//  Date : 2023.01.18 coded
//  Problem : 1780
//  Language : C++
//  by Kwanwoo Kim                                                      
////////////////////////////////////////////////////////////   

/*
문제
N×N크기의 행렬로 표현되는 종이가 있다. 종이의 각 칸에는 -1, 0, 1 중 하나가 저장되어 있다. 우리는 이 행렬을 다음과 같은 규칙에 따라 적절한 크기로 자르려고 한다.

만약 종이가 모두 같은 수로 되어 있다면 이 종이를 그대로 사용한다.
(1)이 아닌 경우에는 종이를 같은 크기의 종이 9개로 자르고, 각각의 잘린 종이에 대해서 (1)의 과정을 반복한다.
이와 같이 종이를 잘랐을 때, -1로만 채워진 종이의 개수, 0으로만 채워진 종이의 개수, 1로만 채워진 종이의 개수를 구해내는 프로그램을 작성하시오.

입력
첫째 줄에 N(1 ≤ N ≤ 37, N은 3k 꼴)이 주어진다. 다음 N개의 줄에는 N개의 정수로 행렬이 주어진다.

출력
첫째 줄에 -1로만 채워진 종이의 개수를, 둘째 줄에 0으로만 채워진 종이의 개수를, 셋째 줄에 1로만 채워진 종이의 개수를 출력한다.
*/

#include <iostream>
using namespace std;

#define MAX_LEN 2187

int _paper[MAX_LEN][MAX_LEN] = {0,};
int _MO = 0;
int _ZR = 0;
int _PO = 0;
int CheckPaperIsFull(int x, int y, int N){
    int k,s;
    int comp = _paper[x][y];
    int flag = 1;
    for(k=x;k<x+N;k++){
        for(s=y;s<y+N;s++){
            if(comp != _paper[k][s]) {flag = 0; break;}
        }
    }
    return flag;
}
void AddPaperCount(int p){
    if(p == -1){
        _MO += 1;
    }else if(p == 0){
        _ZR += 1;
    }else{
        _PO += 1;
    }
}
void DividePaper(int x, int y, int N){
    int flag = CheckPaperIsFull(x,y,N);
    if(flag == 1){
        AddPaperCount(_paper[x][y]);
    }else if(N == 1){
        AddPaperCount(_paper[x][y]);
    }else{
        DividePaper(x, y, N/3);
        DividePaper(x + N/3, y, N/3);
        DividePaper(x + N/3 * 2, y, N/3);
        DividePaper(x, y + N/3, N/3);
        DividePaper(x + N/3, y + N/3, N/3);
        DividePaper(x + N/3 * 2, y + N/3, N/3);
        DividePaper(x, y + N/3 * 2, N/3);
        DividePaper(x + N/3, y + N/3 * 2, N/3);
        DividePaper(x + N/3 * 2, y + N/3 * 2, N/3);
    }
    
    
}

int main(void){
    int N;
    int k, s;
    cin>>N;
    
    for(k=0;k<N;k++){
        for(s=0;s<N;s++){
            cin>>_paper[k][s];
        }
    }
    DividePaper(0, 0, N);
    cout << _MO << endl << _ZR << endl << _PO;
}