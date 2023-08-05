import java.util.*;
import java.io.*;


public class Main
{
    static List<Integer>[] list;
    static int[] parents;
    static boolean[] visited;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(br.readLine());
        list = new ArrayList[size+1];
        parents = new int[size+1];
        visited = new boolean[size+1];
        
        for(int i=0; i<=size; i++){
            list[i] = new ArrayList<Integer>();
        }

        StringTokenizer st;
        for(int i=0; i<size-1; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            list[s].add(e);
            list[e].add(s);
        }

        dfs(1,0);

        for(int i=2; i<=size; i++){
            sb.append(parents[i]).append("\n");
        }

        System.out.print(sb.toString());
    }

    static void dfs(int child, int parent) {
        parents[child] = parent;
        visited[child]=true;
        List<Integer> curList = list[child];

        for(int i=0, endi=curList.size(); i<endi; i++){
            int next = curList.get(i);

            if(visited[next]) continue;

            dfs(next,child);
        }
    }

}