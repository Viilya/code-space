const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

/**
 * 좌우 N 개
 * 서로 다른 두곳 벌 한 마리 씩 / 다른 한 장소 벌 통
 * 벌통으로 날아가면서 지나가는 모든 칸에서 꿀을 딴다. (꿀을 딸 수 있는 양 )
 * 지나간 장소에서 표시된 양 만큼의 꿀을 딴다 / 시작한 장소는 꿀을 딸 수 없음
 *
 * 벌들이 딸 수 있는 가능한 최대의 꿀의 양을 계산
 *
 * 3 <= N <= 100,000
 * 꿀은 1 ~ 10 000 이하의 정수
 */

const N = Number(input[0]);

const honeyList = input[1].split(" ").map(Number);
const honeySumList: number[] = Array(N).fill(0);
let result = 0;

function solve() {
  // 부분합 계산
  honeySumList[0] = honeyList[0];
  for (let k = 1; k < N; k++) {
    honeySumList[k] = honeySumList[k - 1] + honeyList[k];
  }

  case1();
  case2();
  case3();
}

// Bee Bee Hive
function case1() {
  let firstBeeSum = getSum(0, N - 1, 1);
  for (let k = 1; k <= N - 2; k++) {
    let secondBeeSum = getSum(k, N - 1, 1);

    result = Math.max(result, firstBeeSum + secondBeeSum - honeyList[k]);
  }
}

// Hive Bee Bee
function case2() {
  let firstBeeSum = getSum(N - 1, 0, 2);
  for (let k = 1; k <= N - 2; k++) {
    let secondBeeSum = getSum(k, 0, 2);
    result = Math.max(result, firstBeeSum + secondBeeSum - honeyList[k]);
  }
}

// Bee Hive Bee
function case3() {
  for (let k = 1; k <= N - 2; k++) {
    let firstBeeSum = getSum(0, k, 1);
    let secondBeeSum = getSum(N - 1, k, 2);
    result = Math.max(result, firstBeeSum + secondBeeSum);
  }
}

// bee to hive ( b is Hive )
function getSum(a: number, b: number, dir: number): number {
  // left -> right
  if (dir === 1) {
    return honeySumList[b] - honeySumList[a];
  }
  // right -> left
  else {
    return honeySumList[a] - honeySumList[b] + honeyList[b] - honeyList[a];
  }
}

function output() {
  console.log(result);
}

solve();
output();
