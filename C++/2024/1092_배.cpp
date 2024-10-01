#include <iostream>
#include <algorithm>
#include <deque>
using namespace std;

#define FIO ios_base::sync_with_stdio(false), cin.tie(0), cout.tie(0)

/**
 *  화물에 배를 실어야 한다.
 *  모든 화물은 박스 안에 넣어져있다.
 *  항구에는 크레인이 N 대
 *  1분에 박스는 하나씩 배에 실을 수 있다. (모든 크레인은 동시에 움직인다.)
 *  각 크레인은 무게 제한이 있다. 무게 제한보다 무거운 박스는 크레인으로 움직일 수 없다.
 *  모든 박스를 배로 옮기는 데 드는 시간의 최솟값
 *
 *  INPUT )
 *  N (<= 50)
 *  크레인 무게 제한 (<= 1000000)
 *  M (<= 10000)
 *  각 박스의 무게 (<= 1000000)
 */

#define MAX_BOX_WEIGHT 1000000
#define MAX_BOX_COUNT 10000
#define MAX_CRANE_COUNT 50



int _N, _M;
int _crane_list[MAX_CRANE_COUNT];
int _time = 0;
deque<int> _box_list;

bool compare(int a, int b){
  return a > b;
}

void test_print(){
    for (int k = 0; k < _N; k++)
  {
    cout << _crane_list[k] << " ";
  }
  cout << endl;

  for (int k = 0; k < _M; k ++){
    cout << _box_list[k] << " ";
  }
  cout << endl;
}

void solve(){
  // 내림 차순으로 crane, box를 정렬
  sort(_crane_list, _crane_list + _N, compare);
  sort(_box_list.begin(), _box_list.end(), compare);

  if (_box_list.front() > _crane_list[0])
  {
    _time = -1;
    return;
  }
  // 모든 box를 제거할 때 까지 반복
  while(!_box_list.empty()){
    // 시간 추가
    _time++;
    // crane idx
    int curr_crane = 0;


    deque<int>::iterator iter;

    for (iter = _box_list.begin(); iter != _box_list.end(); ){
      // crane의 가능 무게보다 박스가 작으면 옮기기
      if(curr_crane <_N && _crane_list[curr_crane] >= *iter) {
        // 박스 리스트에서 지우고, crane idx 1 더하기
        iter = _box_list.erase(iter);
        curr_crane++;
        if(curr_crane >= _N)
          break;
      }
      else
      {
        iter++;
      }
    }
  }
}

void input(){
  cin >> _N;
  for (int k = 0; k < _N; k ++) {
    cin >> _crane_list[k];
  }
  cin >> _M;
  for (int k = 0; k < _M; k++) {
    int temp;
    cin >> temp;
    _box_list.push_front(temp);
  }
}

void output() {
  cout << _time << "\n";
}

int main(void) {
  input();
  solve();
  output();
}
