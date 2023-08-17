import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
똑같은 위상정렬 문제이다.
그런데 여기서 건설시간 총합을 구하기 위해서는
진입차수 0이된 것이 que에 들어갈 때
건설시간이 가장 큰 것만 총 건설시간에 반영이 되어있으면 됨.
 */
public class Main {

    static class Building {
        int num;
        int count;
        int cost;
        boolean isRoot; //경로에 포함되는지 여부 저장
        Set<Integer> nextBuilding;

        public Building(int num, int cost) {
            this.num = num;
            this.count = 0;
            this.cost = cost;
            this.isRoot = false;
            nextBuilding = new HashSet<>();
        }

        @Override
        public String toString() {
            return "Building{" +
                    "num=" + num +
                    ", count=" + count +
                    ", cost=" + cost +
                    ", isRoot=" + isRoot +
                    ", nextBuilding=" + nextBuilding +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int reqCount = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            Building[] buildings = new Building[size + 1];
            for (int i = 1; i <= size; i++) {
                int cost = Integer.parseInt(st.nextToken());
                buildings[i] = new Building(i, cost);
            }

            for (int r = 0; r < reqCount; r++) {
                st = new StringTokenizer(br.readLine());
                int cur = Integer.parseInt(st.nextToken());
                int next = Integer.parseInt(st.nextToken());

                if (!buildings[cur].nextBuilding.contains(next)) {
                    buildings[cur].nextBuilding.add(next);
                    buildings[next].count++;
                }
            }

            int targetBuilding = Integer.parseInt(br.readLine());
            Queue<Integer> que = new ArrayDeque<>();
            int[] dp = new int[size + 1];
            for (int i = 1; i <= size; i++) {
                dp[i] = buildings[i].cost;
                if (buildings[i].count == 0) {
                    que.offer(i);
                }
            }


            while (!que.isEmpty()) {
                int cur = que.poll();
                for (int n : buildings[cur].nextBuilding) {
                    dp[n] = Math.max(dp[n], dp[cur] + buildings[n].cost);
                    if (--buildings[n].count == 0) {
                        que.offer(n);
                    }
                }
            }

            sb.append(dp[targetBuilding]).append("\n");


        }
        System.out.println(sb.toString());
    }
}

/*
1
4 4
10 1 100 10
1 2
1 3
2 4
3 4
4

1
4 3
5 5 5 5
1 2
1 3
2 3
4

1
8 8
10 20 1 5 8 7 1 43
1 2
1 3
2 4
2 5
3 6
5 7
6 7
7 8
1

1
6 6
10 5 1 1 9 8
1 2
1 4
2 3
4 5
3 6
5 6
6

// 출력: 32
// 정답: 28
 */