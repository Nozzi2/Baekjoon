import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        int[] dp = new int[size + 1];

        //홀수일 경우는 없기 때문에 바로 종료
        if (size % 2 != 0) {
            System.out.println(0);
            System.exit(0);
        }

        //직접 계산한 타일의 경우의 수
        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 3;

        // N은 홀수가 될 수 없고 짝수만 될 수 있기 때문에 2씩 증가를 한다.
        for (int i = 4; i <= size; i += 2) {
            dp[i] = dp[i - 2] * dp[2];
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] = dp[i] + (dp[j] * 2);
            }
        }

        System.out.println(dp[size]);
    }
}