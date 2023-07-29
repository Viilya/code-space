////////////////////////////////////////////////////////////
//  Date : 2023.01.25 coded
//  Problem : 2178
//  Language : C++
//  by Kwanwoo Kim                                                      
////////////////////////////////////////////////////////////    

/*
문제
N×M크기의 배열로 표현되는 미로가 있다.

1	0	1	1	1	1
1	0	1	0	1	0
1	0	1	0	1	1
1	1	1	0	1	1
미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.

위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.

입력
첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.

출력
첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
*/


#include <iostream>
#define MAX_LEN 1000

using namespace std;

int _maze[MAX_LEN][MAX_LEN] ={0,};
int _res[MAX_LEN][MAX_LEN] ={0, };
int _result = 1000000;
int _N, _M;

int _MIN(int a, int b){
    if(a>b) return b;
    else return a;
}

class Node{
    public:
        int xValue;
        int yValue;
        Node *next;
        Node() : xValue(0), yValue(0), next(nullptr) {};
        ~Node() {};
};
class Queue{
    private:
        Node *head;
        Node *tail;
        int size;
    public:
        Queue() : head(nullptr), tail(nullptr), size(0) {};
        ~Queue() {};
        void Enqueue(int _x, int _y);
        int Dequeue();
        int GetXCor();
        int GetYCor();
        bool empty();
};

void Queue::Enqueue(int _x, int _y){
    Node * newNode = new Node;
    newNode -> xValue = _x;
    newNode -> yValue = _y;
    size ++;
    if(head == nullptr){
        head = newNode;
        tail = newNode;
    }else{
        tail->next = newNode;
        tail = tail->next;
    }
}
int Queue::Dequeue(){
    size --;
    Node *ptr = head;
    if(head == tail){
        head = nullptr;
        tail = nullptr;
        delete(head);
    }else{
        ptr = ptr->next;
        delete(head);
        head = ptr;
    }return 0;
}

int Queue::GetXCor(){
    Node * ptr = head;
    return ptr->xValue;
}
int Queue::GetYCor(){
    Node * ptr = head;
    return ptr->yValue;
}

Queue _Queue;

bool Queue::empty(){
    if(tail == nullptr)
        return true;
    else
        return false;
}

void EnqueueClosePoints(int _x, int _y, int _s){
    if(_x >= 0 && _y >= 0 && _x < _N && _y < _M && _maze[_x][_y] == 1){
        if(_res[_x][_y] == 0){
            _Queue.Enqueue(_x, _y);
            _res[_x][_y] = _s+1;
        }

    }
}

void BFS(int _x, int _y){
    int s = _res[_x][_y];
    if(_x == _N-1 && _y == _M-1)
        _result = _MIN(_result, s);
    else {
        EnqueueClosePoints(_x+1, _y, s);
        EnqueueClosePoints(_x-1, _y, s);
        EnqueueClosePoints(_x, _y-1, s);
        EnqueueClosePoints(_x, _y+1, s);

        if(!_Queue.empty()){
            int x_t, y_t;
            x_t = _Queue.GetXCor();
            y_t = _Queue.GetYCor();
            if(_x == _N-1 && _y == _M-1)
            _result = _MIN(_result, s);
            _Queue.Dequeue();
            BFS(x_t, y_t);
        }
    }

}

int main(void){
    int k, s;
    cin >> _N >> _M;
    
    for(k=0;k<_N;k++){
        for(s=0;s<_M;s++){
            scanf("%1d", &_maze[k][s]);
        }
    }
    BFS(0, 0);
    cout << _result;
}