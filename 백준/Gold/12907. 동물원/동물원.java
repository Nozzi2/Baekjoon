import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
모든 동물은 토끼와 고양이만 있다.
토끼와 고양이는 자신보다 키가 큰 동족이 몇명인지 대답한다.
모든 동물의 대답만 주어졌을 때,
토끼와 고양이 조합의 갯수를 구하시오.

예시1.
5
0 1 2 3 4

0은 나보다 키큰 동족이 0명
1은 나보다 키큰 동족이 1명
...
4는 나보다 키큰 동족이 4명

나보다 키큰 동족이 4명이란 의미는 나포함 동족이 5명이란 것임
그러므로 5명 다 고양이(c)거나 토끼(r)임

0 1 2 3 4
c c c c c
r r r r r

예시2.
5
1 0 2 0 1

r r r c c
c c c r r

c c r r r
r r c c c

c r r c r
r c c r c

r c r r c
c r c c r

총 8개의 조합


먼저 2가지 동물 종류가 각각 몇마리 인지 계산한다.
    예시 2의 경우 동물1=2마리, 동물2=3마리
그리고 동물1,2의 숫자가 제대로 들어왔는지 파악한다.
(동물1이 동물2의 마릿수보다 작거나 같음)
    1 0 1은 제대로 들어오지 않았음
    0 0 1은 제대로 들어왔음 (동물1=1마리, 동물2=2마리)

    오름차순으로 정렬하고,
    0 0 1 1 2
    이런식으로 정렬이 되는지 확인한다.
    동물1의 모든 숫자는 2개씩 나와야하고,
    동물2는 동물1보다 1마리 많을떄부터 1개씩 순차적으로 나와야한다.
동물1,2의 마릿수에 따라서 조합을 계산한다.
    동물1=2마리, 동물2=3마리
    1 1 2 2 2
    c c r r r
    c r r c r
    겹치는 마릿수는 바꿔줄 수 있음.
        0,1마리는 모두 r, c가 서로 바꿔줄 수 있음
        2마리는 오직 r이거나 c만 올 수 있음.
    0마리 rc cr
    1마리 rc cr
    2마리 r c

    그러니까 두 동물의 마릿수가 동일한 부분까지 계속 *2해주면 될듯
        동물1=5마리, 동물2=7마리
        0,1,2,3,4마리 = 2^5
        6,7마리 = 2
        => 2^6 = 64

 */
public class Main {

    static int[] comb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[] arr = new int[size];
        comb = new int[2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
//        System.out.println("입력 후 정렬 : "+Arrays.toString(arr));


        if(check(arr)){
//            System.out.println("마릿수 : "+Arrays.toString(comb));
            System.out.println(comb[0]!=comb[1]?1<<(comb[0]+1):1<<comb[0]);
        } else {
            System.out.println(0);
        }
    }

    static boolean check(int[] arr) {
        if(arr[0] != 0) return false;

        /*
        동물1은 항상 적은 개체수를 가진 동물임
        0 0 1 1 2 => 동물1 개체수 2
        0 1 2 3 4 => 동물1 개체수 0
         */
        int tmp = 0;
        int tmpCnt = 0;
        for (int i = 1, endi = arr.length; i < endi; i+=2) { //동물1의 개체수 구하기
            if(tmpCnt == arr[i] && arr[i-1]==arr[i]){ //이전 인덱스와 같다면
                tmp = (i+1)/2; //동물1 개체수 갱신
                tmpCnt++;
            } else {
                break;
            }
        }

//        System.out.println(Arrays.toString(arr));


        //동물2의 마릿수가 순차적으로 증가하지 않는다면 false반환
        //0 0 1 1 2 3 4 => true
        //0 0 1 1 3 3 4 => false
        //0 0 1 1 1 4 4 => false
        for (int i = tmp>0?tmp*2-1:0, endi = arr.length-1; i < endi; i++) {
//            System.out.printf("%d, %d 비교 \n", arr[i], arr[i+1]);

            if(arr[i]+1 != arr[i+1]){
                return false;
            }
        }

        comb[0] = tmp;
        comb[1] = arr.length-tmp;

        return true;
    }
}

/*
7
1 2 3 0 2 1 0

7
1 4 3 0 2 1 0

2
1 0

7
1 4 1 0 2 1 0

3
1 2 0

1
0

7
0 0 1 2 2 3 4

7
0 0 1 1 3 4 5

3
1 0 0

2
0 0
out 4
ans 2
 */