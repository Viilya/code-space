'''
문제
정수로 이루어진 크기가 같은 배열 A, B, C, D가 있다.

A[a], B[b], C[c], D[d]의 합이 0인 (a, b, c, d) 쌍의 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 배열의 크기 n (1 ≤ n ≤ 4000)이 주어진다. 다음 n개 줄에는 A, B, C, D에 포함되는 정수가 공백으로 구분되어져서 주어진다. 배열에 들어있는 정수의 절댓값은 최대 228이다.

출력
합이 0이 되는 쌍의 개수를 출력한다.

예제 입력 1 
6
-45 22 42 -16
-41 -27 56 30
-36 53 -37 77
-36 30 -75 -46
26 -38 -10 62
-32 -54 -6 45

예제 출력 1 
5

# function , quicksort 
def quicksort(arr, start, end):
    ##print("****QS", arr[start:end+1], start, end)
    pivot = arr[(start + end) // 2]
    if(start >= end):
        return
    left = start
    right = end
    for i in range(start, end+1):
        for _ in range(end - start + 1):
            if(arr[left] < pivot):
                left += 1
            else: 
                break
        for _ in range(end - start + 1):
            if(arr[right] > pivot):
                right -= 1
            else:
                break
        
        if(left <= right):
            tmp = arr[left]            
            arr[left] = arr[right]
            arr[right] = tmp
            left += 1
            right -= 1
    
    ##print("*result", arr[start:end+1], left, right);
    ##print(" ")
    quicksort(arr, start, left-1)
    quicksort(arr, left, end)

# transpose the given array
_arrT = []
for i in zip(*_arr):
    tmparr = list(i)
    _arrT.append(tmparr)
# print(_arrT)
'''

import sys; read=sys.stdin.readline
from pprint import pprint
sys.setrecursionlimit(10**6)

n = 9

n = int(read())

_arr = []
al = []
bl = []
cl = []
dl = []
for i in range(n):
    a, b, c, d = (list(map(int,read().split())))
    al.append(a)
    bl.append(b)
    cl.append(c)
    dl.append(d)
# make A+B array and C+D array
_arrAB = []
_arrCD = []
for i in range(n):
    for s in range(n):
        _arrAB.append(al[i] + bl[s])
        _arrCD.append(cl[i] + dl[s])
_arrAB.sort()
_arrCD.sort()


ab = 0
cd = n * n - 1
res = 0
for i in range(n*n*2):
    if(ab>n*n - 1 or cd<0):
        break
    _sum = _arrAB[ab] + _arrCD[cd]
    if(_sum < 0):
        ab += 1
    elif(_sum > 0):
        cd -= 1
    else:
        tmp = ab
        tmpab = 0
        tmpcd = 0
        while(_arrAB[ab] + _arrCD[cd] == 0):
            tmpab += 1
            ab += 1
            if(ab > n*n - 1):
                break;
        while(_arrAB[tmp] + _arrCD[cd] == 0):
            tmpcd += 1
            cd -= 1
            if(cd < 0):
                break;
        res += tmpab * tmpcd
print(res)