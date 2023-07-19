import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
길이가 N인 수열에서
중복을 허용할 수 있는 K 값만큼 해서
가장 긴 부분 수열의 길이를 구하는 문제임
K=2라면
    1 1 이렇게 1이 2개까지라면 부분 수열을 허용하지만,
    1 1 1 이렇게 3개라면 부분수열을 허용하지 않는다.
    < 1 2 3 1 2 > 1

시간 복잡도를 계산하자면
N이 200_000, K가 100이다.
N을 순회할 때 O(N^2)보다 적게 해야 풀 수 있는 문제임.

처음부터 끝까지 순회하는데,
현재 순회하면서 숫자가 몇개씩 등장했는지 배열에 저장한다.
만약 등장 횟수 배열에 저장한 값이 K값을 넘어간다면
     앞번호를 하나씩 뺴주면서 K값이 넘어가지 않을 때까지 반복한다.
넘어가지 않는다면 계속 탐색을 진행한다.
탐색을 진행하면서 연속부분수열 크기를 갱신해주고
순회가 끝나면 연속부분수열크기의 최댓값을 출력한다.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int allowCount = Integer.parseInt(st.nextToken());

        int[] counts = new int[100001];
        int[] numbers = new int[size];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int maxSize = 0;
        while (end < size) {
            end++;
            int nextNumber = numbers[end - 1];
            counts[nextNumber]++;

            if (counts[nextNumber] > allowCount) { //허용횟수보다 더 많은 숫자가 들어있다면
                //앞에 있는 숫자부터 하나씩 줄여나간다.
                while (counts[nextNumber] > allowCount) {
                    int preNumber = numbers[start];
                    counts[preNumber]--;
                    start++;
                }
            } else {
                maxSize = Math.max(maxSize, end - start); //end가 늘어날 때만 최장 길이가 갱신됨
            }
        }

        System.out.println(maxSize);
    }
}

/*
for (int i = start; i < end; i++) {
    System.out.printf("%d ",numbers[i]);
}
System.out.println();

13 1
1 2 3 1 4 2 3 5 4 1 5 2 3
->
1
1 2
1 2 3
2 3 1 4
1 4 2 3 5
2 3 5 4 1
4 1 5 2
4 1 5 2 3
5

13 2
1 2 3 1 4 2 3 5 4 1 5 2 3
->
1
1 2
1 2 3
1 2 3 1
1 2 3 1 4
1 2 3 1 4 2
1 2 3 1 4 2 3
1 2 3 1 4 2 3 5
1 2 3 1 4 2 3 5 4
2 3 1 4 2 3 5 4 1 5
10


 */