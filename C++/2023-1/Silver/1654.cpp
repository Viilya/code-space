////////////////////////////////////////////////////////////
//  Date : 2023.01.31 coded
//  Problem : 1654
//  Language : C++
//  by Kwanwoo Kim                                                      
////////////////////////////////////////////////////////////    

#include<iostream>
#define MAX_K 10000
#define MAX_N 1000000
#define MAX_LEN 2147583647 // 2^31 - 1

using namespace std;

//                  GLOBAL VARIABLES

/*int _N;
int _K;
long int _arrK[MAX_K] = {0,};
long int _result = MAX_LEN;
long int _maxLen= 0;

int Cut(int middle){
    int count = 0;
    int k ;
    for(k=0;k<_K;k++)
        count += _arrK[k] / middle; 
    return count;
}

void FindMaxCut(){
    long long int start = 1;
    long long int end = _maxLen;
    long long int middle = (start + end + 1) / 2;
    int count = 0;
    int k;
    int _Count =0;
    //cout << "  CP # 1 \n";
    while(1){
        count = Cut(middle);
        
        cout << start << " " << middle << " " << end << "# #" << count << " \n";
        if(count >= _N)
            break;
        else {
            end = middle;
            middle = (start + end + 1) / 2;
        }
    }
    count = 0;
    _Count = 0;
    cout << "  CP # 2 \n";
    while(1){
        count = Cut(middle);
        
        cout << start << " " << middle << " " << end << "# #" << count << " \n";
        if(count < _N){
            cout << " c < n # \n";
            for(k=start;k<=middle;k++){
                count = Cut(k);
                cout << k << " " << count << " "<< " \n";
                if(count < _N){
                    _maxLen = k - 1;
                    break;
                }
            }
            break;
        }else if(end - 1 == middle){
            cout << " c = n # \n";
            count = Cut(end);
            if(count == _N)
                _maxLen = end;
            else
                _maxLen = middle;
            break;
        }else{
            start = middle;
            middle = (start + end + 1) / 2;
        }
    }
    cout << _maxLen;
    
    
}

int main(void){
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> _K >> _N;
    //cout << _K << " " << _N << endl;
    int k, s;

    for(k=0 ;k<_K ;k++){
        cin >> _arrK[k];
        if(_maxLen < _arrK[k])
            _maxLen = _arrK[k];
    }
    
    
    FindMaxCut();
}*/

/*
문제
집에서 시간을 보내던 오영식은 박성원의 부름을 받고 급히 달려왔다. 박성원이 캠프 때 쓸 N개의 랜선을 만들어야 하는데 너무 바빠서 영식이에게 도움을 청했다.

이미 오영식은 자체적으로 K개의 랜선을 가지고 있다. 그러나 K개의 랜선은 길이가 제각각이다. 박성원은 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶었기 때문에 K개의 랜선을 잘라서 만들어야 한다. 예를 들어 300cm 짜리 랜선에서 140cm 짜리 랜선을 두 개 잘라내면 20cm는 버려야 한다. (이미 자른 랜선은 붙일 수 없다.)

편의를 위해 랜선을 자르거나 만들 때 손실되는 길이는 없다고 가정하며, 기존의 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다고 가정하자. 그리고 자를 때는 항상 센티미터 단위로 정수길이만큼 자른다고 가정하자. N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다. 이때 만들 수 있는 최대 랜선의 길이를 구하는 프로그램을 작성하시오.

입력
첫째 줄에는 오영식이 이미 가지고 있는 랜선의 개수 K, 그리고 필요한 랜선의 개수 N이 입력된다. K는 1이상 10,000이하의 정수이고, N은 1이상 1,000,000이하의 정수이다. 그리고 항상 K ≦ N 이다. 그 후 K줄에 걸쳐 이미 가지고 있는 각 랜선의 길이가 센티미터 단위의 정수로 입력된다. 랜선의 길이는 231-1보다 작거나 같은 자연수이다.

출력
첫째 줄에 N개를 만들 수 있는 랜선의 최대 길이를 센티미터 단위의 정수로 출력한다.
*/

long long CuttingLan(long long int *lan, int k, int n, long long int start, long long int end){
    if(start>end){
        return end;
    }
    int count = 0;
    long long int mid = (start + end) / 2;
    for(int s =0;s<k;s++){
        count += lan[s] / mid;
    }
    if(count >= n)
        return CuttingLan(lan, k, n, mid + 1, end);
    else
        return CuttingLan(lan, k, n, start, mid -1);
    
}

int main(void){
    long long int start;
    long long int end;
    int n, k;
    long long int lan[MAX_K];
    cin >> k >> n;
    int s;
    for(s=0;s<k;s++){
        cin >> lan[s];
    }
    start = 1;
    end = MAX_LEN;
    printf("%lld", CuttingLan(lan, k, n, start, end));
}