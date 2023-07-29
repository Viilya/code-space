////////////////////////////////////////////////////////////
//  Date : 2023.01.27 coded
//  Problem : 2667
//  Language : C++
//  by Kwanwoo Kim                                                      
////////////////////////////////////////////////////////////    

/*
문제
<그림 1>과 같이 정사각형 모양의 지도가 있다. 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다. 철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다. 여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 대각선상에 집이 있는 경우는 연결된 것이 아니다. <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다. 지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.

입력
첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.

출력
첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
*/

#include <iostream>
#define MAX_MAP 25

using namespace std;

//              CLASS DECLARATION
class Node{
    public :
        int xVal;
        int yVal;
        Node *next;
        Node() : xVal(0), yVal(0), next(nullptr) {};
        ~Node() {};
};

class Queue{
    private:
        Node *head;
        Node *tail;
        int size;
    public:
        Queue() : head(nullptr), tail(nullptr), size(0) {}
        ~Queue () {};
        void Enqueue(int _x, int _y);
        int Dequeue();
        int GetXVal();
        int GetYVal();
        bool Empty();
};

void Queue::Enqueue(int _x, int _y){
    Node * newNode = new Node;
    newNode -> xVal = _x;
    newNode -> yVal = _y;
    size ++;
    if(head == nullptr){
        head = newNode;
        tail = newNode;
    }else{
        tail -> next = newNode;
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
        ptr = ptr -> next;
        delete(head);
        head = ptr;
    }return 0;
}int Queue::GetXVal(){
    Node * ptr = head;
    return ptr->xVal;
}int Queue::GetYVal(){
    Node *ptr = head;
    return ptr->yVal;
}bool Queue::Empty(){
    if(tail == nullptr)
        return true;
    else 
        return false;
}


//              GLOBAL VARIABLE

int _map[MAX_MAP][MAX_MAP] = {0,};
int _mapCounted[MAX_MAP][MAX_MAP] = {0, };
int _complexCount = 0;
int _houseCount =0;
int _houseCountArr[MAX_MAP * MAX_MAP] = {0, };
Queue _Queue;

//              FUNCTION DECLARATION

void Swap(int k, int s){
    int tmp;
    tmp = _houseCountArr[k];
    _houseCountArr[k] = _houseCountArr[s];
    _houseCountArr[s] = tmp;
}
/** 
 * 퀵소트
*/
void QuickSort(int start, int end){
    if(start >= end){
        return ;
    }
    int pivot = start;
    int point_start = pivot + 1;
    int point_end = end;
    int temp;
    while(point_start <= point_end){
        while(point_start <= end && _houseCountArr[point_start] <= _houseCountArr[pivot])
            point_start += 1;
        while(point_end > start && _houseCountArr[point_end] >= _houseCountArr[pivot])
            point_end -= 1;
        if(point_start > point_end){
            Swap(pivot, point_end);
        }else{
            Swap(point_start, point_end);
        }
    }
    
    QuickSort(start, point_end - 1);
    QuickSort(point_end + 1, end);
    
    
}

void EnqueuePoints(int x, int y){
    if(x >= 0 && y>= 0 && x < MAX_MAP && y < MAX_MAP ){
        if(_mapCounted[x][y] == 0 && _map[x][y] == 1){
            _Queue.Enqueue(x, y);
            _mapCounted[x][y] = _complexCount;
        }
    }
}

void BFS(int x, int y){
    
    EnqueuePoints(x-1, y);
    EnqueuePoints(x+1, y);
    EnqueuePoints(x, y-1);
    EnqueuePoints(x, y+1);

    if(!_Queue.Empty()){
        
        _houseCount += 1;
        int _x, _y;
        _x = _Queue.GetXVal();
        _y = _Queue.GetYVal();
        _Queue.Dequeue();
        BFS(_x, _y);
    }

}

void FindComplex(int N){
    int k, s;
    for(k=0;k<N;k++){
        for(s=0;s<N;s++){
            _houseCount = 0;
            if(_map[k][s] == 1 && _mapCounted[k][s] == 0){
                _complexCount += 1;
                _houseCount += 1;
                _mapCounted[k][s] = _complexCount;
                BFS(k, s);
                _houseCountArr[_complexCount] = _houseCount;
            }
        }
    }
    cout << _complexCount << endl;
    QuickSort(1, _complexCount);
    for(k=1;k<=_complexCount;k++){
        cout << _houseCountArr[k] << endl;
    }
}

int main(void){
    int N;
    int k, s;

    cin >> N;
    for(k=0;k<N;k++){
        for(s=0;s<N;s++){
            scanf("%1d", &_map[k][s]);
        }
    }
    
    FindComplex(N);

}