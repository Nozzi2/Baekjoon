import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
2차원 배열의 칸별로 방향이 주어지고 해당 방향에 따라 배열을 이동함.
더이상 이동하지 못하게 하는 칸을 최소의 갯수로 만든다고 함.
모든 칸에서 이동을 시작하여 더이상 이동하지 못하게 하는 칸으로 도착하게 하기위해
만들어야하는 칸의 갯수는?

방향대로 움직이면서 방문체크를 해준다.
방문체크는 int값으로 하는데,
이 int값은 몇번째 탐색인지 저장하는 값임.
만약 이미 방문체크 되어있는 칸에 도착한다면
    저장되어 있는 값이 현재 탐색과 같다면
        만들어야하는 칸 갯수++
아니라면
    이미 방문했었으므로 다음꺼 탐색
 */
public class Main {

    static int[][] rounds;
    static char[][] matrix;
    static int sizeR, sizeC;

    static class Man {
        int r;
        int c;
        int round;

        public Man(int r, int c, int round) {
            this.r = r;
            this.c = c;
            this.round = round;
        }

        void move() {
            switch (matrix[r][c]) {
                case 'D' :
                    r++;
                    break;
                case 'U' :
                    r--;
                    break;
                case 'L' :
                    c--;
                    break;
                case 'R' :
                    c++;
                    break;
            }
        }

        boolean isOut() {
            return r < 1 || r > sizeR || c < 1 || c > sizeC;
        }

        boolean isThisRoundVisited() {
            return rounds[r][c] == this.round;
        }

        boolean isVisited() {
            return rounds[r][c] != 0;
        }

        void setVisit() {
            rounds[r][c] = this.round;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sizeR = Integer.parseInt(st.nextToken());
        sizeC = Integer.parseInt(st.nextToken());
        matrix = new char[sizeR + 2][sizeC + 2];
        rounds = new int[sizeR + 2][sizeC + 2];

        for (int r = 1; r <= sizeR; r++) {
            char[] input = br.readLine().toCharArray();
            for (int c = 1; c <= sizeC; c++) {
                matrix[r][c] = input[c - 1];
            }
        }

        int round = 0;
        int count = 0;
        for (int r = 1; r <= sizeR; r++) {
            for (int c = 1; c <= sizeC; c++) {
                if (rounds[r][c] != 0) continue; //이전에 방문한적이 있다면 종료

                Man man = new Man(r, c, ++round);
                while (true) {

//                    for (int r1 = 1; r1 <= sizeR; r1++) {
//                        for (int c1 = 1; c1 <= sizeC; c1++) {
//                            System.out.print(man.r==r1 && man.c==c1?"# ":"- ");
//                        }
//                        System.out.println();
//                    }
//                    System.out.println();

                    man.setVisit();
                    man.move();
                    if(man.isOut()) {
                        count++;
                        break;
                    }
                    if(man.isVisited()) {
                        if (man.isThisRoundVisited()) {
                            count++;
                        }
                        break;
                    }
                }
//                System.out.println("---------------------");
            }
        }

//        for (int r = 1; r <= sizeR; r++) {
//            for (int c = 1; c <= sizeC; c++) {
//                System.out.print(rounds[r][c]+" ");
//            }
//            System.out.println();
//        }

        System.out.println(count);
    }
}
/*
3 4
DLLL
DRLU
RRRU

5 6
DRRDLL
DURDDL
DUULLU
RRRRDU
UUUURR

# - - - - -
- - - - - -
- - - - - -
- - - - - -
- - - - - -

- - - - - -
# - - - - -
- - - - - -
- - - - - -
- - - - - -

- - - - - -
- - - - - -
# - - - - -
- - - - - -
- - - - - -

- - - - - -
- - - - - -
- - - - - -
# - - - - -
- - - - - -

- - - - - -
- - - - - -
- - - - - -
- # - - - -
- - - - - -

- - - - - -
- - - - - -
- - - - - -
- - # - - -
- - - - - -

- - - - - -
- - - - - -
- - - - - -
- - - # - -
- - - - - -

- - - - - -
- - - - - -
- - - - - -
- - - - # -
- - - - - -

- - - - - -
- - - - - -
- - - - - -
- - - - - -
- - - - # -

- - - - - -
- - - - - -
- - - - - -
- - - - - -
- - - - - #

---------------------
- # - - - -
- - - - - -
- - - - - -
- - - - - -
- - - - - -

- - # - - -
- - - - - -
- - - - - -
- - - - - -
- - - - - -

- - - # - -
- - - - - -
- - - - - -
- - - - - -
- - - - - -

- - - - - -
- - - # - -
- - - - - -
- - - - - -
- - - - - -

- - - - - -
- - - - - -
- - - # - -
- - - - - -
- - - - - -

- - - - - -
- - - - - -
- - # - - -
- - - - - -
- - - - - -

- - - - - -
- - # - - -
- - - - - -
- - - - - -
- - - - - -

---------------------
- - - - # -
- - - - - -
- - - - - -
- - - - - -
- - - - - -

---------------------
- - - - - #
- - - - - -
- - - - - -
- - - - - -
- - - - - -

---------------------
- - - - - -
- # - - - -
- - - - - -
- - - - - -
- - - - - -

---------------------
- - - - - -
- - - - # -
- - - - - -
- - - - - -
- - - - - -

- - - - - -
- - - - - -
- - - - # -
- - - - - -
- - - - - -

---------------------
- - - - - -
- - - - - #
- - - - - -
- - - - - -
- - - - - -

---------------------
- - - - - -
- - - - - -
- # - - - -
- - - - - -
- - - - - -

---------------------
- - - - - -
- - - - - -
- - - - - #
- - - - - -
- - - - - -

---------------------
- - - - - -
- - - - - -
- - - - - -
- - - - - #
- - - - - -

---------------------
- - - - - -
- - - - - -
- - - - - -
- - - - - -
# - - - - -

---------------------
- - - - - -
- - - - - -
- - - - - -
- - - - - -
- # - - - -

---------------------
- - - - - -
- - - - - -
- - - - - -
- - - - - -
- - # - - -

---------------------
- - - - - -
- - - - - -
- - - - - -
- - - - - -
- - - # - -

---------------------
1 2 2 2 3 4
1 5 2 2 6 7
1 8 2 2 6 9
1 1 1 1 1 10
11 12 13 14 1 1
2

 */