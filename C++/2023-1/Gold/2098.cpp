

#include <iostream>
#include <deque>
using namespace std;
#define fio         \
    cin.tie(NULL);  \
    cout.tie(NULL); \
    ios_base::sync_with_stdio(false);

class Point
{
public:
    int current;
    int count;
    int sum;
    int mask;
    Point(int curr, int cnt, int s, int m);
};

Point::Point(int curr, int cnt, int s, int m)
{
    current = curr;
    count = cnt;
    sum = s;
    mask = m;
}

int n;
int arr[16][16];
int resMin = INT_MAX;

void input()
{
    cin >> n;
    for (int k = 0; k < n; k++)
    {
        for (int s = 0; s < n; s++)
        {
            int t;
            cin >> t;
            arr[k][s] = t;
        }
    }
}

int min(int a, int b)
{
    if (a > b)
        return b;
    return a;
}

void bfs(int dp[][16])
{
    deque<Point> dq;
    for (int k = 0; k < n; k++)
    {
        if (arr[0][k] != 0)
        {
            Point point(k, 1, arr[0][k], (1 << k));
            dq.push_back(point);
            dp[k][0] = arr[0][k];
        }
    }

    while (!dq.empty())
    {
        Point p = dq.front();
        dq.pop_front();
        if (p.count == 15)
        {
            if (arr[15][0] == 0)
            {
                continue;
            }
            else
            {
                resMin = min(resMin, p.sum + arr[15][0]);
                continue;
            }
        }

        for (int k = 0; k < n; k++)
        {
            if (arr[p.current][k] && !((1 << k) & p.mask) && (p.sum + arr[p.current][k]) < dp[k][p.count + 1])
            {
                dp[k][p.count + 1] = p.sum + arr[p.current][k];
                Point newPoint(k, p.count + 1, p.sum + arr[p.current][k], p.mask | (1 << k));
                dq.push_back(newPoint);
            }
        }
    }
}

void solve()
{
    int dp[16][16];

    for (int k = 0; k < n; k++)
    {
        for (int s = 0; s < n; s++)
        {
            dp[k][s] = INT_MAX;
        }
    }

    bfs(dp);
}

int main(void)
{
    fio;
    input();
    solve();
    cout << resMin << " ";
}