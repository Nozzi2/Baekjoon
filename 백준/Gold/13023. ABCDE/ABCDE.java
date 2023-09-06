import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
간단한 DFS로 한번 접근해보면 될듯
depth가 최대 5니까 5를 넘지 않는 선에서 DFS탐색
 */
public class Main {
    static List<Integer>[] relations;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int relationSize = Integer.parseInt(st.nextToken());

        relations = new List[size];
        for (int i = 0; i < size; i++) {
            relations[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < relationSize; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relations[a].add(b);
            relations[b].add(a);
        }


        for (int i = 0; i < size; i++) {
            boolean[] visited = new boolean[size];
            dfs(i, visited, 1);
        }
        System.out.println(0);
    }

    static void dfs(int now, boolean[] visited, int count) {
        if (count == 5) {
            System.out.println(1);
            System.exit(0);
        }

        visited[now] = true;

        for (int next : relations[now]) {
            if(visited[next]) continue;
            visited[next] = true;
            dfs(next, visited, count + 1);
            visited[next] = false;
        }
    }

}
/*
5 4
0 1
1 2
2 3
3 4

 */