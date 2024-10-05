#include <iostream>
using namespace std;

#define FIO ios_base::sync_with_stdio(false), cin.tie(0), cout.tie(0)

/**
 * N 번째 피보나치 수
 * f(n)을 호출 했을 때 0, 1 몇번 출력되는지 구하는 프로그램을 작성
 * 
 */

int fibonacci_array[40] = {
    0,
};

int zero_count[40] = {
    0,
};

int one_count[40] = {
    0,
};

int fibonacci(int n) {
  if(n==0){
    return 0;
  }
  else if (n == 1)
  {
    return 1;
  }
  else
  {

    return fibonacci_array[n] != 0 ? fibonacci_array[n] : fibonacci_array[n] = fibonacci(n - 1) + fibonacci(n - 2);
  }
}


void solve(int n){
  fibonacci(n);
}
void output(int n){
  cout << ((n==0) ? 1 : fibonacci_array[n-1]) << " " << fibonacci_array[n] << "\n";
}

void input(){
  int test_case_count;
  cin >> test_case_count;

  zero_count[0] = 1;
  one_count[0] = 1;
  fibonacci_array[1] = 1;

  for (int k = 0; k < test_case_count ; k++){
    int n;
    cin >> n;
    solve(n);
    output(n);
    
  }
}

int main(void){
  input();
}