const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

/**
 * 공항 1~G 게이트
 * P 개의 비행기가 순서대로 도착 예정
 * i 번째 비행기를 1~g 번째 게이트 중 하나에 영구적으로 도킹
 * 가장 많은 비행기를 도킹 시켜서
 *
 * 1 <= G <= 10,000
 * 1 <= P <= 10,000
 * 1 <= g <= G
 *
 */

// 게이트의 수
const G = Number(input[0]);
// 비행기의 수
const P = Number(input[1]);
// console.log(G, P);
// 목표 게이트 배열
let unionParent = Array(G + 1).fill(0);
let result = 0;

// union
function union(a: number, b: number) {
  let aRoot = findRoot(a);
  let bRoot = findRoot(b);
  if (aRoot != bRoot) {
    unionParent[aRoot] = b;
    findRoot(b);
  }
}

// finding root
function findRoot(a: number) {
  if (a == unionParent[a]) return a;
  return (unionParent[a] = findRoot(unionParent[a]));
}

function solve() {
  for (let k = 2; k < P + 2; k++) {
    let currentTargetGate = Number(input[k]);
    // console.log("ctg : ", currentTargetGate);
    if (unionParent[currentTargetGate] === 0) {
      // g에 비행기가 없을 경우
      unionParent[currentTargetGate] = currentTargetGate; // g에 안착
      result++;
    } else {
      while (true) {
        let currentTargetGateRoot = findRoot(currentTargetGate); // g부터 아래로 비행기 안 찬곳 찾기

        // console.log("current target gate root : ", currentTargetGateRoot);
        // console.log(unionParent);
        if (unionParent[currentTargetGateRoot - 1] !== 0) {
          union(currentTargetGate, currentTargetGateRoot - 1);
          continue;
        }
        if (currentTargetGateRoot === 1) {
          // 1까지 다 차있을 때 패스
          return;
        }
        unionParent[currentTargetGateRoot - 1] = currentTargetGateRoot - 1;
        result++;
        break;
      }
    }
  }
}

function output() {
  console.log(result);
}

solve();
output();

// console.log(unionParent);
