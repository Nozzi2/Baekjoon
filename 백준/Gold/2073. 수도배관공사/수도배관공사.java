/*
문제가 이해하기 어려웠는데,
일렬로 연결된 파이프들의 용량 중 최소값이 해당 연결된 파이프의 용량이 된다.
길이가 D인 연결된 파이프의 최대 용량을 구해야한다.

예를 들어, 문제에서 주어진 입력대로 파이프가 있고, 구하려는 파이프의 길이가 7이라면
[1 5] [1 4] [2 7] [3 6]으로 파이프를 일렬로 연결했다면 길이가 7이 된다. (1+1+2+3)
그리고 이 파이프의 용량은 5, 4, 7, 6 중 최소값인 4가 파이프의 용량이 된다.

dp로 풀어야하는데 점화식을 어떻게 세워야할까..

배열의 기준을 길이로 하면 어떻게 될까?
dp[n] = 길이가 1일때 최대 용량
dp[1] = 5
dp[2] = dp[1]+ ... 아 길이 기준은 잘 모르겠다

dp[n] = 용량이 n일때 나올 수 있는 길이
dp[1] = 0
dp[2] = 0
dp[3] = 0
dp[4] = 1
dp[5] = ... 이건 의미가 없는거같은데?

역시 길이가 맞는거같음.
길이로 풀기 위해선 어떤 파이프를 사용했는지 알 수 있어야함.
dp배열마다 모든 파이프를 순회하면서 값을 갱신하더라도 시간초과가 나진 않을 것임
길이 제약 x 파이프 갯수 = 100_000 * 350 = 35_000_000 (1억 미만임)

dp[1] = [1 5] = 5
dp[2] = [2 7], [1 5]+[1 4] = 7
dp[3] = [3 6], [2 7]+[1 5] = 6
dp[4] = [4 5], dp[3]+[1 5], ... = 5
dp[n] = [Ln Cn] (길이가 n인 파이프 1개), dp[n-idx]+[Lidx Cidx]

dp배열을 갱신하기 위해서 바텀업으로 진행하고
모든 파이프를 검사하면서 해당 파이프를 쓸 수 있을 경우 값을 갱신시켜준다.
dp배열에 어떤 파이프를 썼는지 확인하기 위한 Set 자료구조를 쓰면 좋을듯

!!4%에서 메모리 초과!!
최악의 공간복잡도가 140MB 정도 돼서 메모리를 줄여야겠음.
비트마스킹으로 Set을 대체해야될듯
long이 2^53까지 저장 가능하기 때문에 범위가 350이므로 long형 변수 7개 사용하면 됨.
비트마스킹으로 대체하면 140MB > 5.6MB로 줄일 수 있음.

!!틀렸습니다!!
걍 로직 자체가 틀렸는지...
검색해보고 풀어야겠다..ㅠㅠ

생각한 점화식은 맞는데, 점화식을 갱신시키는 순서가 틀렸음
모든 파이프를 순회하면서
    해당 파이프를 사용했을 때
    모든 길이에서 최대 용량을 갱신시켜주면 됨.
    (목표 길이부터 해당 파이프의 길이까지 갱신)
해당 파이프를 단한번한 순회하기 때문에 파이프 사용여부 검사는 안해도 됨.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int MAX_VALUE = 987654321;

    static class Pipe {
        int index;
        int length;
        int cap;

        public Pipe(int index, int length, int cap) {
            this.index = index;
            this.length = length;
            this.cap = cap;
        }

        @Override
        public String toString() {
            return "Pipe{" +
                    "index=" + index +
                    ", length=" + length +
                    ", cap=" + cap +
                    '}';
        }
    }



    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int targetLength = Integer.parseInt(st.nextToken());
        int pipeCount = Integer.parseInt(st.nextToken());
        List<Pipe> pipes = new ArrayList<>();
        /*
        최악 공간 복잡도 계산
        Pipe의 공간복잡도
            int 4byte
            int 4byte
        8byte * 350 = 2800byte = 2.8 KB
         */
        for (int i = 0; i < pipeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int length = Integer.parseInt(st.nextToken());
            int cap = Integer.parseInt(st.nextToken());
            pipes.add(new Pipe(i, length, cap));
        }

        int[] dp = new int[targetLength+1];
        dp[0] = MAX_VALUE;

        //모든 파이프를 순회
        for(int i=0; i<pipeCount; i++){
            Pipe pipe = pipes.get(i); //모든 길이에서 해당 파이프를 사용한다고 가정
            

            //목표 길이부터 현재 파이프 길이까지 순회하기
            for(int j=targetLength, endj=pipe.length; j>=endj; j--){
                int minCap = Math.min(dp[j - pipe.length], pipe.cap); //갱신되지 않은 값은 초기값인 0이 저장됨. Math.min(0, cap)
                //갱신되지 않은 값이란? > 해당 파이프의 길이를 뺸 나머지 파이프의 용량이 저장되지 않은 값
                //초기에는 dp[0]은 최대값이고, dp[n]은 0이기 때문에
                //minCap을 계산할 때 현재 파이프 길이에서만 용량이 저장됨.
//                System.out.printf("   dp[j - pipe.length] : %d, pipe.cap : %d 비교 \n", dp[j - pipe.length], pipe.cap);
//                System.out.printf("     > dp[j] : %d, minCap : %d =갱신=> %d \n", dp[j], minCap, Math.max(dp[j], minCap));
                dp[j] = Math.max(dp[j], minCap); //dp[j]가 초기값이라면 minCap 값에 의해 갱신됨.
            }
        }
        System.out.println(dp[targetLength]);
    }
}

/*
    7 6 //목표 수도관 길이 7, 수도관 갯수 6
0 > 4 5 //0번째 수도관. 길이 4, 용량 5
1 > 3 6
2 > 2 7
3 > 1 4
4 > 6 7
5 > 1 5


7 6
4 5  0
3 6  1
2 7  2
1 9  3
6 7  4
1 10 5

Pipe{index=0, length=4, cap=5}
[987654321, 0, 0, 0, 5, 0, 0, 0]

Pipe{index=1, length=3, cap=6}
[987654321, 0, 0, 6, 5, 0, 0, 5]

Pipe{index=2, length=2, cap=7}
[987654321, 0, 7, 6, 5, 6, 5, 5]

Pipe{index=3, length=1, cap=4}
[987654321, 4, 7, 6, 5, 6, 5, 5]

Pipe{index=4, length=6, cap=7}
[987654321, 4, 7, 6, 5, 6, 7, 5]

Pipe{index=5, length=1, cap=5}
[987654321, 5, 7, 6, 5, 6, 7, 5]

5

 */