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

#define FIO cin.tie(0), cout.tie(0), ios::sync_with_stdio(false);
using namespace std;


class Stuff{
    public: int index;
    public: int weight;
    public: int value;

    public: Stuff(int i, int w, int v){
        this->index = i;
        this->weight = w;
        this->value = v;
    }
};

int N, K;
int stuff[100][2]; // 0 : val , 1 : wgt
int dp[100000];

void input(){
    cin >> N >> K ;
    for(int k = 0 ; k < N ; k ++){
        int wgt, val;
        cin >> wgt >> val;
        stuff[k][0] = val;
        stuff[k][1] = wgt;
    }

    for(int k = 0 ; k < K+1; k++){
        dp[k] = 0;
    }
}

void solve() {
    queue<Stuff> v;
    for(int k = 0 ; k < N ; k ++){
        Stuff initStuff = Stuff(k, stuff[k][0], stuff[k][1]);
        v.push(initStuff);

        while(!v.empty()){
            Stuff currStuff = v.front();
            v.pop();

            int currStuffNumber = currStuff.index;
            int currSumWeight = currStuff.weight;
            int currSumValue = currStuff.value;

            for(int s = currStuffNumber + 1 ;s < N ; s++){

                int sum = currSumWeight + stuff[s][1];
                if(currSumWeight + stuff[s][1] > K) continue;
                int val = currSumValue + stuff[s][0];
                if(dp[sum] > val){

                }

            }
        }
    }
}







int main(void){
    FIO;
    input();
    solve();
}