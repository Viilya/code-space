////////////////////////////////////////////////////////////
//  Date : 2023.01.27 coded
//  Problem : 1920
//  Language : C++
//  by Kwanwoo Kim                                                      
////////////////////////////////////////////////////////////    

/*
문제
N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 N(1 ≤ N ≤ 100,000)이 주어진다. 다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다. 다음 줄에는 M(1 ≤ M ≤ 100,000)이 주어진다. 다음 줄에는 M개의 수들이 주어지는데, 이 수들이 A안에 존재하는지 알아내면 된다. 모든 정수의 범위는 -231 보다 크거나 같고 231보다 작다.

출력
M개의 줄에 답을 출력한다. 존재하면 1을, 존재하지 않으면 0을 출력한다.
*/

#include <iostream>
#define MAX_LEN 100000

using namespace std;

//                  GLOBAL VARIBALES

int _arrN[MAX_LEN] = {0, };
int _arrM[MAX_LEN] = {0, };

//                  FUNCTION DECLARATION

void Swap(int k, int s){
    int tmp = _arrN[k];
    _arrN[k] = _arrN[s];
    _arrN[s] = tmp;
}

void QuickSort(int start, int end){
    if(start >= end)
        return;
    int pivot = _arrN[(start + end)/2];
    int left = start;
    int right = end;
    while(left <= right){
        while(_arrN[left] < pivot )
            left += 1;
        while(_arrN[right] > pivot)
            right -= 1;
        if(left <= right){
            Swap(left, right);
            left +=1 ;
            right -= 1;
        }
    }

    QuickSort(start, right);
    QuickSort(left, end);
}

int Search(int m, int N){
    int StartNum = 0;
    int EndNum = N - 1;
    
    int MidNum;
    /*for(int k = 0 ; k < N ; k ++){
        cout << _arrN[k] << " ";
    }
    cout << " // " << m << endl;*/
    
    while(StartNum <= EndNum){
        
        MidNum = (StartNum + EndNum) / 2;
        //cout << StartNum << "   " << MidNum << "   " << EndNum << endl;
        
        if(_arrN[MidNum] == m){
            return 1;
        }
        else{
            if(StartNum == EndNum)
                break;
            if(m > _arrN[MidNum])
                StartNum = MidNum+1;
            else
                EndNum = MidNum;
        }
    }
    return 0;
}

void SearchAndPrint(int N, int M){
    int k;

    for(k=0;k<M;k++){
        cout << Search(_arrM[k], N) << "\n";
    }
}

int main(void){
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int N, M;
    int k;
    cin >> N;
    for(k=0;k<N;k++){
        cin >> _arrN[k];
    }
    cin >> M;
    for(k=0;k<M;k++){
        cin >> _arrM[k];
    }


    
    QuickSort(0, N-1);
    SearchAndPrint(N, M);
}