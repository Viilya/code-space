////////////////////////////////////////////////////////////
//  Date : 2023.01.18 coded
//  Problem : 9465
//  Language : C++
//  by Kwanwoo Kim                                                      
////////////////////////////////////////////////////////////   

/*
문제
상근이의 여동생 상냥이는 문방구에서 스티커 2n개를 구매했다. 스티커는 그림 (a)와 같이 2행 n열로 배치되어 있다. 상냥이는 스티커를 이용해 책상을 꾸미려고 한다.

상냥이가 구매한 스티커의 품질은 매우 좋지 않다. 스티커 한 장을 떼면, 그 스티커와 변을 공유하는 스티커는 모두 찢어져서 사용할 수 없게 된다. 즉, 뗀 스티커의 왼쪽, 오른쪽, 위, 아래에 있는 스티커는 사용할 수 없게 된다.



모든 스티커를 붙일 수 없게된 상냥이는 각 스티커에 점수를 매기고, 점수의 합이 최대가 되게 스티커를 떼어내려고 한다. 먼저, 그림 (b)와 같이 각 스티커에 점수를 매겼다. 상냥이가 뗄 수 있는 스티커의 점수의 최댓값을 구하는 프로그램을 작성하시오. 즉, 2n개의 스티커 중에서 점수의 합이 최대가 되면서 서로 변을 공유 하지 않는 스티커 집합을 구해야 한다.

위의 그림의 경우에 점수가 50, 50, 100, 60인 스티커를 고르면, 점수는 260이 되고 이 것이 최대 점수이다. 가장 높은 점수를 가지는 두 스티커 (100과 70)은 변을 공유하기 때문에, 동시에 뗄 수 없다.

입력
첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스의 첫째 줄에는 n (1 ≤ n ≤ 100,000)이 주어진다. 다음 두 줄에는 n개의 정수가 주어지며, 각 정수는 그 위치에 해당하는 스티커의 점수이다. 연속하는 두 정수 사이에는 빈 칸이 하나 있다. 점수는 0보다 크거나 같고, 100보다 작거나 같은 정수이다. 

출력
각 테스트 케이스 마다, 2n개의 스티커 중에서 두 변을 공유하지 않는 스티커 점수의 최댓값을 출력한다.
*/
#include <iostream>
using namespace std;
#define MAX_N 100001
int _stickerNum;
int _stickerScore[2][MAX_N] = {0,};
int _res[3][MAX_N] = {0,};
int _result =0;


int _MAX(int a, int b){
    if(a>b) return a;
    return b;
}

void GetRidSticker(){
    int k, s;
    _res[1][1] = _stickerScore[0][0];
    _res[2][1] = _stickerScore[1][0];
    for(k=0;k<_stickerNum;k++){
        _res[0][k+1] = _MAX(_res[1][k], _res[2][k]);
        _res[1][k+1] = _MAX(_res[0][k], _res[2][k]) + _stickerScore[0][k];
        _res[2][k+1] = _MAX(_res[0][k], _res[1][k]) + _stickerScore[1][k];
        /*
        cout << _MAX(_res[0][k], _res[2][k]) << endl;
        for(s=0;s<=_stickerNum;s++)
        cout<<_res[0][s] << " ";
        cout << endl;
        for(s=0;s<=_stickerNum;s++)
            cout<<_res[1][s] << " ";
        cout << endl;
        for(s=0;s<=_stickerNum;s++)
            cout<<_res[2][s] << " ";
        cout << endl;
       cout << "####### "<<_stickerScore[0][k]<<" # "<<_stickerScore[1][k]<<" #######"<< endl;*/
    }
    /*
    for(s=0;s<=_stickerNum;s++)
        cout<<_res[0][s] << " ";
    cout << endl;
    for(s=0;s<=_stickerNum;s++)
        cout<<_res[1][s] << " ";
    cout << endl;
    for(s=0;s<=_stickerNum;s++)
        cout<<_res[2][s] << " ";
    cout << endl;
    cout << "#########################" << endl;*/
    _result = _MAX(_res[1][_stickerNum], _res[2][_stickerNum]);
}

