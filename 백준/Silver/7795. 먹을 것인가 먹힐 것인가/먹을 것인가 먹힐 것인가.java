/*
A,B를 모두 정렬한다음
A가 먹는 주체이기 때문에
A의 첫번째부터 순회를 하면서
    B의 첫번쨰부터 A의 첫번째와 비교해서
    A가 더 크다면
        A 카운트 배열에 1++ 해주고
        B 다음 번쨰를 탐색을 진행하고
    같거나 작다면
        A 다음 번쨰 탐색을 진행하고
        또 비교를 진행한다.
이렇게 모든 배열의 순회가 끝나면
A카운트 배열의 모든 값을 합산하여 출력

이렇게 하면 최대 20_000의 범위를 A,B배열 한번씩만 순회하기 떄문에
테스트케이스가 엄청 큰 것만 아니면 괜찮을듯
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int sizeA = Integer.parseInt(st.nextToken());
            int sizeB = Integer.parseInt(st.nextToken());
            int[] counts = new int[sizeA];  //쌍의 횟수를 저장할 배열
            int[] arrA = new int[sizeA];
            int[] arrB = new int[sizeB];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < sizeA; i++) {
                arrA[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < sizeB; i++) {
                arrB[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arrA);
            Arrays.sort(arrB);

            int idxB = 0;
            for (int a = 0; a < sizeA; a++) {
                if (a != 0) {
                    counts[a] = counts[a - 1];
                }

                while (idxB < sizeB && arrB[idxB] < arrA[a]) {
                    counts[a]++;
                    idxB++;
                }
            }

//            System.out.println("A : "+Arrays.toString(arrA));
//            System.out.println("B : "+Arrays.toString(arrB));
//            System.out.println(Arrays.toString(counts));
//            System.out.println();

            int sum = 0;
            for (int c : counts) {
                sum += c;
            }
            sb.append(sum).append("\n");
        }

        System.out.print(sb.toString());

    }
}

/*
2
5 3
8 1 7 3 1
3 6 1
3 4
2 13 7
103 11 290 215

 */