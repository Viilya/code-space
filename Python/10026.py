'''
문제
적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다. 따라서, 적록색약인 사람이 보는 그림은 아닌 사람이 보는 그림과는 좀 다를 수 있다.
크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다. 그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다. 

또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다. (색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다)

예를 들어, 그림이 아래와 같은 경우에


RRRBB
GGBBB
BBBRR
BBRRR
RRRRR

적록색약이 아닌 사람이 봤을 때 구역의 수는 총 4개이다. (빨강 2, 파랑 1, 초록 1) 하지만, 적록색약인 사람은 구역을 3개 볼 수 있다. (빨강-초록 2, 파랑 1)

그림이 입력으로 주어졌을 때, 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N이 주어진다. (1 ≤ N ≤ 100)

둘째 줄부터 N개 줄에는 그림이 주어진다.

출력
적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.

'''

import sys;read=sys.stdin.readline
from collections import deque
from pprint import pprint
n = int(input())

_map = []
for i in range(n):
    _map.append(read())

def MakeGroup(k, s, count, flag, n, _map, _count, changeflag):
    if k >= 0 and k < n and s >= 0 and s < n and _count[k][s] == 0:
        if _map[k][s] == flag:
            _count[k][s] = count
            changeflag[0] = True

def MakeGroupRB(k, s, count, flag, n, _map, _count, changeflag):
    if k >= 0 and k < n and s >= 0 and s < n and _count[k][s] == 0:
        if _map[k][s] == flag:
            _count[k][s] = count
            changeflag[0] = True
        elif _map[k][s] == 'R' and flag == 'G':
            _count[k][s] = count
            changeflag[0] = True
        elif _map[k][s] == 'G' and flag == 'R':
            _count[k][s] = count
            changeflag[0] = True

_count = [[0 for i in range(n)] for s in range(n)]
_countRB = [[0 for i in range(n)] for s in range(n)]
count = 1
flag = ''
changeflag = [True]
for k in range(n):
    for s in range(n):
        flag = ''
        if _count[k][s] == 0 :
            _count[k][s] = count
            flag = _map[k][s]
            changeflag[0] = True
            while changeflag[0] == True:
                changeflag[0] = False
                for i in range(n):
                    for t in range(n):
                        if _count[i][t] == count : 
                            MakeGroup(i-1, t, count, flag, n, _map, _count, changeflag)
                            MakeGroup(i+1, t, count, flag, n, _map, _count, changeflag)
                            MakeGroup(i, t-1, count, flag, n, _map, _count, changeflag)
                            MakeGroup(i, t+1, count, flag, n, _map, _count, changeflag)
            count = count + 1

countRB = 1
flag = ''
for k in range(n):
    for s in range(n):
        flag = ''
        if _countRB[k][s] == 0 :
            _countRB[k][s] = countRB
            flag = _map[k][s]
            changeflag[0] = True
            while changeflag[0] == True:   
                changeflag[0] = False
                for i in range(n):
                    for t in range(n):
                        if _countRB[i][t] == countRB :
                            MakeGroupRB(i-1, t, countRB, flag, n, _map, _countRB, changeflag) 
                            MakeGroupRB(i+1, t, countRB, flag, n, _map, _countRB, changeflag)
                            MakeGroupRB(i, t-1, countRB, flag, n, _map, _countRB, changeflag)
                            MakeGroupRB(i, t+1, countRB, flag, n, _map, _countRB, changeflag)
            countRB = countRB + 1


print(count - 1, countRB - 1)

                                        

    