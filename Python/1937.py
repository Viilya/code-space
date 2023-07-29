'''
문제
n × n의 크기의 대나무 숲이 있다. 욕심쟁이 판다는 어떤 지역에서 대나무를 먹기 시작한다. 그리고 그 곳의 대나무를 다 먹어 치우면 상, 하, 좌, 우 중 한 곳으로 이동을 한다. 그리고 또 그곳에서 대나무를 먹는다. 그런데 단 조건이 있다. 이 판다는 매우 욕심이 많아서 대나무를 먹고 자리를 옮기면 그 옮긴 지역에 그 전 지역보다 대나무가 많이 있어야 한다.

이 판다의 사육사는 이런 판다를 대나무 숲에 풀어 놓아야 하는데, 어떤 지점에 처음에 풀어 놓아야 하고, 어떤 곳으로 이동을 시켜야 판다가 최대한 많은 칸을 방문할 수 있는지 고민에 빠져 있다. 우리의 임무는 이 사육사를 도와주는 것이다. n × n 크기의 대나무 숲이 주어져 있을 때, 이 판다가 최대한 많은 칸을 이동하려면 어떤 경로를 통하여 움직여야 하는지 구하여라.

입력
첫째 줄에 대나무 숲의 크기 n(1 ≤ n ≤ 500)이 주어진다. 그리고 둘째 줄부터 n+1번째 줄까지 대나무 숲의 정보가 주어진다. 대나무 숲의 정보는 공백을 사이로 두고 각 지역의 대나무의 양이 정수 값으로 주어진다. 대나무의 양은 1,000,000보다 작거나 같은 자연수이다.

출력
첫째 줄에는 판다가 이동할 수 있는 칸의 수의 최댓값을 출력한다.

예제 입력 1 
4
14 9 12 10
1 11 5 4
7 15 2 13
6 3 16 8
예제 출력 1 
4
'''

import sys; read = sys.stdin.readline
from pprint import pprint
#from collections import deque
sys.setrecursionlimit(10**6)

n = int(read())
_map = []
for i in range(n):
    _map.append(list(map(int, read().split())))

dp = [[1 for _ in range(n)] for _ in range(n)]

def val_chk(a, b):
    return a>=0 and a<n and b>=0 and b<n

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]
'''
def dfs(i, s, count):
    curr = _map[i][s]
    for k in range(4):
        if(val_chk(i+dx[k], s+dy[k])):
            if(_map[i+dx[k]][s+dy[k]] > curr and count+1 > dp[i+dx[k]][s+dy[k]]):
                dp[i+dx[k]][s+dy[k]] = count + 1
                dfs(i+dx[k], s+dy[k], count+1)
'''

def dfs(i, s, count):
    curr = _map[i][s]
    get_day = [0, 0, 0, 0]
    for k in range(4): 
        if(val_chk(i+dx[k], s+dy[k])):
            if(_map[i+dx[k]][s+dy[k]] < curr):
                if(dp[i+dx[k]][s+dy[k]] > dp[i][s]):
                    get_day[k] = dp[i+dx[k]][s+dy[k]] + 1
                else:
                    get_day[k] = dfs(i+dx[k], s+dy[k], count+1)
    dp[i][s] = max(max(get_day), dp[i][s])
    if(count == 0):
        return dp[i][s]
    return dp[i][s] + 1

#pprint(_map)


for i in range(n):
    for s in range(n):
        if(dp[i][s] == 1):
            dp[i][s] = dfs(i, s, 0)
            #print(i, s)
            #print(*dp, sep="\n")
            #print()

result= max(map(max, dp))
print(result)

