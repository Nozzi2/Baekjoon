/*
내리막길로만 내려가서 가장 오른쪽 아래칸으로 도착하는 '모든 경우의 수'를 출력해야하므로
DFS로 탐색하면 된다.

DFS 탐색할때 다음 탐색할 위치가 현재 위치보다 숫자가 작은지 여부 검사해서
계속 재귀 호출해주면 된다!

재귀 호출할때는 호출 후 검사하는 것 보다
호출 전 검사하여 백트래킹하는 것이 훨씬 더 메모리를 줄일 수 있다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] matrix;
    static int[][] memo;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int count, sizeR, sizeC;

    static class Pos {
        int r;
        int c;
        int cost;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
            this.cost = matrix[r][c];
        }

        public Pos(Pos p, int d) {
            this.r = p.r+dr[d];
            this.c = p.c+dc[d];
            this.cost = matrix[r][c];
        }

        //영역을 벗어났는지 검사
        public boolean isOut() {
            return r < 1 || r > sizeR || c < 1 || c > sizeC;
        }

        //도착했는지 여부 검사
        public boolean isArrived() {
            return r == sizeR && c == sizeC;
        }

        public boolean isChecked() {
            return memo[r][c] != -1;
        }


    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sizeR = Integer.parseInt(st.nextToken());
        sizeC = Integer.parseInt(st.nextToken());
        matrix = new int[sizeR + 2][sizeC + 2];
        memo = new int[sizeR + 2][sizeC + 2];
        count = 0;

        for (int i = 1; i <= sizeR; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= sizeC; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                memo[i][j] = -1;
            }
        }

        Pos start = new Pos(1,1);

        System.out.println(dfs(start));
    }

    static int dfs(Pos cur) {
        if(cur.isArrived()){
            return 1;
        }

        //이미 방문했었다면
        if(cur.isChecked()){
            return memo[cur.r][cur.c];
        }

        memo[cur.r][cur.c] = 0; //방문처리

        for (int i = 0; i < 4; i++) {
            Pos next = new Pos(cur, i);
            if(next.isOut()) continue;
            if(!isDown(cur, next)) continue;

            memo[cur.r][cur.c] += dfs(next);
        }

        return memo[cur.r][cur.c];
    }

    static boolean isDown(Pos cur, Pos next) {
        return cur.cost > next.cost;
    }

}