import java.util.*;
import java.io.*;


public class Main
{
    static boolean[][] gragh;
    static boolean[] visited;
    static int nodeSize;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        nodeSize = Integer.parseInt(st.nextToken());
        int edgeSize = Integer.parseInt(st.nextToken());
        gragh = new boolean[nodeSize+1][nodeSize+1];
        visited = new boolean[nodeSize+1];

        for(int i=0; i<edgeSize; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            gragh[s][e] = true;
            gragh[e][s] = true;
        }

        int count=0;
        for(int i=1; i<=nodeSize; i++){
            if(!visited[i]){
                count++;
                bfs(i);
            }
        }

        System.out.println(count);
    }

    static void bfs(int idx){
        Queue<Integer> que = new LinkedList<>();
        que.offer(idx);
        visited[idx]=true;

        while(!que.isEmpty()){
            int cur = que.poll();

            for(int i=1; i<=nodeSize; i++){
                if(visited[i]) continue;
                if(!gragh[cur][i]) continue;

                que.offer(i);
                visited[i] = true;
            }
        }

    }


}