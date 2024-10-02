#include <iostream>
#include <deque>
using namespace std;
#define FIO ios_base::sync_with_stdio(false), cin.tie(0), cout.tie(0);


/**
 * 로봇은 바라보는 방향으로 궤도를 따라 움직임 (동서남북)
 * 명령 1 : go k (k = 1, 2, 3) 향한 방향으로 k칸
 * 명령 2 : turn dir : left, right (90 회전)
 * 
 * 원하는 위치와 방향으로 이동하는 법
 */


#define ABS(a) ((a) >= 0 ? (a) : -(a))
#define MIN(a, b) a < b ? a : b
#define MAX_LENGTH 100


int _N, _M;
int _map[MAX_LENGTH][MAX_LENGTH];
bool _isVisited[MAX_LENGTH][MAX_LENGTH];
int _min_command_count = 2147483647;

int dir_x[] = {0, 0, 0, 1, -1};
int dir_y[] = {0, 1, -1, 0, 0};

class robot{
  public:int dir;
  public:int x;
  public:int y;
};

robot _start_point, _end_point;

void input(){
  cin >> _N >> _M;
  for (int k = 0; k < _N; k ++){
    for (int s = 0; s < _M; s++){
      cin >> _map[k][s];
    }
  }
  cin >> _start_point.x >> _start_point.y >> _start_point.dir;
  cin >> _end_point.x >> _end_point.y >> _end_point.dir;

  _start_point.x -= 1;
  _start_point.y -= 1;
  _end_point.x -= 1;
  _end_point.y -= 1;
}

void output(){
  cout << _min_command_count << "\n";
}


// 유효한 좌표인지 확인하는 함수
bool isAvailablePosition(int x, int y){
  return !(x < 0 || y < 0 || x >= _N || y >= _M || _map[x][y] == 1 || _isVisited[x][y]);
}

// // 현재 위치와 다음 위치를 계산해 방향을 계산하는 함수
// int getDirection(int x1, int y1, int x2, int y2, int dir){
//   int dx = x1 - x2;
//   int dy = y1 - y2;
//   for (int k = 1; k < 5; k++){
//     if(dx == dir_x[k] && dy == dir_y[k]){
//       return k;
//     }
//   }
//   return 0;
// }


// 방향 전환에 필요한 명령 숫자 구하기
int calcCommandCountWithDirection(int dir1, int dir2){
  if(dir1 == dir2)
    return 0;
  int dx = ABS(dir_x[dir1] - dir_x[dir2]);
  int dy = ABS(dir_y[dir1] - dir_y[dir2]);
  // cout << "dx dy " << dx << " " << dy << "\n";

  return (dx == 2 || dy == 2) ? 2 : 1;
}



// DFS 알고리즘 방식으로 길을 찾는 함수
void dfs(robot curr_robot, int count, int dir_move_count) {

  // cout << "[ " << curr_robot.x << ", " << curr_robot.y << " / " << count << "]\n";
  if(curr_robot.x == _end_point.x && curr_robot.y == _end_point.y){
    int final_count = count + calcCommandCountWithDirection(curr_robot.dir, _end_point.dir);
    _min_command_count = MIN(final_count, _min_command_count);
    return;
  }

  for (int next_dir = 1; next_dir < 5;  next_dir++){
    int next_x = curr_robot.x + dir_x[next_dir];
    int next_y = curr_robot.y + dir_y[next_dir];

    if(isAvailablePosition(next_x, next_y)){
      int rotation_command_count = calcCommandCountWithDirection(curr_robot.dir, next_dir);
      robot next_robot;
      int command_count = 0;
      int next_dir_move_count = 0;

      if (next_dir != curr_robot.dir || dir_move_count == 3 || dir_move_count == 0)
      {
        command_count = 1;
        next_dir_move_count = 1;
      }else{
        next_dir_move_count = dir_move_count + 1;
      }

      _isVisited[next_x][next_y] = true;
      next_robot.x = next_x;
      next_robot.y = next_y;
      next_robot.dir = next_dir;
      dfs(next_robot, count + command_count + rotation_command_count, next_dir_move_count);
      _isVisited[next_x][next_y] = false;
    }
  }
}

void solve(){
  _isVisited[_start_point.x][_start_point.y] = true;
  dfs(_start_point, 0, 0);
}

int main(void){
  FIO;
  input();
  solve();
  output();
}