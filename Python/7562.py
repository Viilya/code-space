"""
문제
체스판 위에 한 나이트가 놓여져 있다. 나이트가 한 번에 이동할 수 있는 칸은 아래 그림에 나와있다. 
나이트가 이동하려고 하는 칸이 주어진다. 나이트는 몇 번 움직이면 이 칸으로 이동할 수 있을까?

입력
입력의 첫째 줄에는 테스트 케이스의 개수가 주어진다.

각 테스트 케이스는 세 줄로 이루어져 있다. 첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다. 
체스판의 크기는 l × l이다. 체스판의 각 칸은 두 수의 쌍 {0, ..., l-1} × {0, ..., l-1}로 나타낼 수 있다.
둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.

출력
각 테스트 케이스마다 나이트가 최소 몇 번만에 이동할 수 있는지 출력한다.
"""

from collections import deque

dq = deque()
testCase = int(input())
_map = [[0 for i in range(300)] for s in range(300)]

def AddDq(X, Y, currCount, length, dq):
    if X>=0 and X<length and Y>=0 and Y<length and _map[X][Y] != 1 :
        dq.append([X, Y, currCount])
        _map[X][Y] = 1
currCount = 0
for i in range(testCase):
    length = int(input())
    startX, startY = list(map(int, input().split()))
    destX, destY = list(map(int, input().split()))
    for k in range(300):
        for s in range(300):
            _map[k][s] = 0
    dq.clear()
    count = 0
    dq.append([startX, startY, 0])
    currCount = 0
    while True :
        currX, currY, currCount = dq.popleft()
        ##print(currX, currY, currCount)
        if currX == destX and currY == destY :
            break
        else:
            AddDq(currX-1, currY-2, currCount+1, length, dq)
            AddDq(currX-1, currY+2, currCount+1, length, dq)
            AddDq(currX-2, currY-1, currCount+1, length, dq)
            AddDq(currX-2, currY+1, currCount+1, length, dq)
            AddDq(currX+1, currY-2, currCount+1, length, dq)
            AddDq(currX+1, currY+2, currCount+1, length, dq)
            AddDq(currX+2, currY-1, currCount+1, length, dq)
            AddDq(currX+2, currY+1, currCount+1, length, dq) 
    print(currCount)

        

