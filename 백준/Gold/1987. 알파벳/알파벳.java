/*
알파벳이 다른것만 움직일 수 있는 조건이 있다.
즉, 많이 가봐야 26칸이라는 의미임.

BFS는 갈 수가 없는게, 내가 현재 방문한 칸에 따라서 다음 갈 수 있는 칸이 정해지기 때문에
(큐에 등록된 경로 기준) 두 번이상 같은 칸에 접근할 수 있어야 한다.

그래서 DFS로 탐색을 하면서, 현재까지 방문한 내역을 들고다니면서 탐색을 진행하면 될거같음.

DFS할때 들고다닐 것들
 - 칸 방문 여부 > 없어도됨 어차피 알파벳으로 대체가능
 - 알파벳 방문 여부
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C, maxCount;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int[][] matrix;     //2차원 배열

    static class Pos {
        int r;
        int c;
        int count;
//        boolean[] isVisited;    //알파벳 26개의 방문여부 저장
        // > 이거 비트마스킹으로 최적화 할 수 있음.
        //  왜냐하면 2^26은 int의 최대값보다 작기때문!
        int isVisitedBit;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
            this.count = 0;
//            this.isVisited = new boolean[26];
            this.isVisitedBit=0;
        }

        public Pos(Pos p, int num) {
            this.r = p.r + dr[num];
            this.c = p.c + dc[num];
            this.count = p.count;
//            this.isVisited = p.isVisited.clone();
            this.isVisitedBit = p.isVisitedBit;
        }

        //배열 경계 검사
        public boolean isOut() {
            return r < 1 || r > R || c < 1 || c > C;
        }

        //방문할 수 있는지 여부 검사 (방문했다면 false)
//        public boolean canVisit() {
//            int alpha = matrix[r][c];
//            return !isVisited[alpha];
//        }

        //방문할 수 있는지 여부 검사 (방문했다면 false)
        public boolean canVisitBit() {
            int alpha = matrix[r][c];
            return (isVisitedBit & 1 << alpha) != 1 << alpha;
        }

        //해당 알파벳 방문 처리
        public void setVisit() {
            int alpha = matrix[r][c];
            count++;
            isVisitedBit += 1 << alpha;
//            isVisited[alpha] = true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        matrix = new int[R+2][C+2];

        //입력받기
        for (int i = 1; i <= R; i++) {
            String input = br.readLine();
            char[] chars = input.toCharArray();
            for (int j = 1; j <= C; j++) {
                matrix[i][j] = chars[j-1]-'A';
            }
        }

        Pos start = new Pos(1, 1);
        dfs(start);

        System.out.println(maxCount);
    }

    private static void dfs(Pos cur) {
        if (!cur.isOut() && cur.canVisitBit()) {
            cur.setVisit();
        } else {
            return;
        }

        maxCount = Math.max(maxCount, cur.count);

        //사방으로 탐색
        for (int i = 0; i < 4; i++) {
            Pos next = new Pos(cur, i);
            dfs(next);
        }
    }
}