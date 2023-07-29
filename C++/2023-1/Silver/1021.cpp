////////////////////////////////////////////////////////////
//  Date : 2023.01.13 coded
//  Problem : 1021
//  Language : C++
//  by Kwanwoo Kim                                                      
////////////////////////////////////////////////////////////       
 /*
 Problem : 
지민이는 N개의 원소를 포함하고 있는 양방향 순환 큐를 가지고 있다. 지민이는 이 큐에서 몇 개의 원소를 뽑아내려고 한다.

지민이는 이 큐에서 다음과 같은 3가지 연산을 수행할 수 있다.

첫 번째 원소를 뽑아낸다. 이 연산을 수행하면, 원래 큐의 원소가 a1, ..., ak이었던 것이 a2, ..., ak와 같이 된다.
왼쪽으로 한 칸 이동시킨다. 이 연산을 수행하면, a1, ..., ak가 a2, ..., ak, a1이 된다.
오른쪽으로 한 칸 이동시킨다. 이 연산을 수행하면, a1, ..., ak가 ak, a1, ..., ak-1이 된다.
큐에 처음에 포함되어 있던 수 N이 주어진다. 그리고 지민이가 뽑아내려고 하는 원소의 위치가 주어진다. (이 위치는 가장 처음 큐에서의 위치이다.) 이때, 그 원소를 주어진 순서대로 뽑아내는데 드는 2번, 3번 연산의 최솟값을 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 큐의 크기 N과 뽑아내려고 하는 수의 개수 M이 주어진다. N은 50보다 작거나 같은 자연수이고, M은 N보다 작거나 같은 자연수이다. 둘째 줄에는 지민이가 뽑아내려고 하는 수의 위치가 순서대로 주어진다. 위치는 1보다 크거나 같고, N보다 작거나 같은 자연수이다. */

#include <iostream>
#define MAX_QUEUE_SIZE 52
#define MIN(x,y)x>y?y:x
using namespace std;

int _Queue[MAX_QUEUE_SIZE] = {0,};
int _result = 0; // count;;
int _currPos = 1;

void adjustCurrPos(){
    while(1){
        if(_Queue[_currPos] == 0){
            _currPos += 1;
            if(_currPos > _Queue[0])
                _currPos = 1;
        }else{
            break;
        }
    }
}

// pop Number from the queue
void popNum(int Num){
    int leftCount = 0;
    int rightCount = 0;
    int leftCountQueue[MAX_QUEUE_SIZE] ={0,};
    int rightCountQueue[MAX_QUEUE_SIZE] = {0,};
    int tmpPos = _currPos;
    for(int i=0;i<MAX_QUEUE_SIZE;i++)
        leftCountQueue[i] = _Queue[i];
    
    //leftCount
    while(1){
        //cout<<_currPos << " ";
        if(_currPos == Num){ // pop
            leftCountQueue[_currPos] = 0;
            _currPos += 1;
            adjustCurrPos();        
            break;
        }
        if(_currPos == 0)
            _currPos = leftCountQueue[0] + 1;
        if(leftCountQueue[_currPos] != 0)
            leftCount += 1;
        _currPos -= 1;
    }
    
    //cout << "\n## CP 1.1 ## Count : " << leftCount <<" ##\n";
    
    for(int i=0;i<MAX_QUEUE_SIZE;i++)
        rightCountQueue[i] = _Queue[i];
    _currPos = tmpPos;
    //rightCount
    while(1){
        //cout<<_currPos << " ";
        if(_currPos == Num){
            rightCountQueue[_currPos] = 0;
            _currPos += 1;
            adjustCurrPos();
            
            break;
        }
        if(_currPos > rightCountQueue[0])
            _currPos = 0;
        if(rightCountQueue[_currPos] != 0 && _currPos != 0)
            rightCount += 1;
        _currPos += 1;
    }
    //cout << "\n## CP 1.2 ## Count : " << rightCount <<" ##\n";
    _result += MIN(leftCount, rightCount);
    if(leftCount < rightCount){
        for(int i=0;i<MAX_QUEUE_SIZE;i++)
        _Queue[i] = leftCountQueue[i];
    }else{
        for(int i=0;i<MAX_QUEUE_SIZE;i++)
        _Queue[i] = rightCountQueue[i];
    }
}

int main(void){
    int QueueSize;
    int PopNumSize;
    int Num;
    int k, s; 
    
    //input session
    cin >> QueueSize >> PopNumSize;
    _Queue[0] = QueueSize;
    for(k=1;k<=QueueSize;k++)
        _Queue[k] = k;
    //cout << QueueSize << "\n" << PopNumSize << " \n";
    //cout << "## CP 1 ##\n";
    for(k=0;k<PopNumSize;k++){
        cin >> Num;
        //cout<< "######### CP 1.05 Num : " << Num<<" ############\n";
        popNum(Num);
    }

    cout << _result;    
}