#include <iostream>
using namespace std;

int main() {
	int N, F;
	int answer = 0;
	cin >> N >> F;
	
	N /= 100;
	N *= 100;
	
	while((N+answer) % F != 0 && answer < 100) answer ++;
	
	if(answer < 10) printf("0%d", answer);
	else printf("%d", answer);
}
