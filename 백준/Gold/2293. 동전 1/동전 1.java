import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i += arr[0]) {
            dp[i] = 1;
        }

        for (int i = 1; i < size; i++) {
//            System.out.println(Arrays.toString(dp));
            int cur = arr[i];
            for (int j = 0; j <= target; j++) {
                if (j - cur >= 0) {
                    dp[j] = dp[j] + dp[j - cur];
                }
            }
        }

        System.out.println(dp[target]);
    }
}

/*
3 10
1
2
5
 */