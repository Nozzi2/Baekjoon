import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
그래프를 그려가면서 확인해봤는데,
리프노드의 부모노드는 무조건 얼리어답터여만한다.
그래야 최소값을 유지할 수 있음.

그래서 리프노드의 부모노드를 얼리어답터라고 계산하고, 해당 노드를 지워준다.
노드를 지워주면 새로 리프노드가 생기게 되는데 그걸 또 얼리어답터라고 계산하는 작업을 반복한다.
이것을 그래프의 모든 영역을 반복하면 됨.

그래서 위 로직대로 했을 때 시간 초과 문제는 없는지?
정점의 갯수 N은 최대 1,000,000임
O(N^2)로 풀면 시간초과이므로
최대 O(NlogN)으로 풀어야함.
근데 위 로직을 리프노드부터 시작하는 걸로 한다면 O(N)으로 풀 수 있을 것임.

!! 반례 !!
1. 앞번호가 부모노드이고 뒷번호가 자식노드라는 보장이 없음
2. 1이나 다른 숫자가 루트노트가 아닐 수 있음.
    1 2, 3 4 이런 식으로 들어오면 1,3 둘 다 루트노드라고 저장됨..

결국 구글링해서 풀이를 이해해보았다.

이문제도 DP로 접근해야만 한다.
왜냐하면 루트노드 (아무노드나 상관 없음)가 얼리어답터일 경우(0)와 아닐경우(1)밖에 존재하지 않고,
각각의 경우별로 자식노드들의 값이 변경되게 된다.
핵심은
본인이 얼리어답터라면 자식들은 얼리어답터여도 되고, 얼리어답터가 아니여도 된다.
    그렇기 때문에 최솟값을 구해야하므로 자식들의 두 경우에서 작은 값을 더해주면 된다.
본인이 얼리어답터가 아니라면 자식들은 무조건 얼리어답터여야만 한다.
    그렇기 때문에 자식이 얼리어답터일 경우의 값을 더해주면 된다.
(https://hqjang.tistory.com/104 참고)



 */
public class Main {

    static List<Integer>[] gragh; //노드별로 연결된 노드의 리스트
    static boolean[] isVisited;
    static int[][] dp; //본인노드 포함하여 얼리어답터 인원수 저장

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nodeSize = Integer.parseInt(br.readLine());
        isVisited = new boolean[nodeSize + 1];
        dp = new int[nodeSize+1][2]; //[n][0] => 얼리어답터 / [n][1] => 일반인
        gragh = new List[nodeSize+1];
        for (int i = 0; i <= nodeSize; i++) {
            gragh[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i < nodeSize; i++) { //트리구조는 노드갯수-1이 간선 갯수임
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            gragh[a].add(b);
            gragh[b].add(a);
        }

        int start = 1;
        dfs(start);

//        for (int i = 1; i < nodeSize + 1; i++) {
//            System.out.printf("%d : [%d, %d]\n",i,dp[i][0], dp[i][1]);
//        }

        System.out.println(Math.min(dp[start][1], dp[start][0]));
    }

    private static void dfs(int parent) {
        isVisited[parent] = true;
        dp[parent][0] = 1;

        for (int child : gragh[parent]) {
            if(isVisited[child]) continue;
            dfs(child);

            //본인이 얼리어답터일 경우에는
                //자식이 얼리어답터이든지 아닌지 상관없으므로
                //두 경우에서 '최소값'을 더해주면 됨.
            dp[parent][0] += Math.min(dp[child][0], dp[child][1]);

            //본인이 일반인일 경우에는
                //자식이 무조건 얼리어답터여야만 함
            dp[parent][1] += dp[child][0];
        }
    }
}

/*
8
1 2
1 3
1 4
2 5
2 6
4 7
4 8

=> 3


6
1 2
2 3
3 4
4 5
5 6

=> 3

8
1 2
2 3
2 4
4 5
4 6
6 7
6 8

=> 3

11
1 2
2 3
2 4
2 5
3 6
6 7
4 8
4 9
5 10
1 11

=> 5
4라고 잘못나옴 > 수정함.

10
1 6
6 7
1 3
6 8
8 5
5 4
4 9
4 10
9 2

=> 5

16
1 2
1 3
1 4
1 5
1 6
2 7
3 8
4 9
5 10
8 11
9 12
9 13
9 14
9 15
9 16

=> 5

15
1 2
1 3
3 4
1 5
5 6
6 7
7 8
8 9
9 10
10 11
11 12
12 13
13 14
14 15

=> 7

5
1 2
2 3
3 4
4 5

4
1 2
3 4
2 4

=> 2
1이라고 잘못나옴.
> 앞번호가 부모이고, 뒷번호가 자식이라는 보장이 없음.
> 어떤 번호가 루트인지 알 수가 없고,
  앞, 뒷번호가 항상 부모자식 관계라는 것도 보장이 없음.
  그래서 해당 코드는 쓸 수가 없음.......
 */