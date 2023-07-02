import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
1번 컴퓨터에 연결된 노드를 찾아서,
결과를 출력하면 됨.

단, 사이클이 있을 때 무한으로 돌지 않도록 처리를 해줘야함.

컴퓨터 수는 최대 100개
연결되어있는 배열을 선언해서 BFS 탐색 진행하면 될듯

1. 2차원 배열에 양방향 연결헌다.
2. BFS탐색을 진행하는데,
    1번 컴퓨터에 연결된 컴퓨터를 que에 담고
    1번 컴퓨터는 방문체크 해주고
    바이러스 감염 갯수++
3. que에 담겨있는 컴퓨터에 연결된 컴퓨터를 검사해서
    방문한적이 없는지?
        que에 담고
        방문체크
        감염갯수 ++
4. que가 빌 때 까지
 */
public class Main {

    static boolean[][] matrix;
    static boolean[] isVisited;
    static int count;
    static int size;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        size = Integer.parseInt(br.readLine());
        int T = Integer.parseInt(br.readLine());
        count=0;
        matrix = new boolean[size][size];
        isVisited = new boolean[size];

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            matrix[s][e] = true;
            matrix[e][s] = true;
        }

        bfs(0);

        System.out.println(count);
    }

    private static void bfs(int start) {
        Queue<Integer> que = new ArrayDeque<>();
        que.offer(start);
        isVisited[start] = true;

        while (!que.isEmpty()) {
            int next = que.poll();
//            System.out.println("??");
            for (int i = 1; i < size; i++) {
                if (!matrix[next][i] || isVisited[i]) continue;
                count++;
                que.offer(i);
                isVisited[i] = true;
            }
        }
    }
}