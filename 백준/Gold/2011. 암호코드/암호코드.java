import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    /*
    A=1, B=2, ... , Z=26
    BEAN을 암호화하면 25114가 나오는데
    BEAN 뿐만 아니라 BEAAD, YAAD 와 같은 방법으로 해석할 수 있다.
    BEAN의 경우 이러한 해석할수 있는 방법은 6개인데,
    주어진 숫자에서 해석할 수 있는 방법의 수를 구하면 된다.

    첫번째 숫자부터 끝까지의 숫자를 재귀로 호출해서
    모든 경우를 구해주면 될듯?ㅋㅋ
    -> 시간초과.. 경우의 수가 너무너무너무*100 많음
    문제에서 경우의수를 10만으로 나누기 계산할 때부터 재귀로 접근하면 안된다는 것을 알았어야한다..

    중복되는 경우가 많기 때문에 DP로 풀어야만 함.
    ... 도저히 점화식이 생각이 안나서 블로그 보고 풀었음.
    https://jgeun97.tistory.com/221


     */
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int size = input.length();
        int[] dp = new int[size + 1];

        dp[0] = 1; //공집합의 경우. 딱 1개뿐.

        for (int i = 1; i <= size; i++) {
            int cur = input.charAt(i-1)-'0'; //현재 숫자는 반드시 1~9의 값이여야함. 0일 경우는 아래에서 카운트됨
            if (cur >= 1 && cur <= 9) {
                dp[i] += dp[i-1]; //이 경우는 앞번호서 그냥 뒤에 따로 숫자를 붙힌 경우
                dp[i]%=1000000;
            }

            if(i==1) continue;

            int prev = input.charAt(i-2)-'0';

            if(prev == 0) continue;

            int value = prev * 10 + cur;

            if (value >= 10 && value <= 26) { //이 경우는 앞번호와 앞앞번호가 경우의 수로 더할 수 있는경우
                dp[i]+=dp[i-2];
                dp[i]%=1000000;
            }
        }

        System.out.println(dp[size]);
    }
}