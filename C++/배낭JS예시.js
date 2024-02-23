/* 평범한 배낭 */
const fs = require('fs');
const filePath = process.platform === 'linux' ? '/dev/stdin' : '../input.txt';
// const filePath = process.platform === 'linux' ? '/dev/stdin' : 'BOJ/input.txt';
const input = fs.readFileSync(filePath).toString().trim().split('\n');

// N = 물품의 수, K = 버틸 수 있는 무게
const [N, K] = input.shift().split(' ').map(Number);
const PRODUCT_INFO_LIST = input.map((info) => {
  return info.split(' ').map(Number);
});

function solution(N, K, PRODUCT_INFO_LIST) {
  // 인덱스를 무게로 가지고, 값은 가치로 가지는 배열
  let dp = Array.from({ length: K + 1 }, () => 0);

  for(const INFO of PRODUCT_INFO_LIST) {
    const [WEIGHT, VALUE] = INFO;

    for(let i=K; i>=WEIGHT; i-=1) {
      // WEIGHT에 해당하는 기치의 물건을 넣지 않았을 때와
      // 물건을 넣었을 때의 가치를 비교해서 더 큰값으로 설정
      dp[i] = Math.max(dp[i], dp[i - WEIGHT] + VALUE);
    }
  }

  console.log(dp[K]);
}

solution(N, K, PRODUCT_INFO_LIST);

/*!SECTION
4 7
6 13
4 8
3 6
5 12

0 0 6 8 12 14 14






*/