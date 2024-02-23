#include <iostream>
using namespace std;

int main()
{
    int a[5];

    for (int k = 0; k < 5; k++)
    {
        cin >> a[k];
    }

    int sum = 0;
    for (int k = 0; k < 5; k++)
    {
        sum += a[k] * a[k];
    }

    cout << sum % 10 << endl;
}