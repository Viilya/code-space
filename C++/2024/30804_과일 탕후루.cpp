//
// Created by KKO on 2024-12-23.
//

#include <iostream>
using namespace std;

#define FIO ios_base::sync_with_stdio(false);cin.tie(NULL);cout.tie(NULL);

/**
* N 개의 과일 탕후루
  1 ~ 9 종류의 과일
  s1, s2 ... sn 과일이 꽂혀있다.
  두 종류 이하로 사용해달라는 요청
  앞, 뒤에서 몇개의 과일을 빼서 두 종류 이하의 과일만 남기기로 했음
  N - (a+b) 개의 과일
  과일 개수가 가장 많은 탕후루의 과일 개수
*/


int _N;
int _S[200000] = {0, };
int _max_len;

int max(int a, int b){
  return a>b?a:b;
}

void input(void){
  cin >> _N;

  for(int k = 0 ; k < _N ; k++){
    cin >> _S[k];
  }
}

void solve(){
  int count_fruit[10] = {0,};
  int count_kind = 0;
  int length = 0;
  int left = 0, right = 0;

  while(right < _N){
    if(count_fruit[_S[right]] == 0){
      count_kind ++;
    }
    count_fruit[_S[right]] ++;
    length ++;

    if(count_kind > 2){
      while(count_kind > 2){
        count_fruit[_S[left]] --;
        length --;
        if(count_fruit[_S[left]] == 0){
          count_kind--;
        }
        left ++;
      }
    }
    _max_len = max(_max_len, length);
    right ++;
  }
}

void output(){
  cout << _max_len;
}

int main (void){
  FIO;
  input();
  solve();
  output();
  return 0;
}