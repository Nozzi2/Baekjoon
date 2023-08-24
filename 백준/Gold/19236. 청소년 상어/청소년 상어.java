import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
시뮬레이션 문제임
먼저 상어에게 잡아먹히지 않은 물고기들의 이동을 갱신시켜준다.
물고기들은 1번부터 16번까지 있을 수 있는데,
물고기 클래스를 선언하고 배열에 각각 저장해놓으면
번호 순서대로 이동시키기 수월할 것 같다.

물고기 클래스
    방향
    위치
    상어에게 먹혔는지 여부

상어 클래스
    방향
    위치
    현재까지 먹은 물고기들의 총합

2차원배열에는 물고기 클래스 배열의 인덱스를 저장
1번부터 16번 물고기 클래스가 저장된 배열

상어가 가려는 경로에 물고기가 하나도 없는 경우도 출력해야함!

 */
public class Main {

    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
//    static int[][] matrix;
//    static Fish[] fishes;
//    static Shark shark;
    static final int SIZE_R = 4;
    static final int SIZE_C = 4;
    static final int FISH_SIZE = 16;
    static int maxSum;



    static class Fish {
        int num;
        int row;
        int col;
        int direction;
        boolean isAlive;

        public Fish(int num, int row, int col, int direction) {
            this.num = num;
            this.row = row;
            this.col = col;
            this.direction = direction;
            this.isAlive = true;
        }

        public Fish(Fish fish) {
            this.num = fish.num;
            this.row = fish.row;
            this.col = fish.col;
            this.direction = fish.direction;
            this.isAlive = fish.isAlive;
        }

        void turn() {
            this.direction = ++direction % 8;
        }

        boolean isNextOut() {
            int nextRow = this.row + dr[direction];
            int nextCol = this.col + dc[direction];
            return nextRow < 1 || nextRow > SIZE_R || nextCol < 1 || nextCol > SIZE_C;
        }

        boolean isNextShark(Shark shark) {
            int nextRow = this.row + dr[direction];
            int nextCol = this.col + dc[direction];
            return shark.row == nextRow && shark.col == nextCol;
        }

        void move(Fish[] fishes, int[][] matrix) {
            int nextRow = this.row + dr[direction];
            int nextCol = this.col + dc[direction];
            int nextNum = matrix[nextRow][nextCol];
            Fish next = fishes[nextNum];
            next.row = this.row;
            next.col = this.col;
            this.row = nextRow;
            this.col = nextCol;

            matrix[this.row][this.col] = this.num;
            matrix[next.row][next.col] = next.num;
        }

        @Override
        public String toString() {
            if (!this.isAlive) {
                return "dead";
            }
            String arrow = "";
            switch(direction){
                case 0:
                    arrow="↑";
                    break;
                case 1:
                    arrow="↖";
                    break;
                case 2:
                    arrow="←";
                    break;
                case 3:
                    arrow="↙";
                    break;
                case 4:
                    arrow="↓";
                    break;
                case 5:
                    arrow="↘";
                    break;
                case 6:
                    arrow="→";
                    break;
                case 7:
                    arrow="↗";
                    break;
            }
            return arrow + " (" + row +
                    ", " + col +")";
        }
    }//class fish end

    static class Shark {
        int row;
        int col;
        int direction;
        int sum;

        public Shark(Fish[] fishes, int[][] matrix) {
            this.row = 1;
            this.col = 1;
            Fish feed = fishes[matrix[1][1]];
            this.direction = feed.direction;
            this.sum = feed.num;
            feed.isAlive = false;
        }

        //해당 물고기를 먹은 후의 상어
        public Shark(int sum, Fish fish) {
            this.row = fish.row;
            this.col = fish.col;
            this.direction = fish.direction;
            this.sum = sum + fish.num;
        }

        public Shark(Shark shark) {
            this.row = shark.row;
            this.col = shark.col;
            this.direction = shark.direction;
            this.sum = shark.sum;
        }

//        boolean isNextOut() {
//            int nextRow = this.row + dr[direction];
//            int nextCol = this.col + dc[direction];
//            return row < 1 || row > SIZE_R || col < 1 || col > SIZE_C;
//        }

        //해당 물고기를 먹었을 때의 상어 객체를 반환
        Shark eat(Fish fish) {
            fish.isAlive = false;
            return new Shark(this.sum, fish);
        }

        void getBack(Fish fish) {
            fish.isAlive = true;
        }

