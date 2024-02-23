//
// Created by SSAFY on 2024-02-22.
//
/*
 * N개의 물건 (1 <= 100)
 * 무게 W / 가치 V (1 <= 100000, 0 <= 1000)
 * 최대 K 만큼 무게를 넣을 수 있는 배낭 (1 <= 100000)
 * 가치의 최댓값
 */

#include <iostream>
#include <queue>
#include <math.h>
#define FIO cin.tie(0), cout.tie(0), ios::sync_with_stdio(false);
using namespace std;

int N, K;
int stuff[100][2]; // 0 : val , 1 : wgt
int dp[100001];
void input()
{
    cin >> N >> K;

    for (int s = 0; s < 100001; s++)
    {
        dp[s] = 0;
    }
    for (int k = 0; k < N; k++)
    {
        int wgt, val;
        cin >> wgt >> val;
        stuff[k][0] = val;
        stuff[k][1] = wgt;
    }
}

int MAX_VAL = 0;

void solve()
{
    for (int k = 0; k < N; k++)
    {
        for (int s = K; s >= stuff[k][1]; s--)
        {
            dp[s] = max(dp[s], dp[s - stuff[k][1]] + stuff[k][0]);
        }
    }
}

void output()
{
    cout << dp[K] << "\n";
}

int main(void)
{
    FIO;
    input();
    solve();
    output();
}