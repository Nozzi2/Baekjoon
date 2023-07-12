/*
!! 적어도 C명을 늘려야 하기 때문에 C를 넘어도 최솟값이기만 하면 된다.

도시의 갯수는 최대 20개,
늘려야하는 인원수는 최대 1000명, (넘을 수도 있음)

1명당 필요한 비용을 계산해서 정렬하여 구현하려고 했는데, C명을 넘어도 된다는 조건을 만족시키지 못할 것 같았음
그래서 구글링해서 약간 힌트를 받음

일단 DP로 구현하는 문제이고,
점화식의 기준은 비용당 최대 인원수임.
** 어떻게 이 문제에서 dp배열 기준이 비용인걸 생각해낼 수 있을까? ㅠㅠ **

dp배열을 선언해야하는데, 최대 1000명, 도시에서 발생할 수 있는 비용 최대 100원이므로
100 * 1000이여서 100_000 만큼 배열 선언하면됨.

dp[1]은 1원으로 모을 수 있는 인원수.
그래서 도시가 총 20개 이므로 완전탐색으로 dp배열을 채워나가야함.
예를 들어, 1번도시가 3원에 5명 이라면
dp[3], dp[6], dp[9], ..., dp[99_999] 까지 5*n을 배열에 넣어주면 된다.
    -> 이렇게 하면 조합으로 채웠을 때 더 높은 인원이 될 수 있는 경우는 고려가 안됨..
    그럼 그냥 dp[3] = 5 이렇게만 저장하자
그리고 2번 도시부터 마지막 도시까지 dp배열을 채워준다.

dp배열을 다 채웠다면 1부터

다 채웠다면 dp배열을 1부터 순회하면서 문제에서 요구하는 C명보다 큰 값이 존재하는 인덱스가 있다면
해당 인덱스를 출력하면 된다.


 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int MAX_VALUE = 100_000;

    static class Town {
        int cost;
        int count;

        public Town(int cost, int count) {
            this.cost = cost;
            this.count = count;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        List<Town> towns = new ArrayList<>();
        int[] dp = new int[100_001];
//        Arrays.fill(dp, Integer.MIN_VALUE);
//        dp[0] = 0;

        for (int T = 0; T < N; T++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            towns.add(new Town(cost, count));
        }

        for (Town t : towns) {
//            int idx = 1;
//            while (t.cost * idx <= MAX_VALUE) {
//                int curIndex =t.cost * idx;
//                int curCount = t.count * idx;
//                dp[curIndex] = Math.max(dp[curIndex], curCount); //같은 비용대비 높은 인원수 저장
//                idx++;
//            }
            dp[t.cost] = Math.max(dp[t.cost], t.count);
        }

        //10_000_000_000


        for (int i = 1; i <= MAX_VALUE; i++) {
            for (Town t : towns) {
                if (t.cost > i) continue;
                dp[i] = Math.max(dp[i], dp[i - t.cost] + t.count);
            }

            if (dp[i] >= C) {
                System.out.println(i);
                break;
            }
        }

//        for (int i = 1; i <= 100; i++) {
//            System.out.printf("%3d : %d\n",i,dp[i]);
//        }



    }
}


/*
12 2    //'적어도' 12명의 고객을 늘려야한다. 도시의 갯수는 2개이다.
3 5     //A 도시는 3원으로 5명 늘릴 수 있다.
1 1     //B 도시는 1원으로 1명 늘릴 수 있다.

A 6원 = 10명
B 2원 = 2명
=> 8원


10 3
3 1
2 2
1 3

C 4원 = 12명


10 10
1 1
2 2
3 3
4 4
5 5
6 6
7 7
8 8
9 9
10 10

A 10원 = 10명
OR
B 10원 = 10명
...

10 10
1 1
2 2
3 3
4 4
5 8
6 6
7 7
8 8
9 9
10 10

 */