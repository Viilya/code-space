#include <iostream>
#include <vector>
#define FIO cin.tie(0), cout.tie(0), ios::sync_with_stdio(false)

using namespace std;

vector<vector<int>> MAP(15, vector<int>(15, 0));
int N;
int RESULT;

void input() {
    cin >> N;
}

void backTracking(int x, int y, int count){
    int s = y;
    for(int k = 0 ; k < N ; k ++){
        for(; s < N; s++){

        }
        s = 0;
    }
}


void solve(){
    for(int k = 0 ; k < N ; k ++){
        for(int s = 0 ; s< N ; s++){
            MAP[k][s] = 1;
            backTracking(k, s, 1);
            MAP[k][s] = 0;
        }
    }
}

void output(){
    cout << RESULT << "\n";
}

int main(void){
    FIO;
    input();
    solve();
    output();
}


