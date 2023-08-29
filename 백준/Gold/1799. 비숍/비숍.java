import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ListResourceBundle;
import java.util.StringTokenizer;

/*
NQUEEN같은 아이디어가 생각나지 않는다.
일단 구현해보고 백트레킹 할 수 있는지 한번 봐야게따
NQueen은 한줄에 하나씩 놓는데, 이문제는 한줄에 여러개를 놓을 수 있어서
dfs의 depth가 최대 100까지도 갈 수가 있다...
아이디어 없으면 풀 수가 없음..

검색해보니 체크판은 검정, 흰 칸이 반복되는데,
검정칸에 있는 비숍은 흰칸의로 절대 갈 수 없고
흰칸 비숍도 검정칸으로 갈 수 없다.
그렇기 때문에 절반씩 나눠서 한다면 최대 depth가 50으로 줄어들 수 있음.

아니다 이렇게 depth를 깊게 가는 것이 아닌 
해당 위치에 놓았을떄 놓지 않았을 때를 계속 재귀로 호출해주면 됨
그럼 모든 경우의 수를 고려할 수 있으면서 depth가 깊어지지 않음
 */
public class Main {

    static int size, maxBlack, maxWhite;
    static int[] dr = {-1, -1, 1, 1};
    static int[] dc = {-1, 1, -1, 1};

    static class Pos {
        int r;
        int c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        boolean isEnd() {
            return r >= size;
        }

        Pos next() {
            int nextR = r;
            int nextC = c;
            if (c + 2 < size) {
                nextC+=2;
            } else {
                nextR++;
                if (c % 2 == 0) {
                    nextC = 1;
                } else {
                    nextC = 0;
                }
            }

            return new Pos(nextR, nextC);
        }

        void placeBishop(int[][] matrix) {
            matrix[r][c] = 2;
        }

        void removeBishop(int[][] matrix) {
            matrix[r][c] = 1;
        }

        boolean canPlaceBishop(int[][] matrix) {
            if(matrix[r][c] == 0) return false;

            for (int d = 0; d < 4; d++) { //4방향으로 검사
                int nr = r + dr[d];
                int nc = c + dc[d];

                while (nr >= 0 && nr < size && nc >= 0 && nc < size) { //경계를 벗어나기 전까지 반복
                    if (matrix[nr][nc] == 2) {
                        return false;
                    }
                    nr += dr[d];
                    nc += dc[d];
                }
            }

            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        int[][] matrix = new int[size][size];
        StringTokenizer st = null;
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        boolean isBlack = true;
        Pos start = new Pos(0, 0);
        int[][] newMatrix = deepCopyMatrix(matrix);
        dfs(newMatrix, start, count, isBlack);

        count=0;
        isBlack = false;
        start = new Pos(0, 1);
        dfs(matrix, start, count, isBlack);

        System.out.println(maxBlack+maxWhite);
    }

    static void dfs(int[][] matrix, Pos cur, int count, boolean isBlack) {
        if (cur.isEnd()) {
            if (isBlack) {
                maxBlack = Math.max(maxBlack, count);
            } else {
                maxWhite = Math.max(maxWhite, count);
            }
            return;
        }

        if (cur.canPlaceBishop(matrix)) { //현재 위치에 비숍을 놓을 수 있다면
            cur.placeBishop(matrix);
            dfs(matrix, cur.next(), count+1, isBlack);
            cur.removeBishop(matrix);
        }

        dfs(matrix, cur.next(), count, isBlack); //놓지 않는 경우도 dfs 탐색
    }

    static int[][] deepCopyMatrix(int[][] matrix) {
        int[][] output = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                output[i][j] = matrix[i][j];
            }
        }

        return output;
    }

}
/*
5
1 1 0 1 1
0 1 0 0 0
1 0 1 0 1
1 0 0 0 0
1 0 1 1 1
 */