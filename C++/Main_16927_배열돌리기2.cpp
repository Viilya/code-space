//
// Created by SSAFY on 2024-02-20.
//

#include <iostream>
#include <queue>
#include <math.h>
using namespace std;
#define FIO ios::sync_with_stdio(0), cin.tie(NULL), cout.tie(NULL)

int N, M, R;
int arr[300][300];
int res[300][300];

void input(){
    cin >> N >> M >> R;
    for(int k = 0 ; k < N ; k ++ ) {
        for(int s = 0 ; s< M ; s++){
            cin >> arr[k][s];
        }
    }
}

bool isValid(int x, int y, int k){
    if(x >= N - k || x < k || y >= M - k || y < k){
        return false;
    }
    return true;
}

void solve(){
    queue<int> q[150];
    int dx[] = {0, 1, 0, -1};
    int dy[] = {1, 0, -1, 0};
    int minNum = min(N, M) / 2;

    for(int k = 0; k < minNum ; k++){
        int dir = 0;
        int x = k;
        int y = k;
        int count = 0 ;
        while(true){
//            cout << x << " " << y << "\n";
            q[k].push(arr[x][y]);
//            cout << " " << arr[x][y] << " ";
            x += dx[dir];
            y += dy[dir];

            if(!isValid(x, y, k)){
                x -= dx[dir];
                y -= dy[dir];
                dir ++;
                x += dx[dir];
                y += dy[dir];
            }

            if(x == k && y == k) break;
        }
//        cout << "\n";

        int rotate = R % q[k].size();

        for(int s = 0 ; s < rotate ; s++){
            int num = q[k].front();
            q[k].pop();
            q[k].push(num);
        }

        dir = 0;
        x = k;
        y = k;
        while(true){
            int num = q[k].front();
            q[k].pop();
            res[x][y] = num;
            x += dx[dir];
            y += dy[dir];

            if(!isValid(x, y, k)){
                x -= dx[dir];
                y -= dy[dir];
                dir ++;
                x += dx[dir];
                y += dy[dir];
            }

            if(x == k && y == k) break;
        }
    }



}

void output(){
    for(int k = 0 ; k < N ; k ++){
        for(int s = 0 ; s < M ; s++){
            cout << res[k][s] << " ";
        }
        cout << "\n";
    }
}

int main(void){
    FIO;
    input();
    solve();
    output();
}
