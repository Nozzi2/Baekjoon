import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    /*
    주어진 배열에서 가장 큰 정사각형을 찾는 문제임.
    어떻게 접근해야하나 고민하기 전에 문제에서 주어진 시간 복잡도를 먼저 살펴보자.

    2차원 배열의 최대값은 1000x1000 = 10_000_000
    시간 제한은 1초이니까
    O(n^2)로 풀면 시간초과가 날 것임.

    처음부터 끝까지 한번만 순회해야만 문제를 풀 수 있음.

    흠.. BFS로 풀어야되나?
    각 칸별로 탐색하면서 1이면 >, v 방향으로 탐색하고,
    만약 탐색 중 막힌 방향이 있으면 해당 방향은..
    이렇게 해도 칸별로 탐색해야할 범위가 넓기 때문에 시간초과가 날것.

    도저히 모르겠어서 검색했고, DP로 풀어야한다.
    https://breakcoding.tistory.com/366
    위 블로그 참조하였음

    핵심은 해당 칸의 정사각형의 크기는
    윗칸, 왼쪽칸, 대각선 윗칸의 최소값 +1이다.
    점화식으로 보자면
    dp[i][j] = MIN(dp[i-1][j-1],dp[i][j-1],dp[i-1][j]) +input[i][j]
    블로그 한칸씩 저렇게 계산해나가는데,
    단한번의 탐색으로 최대값을 찾을 수 있음.
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sizeR = Integer.parseInt(st.nextToken());
        int sizeC = Integer.parseInt(st.nextToken());
        int[][] gridInput = new int[sizeR + 1][sizeC + 1];
        int[][] gridDp = new int[sizeR + 1][sizeC + 1];

        //입력받기
        for (int i = 1; i <= sizeR; i++) {
            String input = br.readLine();
            for (int j = 1; j <= sizeC; j++) {
                gridInput[i][j] = input.charAt(j - 1) - '0';
            }
//            System.out.println(Arrays.toString(gridInput[i]));
        }

        //모든 칸 순회하면서 정사각형 사이즈 최대값 찾기
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= sizeR; i++) {
            for (int j = 1; j <= sizeC; j++) {
                int min = Math.min(gridDp[i-1][j-1], gridDp[i-1][j]);
                min = Math.min(min, gridDp[i][j-1]);
                int dp = gridInput[i][j] != 0 ? min + gridInput[i][j] : 0;
                gridDp[i][j] = dp;
                max = Math.max(max,dp);
            }
        }

        System.out.println(max*max);
    }
}