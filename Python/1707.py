'''
문제
그래프의 정점의 집합을 둘로 분할하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때, 

그러한 그래프를 특별히 이분 그래프 (Bipartite Graph) 라 부른다.

그래프가 입력으로 주어졌을 때, 이 그래프가 이분 그래프인지 아닌지 판별하는 프로그램을 작성하시오.

입력
입력은 여러 개의 테스트 케이스로 구성되어 있는데, 첫째 줄에 테스트 케이스의 개수 K가 주어진다. 

각 테스트 케이스의 첫째 줄에는 그래프의 정점의 개수 V와 간선의 개수 E가 빈 칸을 사이에 두고 순서대로 주어진다. 
ㅑ
각 정점에는 1부터 V까지 차례로 번호가 붙어 있다.

이어서 둘째 줄부터 E개의 줄에 걸쳐 간선에 대한 정보가 주어지는데,
 
각 줄에 인접한 두 정점의 번호 u, v (u ≠ v)가 빈 칸을 사이에 두고 주어진다. 

출력
K개의 줄에 걸쳐 입력으로 주어진 그래프가 이분 그래프이면 YES, 아니면 NO를 순서대로 출력한다.

제한
2 ≤ K ≤ 5
1 ≤ V ≤ 20,000
1 ≤ E ≤ 200,000

예제 입력 1 
2
3 2
1 3
2 3
4 4
1 2
2 3
3 4
4 2
예제 출력 1 
YES
NO

'''

## 메모리 초과 관련
##
## 배열 v가 20000 이라서 int 형 배열로 만드면 절대로 불가능. 따라서 다른 방법 생각할 것

from collections import deque
import sys;read=sys.stdin.readline

def BipartiteCheck(pointCount, lineCount, Graph, colorMap1, colorMap2):
    dq = deque()
    startPoint = -1
    for i in range(pointCount):
        if startPoint != -1:
            break
        for s in range(pointCount):
            if s in Graph[i]:
                startPoint = i
                break
    if startPoint == -1:
        return 'YES'
    Color = 1
    nextColor = 2
    dq.append([startPoint, Color])
    caseCount = 0
    while True: 
        ##print(colorMap1[1:])
        ##print(colorMap2[1:])
        ##print(dq)
        currPoint, Color = dq.popleft()
        caseCount += 1
        ##print("Point : ", currPoint, Color)
        if Color == 1:
            nextColor = 2
        else : 
            nextColor = 1
        for i in range(1, pointCount+1):
            if i in Graph[currPoint]:
                if i in colorMap1[currPoint]:
                    ##print("### connected point ", i, " is in colormap 1 ###")
                    if Color == 1:
                        return 'NO'
                elif i in colorMap2[currPoint]:
                    ## print("### connected point ", i, " is in colormap 2 ###")
                    if Color == 2:
                        return 'NO'
                else:
                    if Color == 1:
                        colorMap2[currPoint].append(i)
                        dq.append([i, nextColor])
                    else:
                        colorMap1[currPoint].append(i)
                        dq.append([i, nextColor])
        if not dq:
            if caseCount < pointCount :
                for s in range(pointCount+1):
                    if s in Graph[i] :
                        if s not in colorMap1 and s not in colorMap2:
                            dq.append([s, Color])
            else:
                break
    return 'YES'


    

testCaseNumber = int(read())
for i in range(testCaseNumber):
    pointCount, lineCount = list(map(int, read().split()))
    Graph = [deque() for k in range(pointCount + 1)]
    colorMap1 = [deque() for k in range(pointCount + 1)]
    colorMap2 = [deque() for k in range(pointCount + 1)]
    for s in range(lineCount):
        pointA, pointB = list(map(int, read().split()))
        Graph[pointA].append(pointB)
        Graph[pointB].append(pointA)
    Ans = BipartiteCheck(pointCount, lineCount, Graph, colorMap1, colorMap2)
    print(Ans)
    ##print(Graph)
    ##print(colorMap1)
    ##print(colorMap2)
    Graph.clear()
    colorMap1.clear()
    colorMap2.clear()