        @Override
        public String toString() {
            String arrow = "";
            switch(direction){
                case 0:
                    arrow="↑";
                    break;
                case 1:
                    arrow="↖";
                    break;
                case 2:
                    arrow="←";
                    break;
                case 3:
                    arrow="↙";
                    break;
                case 4:
                    arrow="↓";
                    break;
                case 5:
                    arrow="↘";
                    break;
                case 6:
                    arrow="→";
                    break;
                case 7:
                    arrow="↗";
                    break;
            }
            return " = "+sum+" 상어"+arrow + " (" + row +
                    ", " + col +") > "+sum;
        }
    }//class shark end


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] matrix = new int[SIZE_R + 2][SIZE_C + 2];
        Fish[] fishes = new Fish[16 + 1];
        StringTokenizer st;
        for (int r = 1; r <= SIZE_R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= SIZE_C; c++) {
                int num = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken()) - 1;
                fishes[num] = new Fish(num, r, c, direction);
                matrix[r][c] = num;
            }
        }
        fishes[0] = new Fish(0, 1, 1, 0);
        Shark shark = new Shark(fishes, matrix);

        maxSum = 0;

        dfs(shark, fishes, matrix);

        System.out.println(maxSum);
    }

    static void dfs(Shark shark, Fish[] fishes, int[][] matrix) {
        //물고기 움직여서 matrix, fishes 갱신
        fishesMove(shark, fishes, matrix);

        //상어 먹이 리스트 저장
        List<Integer> feedList = getFeedList(shark, fishes, matrix);

        //만약 상어가 먹을게 없다면
        if (feedList.size() == 0) {
            maxSum = Math.max(maxSum, shark.sum);
            return;
        }

        //상어가 해당 물고기를 먹었을 경우로 재귀호출
        for (int num : feedList) {
            //재귀로 보낼 deepCopy 파라미터 생성
            Fish[] newFishes = deepCopyFishes(fishes);
            int[][] newMatrix = deepCopyMatrix(matrix);
            Shark newShark = shark.eat(newFishes[num]);
            
            dfs(newShark, newFishes, newMatrix);
        }
    }

    static int[][] deepCopyMatrix(int[][] matrix) {
        int[][] output = new int[SIZE_R + 2][SIZE_C + 2];

        for (int r = 1; r <= SIZE_R; r++) {
            for (int c = 1; c <= SIZE_C; c++) {
                output[r][c] = matrix[r][c];
            }
        }
        return output;
    }

    static Fish[] deepCopyFishes(Fish[] fishes) {
        Fish[] output = new Fish[17];

        for (int i = 0; i <= FISH_SIZE; i++) {
            output[i] = new Fish(fishes[i]);
        }
        return output;
    }

    //상어의 이동경로에 먹을 수 있는 물고기들의 리스트를 반환하는 메소드
    static List<Integer> getFeedList(Shark shark, Fish[] fishes, int[][] matrix) {
        List<Integer> feedList = new ArrayList<>();

        int nextRow = shark.row + dr[shark.direction];
        int nextCol = shark.col + dc[shark.direction];

        while (nextRow >= 1 && nextRow <= SIZE_R && nextCol >= 1 && nextCol <= SIZE_C) {
            int num = matrix[nextRow][nextCol];
            if(fishes[num].isAlive){
                feedList.add(num);
            }
            nextRow += dr[shark.direction];
            nextCol += dc[shark.direction];
        }

        return feedList;
    }

    static void fishesMove(Shark shark, Fish[] fishes, int[][] matrix) {
        for (int i = 1; i <= FISH_SIZE; i++) {
            Fish fish = fishes[i];
            if(!fish.isAlive) continue;

            while (fish.isNextOut() || fish.isNextShark(shark)) {
                fish.turn();
            }

            fish.move(fishes, matrix);
        }
    }

    static void printMatrix(Shark shark, Fish[] fishes, int[][] matrix) {

        for (int r = 1; r <= SIZE_R; r++) {
            for (int c = 1; c <= SIZE_C; c++) {
                if (r == shark.row && c == shark.col) {
                    System.out.printf("%3s ", "상");
                    continue;
                }

                int num = matrix[r][c];
                Fish fish = fishes[num];
                if(!fish.isAlive){
                    System.out.printf("%3d ", 0);
                } else {
                    System.out.printf("%3d ", num);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    static void printFishes(Fish[] fishes) {
        int index = 0;
        for (Fish f : fishes) {
            System.out.println("Fish["+index+++"] "+f);
        }
    }
}

/*
7 6 2 3 15 6 9 8
3 1 1 8 14 7 10 1
6 1 13 6 4 3 11 4
16 1 8 7 5 2 12 2
 */