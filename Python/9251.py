'''
문제
LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.

예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.

입력
첫째 줄과 둘째 줄에 두 문자열이 주어진다. 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.

출력
첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를 출력한다.

예제 입력 1 
ACAYKP
CAPCAK
예제 출력 1 
4
'''
import sys; read = sys.stdin.readline
from pprint import pprint
tmp = read().strip()
a = []
for i in tmp:
    a.append(i)
tmp = read().strip()
b= []
for i in tmp:
    b.append(i)
strlen = max(len(a), len(b))
alen = len(a)
blen = len(b)
dp = [0 for _ in range(strlen + 1)]
tmp = [0 for _ in range(strlen + 1)]
#print(a)
#print(b)

for i in range(blen):
    res = 0
    for s in range(alen):
        if(res < dp[s]):
            res = dp[s]
        elif(b[i] == a[s]):
            dp[s] = res + 1
#pprint(dp)
#print(max(list(map(max,dp))))
print(max(dp))