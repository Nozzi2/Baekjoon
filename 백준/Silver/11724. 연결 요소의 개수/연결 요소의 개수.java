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
                dfs(i);
            }
        }

        System.out.println(count);
    }

    static void dfs(int idx) {
        visited[idx] = true;
        for(int i=1; i<=nodeSize; i++){
            if(visited[i]) continue; //방문한 적이 있다면 패스
            if(!gragh[idx][i]) continue; //연결되어있지 않다면 패스

            dfs(i);
        }
    }
}