import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][][] tomatoes;
    static boolean[][][] isVisited;
    static int[] dr = {-1, 0, 1, 0, 0, 0};
    static int[] dc = {0, -1, 0, 1, 0, 0};
    static int[] dh = {0, 0, 0, 0, -1, 1};
    static int emptyCount;
    static int dayCount;
    static int hSize;
    static int rSize;
    static int cSize;

    static class Pos {
        int h;
        int r;
        int c;

        public Pos(int h, int r, int c) {
            this.h = h;
            this.r = r;
            this.c = c;
        }

        public Pos(Pos p, int d) {
            this.h = p.h + dh[d];
            this.r = p.r + dr[d];
            this.c = p.c + dc[d];
        }

        public boolean isOut() {
            return r < 1 || r > rSize || c < 1 || c > cSize || h < 1 || h > hSize;
        }

        public boolean isVisited() {
            return isVisited[h][r][c];
        }

        public boolean isBlocked() {
            return tomatoes[h][r][c] == -1;
        }

        public void setVisited() {
            tomatoes[h][r][c] = 1;
            isVisited[h][r][c] = true;
        }

        public void print() {
            if(h != 1) return;
            for (int i = 1; i <= rSize; i++) {
                for (int j = 1; j <= cSize; j++) {
                    System.out.print(tomatoes[1][i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }

    }

    public static void main(String[] args) throws Exception {
        emptyCount = 0;
        dayCount = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        cSize = Integer.parseInt(st.nextToken());
        rSize = Integer.parseInt(st.nextToken());
        hSize = Integer.parseInt(st.nextToken());
        tomatoes = new int[hSize+2][rSize+2][cSize+2];
        isVisited = new boolean[hSize+2][rSize+2][cSize+2];

        Queue<Pos> goodTomatoes = new LinkedList<>();
        for (int i = 1; i <= hSize; i++) {
            for (int j = 1; j <= rSize; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= cSize; k++) {
                    int input = Integer.parseInt(st.nextToken());
                    tomatoes[i][j][k] = input;
                    if (input == 0) emptyCount++;
                    else if(input == 1) {
                        goodTomatoes.offer(new Pos(i, j, k));
                        isVisited[i][j][k] = true;
                    }
                }//for k
            }//for j
        }//for i

        if (emptyCount == 0) {
            System.out.println(0);
            System.exit(0);
        }

        dfs(goodTomatoes);

        System.out.println(emptyCount!=0?-1:--dayCount);
    }

    private static void dfs(Queue<Pos> que) {
        while (!que.isEmpty()) {
            dayCount++;
            for (int q = 0, endq = que.size(); q < endq; q++) {
                Pos cur = que.poll();
                for (int i = 0; i < 6; i++) {
                    Pos next = new Pos(cur, i);
                    if(next.isOut()) continue;
                    if(next.isBlocked()) continue;
                    if(next.isVisited()) continue;
                    next.setVisited();
//                    next.print();
                    que.offer(next);
                    emptyCount--;
                }
            }
//            System.out.println("\n------------쨘-------------\n");
        }
    }
}