import java.util.*;
import java.io.*;


public class Main
{
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    static int[][] matrix;
    static boolean[][] visited;
    static int sizeR, sizeC;

    static class Pos{
        int r;
        int c;

        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }

        public Pos(Pos p, int d){
            this.r = p.r+dr[d];
            this.c = p.c+dc[d];
        }

        boolean isOut() {
            return r<1 || r>sizeR || c<1 || c>sizeC;
        }

        boolean isWall() {
            return matrix[r][c]==0;
        }

        boolean isVisited() {
            return visited[r][c];
        }

        void setVisit(int count) {
            visited[r][c] = true;
            matrix[r][c] = count;
        }
    }

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sizeR = Integer.parseInt(st.nextToken());
        sizeC = Integer.parseInt(st.nextToken());
        matrix = new int[sizeR+2][sizeC+2];
        visited = new boolean[sizeR+2][sizeC+2];
        
        Pos start = null;
        for(int r=1; r<=sizeR; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=1; c<=sizeC; c++){
                int input = Integer.parseInt(st.nextToken());
                matrix[r][c] = input;
                if(input == 2){
                    start = new Pos(r,c);
                    // System.out.println(r+c+"에서 선언됨!");
                }
            }
        }

        bfs(start);

        StringBuilder sb = new StringBuilder();
        for(int r=1; r<=sizeR; r++){
            for(int c=1; c<=sizeC; c++){
                if(visited[r][c]){
                    sb.append(matrix[r][c]).append(' ');
                } else if(matrix[r][c]==0){
                    sb.append(0).append(' ');
                } else {
                    sb.append(-1).append(' ');
                }
                
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }

    public static void bfs(Pos start){
        Queue<Pos> que = new ArrayDeque<>();
        que.offer(start);
        int count=0;
        start.setVisit(count);

        while(!que.isEmpty()){
            count++;
            for(int q=0, endq=que.size(); q<endq; q++){
                Pos cur = que.poll();
                for(int i=0; i<4; i++){
                    Pos next = new Pos(cur, i);
                    if(next.isOut()) continue;
                    if(next.isWall()) continue;
                    if(next.isVisited()) continue;
                    next.setVisit(count);
                    que.offer(next);
                }
            }
        }
    }
}