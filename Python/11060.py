'''
문제
재환이가 1×N 크기의 미로에 갇혀있다. 미로는 1×1 크기의 칸으로 이루어져 있고, 각 칸에는 정수가 하나 쓰여 있다. 

i번째 칸에 쓰여 있는 수를 Ai라고 했을 때, 재환이는 Ai이하만큼 오른쪽으로 떨어진 칸으로 한 번에 점프할 수 있다.

예를 들어, 3번째 칸에 쓰여 있는 수가 3이면, 재환이는 4, 5, 6번 칸 중 하나로 점프할 수 있다.

재환이는 지금 미로의 가장 왼쪽 끝에 있고, 가장 오른쪽 끝으로 가려고 한다. 

이때, 최소 몇 번 점프를 해야 갈 수 있는지 구하는 프로그램을 작성하시오.

만약, 가장 오른쪽 끝으로 갈 수 없는 경우에는 -1을 출력한다.

입력
첫째 줄에 N(1 ≤ N ≤ 1,000)이 주어진다. 둘째 줄에는 Ai (0 ≤ Ai ≤ 100)가 주어진다.

출력
재환이가 최소 몇 번 점프를 해야 가장 오른쪽 끝 칸으로 갈 수 있는지 출력한다. 

만약, 가장 오른쪽 끝으로 갈 수 없는 경우에는 -1을 출력한다.

'''

import sys;read=sys.stdin.readline

n = int(read())
maze = list(map(int, read().split()))
chk = [0 for i in range(n)]
ans = 0
for i in range(n) :
    currcnt = maze[i]
    if i != 0 and chk[i] == 0 : 
        ans = -1
    for s in range(i+1, i+currcnt+1):
        if s < n:
            if chk[s] == 0:
                chk[s] = chk[i] + 1
            elif chk[s] > chk[i] + 1:
                chk[s] = chk[i] + 1
##print(maze)
##print(chk)
if ans != -1:
    print(chk[n-1])
else :
    print(ans)

    