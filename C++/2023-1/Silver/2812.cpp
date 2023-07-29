 ////////////////////////////////////////////////////////////
//  Date : 2023.02.07 coded
//  Problem : 2812
//  Language : C++
//  by Kwanwoo Kim                                                      
////////////////////////////////////////////////////////////    

/*
문제
N자리 숫자가 주어졌을 때, 여기서 숫자 K개를 지워서 얻을 수 있는 가장 큰 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N과 K가 주어진다. (1 ≤ K < N ≤ 500,000)

둘째 줄에 N자리 숫자가 주어진다. 이 수는 0으로 시작하지 않는다.

출력
입력으로 주어진 숫자에서 K개를 지웠을 때 얻을 수 있는 가장 큰 수를 출력한다.
*/
#include <iostream>
#include <deque>
#define MAX_N 500000
using namespace std;


int main(void) {
    int n, k;
    int N[500000] = {0,};
    cin >> n >> k;
    deque dq(0,1);
    int kCount = 0;
    
    for(int s=0;s<n;s++){
        scanf("%1d", &N[s]);

        if(dq.empty() || kCount == k) dq.push_back(N[s]);
        else{
            if(dq.back() >= N[s]) dq.push_back(N[s]);
            else {
                while(dq.back() < N[s] && kCount < k){
                    kCount += 1;
                    dq.pop_back();
                    if(dq.empty()) break;
                }
                dq.push_back(N[s]);
            }
        }
    }
    for(int s=0;s<dq.size() -k+kCount;s++){
        cout<<dq[s];
    }

}

