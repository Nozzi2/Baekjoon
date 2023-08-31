/*
n자릿수 값이 주어졌을 때 모든 숫자가 오른쪽으로 입력되면서 같거나 커지기만 하는 값의 갯수를 구하는 문제이다.
특이하게 0으로 시작해도,오르막수라고 본다.

DP로 떠올릴 수 있는 가장 쉬운 문제인것같다.
0~9일때 바텀업으로 해당 자릿수일 때 나올 수 있는 오르막 수의 갯수를 구하고
다음 자릿수에서 이전에 메모이제이션 했던 값을 다 더해주면 됨!

 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static final int MOD = 10007;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());
        int[][] dp = new int[target + 1][11];

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
            sum++;
        }
        dp[1][10] = sum;

        for (int i = 2; i <= target; i++) {
            sum = 0;
            dp[i][0] = dp[i - 1][10];
            sum += dp[i][0];
            for (int j = 1; j < 10; j++) {
                int next = dp[i][j - 1] - dp[i - 1][j - 1];
                if (next < 0) {
                    next += MOD;
                }
                dp[i][j] = next;
                sum += dp[i][j];
            }
            dp[i][10] = sum % MOD;
        }

        System.out.println(dp[target][10]);

    }
}
/*
1

2

3

 */