//
// Created by SSAFY on 2024-02-20.
//

#include <iostream>
#include <math.h>
#define COUNT_MAX 100000
using namespace std;

int N;
int *dp;

void input(){

    cin >> N;
    dp = new int[N+1];

    for(int k = 0 ; k < N+1; k ++){
        dp[k] = COUNT_MAX;
    }

    dp[0] = 0;
    dp[1] = -1;
    dp[3] = -1;

}

void solve(){
    for(int k = 0 ; k < N+1 ; k ++){
        if(dp[k] != -1) {
            if (k + 2 < N + 1) {
                 dp[k + 2] = min(dp[k + 2], dp[k] + 1);
            }
            if(k + 5 < N + 1) {
                dp[k + 5] = min(dp[k + 5], dp[k] + 1);
            }
        }
    }
}

void output(){
    cout << dp[N];
}

int main (void){
    input();
    solve();
    output();
}



/*

 10 5 5
 11 5 2 2 2
 12 5 5 2
 13 5 2 2 2 2
 14 5 5 2 2

1 3 2 4 3

 */