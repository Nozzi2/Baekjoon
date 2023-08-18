import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
음수부터 양수까지의 정수가 주어지고
2개의 값을 합쳤을 때 0에서 가장 가까운 두 숫자를 출력하는 문제이다.

세용액을 풀었을 때는 1개를 선정하고 투포인터로 결정했는데,
일단 투포인터로 접근하는 방법과
1개를 고정하고 이분탐색을 하는 방법이 있는데,
두개 다 구현해보고 싶다.

근데 일단 투포인터는 정답이 나오지 않을 수도 있지 않나 생각은 드는데
딱히 어떤 경우에 반례가 있는지는 생각이 나지 않는다.
그래서 일단 투포인터로 빠르게 구현하고
그 다음 이진탐색을 쓰는 방식으로 바꿔봐야겠다.

 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[] arr = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int minAbsSum = Math.abs(arr[0] + arr[size - 1]);
        int[] output = {0, size - 1};

        for (int i = 0; i < size - 1; i++) {
//            System.out.println("기준 값");
            int left = i+1;
            int right = size-1;
            while (left <= right) {
                int mid = (left + right) / 2;
                int sum = arr[i] + arr[mid];
//                System.out.printf("%d + %d = %d", arr[i], arr[mid], sum);
                int absSum = Math.abs(sum);
                if (absSum < minAbsSum) {
//                    System.out.print(" << 갱신");
                    output[0] = i;
                    output[1] = mid;
                    minAbsSum = absSum;
                }
//                System.out.println();

                if (sum > 0) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        System.out.println(arr[output[0]]+" "+arr[output[1]]);
    }
}

/*
5
-99 -2 -1 4 98

5
-10 -9 -8 50 60

6
-10 -8 -5 -3 -2 100

9
-100 -99 0 1 2 3 4 5 99
-100 -99 0 1 2 3 4 5 99
-100 + 5 = -95
-99 + 5 = -94  << 갱신
0 + 5 = 5  << 갱신
0 + 4 = 4  << 갱신
0 + 3 = 3  << 갱신
0 + 2 = 2  << 갱신
0 + 1 = 1  << 갱신
0 1

9
-120 -99 0 1 2 3 4 5 99
 */