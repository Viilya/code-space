//
// Created by KKO on 2024-12-24.
//

#include <iostream>
#include <deque>
#include <vector>
using namespace std;


#define FIO ios_base::sync_with_stdio(false), cin.tie(0), cout.tie(0);

/**
* NxM size campus
  상하좌우
  도연이가 만날 수 있는 사람의 수
*/

int N, M;
int init_x, init_y;
char map[600][600];

int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, 1, 0, -1};

bool isVisited[600][600];

int result = 0;

bool isAvailable(int x, int y){
    return !(x < 0 || x >= N || y < 0 || y >= M || isVisited[x][y] || map[x][y] == 'X');
}

void solve(){
  deque<pair<int,int>> dq;
  dq.emplace_back(init_x, init_y);

  while(!dq.empty()){
    pair<int, int> curr_point = dq.front();
    dq.pop_front();

    for(int k = 0 ; k < 4; k ++){
      int nx = curr_point.first + dx[k];
      int ny = curr_point.second + dy[k];

      if(isAvailable(nx, ny)){
        if(map[nx][ny] == 'P') result ++;
        isVisited[nx][ny] = true;
        dq.emplace_back(nx, ny);
      }
    }
  }
}

void input(){
  cin >> N >> M;
  for(int k = 0 ; k < N ; k ++ ){
    for(int s = 0 ; s < M ; s++){
      cin >> map[k][s];
      isVisited[k][s] = false;
      if(map[k][s] == 'I'){
        init_x = k;
        init_y = s;
        isVisited[k][s] = true;
      }
    }
  }
}

void output(){
  if (result == 0) {
    cout << "TT";
  }else {
    cout << result;
  }
}

int main(){
  FIO;
  input();
  solve();
  output();
}