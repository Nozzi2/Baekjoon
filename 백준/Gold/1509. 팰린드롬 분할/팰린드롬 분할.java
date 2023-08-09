import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
주어진 문자열을 팰린드롬 부분집합으로 나눴을 떄 가장 적은 부분집합의 갯수를 구하는 문제임.

먼저 문자열의 길이가 2500이기 때문에 무조건 DP를 써야하고,
이전에 사용했었던 팰린드롬처럼 dp 2차원배열을 선언하여
s부터 e까지가 펠린드롬 여부인지를 저장하는 배열을 선언한다.

그런다음 해당 배열을 또 dp를 이용해서
해당 인덱스를 사용했을 때 몇번쨰 인덱스까지가 팰린드롬인지 여부를 DFS로 탐색하든 DP로 저장하든 해서
구하면 될듯

먼저 팰린드롬 배열을 만드는 방법
s부터 s까지는 항상 팰린드롬임. (S, E, A)
s부터 s+1까지는 두 알파벳만 같으면 팰린드롬임 (SS, EE, AA)
s부터 s+2까지는 s와 s+2가 같고, 그 사이 값이 팰린드롬이라면 팰린드롬임
    (S ? S, E ???? E)
s+2부터 s+size까지 계속 반복해주면서 팰린드롬 여부를 갱신해주면 된다!

그럼 팰린드롬 배열을 가지고 가장 적은 부분집합 갯수를 구해야한다.
범위가 2500까지이기 때문에 DFS는 좀 무리가 있는 것 같고,
DP를 써야되는데,

!!! 시간초과 !!!
최소 부분집합 갯수 구하는 부분이 3중 for문으로 되어있는데,
이부분에서 시간초과가 나는 것 같음

 */
public class Main {

    static final int INF = 987654321;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        int size = input.length;
        boolean[][] dpPelin = new boolean[size][size]; //s부터 e까지 문자열이 팰린드롬인지 저장할 배열
        int[] dpCount = new int[size+1];


        // S는 펠린드롬
        for (int i = 0; i < size; i++) {
            dpPelin[i][i] = true;
        }

        // SS는 펠린드롬
        for (int i = 0; i < size - 1; i++) {
            if(input[i]==input[i+1]){
                dpPelin[i][i+1] = true;
            }

        }

        // S ??? S는 ???가 펠린드롬이라면 펠린드롬
        for (int d = 2; d < size; d++) {
            for (int s = 0; s < size - d; s++) {
                int e = s+d;
                if (input[s] == input[e] && dpPelin[s + 1][e - 1]) { //첫문자와 끝문자가 같고, 그 사이 문자열이 팰린드롬이라면
                    dpPelin[s][e] = true; //팰린드롬임
                }
            }
        }

        //가장 적은 부분집합의 갯수를 구하는 DP
        Arrays.fill(dpCount,INF);
        dpCount[0] = 0;

        for (int e = 1; e <= size; e++) {
            for (int s = 1; s <= e; s++) {
                if (dpPelin[s-1][e-1]) {
                    dpCount[e] = Math.min(dpCount[e], dpCount[s - 1] + 1);
                }
            }
        }

        System.out.println(dpCount[size]);
    }
}

/*
ABCBABC

ABCBABB

ABBBABB

BBABBBA
 */