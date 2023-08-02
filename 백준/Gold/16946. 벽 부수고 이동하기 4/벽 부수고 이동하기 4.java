import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
벽인칸만 탐색해서 해당 벽을 부쉈을 때 이동할 수 있는 칸의 갯수를 출력해야함.

0(벽이 아닌 칸)을 먼저 BFS 탐색을 통해 벽을 안부쉈을 때 이동할 수 있는 칸을 2차원배열에 저장한다.
2차원 배열에는 해당칸을ㄹ 방문했을 때 이동할 수 있는 칸의 갯수가 저장되어 있음.
단, 칸의 갯수는 int값이 아닌 인스턴스가 저장되어있어야함.
그래야 BFS 탐색이 끝났을 때 칸마다 값을 갱신시켜줄 필요가 없음.
(null값 체크함으로써 방문체크도 가능!)

이제 1(벽)을 탐색하여 상하좌우에 있는 이동가능칸만 합쳐서 10으로 나눈 나머지로 출력하면 됨

 */
public class Main {

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static StepCount[][] stepCounts;
    static boolean[][] matrix;
    static int rowSize, colSize;

    static class Pos {
        int row;
        int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Pos(Pos p, int d) {
            this.row = p.row + dr[d];
            this.col = p.col + dc[d];
        }

        boolean isOut() {
            return row < 1 || row > rowSize || col < 1 || col > colSize;
        }

        boolean isWall() {
            return matrix[row][col];
        }

        boolean isVisited() {
            return stepCounts[row][col] != null;
        }

        int getCount() {
            return stepCounts[row][col].count;
        }

        StepCount getInstance() {
            return stepCounts[row][col];
        }

        void setVisit(StepCount count) {
            stepCounts[row][col] = count;
        }
    }

    static class StepCount {
        int count;

        public StepCount() {
            this.count = 0;
        }
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        matrix = new boolean[rowSize + 2][colSize + 2]; //1=true=벽, 0=false=길
        stepCounts = new StepCount[rowSize + 2][colSize + 2];

        for (int i = 1; i <= rowSize; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < colSize; j++) {
                matrix[i][j + 1] = chars[j] == '1'; //1=true=벽
            }
        }

        //벽을 부수지 않고 이동할 수 있는 갯수 계산
        for (int i = 1; i <= rowSize; i++) {
            for (int j = 1; j <= colSize; j++) {
                if(!matrix[i][j] && stepCounts[i][j]==null) { //길이면서 방문하지 않았다면
                    StepCount curCount = new StepCount();
                    bfs(new Pos(i, j), curCount);
                }
            }
        }

        //
        for (int i = 1; i <= rowSize; i++) {
            for (int j = 1; j <= colSize; j++) {
                int result = 0;
                if(matrix[i][j]) { //벽이라면
                    Pos cur = new Pos(i,j);
                    result++; //현재칸 포함하여 1 저장
                    Set<StepCount> set = new HashSet<>(); //합계에 더했는지 여부 체크

                    for (int d = 0; d < 4; d++) { //상하좌우 이동가능갯수 더하기
                        Pos next = new Pos(cur, d);
                        if(set.contains(next.getInstance())) continue; //이미 합계에 더한 경우는 제외
                        if (next.isVisited()) {
                            result+= next.getCount();
                            set.add(next.getInstance()); //합계 여부 체크
                        }
                    }
                }
                sb.append(result % 10);
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }

    private static void bfs(Pos start, StepCount curCount) {
        Queue<Pos> que = new LinkedList<>();
        que.offer(start);
        start.setVisit(curCount);
        curCount.count++;

        while (!que.isEmpty()) {
            Pos cur = que.poll();
            for (int i = 0; i < 4; i++) {
                Pos next = new Pos(cur, i);
                if(next.isOut()) continue;
                if(next.isWall()) continue;
                if(next.isVisited()) continue;

                curCount.count++;
                next.setVisit(curCount);
                que.offer(next);
            }
        }
    }
}