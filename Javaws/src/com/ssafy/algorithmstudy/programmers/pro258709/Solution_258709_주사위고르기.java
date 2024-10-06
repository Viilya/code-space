import java.util.*;
import java.lang.Math;
class Solution {
    public static int[][] _dice;
    public static int[] _answer = {};
    public static int _maxWinCount = 0;
    public int[] solution(int[][] dice) {
        _dice = dice; // make global
        int diceCount = dice.length;

        solve(diceCount);
        return _answer;
    }

    public static void solve(int diceCount){
        int [] comb = {0, -1, -1, -1, -1};
        combination(diceCount, diceCount / 2, 1, 1, comb); // calc combination
    }

    public static void combination(int diceCount, int maxCount, int combCount, int lastIdx, int[] comb){
        if(maxCount == combCount) { // if combCount reaches max
            calcWin(diceCount, comb);
            return; // stop combination
        }
        // combination
        for(int k = lastIdx; k < diceCount ; k ++){
            comb[combCount] = k; //  insert kth dice to combination
            combination(diceCount, maxCount, combCount + 1, k + 1, comb); //recursive
            comb[combCount] = -1;
        }
        return;
    }

    public static void calcWin(int diceCount, int[] comb){
        int count = 0;
        int[] bComb = {-1, -1, -1, -1, -1};
        for(int k = 0 ; k < diceCount; k++){
            boolean flag = false;
            for(int s = 0 ; s < diceCount / 2 ; s++){
                if(comb[s] == k) {
                    flag = true;
                    break;
                }
            }
            if(!flag){
                bComb[count ++] = k;
            }
        }

        int sumCount = (int)Math.pow(6, diceCount);

        int[] aSum = new int[sumCount];
        int[] bSum = new int[sumCount];
        getSumOfCombination(comb, aSum, diceCount/2);
        getSumOfCombination(comb, bSum, diceCount/2);
        Arrays.sort(aSum);
        Arrays.sort(bSum);
        System.out.println(Arrays.toString(aSum));
        System.out.println(Arrays.toString(bSum));
        int win = 0;
        int lose = 0;

        for(int k = 0 ; k < sumCount ; k ++){
            int idx = 0;
            int bin = sumCount / 2;
            // binary Search for Win
            while(true){
                if(aSum[k] < bSum[idx]){
                    idx += bin;
                }else{
                    idx -= bin;
                }

                if(idx < 0) break;
                bin /= 2;
                if(bin == 0) break;
            }

            win += idx + 1;

            idx = sumCount - 1;
            bin = sumCount / 2;

            while(true){
                if(aSum[k] > bSum[idx]){
                    idx -= bin;
                }else{
                    idx += bin;
                }

                if(idx >= sumCount) break;
                bin /= 2;
                if(bin == 0) break;
            }

            lose += sumCount - idx - 1;
        }

        System.out.println(win + " " + lose);
    }

    public static void getSumOfCombination(int [] comb, int []sum, int count){
        getSum(comb, sum, count, 0, 0, 0, (int)Math.pow(6, count - 1));
    }

    public static void getSum(int [] comb, int[] sum, int maxCount, int count, int idx, int currSum, int idxMult){
        if(count == maxCount){
            sum[idx] = currSum;
            return;
        }
        for(int k = 0; k < 6; k ++){
            getSum(comb, sum, maxCount, count+1, idx + (k * idxMult), currSum + _dice[comb[count]][k], idxMult / 6);
        }

    }

}