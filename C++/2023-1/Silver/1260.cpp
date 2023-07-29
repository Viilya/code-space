////////////////////////////////////////////////////////////
//  Date : 2023.01.25 coded
//  Problem : 1260
//  Language : C++
//  by Kwanwoo Kim                                                      
////////////////////////////////////////////////////////////    

/*
문제
그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.

입력
첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.

출력
첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.
*/

#include <iostream>
#define ROUTE_MAX 10001
#define POINT_MAX 1001

using namespace std;

typedef struct{
    int route[POINT_MAX] = {0,};
}Point;

Point _points[POINT_MAX];

class Node{
    public:
        int value;
        Node* next;
        Node() : value(0), next(nullptr) {};
        ~Node() {};
};

class Queue{
    public:
        Queue() : head(nullptr), tail(nullptr), size(0) {};
        ~Queue() {};
        void Enqueue(int _value);
        int Dequeue();
        bool empty();
    private:
        Node* tail;
        Node* head;
        int size;
};

void Queue::Enqueue(int _value){
    Node * newNode = new Node;
    newNode -> value = _value;
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
    Node* ptr = head;
    int valueT = head->value;
    if(head == tail){
        head = nullptr;
        tail = nullptr;
        delete(head);
    }else{
        ptr = ptr->next;
        delete(head);
        head = ptr;
    }return valueT;
}

bool Queue::empty(){
    if(tail == nullptr)
        return true;
    else
        return false;
}

int _visitedPoints[POINT_MAX] = {0,};
int _pointNum;
int _startPoint;
int _routeNum;

Queue _Queue;

void init(){
    for(int k = 0 ; k < POINT_MAX;k++){
        _visitedPoints[k] = 0;
    }cout << "\n" ;
}

void DFS(int Point){
    int k;
    
    _visitedPoints[Point] = 1;
    cout<<Point<<" ";
    for(k=1;k<=_pointNum;k++){
        if(_points[Point].route[k] != 0 && _visitedPoints[k] == 0){
            DFS(k);
        }
    }
}

void BFS(int Point){
    int k;
    _visitedPoints[Point] = 1;
    for(k=1;k<=_pointNum;k++){
        if(_points[Point].route[k] != 0 && _visitedPoints[k] == 0){
            cout << k <<" ";
            _Queue.Enqueue(k);
            _visitedPoints[k] = 1;
        }
    }
    if(!_Queue.empty())
        BFS(_Queue.Dequeue());
}


int main(void){
    cin >> _pointNum >> _routeNum >> _startPoint;
    int k, s;
    int pointOne, pointTwo;
    for(k=0;k<_routeNum;k++){
        cin >> pointOne >> pointTwo;
        _points[pointOne].route[pointTwo] = 1;
        _points[pointTwo].route[pointOne] = 1;
    }
    
    DFS(_startPoint);
    init();
    cout << _startPoint << " ";
    BFS(_startPoint);
    return 0;
}