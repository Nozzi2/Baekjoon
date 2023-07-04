/*
문제 유형 자체가 LIS와 같은 개념을 사용해야하는 문제이다.
그런데 일단 내가 LIS를 잘 모르기 때문에 한번 개념을 숙지하고 나서 풀어야했었음.

문제에서 주어지는 값의 범위로만 봤을 땐, 1000이기 때문에 O(N^3) (=1_000_000_000) 까지도 풀어낼 수 있음.

특정 인덱스가 최댓값이라고 가정한 바이토닉 수열이라고 가정했을 때
해당 인덱스의 LIS와 LDS를 합쳐준다면, 각 인덱스별로 바이토닉 수열의 길이가 나오게 된다.
그중 최댓값을 출력하면 그것이 가장 긴 바이토닉 부분수열의 길이가 된다.


 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[] arr = new int[size];
        int[] dpInc = new int[size]; //특정 인덱스의 증가하는 배열DP
        int[] dpDec = new int[size]; //특정 인덱스의 감소하는 배열DP
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //인덱스의 LIS 구하기
        dpInc[0]=1;
        for (int i = 1; i < size; i++) {
            dpInc[i]=1;

            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dpInc[i] = Math.max(dpInc[i], dpInc[j] + 1);
                }
            }
        }


        //인덱스의 LDS구하기
        dpDec[size-1]=1;
        for (int i = size-2; i >=0; i--) {
            dpDec[i]=1;

            for (int j = size-1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    dpDec[i] = Math.max(dpDec[i], dpDec[j] + 1);
                }
            }
        }


        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            int val = dpInc[i]+dpDec[i];
            max = Math.max(max, val);
        }

        System.out.println(--max); //123 321이렇게 3이 중복되게 들어간 길이이므로 1 빼줘야함.

    }
}

/*
10
1 5 2 1 4 3 4 5 2 1

1 2 2 1 3 3 4 5 2 1 > dpInc 배열
1 5 2 1 4 3 3 3 2 1 > dpDec 배열
 */