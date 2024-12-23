const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");

/**
 * 길이 Lm / im 좀비 체력 Zi / 0이하 좀비는 사망
 * 1m 이동 마다 기관총, 지뢰  -> 동시에 불가
 *
 * 기관총
 * 유효 거리 Ml 데미지 Mk
 * 지뢰
 * 1m 앞의 좀비 즉사 C개
 * 살아 남을 수 있음?
 *
 * 1<= L <= 3 000 000
 * 1 <= Ml <= 3 000 000
 * 1 <= Mk <= 100
 * 0 <= C <= 3 000 000
 *
 *
 */

// console.log(input);

// 전체 거리
const L = Number(input[0]);
// 기관총 사거리, 데미지
const [Ml, Mk] = input[1].split(" ").map(Number);
// 지뢰 개수
const C = Number(input[2]);
// 전장 현황
let battleGround = input.slice(3).map(Number);

// 결과 저장
let result = "YES";

// console.log(battleGround);

function solve() {
  let remainMine = C;
  let currentDamage = 0;
  // 1m 씩 움직이는 느낌
  let damage = [0];
  for (let dist = 1; dist <= L; dist++) {
    currentDamage = damage[dist - 1] - damage[Math.max(0, dist - Ml)];
    if (battleGround[dist - 1] <= currentDamage + Mk) {
      damage.push(damage[dist - 1] + Mk);
    } else {
      if (remainMine > 0) {
        remainMine--;
        damage[dist] = damage[dist - 1];
      } else {
        result = "NO";
        return;
      }
    }
  }
}

function output() {
  console.log(result);
}

solve();
output();

/**!SECTION


 */
