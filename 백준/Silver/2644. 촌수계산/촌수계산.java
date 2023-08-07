import java.util.*;
import java.io.*;

/*
걍 일반적인 BFS문제임임
단, 인접행렬과 연결리스트 두 유형으로 한번 풀어보자
5시 19분 시작작
*/

public class Main
{
    static boolean[][] matrix;
    static boolean[] visited;
    static int TARGET, size;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        matrix = new boolean[size+1][size+1];
        visited = new boolean[size+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        TARGET = Integer.parseInt(st.nextToken());

        int caseCount = Integer.parseInt(br.readLine());
        for(int i=0; i<caseCount; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            matrix[s][e] = true;
            matrix[e][s] = true;
        }

        System.out.println(bfs(start));
    }

    static int bfs(int start){
        Queue<Integer> que = new ArrayDeque<>();
        que.offer(start);
        visited[start] = true;
        int count = 0;

        while(!que.isEmpty()){
            count++;
            // System.out.println(count+"촌임임");
            for(int q=0, endq=que.size(); q<endq; q++){

                int cur = que.poll();

                for(int i=1; i<=size; i++){
                    if(visited[i]) continue;
                    if(!matrix[cur][i]) continue;
                    if(i==TARGET){
                        return count;
                    }
                    // System.out.println("  "+i+"번 탐색..");
                    que.offer(i);
                    visited[i] = true;
                }
            }
            

            
        }

        return -1;
    }
}