////////////////////////////////////////////////////////////
//  Date : 2023.01.27 coded
//  Problem : 2667
//  Language : C++
//  by Kwanwoo Kim                                                      
////////////////////////////////////////////////////////////

/*
문제
철수의 토마토 농장에서는 토마토를 보관하는 큰 창고를 가지고 있다. 토마토는 아래의 그림과 같이 격자 모양 상자의 칸에 하나씩 넣어서 창고에 보관한다. 



창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다. 보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다. 하나의 토마토의 인접한 곳은 왼쪽, 오른쪽, 앞, 뒤 네 방향에 있는 토마토를 의미한다. 대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다. 철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지, 그 최소 일수를 알고 싶어 한다.

토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때, 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램을 작성하라. 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.

입력
첫 줄에는 상자의 크기를 나타내는 두 정수 M,N이 주어진다. M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다. 단, 2 ≤ M,N ≤ 1,000 이다. 둘째 줄부터는 하나의 상자에 저장된 토마토들의 정보가 주어진다. 즉, 둘째 줄부터 N개의 줄에는 상자에 담긴 토마토의 정보가 주어진다. 하나의 줄에는 상자 가로줄에 들어있는 토마토의 상태가 M개의 정수로 주어진다. 정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.

토마토가 하나 이상 있는 경우만 입력으로 주어진다.
*/

////////////////////////////////////////////////////////////
//  Date : 2023.01.27 coded
//  Problem : 2667
//  Language : C++
//  by Kwanwoo Kim                                                      
////////////////////////////////////////////////////////////

/*
문제
철수의 토마토 농장에서는 토마토를 보관하는 큰 창고를 가지고 있다. 토마토는 아래의 그림과 같이 격자 모양 상자의 칸에 하나씩 넣어서 창고에 보관한다. 



창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다. 보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다. 하나의 토마토의 인접한 곳은 왼쪽, 오른쪽, 앞, 뒤 네 방향에 있는 토마토를 의미한다. 대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다. 철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지, 그 최소 일수를 알고 싶어 한다.

토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때, 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램을 작성하라. 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.

입력
첫 줄에는 상자의 크기를 나타내는 두 정수 M,N이 주어진다. M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다. 단, 2 ≤ M,N ≤ 1,000 이다. 둘째 줄부터는 하나의 상자에 저장된 토마토들의 정보가 주어진다. 즉, 둘째 줄부터 N개의 줄에는 상자에 담긴 토마토의 정보가 주어진다. 하나의 줄에는 상자 가로줄에 들어있는 토마토의 상태가 M개의 정수로 주어진다. 정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.

토마토가 하나 이상 있는 경우만 입력으로 주어진다.
*/

#include <iostream>

//                  define
#define LEN_MAX 1000

using namespace std;

//                  CLASS DECLARATION

class Node{
    public:
        int xValue;
        int yValue;
        int days;
        Node * next;
        Node() : xValue(0), yValue(0), days(0), next(nullptr){};
        ~Node() {};

};

class Queue{
    private:
        Node *head;
        Node *tail;
    public:
        Queue() : head(nullptr), tail(nullptr) {};
        ~Queue() {};

        void Enqueue(int x, int y, int days){
            Node *newNode = new Node;
            newNode -> xValue = x;
            newNode -> yValue = y;
            newNode -> days = days;
            if(head == nullptr){
                head = newNode;
                tail = newNode;
            }else{
                tail -> next = newNode;
                tail = newNode;
            }
        }
        void Dequeue(){
            Node *ptr = head;
            if(head == tail){
                head = nullptr;
                tail = nullptr;
                delete(head);
            }else{
                ptr = ptr->next;
                head = ptr;
            }
        }
        int GetXValue(){
            Node * ptr = head;
            return ptr->xValue;
        }
        int GetYValue(){
            Node * ptr = head;
            return ptr->yValue;
        }
        int GetDays(){
            Node *ptr = head;
            return ptr->days;
        }
        bool isEmpty(){
            if(tail == nullptr){
                return true;
            }else{
                return false;
            }
        }
        
};


//                  GLOBAL VARIABLE

int _box[LEN_MAX][LEN_MAX] = {0,};
int _daysSpent = 0;
Queue _Queue;

//                  FUNCTION DECLARATION

bool isMature(int N, int M){
    int k, s;
    for(k=0;k<N;k++){
        for(s=0;s<M;s++){
            if(_box[k][s] == 0)
                return false;
        }
    }return true;
}

void CheckAndEnqueueTomato(int x, int y, int N, int M, int days){
    if(x >= 0 && x < N && y >= 0 && y < M){
        if(_box[x][y] == 0 ){
            _box[x][y] = 1;
            _Queue.Enqueue(x, y, days);
        }
    }
}

void TomatoGrows(int N, int M, int days){
    int x, y;
    while(!_Queue.isEmpty() && _Queue.GetDays() < days){
        x = _Queue.GetXValue();
        y = _Queue.GetYValue();
        CheckAndEnqueueTomato(x-1, y, N, M, days);
        CheckAndEnqueueTomato(x+1, y, N, M, days);
        CheckAndEnqueueTomato(x, y-1, N, M, days);
        CheckAndEnqueueTomato(x, y+1, N, M, days);

        _Queue.Dequeue();
    }
}

void TomatoSimulation(int N, int M){
    int k, s;

    for(k=0;k<N;k++){
        for(s=0;s<M;s++){
            if(_box[k][s] == 1){
                _Queue.Enqueue(k, s, 0);
            }
        }
    }
    while(!_Queue.isEmpty()){
        _daysSpent += 1;
        TomatoGrows(N, M, _daysSpent);
    }

}

//                  MAIN

int main(void){
    int N, M;
    int k, s;

    cin >> M >> N;

    for(k = 0; k <N; k++){
        for(s=0;s<M;s++){
            cin >> _box[k][s];
        }
    }
    TomatoSimulation(N, M);
    if(isMature(N, M))
        cout << _daysSpent - 1;
    else{
        cout << "-1";
    }
}