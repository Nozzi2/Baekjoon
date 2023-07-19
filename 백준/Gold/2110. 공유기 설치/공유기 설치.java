import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
일직선 위에 집이 N개 있을 때 공유기를 C개 놓아서
공유기와 공유기 사이의 거리가 가장 가까운 경우의 최대 거리를 구하는 문제이다.

N개 입력받고, 정렬한다음
첫값과 마지막 값을 C로 나눠서 나올 수 있는 최대 거리를 구하고,
해당 거리만큼 첫값에서 떨어진 값에서 가장 가까운 집을 이분탐색으로 구한다.
안됨!
이렇게 하면 집이 한쪽에 몰린 경우를 고려할 수 없음.

인덱스 기준으로 공유기를 설치할 곳을 골라도 분명 반례가 있을텐데..

그럼 DP로 풀어야하나?
이것도 아닌거같음..

입력의 최대값은 C = 200_000, N = 200_000 이다.
그렇기 때문에 O(NlogN)으로 풀어야한다.

또 몰라서 검색해서 풀어따..

가장 인접한 공유기 간의 최대 거리를 구하는 문제이지만,
거리를 구하기 위해 집의 갯수를 이용하는 방법으로 풀어야 한다.
1부터 n까지 집이 있다면
    가장 인접한 공유기 사이의 거리가 1이라면 n개를 설치할 수 있다.
    가장 인접한 공유기 사이의 거리가 2라면 n/2개 설치할 수 있다.
    ...
위와 같이 거리를 늘려주면 공유기를 설치할 수 있는 집의 갯수가 줄어들게 되는데,
이런 방식으로 거리를 늘려가면서 계산하여
집의 갯수가 C개가 되는 경우의 거리를 출력해주면 된다.

단, 이렇게 1씩 늘려가는 것이 아니라 1~n 의 중앙부터 시작해서
이진탐색으로 조건을 만족하는 값까지 반복해준다면 O(NlogN)으로 풀어낼 수 있다.
 */
public class Main {

    static int targetCount;
    static int[] houses;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        targetCount = Integer.parseInt(st.nextToken());

        houses = new int[size];
        for (int i = 0; i < size; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        int lo = 1;
        int hi = ((houses[size - 1] - houses[0]) / (targetCount - 1)) + 1;

        //Upper bound
        while (lo < hi) {
            int mid = (lo + hi) / 2;

            if (getInstallCount(mid) < targetCount) { //설치할 수 있는 공유기 댓수가 더 적다면
                //거리가 너무 길어서 줄여줘야함
                hi = mid;       // |/////|-----|
            } else {
                //거리가 너무 짧아서 늘려줘야함
                lo = mid + 1;   // |-----|/////|
            }
        }

        System.out.println(lo-1); //Upper bound는 lo에서 -1해줘야함.
    }

    /**
     * @param dist 공유기 사이의 최소 거리
     * @return 최소 거리를 만족하여 공유기를 설치할 수 있는 집의 갯수
     */
    private static int getInstallCount(int dist) {
        int count = 1; //공유기를 설치한 집의 갯수 (첫번째 집은 무조건 설치하므로 초기값 1)
        int curLocate = houses[0];

        for (int i = 1, endi = houses.length; i < endi; i++) {
            int nextLocate = houses[i];
            if (nextLocate - curLocate < dist) continue; //현재공유기와 다음공유기 사이의 거리가 dist보다 크다면 다음 공유기로

            //설치할 수 있는 경우
            count++;
            curLocate = nextLocate; //다음 집과 비교하기 위해서 현재 공유기 위치 갱신

            if(count>targetCount) break; //만약 목표하는 공유기 설치 대수가 보다 더 크면 더 이상 연산할 필요 없음.
        }

        return count;
    }
}



/*
8 4
1 -----공유기
4
7 -----공유기
12
15 -----공유기
20
22 -----공유기
25
거리 [ 13 ]일때 공유기 [ 2 ]대 설치 가능
-------------*-----------
거리 [ 7 ]일때 공유기 [ 3 ]대 설치 가능
-------*-----------------
거리 [ 4 ]일때 공유기 [ 5 ]대 설치 가능
----*--------------------
거리 [ 6 ]일때 공유기 [ 4 ]대 설치 가능
------*------------------
6


5 3
1 ----공유기
2
4 ----공유기
8 ----공유기
9
거리 [ 5 ]일때 공유기 [ 2 ]대 설치 가능
-----*---
거리 [ 3 ]일때 공유기 [ 3 ]대 설치 가능
---*-----
거리 [ 4 ]일때 공유기 [ 2 ]대 설치 가능
----*----
3


8 5
1
4
7
12
15
20
22
25

2 2
3
4

=> 2??
1이 나와야함..

 */