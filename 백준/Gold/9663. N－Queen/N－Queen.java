import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
첫번쨰줄 첫번쨰 칸에 놓는 경우가 만약 성립한다면 해당 경우의 수는 *N 해주면 됨.
근데 이건 검증해보지 않은 거라 한번 브루트포스로 구현해서 검증해보겠음.
아닌거같음.. 진짜 브루트포스로 다 몇갠지 세봐야할듯

일단 마지막 dfs로 마지막 depth까지 들어가는건 그렇다쳐도
아닌게 나온다면 바로 백트래킹해줘야함.
그러기 위해서는 해당 줄에 놓을 수 있는 칸의 갯수를 저장하고
해당 갯수가 0이라면 더이상 탐색할 필요가 없도록 하면
어느정도 백트래킹 되지 않을까?
> 수행시간 뽑아보니까 전혀 안됨.

도저히 모르겠어서 인터넷 검색하니까 일차원배열로 선언해서 할 수 있다고 함..ㅠㅠ
1차원 배열로 다시 구현해봐야겠음
 */
public class Main {

    static int size;
    static int result;
    static final int INIT_NUMBER = -100;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        int[] arr = new int[size];
        boolean[] isUsed = new boolean[size];
        Arrays.fill(arr, INIT_NUMBER);

        int count=0;
        dfs(arr, isUsed, count);

        System.out.println(result);
    }

    static void dfs(int[] arr, boolean[] isUsed, int count) {
        for (int i = 0; i < size; i++) {
            if (!isUsed[i]) {

                arr[count] = i;
                isUsed[i] = true;

                if (check(arr, count)) {
                    if (count + 1 == size) {
                        result++;
                    } else {
                        dfs(arr, isUsed, count + 1);
                    }
                }

                arr[count] = INIT_NUMBER;
                isUsed[i] = false;
            }

        }
    }

    static boolean check(int[] arr, int count) {
        for (int i = 0; i < count; i++) {
            if (count - i == Math.abs(arr[i] - arr[count])) {
                return false;
            }
        }

        return true; 
    }
}

/*
//counts 배열 X
12
정답 : 14200
소요시간 : 412ms

//counts 배열 O
12
정답 : 14200
소요시간 : 419ms

//1차원 배열 사용시 (딥카피)
12
정답 : 14200
소요시간 : 263ms

15
정답 : 2279184
소요시간 : 62985ms

//1차원 배열, 딥카피 없이
12
정답 : 14200
소요시간 : 238ms

15
정답 : 2279184
소요시간 : 55586ms

//check 메소드 변경
12
정답 : 14200
소요시간 : 104ms

15
정답 : 2279184
소요시간 : 13505ms


 */