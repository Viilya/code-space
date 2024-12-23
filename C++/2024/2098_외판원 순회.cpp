#include <iostream>
using namespace std;

#define FIO ios_base::sync_with_stdio(false), cin.tie(0), cout.tie(0)

/**
 * Traveling Salesman Problem(TSP)
 * 1 ~ N 번호 도시 
 * 한 도시에서 출발해 도시를 모두 거쳐 다시 도시로 돌아오는 순회 여행 경로 계획 
 * 한 번 갔떤 도시로는 갈 수 없음
 * 가장 적은 비용을 들이는 여행계획을 세우려고 한다.
 * 비용은 대칭적이지 않다. (모든 비용은 양의 정수) 
 * 
 * In
 * N (2 <= <= 16)
 * W[N][N]
 */

#define MIN(a,b) ((a>b)?(b):(a))

#define MAX_N 16
#define MAX_DP (1 << 16)
#define MAX_INT (1 << 30)


int _map[MAX_N][MAX_N];
int _dp[16][MAX_DP];
int _N;
int _answer_bit;


void input(){
  cin >> _N;
  _answer_bit = (1 << _N) - 1;
  for (int k = 0; k < _N; k ++){
    for (int s = 0; s < _N; s++){
      cin >> _map[k][s];
    }
  }

  for (int i = 0; i < 16; i ++){
    for (int k = 0; k < MAX_DP; k++){
        _dp[i][k] = -1;
      }
  }
}



// 재귀 탐색
int solve(int curr_point, int curr_bits){
  if(curr_bits == _answer_bit){
    if(_map[curr_point][0] == 0)
      return MAX_INT;
    return _map[curr_point][0];
  }

  if(_dp[curr_point][curr_bits] != -1)
    return _dp[curr_point][curr_bits];
  _dp[curr_point][curr_bits] = MAX_INT;

  for (int k = 0; k < _N; k++){
    if(_map[curr_point][k] == 0)continue;
    if((curr_bits & (1 << k)) == (1<< k)) continue;

    _dp[curr_point][curr_bits] = MIN(_dp[curr_point][curr_bits], _map[curr_point][k] + solve(k, (curr_bits | (1 << k))));
  }
  return _dp[curr_point][curr_bits];
}

void output(){
  cout << solve(0, 1) << "\n" ;
}

int main(void){
  FIO;
  input();
  output();
}