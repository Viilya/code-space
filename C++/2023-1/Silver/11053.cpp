////////////////////////////////////////////////////////////
//  Date : 2023.01.16 coded
//  Problem : 11053
//  Language : C++
//  by Kwanwoo Kim                                                      
////////////////////////////////////////////////////////////    

/*
problem : 
수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.

입력
첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.

둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)

출력
첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.
*/

#include <iostream>
using namespace std;

int _count[1000] = {0,};
int _num[1000] = {0,};
int _result = 0;

void GetSequenceSize(int seqSize){
    int k, s;
    for(k=0;k<seqSize;k++){
        for(s=k;s<seqSize;s++){
            if(_num[k] < _num[s] && _count[k] + 1 > _count[s])
                    _count[s] = _count[k] + 1;
        }  
        if(_result < _count[k])
           _result = _count[k];
    }
}

int main(void){
    int seqSize;
    int k;
    cin >> seqSize;
    for(k=0;k<seqSize;k++)
        cin >> _num[k];
    for(k=0;k<1000;k++){
        _count[k] = 1;
    }
    GetSequenceSize(seqSize);

    cout << _result;
}