const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "../input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

/*
1~n 

트럭 한대, 1번 왼쪽

본부에서 출발해서 1번부터 마지막 마을까지 운송

각 마을은 배송할 물건들을 박스에 넣어 보내며 마을번호, 박스를 받는 마을 번호 앎

1. 박스를 트럭에 실으면 받는 마을에서만 내린다
2. 트럭은 지나온 마을로 돌아가지 않는다.
3. 박스 중 일부만 배송할 수도 있다. 

트럭 한대로 배송할 수 있는 최대 박스 수 (받는 마을 번호 > 보내는 마을 번호)

2 <= N <= 2000
1 <= C <= 10000
1 <= M <= 10000

*/

const [N, C] = input[0].split(" ").map(Number); // N : 거리 C : 최대 무게
const M = Number(input[1]); // M 박스 개수

type box = {
  dep: number;
  dest: number;
  q: number;
};
let boxes: box[] = [];
let result = 0;

for (let k = 2; k < 2 + M; k++) {
  let [dep, dest, q] = input[k].split(" ").map(Number);
  boxes.push({ dep, dest, q });
}

boxes.sort((a, b) => {
  if (a.dest === b.dest) {
    return a.dep - b.dep;
  }
  return a.dest - b.dest;
});

// console.log(boxes);

function solve() {
  let boxesSum: number[] = Array(N + 1).fill(C); // 거리에 따른 박스 숫자

  // 가방 순회
  for (let k = 0; k < M; k++) {
    let min = C;
    let currentDep = boxes[k].dep;
    let currentDest = boxes[k].dest;
    let currentQ = boxes[k].q;
    for (let s = currentDep; s < currentDest; s++) {
      min = Math.min(Math.min(boxesSum[s], currentQ), min);
    }
    for (let s = currentDep; s < currentDest; s++) {
      boxesSum[s] -= min;
    }
    result += min;
  }
}

function output() {
  console.log(result);
}
solve();
output();
