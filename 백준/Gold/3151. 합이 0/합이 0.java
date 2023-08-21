import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
학생들의 코딩실력값 리스트가 주어졌을 때
세명의 팀을 결성해서 코딩실력값의 총합이 0이 되는 팀이 몇개가 나오는지 구하는 문제이다.

전형적인 투포인터 문제이다.
입력받은 배열을 정렬해주고
첫번째 배열부터 선택한 후
    2번쨰 배열부터 끝까지 투포인터를 사용해서 탐색을 진행한다.
    만약 0인 학생이 나온다면
        카운트++
        만약 le 또는 ri가 같은 값이라면 //이렇게 해줘야 0인 모든 경우를 구할 수 있음
            le ++ || ri --
    나오지 않는다면
        합계가 0보다 크면
            le++
        작으면
            ri--
이렇게 첫번째 배열부터 끝까지 진행
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

        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));

        long count = 0;
        for (int i = 0; i < size - 2; i++) {
            int cur = arr[i];
            int left = i + 1;
            int right = size - 1;

            while (left < right) {
                int sum = cur + arr[left] + arr[right];

//                System.out.printf("%d, %d, %d = %d ", cur, arr[left], arr[right], sum);

                if (sum == 0) {
//                    System.out.println("<<< 합이 0!!");

                    if (arr[left] == arr[right]) {
                        long sameCount = right - left + 1;
                        count += (sameCount * (sameCount - 1)) / 2;
//                        System.out.println("  + "+(sameCount*(sameCount-1)/2)+"한번에 더하기! ");
                        break;
                    }

                    //left와 right에 같은 숫자가 몇개있는지 검사하여 count에 더하기
                    int leftCount = 1;
                    while (left < right) {
                        if (arr[left] != arr[left + 1]) {
                            break;
                        }
                        leftCount++;
                        left++;
                    }

                    int rightCount = 1;
                    while (left < right) {
                        if (arr[right] != arr[right - 1]) {
                            break;
                        }
                        rightCount++;
                        right--;
                    }

                    count += (long) leftCount * rightCount;
//                    System.out.printf("lc+rc = %d * %d / l,r = %d, %d\n", leftCount, rightCount, left, right);
                    left++;

                } else if (sum > 0) {
                    right--;
//                    System.out.println();
                } else {
                    left++;
//                    System.out.println();
                }
            }
        }

        System.out.println(count);

    }
}

/*
10
2 -5 2 3 -4 7 -4 0 1 -6

13
-3 -3 -2 -2 -1 -1 0 0 1 2 2 3 3

6
-1 -1 0 0 1 1

7
-1 -1 0 0 1 1 1

-1, -1, 1 = -1
-1, 0, 1 = 0 <<< 합이 0!!
lc+rc = 2 * 3 / l,r = 3, 4

-1, 0, 1 = 0 <<< 합이 0!!
lc+rc = 2 * 3 / l,r = 3, 4

0, 0, 1 = 1
0, 0, 1 = 1
0, 0, 1 = 1
0, 1, 1 = 2
0, 1, 1 = 2
1, 1, 1 = 3
12


2 -5 2 3 -4 7 -4 0 1 -6
[-6, -5, -4, -4, 0, 1, 2, 2, 3, 7]
-6, -5, 7 = -4
-5, 2, 3 = 0 <<< 합이 0!!
lc+rc = 2 * 1 / l,r = 7, 8
-4, -4, 7 = -1
-4, 0, 7 = 3
-4, 0, 3 = -1
-4, 1, 3 = 0 <<< 합이 0!!
lc+rc = 1 * 1 / l,r = 5, 8
-4, 2, 3 = 1
-4, 2, 2 = 0 <<< 합이 0!!
lc+rc = 2 * 1 / l,r = 7, 7
-4, 0, 7 = 3
-4, 0, 3 = -1
-4, 1, 3 = 0 <<< 합이 0!!
lc+rc = 1 * 1 / l,r = 5, 8
-4, 2, 3 = 1
-4, 2, 2 = 0 <<< 합이 0!!
lc+rc = 2 * 1 / l,r = 7, 7
0, 1, 7 = 8
0, 1, 3 = 4
0, 1, 2 = 3
0, 1, 2 = 3
1, 2, 7 = 10
1, 2, 3 = 6
1, 2, 2 = 5
2, 2, 7 = 11
2, 2, 3 = 7
2, 3, 7 = 12

[-6, -5, -4, -4, 0, 1, 2, 2, 2, 3]

7
-2 -1 0 1 1 1 1

10
2 -5 2 3 -4 2 -4 0 1 -6

16
-3 -3 -2 -2 -1 -1 0 0 0 0 0 1 2 2 3 3

6
0 0 0 0 0 0


*/