void ResetAll(){
    for(int k=0;k<_stickerNum;k++){
        for(int s=0;s<2;s++){
            _stickerScore[s][k] = 0;
            _res[s][k+1]=0;
        }
        _res[2][k+1]=0;
    }
}

int main(void){
    int testCase;
    int k, s;
    ios::sync_with_stdio(false);
    cin.tie(NULL);


    //input sequence
    cin>>testCase;
    for(k=0;k<testCase;k++){
        _result = 0;
        cin>>_stickerNum;
        for(s=0;s<_stickerNum;s++)
            cin>>_stickerScore[0][s];
        for(s=0;s<_stickerNum;s++)
            cin>>_stickerScore[1][s];
        
        /*
        for(s=0;s<_stickerNum;s++)
            cout<<_stickerScore[0][s] << " ";
        cout << endl;
        for(s=0;s<_stickerNum;s++)
            cout<<_stickerScore[1][s] << " ";
        cout << endl;
        cout << "#########################" << endl;*/
        GetRidSticker();
        cout << _result << endl;
        ResetAll();
    }
    
}












/*
failed code 
#include <iostream>
using namespace std;

int _stickerNum;
int _stickerScore[2][100000] = {0,};
int _res[2][100000] = {0,};
int _result =0;

void AddScore(int k, int locRem, int locDiv){
    int locRemTemp;
    int locDivTemp;
    int bfAdd;
    int score;
    locRemTemp = k % 2;
    locDivTemp = k / 2;
    bfAdd = _res[locRem][locDiv] + _stickerScore[locRem][locDiv]; 
    score =  _res[locRemTemp][locDivTemp] ;
    if(bfAdd > score)
        _res[locRemTemp][locDivTemp] = bfAdd;
    
}

void SetScoreResult(int locRem, int locDiv){
    int k, s;
    int locRemTemp;
    int locDivTemp;
    int bfAdd;
    int score;
    if(locRem == 0){
        for(k=locDiv*2 + locRem + 3;k<_stickerNum * 2; k++){
            AddScore(k, locRem, locDiv);
        }
    }else{
        k = locDiv*2 + locRem + 1;
        AddScore(k, locRem, locDiv);
        for(k=locDiv*2 + locRem + 3;k<_stickerNum * 2; k++){
            AddScore(k, locRem, locDiv);
        }
    }
}

void GetRidSticker(){
    int k, s;
    int locRem;
    int locDiv;
    int totalNum = _stickerNum * 2;
    for(k=0;k<totalNum;k++){
        locRem = k%2;
        locDiv = k/2;
        SetScoreResult(locRem, locDiv);
        if(_result < _res[locRem][locDiv] + _stickerScore[locRem][locDiv])
        _result = _res[locRem][locDiv] + _stickerScore[locRem][locDiv];
        
        for(s=0;s<_stickerNum;s++)
            cout<<_res[0][s] << " ";
        cout << endl;
        for(s=0;s<_stickerNum;s++)
            cout<<_res[1][s] << " ";
        cout << endl;
        cout << "########"<<_stickerScore[locRem][locDiv]<<"########" << endl;
    }
}

void ResetAll(){
    for(int k=0;k<2;k++){
        for(int s=0;s<_stickerNum;s++){
            _stickerScore[k][s] = 0;
            _res[k][s]=0;
        }
    }
}

int main(void){
    int testCase;
    int k, s;
    ios::sync_with_stdio(false);
    cin.tie(NULL);


    //input sequence
    cin>>testCase;
    for(k=0;k<testCase;k++){
        _result = 0;
        cin>>_stickerNum;
        for(s=0;s<_stickerNum;s++)
            cin>>_stickerScore[0][s];
        for(s=0;s<_stickerNum;s++)
            cin>>_stickerScore[1][s];
        
        
        for(s=0;s<_stickerNum;s++)
            cout<<_stickerScore[0][s] << " ";
        cout << endl;
        for(s=0;s<_stickerNum;s++)
            cout<<_stickerScore[1][s] << " ";
        cout << endl;
        cout << "#########################" << endl;
        GetRidSticker();
        cout << _result << endl;
        ResetAll();
    }
    
}
*/