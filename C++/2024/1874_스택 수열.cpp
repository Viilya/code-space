#include <iostream>
#include <deque>
using namespace std;

#define FIO ios_base::sync_with_stdio(false), cin.tie(0), cout.tie(0)

/**
 * 1~n 수열을 스택에 넣었다가 뽑아 놓음으로써 하나의 수열을 만들 수 있음 -> 스택에 push 하는 순서는 오름차순
 * -> 임의의 수열이 주어졌을 때 스택을 이용해 그 수열을 만들 수 있는지? 있다면 어떤 순서로 push pop 연산을 수행해야 하는지 ?
 * 
 * N (1<=n <= 100,000) */

#define MAX_INT 100000

int _N;
deque<char> result;
deque<int> stack;

void output(){
  while(!result.empty()){
    cout << result.front() << '\n';
    result.pop_front();
  }
}

void input(){
  cin >> _N;
  int current_number = 1;

  for (int k = 0; k < _N; k++)
  {
    int next_T;
    cin >> next_T;
    if(current_number <= next_T){
      while(true){
        if(current_number == next_T) {
          result.push_back('+');
          result.push_back('-');
          current_number += 1;
          break;
        }
        stack.push_back(current_number);
        result.push_back('+');
        current_number += 1;
      }
    }else if(!stack.empty() && stack.back() == next_T){
      stack.pop_back();
      result.push_back('-');
    }else {
      cout << "NO" << "\n";
      return;
    }
  }
  output();
}


int main (void){
  input();
}