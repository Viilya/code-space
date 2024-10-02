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
#define MAX_INT 2147483647


int _N, _M;
int _map[MAX_LENGTH][MAX_LENGTH];
int _isVisited[MAX_LENGTH][MAX_LENGTH][5];
int _min_command_count = MAX_INT;

int dir_x[] = {0, 0, 0, 1, -1};
int dir_y[] = {0, 1, -1, 0, 0};


class robot{
  public:int dir;
  public:int x;
  public:int y;
  public:
    int command_count;
  public:
    int curr_dir_move_count;
};

robot _start_point, _end_point;

deque<robot> dq;

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
  _start_point.command_count = 0;
  _start_point.curr_dir_move_count = 0;
  _end_point.x -= 1;
  _end_point.y -= 1;

  for (int k = 0; k < MAX_LENGTH; k++ ){
    for (int s = 0; s < MAX_LENGTH; s++){
      for (int i = 1; i < 5; i ++){
        _isVisited[k][s][i] = MAX_INT;
      }
    }
  }
}

void output(){
  cout << _min_command_count << "\n";
}


// 유효한 좌표인지 확인하는 함수
bool isAvailablePosition(int x, int y){
  return !(x < 0 || y < 0 || x >= _N || y >= _M || _map[x][y] == 1);
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



// bfs 알고리즘 방식으로 길을 찾는 함수
void bfs() {

  while(!dq.empty()){

    robot curr_robot = dq.front();
    dq.pop_front();

    // cout << "[ " << curr_robot.x << ", " << curr_robot.y << " / " << curr_robot.dir << " / " << curr_robot.command_count << "]\n";
    if(curr_robot.x == _end_point.x && curr_robot.y == _end_point.y && curr_robot.dir == _end_point.dir){
      _min_command_count = curr_robot.command_count;
      continue;
    }
    // 현재 방향에서 앞으로 한칸 움직이는 경우
    int next_x = curr_robot.x + dir_x[curr_robot.dir];
    int next_y = curr_robot.y + dir_y[curr_robot.dir];

    if(isAvailablePosition(next_x, next_y)){

      robot next_robot;
    
      next_robot.x = next_x;
      next_robot.y = next_y;
      next_robot.command_count = curr_robot.command_count;
      next_robot.dir = curr_robot.dir;

      // 움직인 거리에 따라 command 횟수 계산
      if (curr_robot.curr_dir_move_count == 3 || curr_robot.curr_dir_move_count == 0)
      {
        next_robot.curr_dir_move_count = 1;
        next_robot.command_count += 1;
      }else{
        next_robot.curr_dir_move_count = curr_robot.curr_dir_move_count + 1;
      }

      // 이전에 방문 했던 경우 보다 명령 횟수가 같거나 작은 경우만 움직인다.

      if(_isVisited[next_x][next_y][curr_robot.dir] >= next_robot.command_count){
        _isVisited[next_x][next_y][curr_robot.dir] = next_robot.command_count;
        dq.push_back(next_robot);
      }
    }

    // 회전 계산

    for (int next_dir = 1; next_dir < 5; next_dir++){
      
      int rotate_counter = calcCommandCountWithDirection(curr_robot.dir, next_dir);
      if(rotate_counter == 1){
        if(_isVisited[curr_robot.x][curr_robot.y][next_dir] > curr_robot.command_count + 1){
          robot next_robot;
          next_robot.x = curr_robot.x;
          next_robot.y = curr_robot.y;
          next_robot.dir = next_dir;
          next_robot.command_count = curr_robot.command_count + 1;
          next_robot.curr_dir_move_count = 0;
          _isVisited[curr_robot.x][curr_robot.y][next_dir] = curr_robot.command_count + 1;
          dq.push_back(next_robot);
        }
      }
    }

    //   if (curr_robot.x == _end_point.x && curr_robot.y == _end_point.y)
    //   {
    //     int final_count = count + calcCommandCountWithDirection(curr_robot.dir, _end_point.dir);
    //     _min_command_count = MIN(final_count, _min_command_count);
    //     return;
    //   }

    // for (int next_dir = 1; next_dir < 5;  next_dir++){
    //   int next_x = curr_robot.x + dir_x[next_dir];
    //   int next_y = curr_robot.y + dir_y[next_dir];

    //   if(isAvailablePosition(next_x, next_y)){
    //     int rotation_command_count = calcCommandCountWithDirection(curr_robot.dir, next_dir);
    //     robot next_robot;
    //     int command_count = 0;
    //     int next_dir_move_count = 0;

    //     if (next_dir != curr_robot.dir || dir_move_count == 3 || dir_move_count == 0)
    //     {
    //       command_count = 1;
    //       next_dir_move_count = 1;
    //     }else{
    //       next_dir_move_count = dir_move_count + 1;
    //     }

    //     _isVisited[next_x][next_y] = true;
    //     next_robot.x = next_x;
    //     next_robot.y = next_y;
    //     next_robot.dir = next_dir;
    //     bfs(next_robot, count + command_count + rotation_command_count, next_dir_move_count);
    //     _isVisited[next_x][next_y] = false;
    //   }
    // }
  }
}

void solve(){
  dq.push_back(_start_point);
  bfs();
}

int main(void){
  FIO;
  input();
  solve();
  output();
}