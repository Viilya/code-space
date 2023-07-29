////////////////////////////////////////////////////////////
//  Date : 2023.01.16 coded
//  Problem : 11053
//  Language : C++
//  by Kwanwoo Kim                                                      
////////////////////////////////////////////////////////////  

/*
문제
숫자 카드는 정수 하나가 적혀져 있는 카드이다. 상근이는 숫자 카드 N개를 가지고 있다. 정수 M개가 주어졌을 때, 이 수가 적혀있는 숫자 카드를 상근이가 가지고 있는지 아닌지를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 상근이가 가지고 있는 숫자 카드의 개수 N(1 ≤ N ≤ 500,000)이 주어진다. 둘째 줄에는 숫자 카드에 적혀있는 정수가 주어진다. 숫자 카드에 적혀있는 수는 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다. 두 숫자 카드에 같은 수가 적혀있는 경우는 없다.

셋째 줄에는 M(1 ≤ M ≤ 500,000)이 주어진다. 넷째 줄에는 상근이가 가지고 있는 숫자 카드인지 아닌지를 구해야 할 M개의 정수가 주어지며, 이 수는 공백으로 구분되어져 있다. 이 수도 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다

출력
첫째 줄에 입력으로 주어진 M개의 수에 대해서, 각 수가 적힌 숫자 카드를 상근이가 가지고 있으면 1을, 아니면 0을 공백으로 구분해 출력한다.
*/

#include <iostream>
#define MAX_CARD 500000
using namespace std;

void Swap(int a, int b, int* arrn){
    int tmp = arrn[a];
    arrn[a] = arrn[b];
    arrn[b] = tmp;
}

void QuickSort(int* arrn, int start, int end){
    if(start >= end)
        return;

    int pivot = start;
    int left = start + 1;
    int right = end;

    while(left <= right){
        while(left <= end && arrn[left] <= arrn[pivot]) start += 1;
        while(right > start && arrn[right] >= arrn[pivot]) end -= 1;
        if(left > right) Swap(pivot, right, arrn);
        else Swap(left, right, arrn);
    }
    QuickSort(arrn, start, right -1);
    QuickSort(arrn, right +1, end);
}

void CheckExist(int n, int arrn, int s, int start, int end){
    if(start < end) { cout << "0 "; return; }
    int mid = (start + end) / 2;
    if(arrn[mid] == s) { cout <<"1 "; return; }
    else if(s > arrn[mid]) CheckExist(n, arrn, s, mid + 1, end);
    else CheckExist(n, arrn, s, start, mid -1);
}

int main(void){
    int n, m, k, s;
    int arrn[MAX_CARD], arrm[MAX_CARD];
    cin >> n;
    for(k=0;k<n;k++) cin >> arrn[k];
    QuickSort(arrn, 0, n-1);
    cin >> m;
    for(k=0;k<m;k++) {cin >> s;
        CheckExist(n, arrn, s, 0, n-1);
    }


}