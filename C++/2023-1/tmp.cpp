
#include <iostream>

#include <deque>

#include <math.h>
using namespace std;

#define MAX_INT (1 << 30)

#define MAX_VILLAGE 8
int T;
int N;
int map[MAX_VILLAGE][MAX_VILLAGE] = {
    0,
};
int popSum = 0;
int minDiff = MAX_INT;

int abs(int a) { return a < 0 ? -a : a; }

int find(int *parent, int a)
{
    if (parent[a] == a)
        return a;
    return parent[a] = find(parent, parent[a]);
}

void Union(int *parent, int a, int b)
{
    // cout << "#### CP Union ####";
    if (find(parent, a) < find(parent, b))
    {
        parent[b] = parent[a];
    }
    else
    {
        parent[a] = parent[b];
    }
}

bool bfs(int comb)
{
    bool isVisited[MAX_VILLAGE] = {
        false,
    };
    int parent[MAX_VILLAGE];
    deque<int> dq;
    int firstElement = 0;
    for (int k = 0; k < N; k++)
    {
        parent[k] = k;
    }
    for (int k = 0; k < N; k++)
    {
        // cout << (1 << k) << "n"; //cout << ((1 << k) & comb) << 'n';
        if (((1 << k) & comb) == (1 << k))
        {
            firstElement = k;
            dq.push_back(k);
            break;
        }
    } // cout << "firstElement " << firstElement << "n";
    while (!dq.empty())
    {
        int currCity;
        currCity = dq.front();
        dq.pop_front();
        for (int k = 0; k < N; k++)
        {
            if (map[currCity][k] == 1 && (((1 << k) & comb) == (1 << k)) && !isVisited[k])
            {
                isVisited[k] = true;
                dq.push_back(k);
                Union(parent, currCity, k);
            }
        }
    } /* cout << comb << "n";
    for (int k = 0; k < N; k++) {
        cout << parent[k] << " ";
    } cout << "n"; */
    for (int k = 0; k < N; k++)
    {
        if (((1 << k) & comb) == (1 << k))
        {
            if (parent[k] != firstElement)
            {
                return false;
            }
        }
    }
    return true;
}

void getUnion(int *pop, int comb)
{
    if (bfs(comb) && bfs((1 << N) - comb - 1))
    {
        int sum = 0;
        for (int k = 0; k < N; k++)
        {
            if (((1 << k) & comb) == (1 << k))
            {
                sum += pop[k];
            }
        }
        if (minDiff > abs(popSum - sum - sum))
        {
            minDiff = abs(popSum - sum - sum);
        }
    }
}

void getCombination(int *pop, int cnt, int comb)
{
    if (cnt == N)
    {
        getUnion(pop, comb);
    }
    else
    {
        getCombination(pop, cnt + 1, comb | (1 << cnt));
        getCombination(pop, cnt + 1, comb);
    }
}

void printRes()
{
    cout << minDiff << '/n';
}

void initMap()
{
    for (int k = 0; k < N; k++)
    {
        for (int s = 0; s < N; s++)
        {
            map[k][s] = 0;
        }
    }
}

void inputAndRun()
{
    cin >> N;
    int pop[MAX_VILLAGE] = {
        0,
    };
    popSum = 0;
    minDiff = MAX_INT;
    for (int k = 0; k < N; k++)
    {
        int tmp = 0;
        cin >> tmp;
        pop[k] = tmp;
        popSum += tmp;
    }
    for (int k = 0; k < N; k++)
    {
        int tmp = 0;
        cin >> tmp;
        for (int s = 0; s < tmp; s++)
        {
            int tmp1 = 0;
            cin >> tmp1;
            map[k][tmp1 - 1] = 1;
        }
    }

    /* for (int k = 0; k < N; k++) {
        for (int s = 0; s < N; s++) {
            cout << map[k][s];
        }
        cout << " " << pop[k];
        cout << "n"; }*/
    getCombination(pop, 0, 0);
    printRes();
}

int main(void)
{
    cin.tie(NULL);
    ios_base::sync_with_stdio(false);
    inputAndRun();
    return 0;
}