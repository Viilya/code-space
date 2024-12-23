const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

/**
 * 3x3 배열
 * R 연산 -> 모든 행에 대한 정렬 (행 >= 열)
 * C 연산 -> 모든 열에 대해서 정렬 수행  (행 < 열)
 *
 * 각각의 수가 몇번 나왔는지 알아야 함 -> 그 수의 등장 횟수가 커지는 순으로 / 수가 커지는 순으로 정렬
 * 배열 A에 정렬된 결과를 넣어야 한다. -> 수 / 등장 횟수를 모두 넣고 수가 먼저
 * 3 1 1 에서는 3-1 1-2 -> 3 1 1 2 가 된다.
 * 행, 열 크기가 다라질 수 있다.
 * R 연산이 적용된 경우는 가장 큰 행 기준으로 모든 행 크기가 변한다.
 * A에 들어 있는 수 와 r, c, k가 주어졌을 때 A[r][c]에 들어있는 값이
 * 길이 K가 되기 위한 최소 시간
 *
 *1<= r c k <= 100
 *
 */

const [r, c, k] = input[0].split(" ").map(Number);
let map = input.slice(1, 4).map((l: string) => l.split(" ").map(Number));
let count = 0;

type data = {
  num: number;
  count: number;
};

// 1~100 번 시도, 계속해서 k 확인
function solve() {
  while (count <= 100) {
    if (r - 1 < map.length && c - 1 < map[r - 1].length && map[r - 1][c - 1] === k) {
      break;
    }
    // `console.log`(map);
    let xLength = map.length;
    let yLength = 0;
    for (let k = 0; k < xLength; k++) {
      yLength = Math.max(yLength, map[k].length);
    }

    if (xLength >= yLength) {
      rCalculation(xLength);
    } else {
      cCalculation(xLength, yLength);
    }

    count++;
  }
}

// 숫자를 array에 추가
function addNumberToArray(tempMap: data[][], idx: number, n: number) {
  // console.log("tempMap : ", tempMap[idx]);
  for (let k = 0; k < tempMap[idx].length; k++) {
    if (tempMap[idx][k].num === n) {
      tempMap[idx][k].count++;
      return;
    }
  }

  tempMap[idx].push({ num: n, count: 1 });
}

function sortOneLine(mapLine: data[]) {
  mapLine.sort((a, b) => {
    if (a.count === b.count) return a.num - b.num;
    return a.count - b.count;
  });
}

// r 방향 계산
function rCalculation(xLength: number) {
  let tempMap = Array(xLength)
    .fill(null)
    .map(() => Array());
  for (let k = 0; k < xLength; k++) {
    for (let s = 0; s < map[k].length; s++) {
      if (map[k][s] === 0) continue;
      addNumberToArray(tempMap, k, map[k][s]);
    }
    sortOneLine(tempMap[k]);
  }

  map = Array(xLength)
    .fill(null)
    .map(() => Array());
  for (let k = 0; k < xLength; k++) {
    for (let s = 0; s < tempMap[k].length; s++) {
      map[k].push(tempMap[k][s].num);
      map[k].push(tempMap[k][s].count);
    }
  }
  // console.log(map);
}

// c 방향 계산
function cCalculation(xLength: number, yLength: number) {
  let tempMap = Array(yLength)
    .fill(null)
    .map(() => Array());
  for (let k = 0; k < xLength; k++) {
    for (let s = 0; s < map[k].length; s++) {
      if (map[k][s] === 0) continue;
      addNumberToArray(tempMap, s, map[k][s]);
    }
  }
  map = Array(yLength)
    .fill(null)
    .map(() => Array());
  for (let k = 0; k < yLength; k++) {
    sortOneLine(tempMap[k]);
    for (let s = 0; s < tempMap[k].length; s++) {
      map[k].push(tempMap[k][s].num);
      map[k].push(tempMap[k][s].count);
    }
  }
  // console.log(tempMap);
  // console.log("amp : ", map);
  transformMap();
  // console.log("map : ", map);
}

function transformMap() {
  let maxLength = Math.max(...map.map((subArr) => subArr.length));

  map = Array.from({ length: maxLength }, (_, i) => {
    return map.map((subArray) => (subArray[i] !== undefined ? subArray[i] : 0));
  });
}

function output() {
  console.log(count <= 100 ? count : -1);
}

solve();
output();
