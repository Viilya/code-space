from collections import deque
import sys;read = sys.stdin.readline

## D : double
## S : n-1 (0 -> 9999)
## L : leftslide (2341)
## R : rightslide(4123)

'''	
def NumToStr(a):
	strNum = str(int(a/1000)) + str(int(a%1000/100)) + str(int(a%100/10)) + str(a%10)
	return strNum
'''
1
def doubleNum(number):
	##temp = number
	##temp = temp * 2 % 10000
	return number * 2 % 10000

def subNum(number):
	if number == 0:
		return 9999
	else:
		return number- 1

def shiftleft(number):
	##temp = number
	##temp = temp%1000*10 + temp//1000
	return number%1000*10 + number//1000

	
def shiftright(number):
	##temp = number
	##temp = temp%10*1000 + temp//10
	return number%10*1000 + number//10

	
def FindTransCourse(a, b):
	dq = deque()
	number, line = 0, 0
	dq.append([a, line])
	visited = [0 for i in range(10000)]
	while True:
		number, line = dq.popleft()
		if number==b:
			return line
		##print(line)
		tmp= doubleNum(number)
		if visited[tmp] != 1:
			dq.append([tmp, line * 10 + 1])
			visited[tmp] = 1
		tmp= subNum(number)
		if visited[tmp] != 1:
			dq.append([tmp, line * 10 + 2])
			visited[tmp] = 1
		tmp= shiftleft(number)
		if visited[tmp] != 1:
			dq.append([tmp, line * 10 + 3])	
			visited[tmp] = 1
		tmp= shiftright(number)
		if visited[tmp] != 1:
			dq.append([tmp, line * 10 + 4])
			visited[tmp] = 1

def printResult(result):
	strRes = str(result)
	for i in range(len(strRes)):
		if strRes[i] == '1':
			print("D", end="")
		elif strRes[i] == '2':
			print("S", end="")
		elif strRes[i] == '3':
			print("L", end="")
		elif strRes[i] == '4':
			print("R", end="")
	print("")

testCaseCount = int(read())

result = ''
for i in range(testCaseCount):
	a, b = list(map(int, read().split()))
	result = FindTransCourse(a, b)
	printResult(result)