import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
두 배열이 주어졌을 때 두 배열의 연속된 부분집합의 합이 주어진 값과 같게 나오는 경우의 수를 구하는 문제이다.

문제의 메모리 제한을 보면 64MB로 특이한데,
그 이유는 두 배열의 합이 나올 수 있는 경우의 수를 서로 곱해주면 답을 쉽게 구할 수 있는데,
이렇게 하려면 값의 범위인 -10억 부터 10억까지 모든 값에 대한 배열을 선언해줘야한다.
이렇게 되면 메모리 제한을 초과하기 떄문에 이런식으로 접근하지말라고 제한을 둔 것 같다.

그러면 어떻게 할지 고민했을 떄
A,B배열 모두 나올 수 있는 값들의 경우의 수별로 해당 값을 배열에 저장한다.
A [1,3,1,2]
B [1,3,2]
라고 주어졌을 떄
A에서
    값을 1개 쓸 떄 [1,3,1,2]
    값을 2개 쓸 때 [4,4,3]
    값을 3개 쓸 떄 [5,6]
    값을 4개 쓸 떄 [7]
이렇게 모든 경우에 대한 값을 구하고
문제에서 요구하는 값의 합인 5를 넘지 않는 것들만 모아서 정렬한다.
[1,3,1,2,4,4,3] -> [1,1,2,3,3,4,4]
이렇게 하나의 배열로 구하고, B도 마찬가지로 이런식으로 구한다.
    * 근데 여기서 64MB를 초과하지 않을까 하는 염려가 있는데,
    배열의 크기가 1000이므로 최대로 나올 수 있는 새로 구하는 배열의 크기는
    1001*500 = 500,000이다.
    배열이 두개이므로 1,000,000이고, 용량으로 계산하면
    4MB정도 되므로 메모리 초과는 나오지 않을 것임.

그런다음 A배열에서 첫번쨰 값을 기준으로 하여
해당 값을 upperbound해서 A배열에 해당 값이 몇개 있는지 계산하고,
B배열에서 해당 값과 합쳤을 때 목표값과 같은 값이 몇개있는지
lower bound, upper bound를 통해서 시작인덱스, 끝인덱스를 계산한다.
끝 - 시작 으로 해당 값의 갯수를 구하여 A 배열의 값 갯수, B 배열의 값 갯수를 곱해주어 결과로 출력하면 됨.

 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());

        int sizeA = Integer.parseInt(br.readLine());
        int[] arrA = new int[sizeA];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < sizeA; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        int sizeB = Integer.parseInt(br.readLine());
        int[] arrB = new int[sizeB];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < sizeB; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        int[][] prefixSumA = new int[sizeA][sizeA];
        List<Integer> listA = new ArrayList<>();
        for (int i = 0; i < sizeA; i++) { //누적합 초기값 저장
            prefixSumA[0][i] = arrA[i];
            listA.add(arrA[i]);
        }
        for (int i = 1; i < sizeA; i++) {
            for (int j = 0; j < sizeA - i; j++) { //(직전값 + 그 다음값)으로 갱신
                prefixSumA[i][j] = prefixSumA[i - 1][j] + arrA[j+i];
                listA.add(prefixSumA[i][j]);
            }
        }

        int[][] prefixSumB = new int[sizeB][sizeB];
        List<Integer> listB = new ArrayList<>();
        for (int i = 0; i < sizeB; i++) { //누적합 초기값 저장
            prefixSumB[0][i] = arrB[i];
            listB.add(prefixSumB[0][i]);
        }
        for (int i = 1; i < sizeB; i++) {
            for (int j = 0; j < sizeB - i; j++) { //(직전값 + 그 다음값)으로 갱신
                prefixSumB[i][j] = prefixSumB[i - 1][j] + arrB[j+i];
                listB.add(prefixSumB[i][j]);
            }
        }

        Collections.sort(listA);
        Collections.sort(listB);

        long totalCount = 0; //모두 0일떄 넘는다는데 이건 쫌..
        int indexA = 0;
        while (indexA < listA.size()) {
            int curNum = listA.get(indexA); //현재 탐색할 값
            int upperA = binarySearchUpperBound(listA, curNum); //현재 탐색할 값의 다음값이 저장되어있는 첫번쨰 인덱스
            int countA = upperA - indexA;

            int lowerB = binarySearchLowerBound(listB, target - curNum);
            int upperB = binarySearchUpperBound(listB, target - curNum);
            int countB = upperB - lowerB;

            totalCount += (long) countA * (long) countB;
            indexA = upperA; //다음 탐색할 값이 들어있는 인덱스
        }

        System.out.println(totalCount);
    }

    static int binarySearchLowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    static int binarySearchUpperBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}

/*
5
4
1 3 1 2
3
1 3 2

A
[1, 3, 1, 2]
[4, 4, 3, 0]
[5, 6, 0, 0]
[7, 0, 0, 0]

B
[1, 3, 2]
[4, 5, 0]
[6, 0, 0]

A = [1, 1, 2, 3, 3, 4, 4]
B = [1, 2, 3, 4]

2
1
1
1
1

5
5
1 1 1 1 1
5
1 1 1 1 1

-5
5
1 1 1 1 1
5
1 1 1 1 1

10
5
1 1 1 1 1
5
1 1 1 1 1

0
2
0 0
4
0 0 0 0

A : [0, 0]
B : [0, 0, 0, 0]
//여기서 반례 발생
//A의 부분 집합은 0, 0, 0+0 이렇게 세개가 나와야하고 B도 마찬가지임.

-5
2
-3 -4
4
-2 -3 -1 -4
B
[-2, -3, -1, -4]
[-5, -4, -5, 0]
[-6, -8, 0, 0]
[-10, 0, 0, 0]
A : [-7, -4, -3]
B : [-10, -8, -6, -4, -3, -2, -1]

-5
2
-3 -4
4
-2 0 -1 1
 */