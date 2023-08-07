import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
전형적인 재귀 문제임.
좌측 상단부터 시작해서
문제에서 주어지는 위치에 도달할때까지 0,0부터 2^N-1, 2^N-1까지 진행해주면 된다.

먼저 0으로 채워진 배열에 몇번째 방문하는지 볼 수 있는 배열을 먼저 만들어서
배열을 출력시켜 봐야겠다.
감은 오는데 코드로 어케할지가 좀 감이 안잡힘

!!! 시간초과 !!!
실재로 재귀로 코드를 구현해도 결과값은 나온다.
근데 시간초과가 뜸..
지금보면 4분면중에 어디있는지 알면 굳이 하나씩 카운트하지 않고 한꺼번에 올려줘도 된다!
어딘지 r,c값이 주어지니까 필요없는 것들은 그냥 카운트 더해주면 됨.
 */
public class Main {

    static int targetR, targetC, count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        targetR = Integer.parseInt(st.nextToken());
        targetC = Integer.parseInt(st.nextToken());
        count = 0;
        int size = 1<<N; //2의 N제곱

        recursive(0,0, size);
    }

    private static void recursive(int r, int c, int size) {
        if (targetR >= r + size || targetR < r
         || targetC >= c + size || targetC < c) {
            count += size*size;
            return;
        } else if (targetR == r && targetC == c && size == 1) {
            System.out.println(count);
            System.exit(0);
        }
        recursive(r,c,size/2);
        recursive(r, c + size / 2, size / 2);
        recursive(r + size / 2, c, size / 2);
        recursive(r+size/2, c+size/2, size/2);
    }
}