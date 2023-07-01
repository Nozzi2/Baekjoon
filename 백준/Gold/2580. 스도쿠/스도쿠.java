import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
0인 칸마다 들어갈 수 있는 숫자의 배열을 저장한다.
(가로, 세로, 9칸)
모든 0칸을 DFS로 순회하면서 배열의 숫자를 하나씩 집어넣고,
현재 상태에서 틀린 숫자가 없으면 계속 다음 숫자를 재귀 호출해주면서
마지막 칸 까지 가서도 이상 없으면 해당 배열을 출력해주고 종료한다.

가로칸, 세로칸, 3*3칸의 비트마스킹 값을 저장할 배열을 선언하면
모든칸을 순회하는 검사는 한번만 수행하면 된다!
 */
public class Main {

    static boolean isPrinted;
    static int[][] sdoku;
    static int[][] nines;
    static int[] colArr;
    static int[] rowArr;
    static List<Blank> blanks;

    static class Blank {
        int r;
        int c;
        int number;
        List<Integer> numbers;

        public Blank(int r, int c) {
            this.r = r;
            this.c = c;
            this.number = 0;
            this.numbers = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Blank{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        sdoku = new int[9][9];
        colArr = new int[9];
        rowArr = new int[9];
        nines = new int[3][3];
        blanks = new ArrayList<>();

        //입력 받으면서 가로값 비트 갱신
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            int bit=0; //가로 비트값
            for (int j = 0; j < 9; j++) {
                int input = Integer.parseInt(st.nextToken());
                sdoku[i][j] = input;
                if (input == 0) { //빈칸이면 리스트에 추가
                    blanks.add(new Blank(i, j));
                } else {
                    bit += 1<<input; //비트값 갱신
                }
            }
            rowArr[i] = bit; //가로 맨 마지막 칸에 비트값 저장
        }

        //세로 비트값 갱신
        for (int i = 0; i < 9; i++) {
            int bit=0;
            for (int j = 0; j < 9; j++) {
                int input = sdoku[j][i];
                if (input != 0) { //빈칸이면 리스트에 추가
                    bit += 1<<input; //비트값 갱신
                }
            }
            colArr[i] = bit; //맨 마지막 칸에는 비트값 저장
        }

        //3*3 비트값 갱신
        for (int i = 0; i < 7; i += 3) {
            for (int j = 0; j < 7; j += 3) {
                int bit=0;
                for (int x = i; x < i + 3; x++) {
                    for (int y = j; y < j + 3; y++) {
                        if (sdoku[x][y] != 0) {
                            bit += 1<<sdoku[x][y];
                        }
                    }
                }
                nines[i/3][j/3] = bit;
            }
        }




        for (Blank b : blanks) {
            int numbers =0; //비트마스킹으로 저장할 값
            //가로검사
            numbers = numbers|rowArr[b.r];

            //세로검사
            numbers = numbers|colArr[b.c];

            //9칸검사
            numbers = numbers|nines[b.r/3][b.c/3];

            //1부터 9까지 들어갈 수 있는 숫자를 해당 칸 리스트에 추가
            for (int i = 1; i <= 9; i++) {
                if((numbers & 1 << i) != 1 << i){
                    b.numbers.add(i);
                }
            }
        }



        dfs(0);
    }

    private static void dfs(int index) {
        if(isPrinted) return;
        if (index == blanks.size()) {
//            System.out.println("끝났어");
            print();
            isPrinted = true;
            return;
        }

        Blank cur = blanks.get(index);
        for (int num : cur.numbers) {
            int bit = rowArr[cur.r]|colArr[cur.c]|nines[cur.r/3][cur.c/3];
            if((bit & 1 << num) == 1 << num) continue;

            rowArr[cur.r] += 1<<num;
            colArr[cur.c] += 1<<num;
            nines[cur.r/3][cur.c/3] += 1<<num;
            cur.number = num;

            dfs(index+1);

            rowArr[cur.r] -= 1<<num;
            colArr[cur.c] -= 1<<num;
            nines[cur.r/3][cur.c/3] -= 1<<num;
        }
    }

    private static void print() {
        for (Blank b : blanks) {
            sdoku[b.r][b.c] = b.number;
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sdoku[i][j]+" ");
            }
            System.out.println();
        }
    }
}


/*
0 3 5 4 6 9 2 7 8
7 8 2 1 0 5 6 0 9
0 6 0 2 7 8 1 3 5
3 2 1 0 4 6 8 9 7
8 0 4 9 1 3 5 0 6
5 9 6 8 2 0 4 1 3
9 1 7 6 5 2 0 8 0
6 0 3 7 0 1 9 5 2
2 5 8 3 9 4 7 6 0

0 0 0 4 6 9 2 7 8
7 8 2 1 0 5 6 0 9
0 6 0 2 7 8 1 3 5
3 2 1 0 4 6 8 9 7
8 0 4 9 1 3 5 0 6
5 9 6 8 2 0 4 1 3
9 1 7 6 5 2 0 8 0
6 0 3 7 0 1 9 5 2
2 5 8 3 9 4 7 6 0

 */