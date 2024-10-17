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
const map = input.slice(1, 4).map((l: string) => l.split(" ").map(Number));

console.log(map);

function solve() {
  let count = 0;
  let result = false;
  while (count < 100) {
    let xLength = map.length;
    let yLength = 0;
    for (let k = 0; k < xLength; k++) {
      yLength = Math.max(yLength, map[k].length);
    }

    if (xLength >= yLength) {
      rCalculation(xLength);
    } else {
      cCalculation(yLength);
    }

    count++;
  }
}

function rCalculation(xLength: number) {}

function cCalculation(yLength: number) {}
