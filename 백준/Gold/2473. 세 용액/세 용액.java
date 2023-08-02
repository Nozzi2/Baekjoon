import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
세용액의 합계가 0에 최대한 가깝도록 만들어줘야한다.

투포인터를 이용해서 양쪽값을 먼저 합쳐준 후 가운데 값은 모든 값을 탐색하면서 최소값을 갱신해나간다.
이렇게 했을 때 최악의 연산 횟수는 5000*2500이므로 시간초과는 나오지 않을듯.

값들을 입력받아서 정렬한다.
맨 양쪽값을 먼저 합쳐주고
가운데 값은 왼쪽값 +1인덱스부터 끝까지 순회하는데,
만약 합계가 0에 가까워지고 있지 않다면
    종료시켜주고
아니라면
    다음 인덱스를 순회한다.
양쪽값의 합계가 0보다 크다면
    오른쪽값을 왼쪽으로 한칸 옮기고
아니라면
    왼쪽값을 오른쪽으로 한칸 옮긴다.
양쪽의 값을 계속 가운데로 옮겨가고,
더이상 탐색할 수 없다면 종료시켜준다.


!! 4% 틀렸습니다 !!
아 이거 long형 변수로 선언해야함
10억이 3개있으면 오버플로우 발생하므로
근데 이거때매 틀린건 아닌듯;

!! 10연속 2% 틀렸습니다 !!
양쪽에 해놓고 mid를 탐색하는 방법 자체가 잘못됐는듯?
그냥 왼쪽 커서를 기준으로 그다음인덱스를 le, 오른쪽끝인덱스를 ri라고 하고


 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        long[] three = new long[3];
        long[] arr = new long[size];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        long minDist = Long.MAX_VALUE;

        for (int i = 0; i < size-1; i++) {
            int le = i+1;
            int ri = size-1;

            while (le < ri) {
                long curSum = arr[i] + arr[le] + arr[ri];
                long curDist = Math.abs(curSum);

                if (curDist < minDist) {
                    minDist = curDist;
                    three[0] = arr[i];
                    three[1] = arr[le];
                    three[2] = arr[ri];
                }

                if (curSum > 0) {
                    ri--;
                } else {
                    le++;
                }
            }
        }
        Arrays.sort(three);
        System.out.printf("%d %d %d", three[0], three[1], three[2]);

    }
}

/*
6
-100 1 2 3 4 100

6
-100 1 2 3 4 5

6
1 2 3 4 5 6

6
-6 -5 -4 -3 -2 -1

3
999999998 999999999 1000000000

7
-1000000000 1 2 3 4 999999999 1000000000

4
-5 2 3 4
ans : -5 2 3
out : -5 2 4
ri가 줄어들어야하는데, le가 늘어나서 탐색을 하지 못함..

5
-4 -2 -1 2 3

6
-104336608 239510944 997686289 627058077 722156401 -942278495

7
912022275 -968846127 195376182 -212509759 589371385 817446019 -59843192

8
-6 -3 -2 -1 3 4 5 15

 */