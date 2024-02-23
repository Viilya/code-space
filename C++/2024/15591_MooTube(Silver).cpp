#include <iostream>
#include <deque>
#include <vector>
using namespace std;

#define FIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define INT_MAX 1 << 32 - 1
/*
1~N (1<=N<=5000)

USADO -> N-1 동영상 쌍을 골라 계산

서로 연결되어 있는 N-1 동영상 쌍으로 나타냈다.
N-1개의 동영상 쌍을 골라서 경로가 반드시 하나 존재하도록 함

두 쌍 사이의 USADO를 그 경로의 모든 연결들의 USADO 중 최솟값

주어진 영상에 대해서 값 K를 정하고, K 이상인 모든 동영상이 추천되도록 할 것

적절한 K를 결정

I

N Q
p q r (N-1 만큼 / p-r-q)
k v (K가 k라면 v를 보고 있는 소들에게 몇개의 동영상이 추천될 지 묻는 것)

*/

int N, Q;
int _visited[5001] = {
    0,
};

class point
{
public:
    int vertex;
    int min_val;

    point(int v, int m)
    {
        vertex = v;
        min_val = m;
    }

    point(int v)
    {
        vertex = v;
        min_val = INT_MAX;
    }
};

int min(int a, int b)
{
    return a > b ? b : a;
}
void initVisited()
{
    for (int k = 0; k < 5001; k++)
    {
        _visited[k] = 0;
    }
}
void printArr()
{

    cout << "\n";
    for (int k = 1; k < N + 1; k++)
    {
        for (int s = 1; s < N + 1; s++)
        {
            cout << _map[k][s];
        }
        cout << "\n";
    }
}
void printAdj()
{
    cout << "\n adj : \n";
    for (int k = 1; k < N + 1; k++)
    {
        for (int s = 1; s < N + 1; s++)
        {
            if (k == s)
            {
                cout << "  ";
            }
            else
            {
                cout << _adj[k][s] << " ";
            }
        }
        cout << "\n";
    }
}
void initArr()
{
    for (int k = 0; k < N + 1; k++)
    {
        for (int s = 0; s < N + 1; s++)
        {
            _map[k][s] = 0;
            _adj[k][s] = INT_MAX;
        }
    }
}

void input()
{
    cin >> N >> Q;
    initArr();

    int p, q, r;
    for (int k = 0; k < N - 1; k++)
    {
        cin >> p >> q >> r;
        _map[p][q] = r;
        _map[q][p] = r;
    }
}

void outputResult(int usado, int v)
{
    int result = 0;
    deque<point> dq;
    dq.push_back(point(v, 0));
    // cout << "\n";
    _visited[v] = 1;
    while (!dq.empty())
    {

        point curr = dq.front();
        dq.pop_front();
        // cout << "v : " << curr.vertex << " m : " << curr.min_val << "\n";
        for (int k = 1; k <= N; k++)
        {
            if (_visited[k] == 0 && _map[curr.vertex][k] >= usado)
            {
                result++;
                _visited[k] = 1;
                dq.push_back(point(k, _map[curr.vertex][k]));
            }
        }
    }

    cout << result << "\n";
}

void inputSecond()
{
    // printArr();
    int k, v;
    for (int s = 0; s < Q; s++)
    {
        cin >> k >> v;
        outputResult(k, v);
        initVisited();
    }
}

int main(void)
{
    FIO;
    input();
    inputSecond();
}

/*

void adjCalc(int first)
{
    deque<point> dq;
    dq.push_back(point(first));
    while (!dq.empty())
    {
        point curr = dq.front();
        _visited[curr.vertex] = 1;
        dq.pop_front();
        for (int k = 1; k < N + 1; k++)
        {
            if (_map[curr.vertex][k] != 0 && _visited[k] == 0)
            {
                _visited[k] = 1;
                int minU = min(_map[curr.vertex][k], curr.min_val);
                _adj[first][k] = minU;
                _adj[k][first] = minU;
                dq.push_back(point(k, minU));
                // cout << k << " " << minU << "\n";
            }
        }
    }
}

void graphCalc()
{
    for (int k = 1; k <= N; k++)
    {
        adjCalc(k);
        initVisited();
    }
    // printAdj();
}

*/