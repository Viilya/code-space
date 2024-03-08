#include <iostream>
#include <vector>
#define FIO cin.tie(0), cout.tie(0), ios::sync_with_stdio(false)

using namespace std;

vector<int> MAP(15, 0);
int N;
int RESULT;

void input()
{
    cin >> N;
}

bool isValid(int a)
{
    for (int k = 0; k < a; k++)
    {
        if (MAP[k] == MAP[a] || abs(MAP[a] - MAP[k]) == a - k)
        {
            return false;
        }
    }
    return true;
}

void nQueen(int a)
{
    if (a == N)
    {
        RESULT++;
    }
    else
    {
        for (int k = 0; k < N; k++)
        {
            MAP[a] = k;
            if (isValid(a))
            {
                nQueen(a + 1);
            }
        }
    }
}

void solve()
{
    nQueen(0);
}

void output()
{
    cout << RESULT << "\n";
}

int main(void)
{
    FIO;
    input();
    solve();
    output();
}
