#include <iostream>
using namespace std;

#define FIO ios_base::sync_with_stdio(false), cin.tie(0), cout.tie(0)

/**
 * 용액 -100,000,000 ~ 100,000,000
 * 두 용액을 혼합하면 두 용액의 특성값의 합
 * 0에 가장 가까운 용액
 * 10ml 씩 / 빈 20ml 시험관 하나씩
 * 
 * 가장 작은거로
 * 
 * 2<= N <= 100,000
 * Ai-1 <= Ai
 * 
 * I /
 * N
 * a1 a2 a3 ... an 
 *  
 * 
 * O / 
 * b
 */
#define ABS(a) (a>0 ? a : (-a))
#define MIN(a, b) a>b ? b : a

#define MAX_N 100000
#define MAX_INT (1 << 30);

int _N;
int _fluid[MAX_N];
int _min_diff = MAX_INT;


void input(){
  cin >> _N;
  for (int k = 0; k < _N; k++){
    cin >> _fluid[k];             
  }
}

void solve(){
  int left = 0;
  int right = _N - 1;
  while (left != right)
  {
    int real_diff = _fluid[left] + _fluid[right];
    if (real_diff > 0)
    {
      right--;
    }else if(real_diff < 0){
      left++;
    }
    else
    {
      _min_diff = 0;
      return;
    }

    if(ABS(real_diff) < ABS(_min_diff)){
      _min_diff = real_diff;
    }
  }
}

void output(){
  cout << _min_diff << "\n";
}

int main (void){
  FIO;
  input();
  solve();
  output();
}
