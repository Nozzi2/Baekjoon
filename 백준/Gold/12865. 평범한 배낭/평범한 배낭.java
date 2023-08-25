import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
배낭(냅색)의 가장 대표적인 유형이다.
물건을 하나씩 사용했다고 가정하면서 얻을 수 있는 가장 큰 가치를 갱신하는 방식으로 구현해보면 될듯
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int cap = Integer.parseInt(st.nextToken());

        int[] weights = new int[size];
        int[] values = new int[size];
        int[][] dp = new int[size][cap + 1];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        for (int w = weights[0]; w <= cap; w++) {
            dp[0][w] = values[0];
        }

        for (int i = 1; i < size; i++) {
            for (int w = 0; w <= cap; w++) {
                dp[i][w] = dp[i - 1][w];
                if (w - weights[i] >= 0) {
                    dp[i][w] = Math.max(dp[i][w], dp[i-1][w - weights[i]] + values[i]);
                }
            }
        }
//        System.out.println("weights : "+Arrays.toString(weights));
//        System.out.println("values : "+Arrays.toString(values));
//        for (int[] arr : dp) {
//            System.out.println(Arrays.toString(arr));
//        }
        System.out.println(dp[size-1][cap]);
    }
}
/*
4 7
6 13
4 8
3 6
5 12

4 7
4 8
3 9
2 12
1 15

5 12
4 8
3 9
9 20
2 12
1 15
 */