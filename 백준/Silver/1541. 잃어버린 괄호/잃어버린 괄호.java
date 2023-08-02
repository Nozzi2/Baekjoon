import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
값을 최소로 만들어야한다.
-가 나오면 그 다음 -가 나올때까지 모든 숫자를 빼줄 숫자에 더해주면 된다.

먼저 -로 숫자를 구분해준다.
구분한 문자열의 첫번째는 항상 +이므로 합계에 미리 더해준다.
    만약 안에 +가 있으면 계산해줘야한다.
2번째 문자열부터 마지막까지 해당 숫자의 합계를 구한다.
    만약 안에 +가 있으면 계산해줘야한다.
해당 숫자의 합계는 결과값에서 빼준다.
 */
public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "-");
        int result = getSum(st.nextToken()); //첫번째 값은 항상 양수

        //뺴줄 값들 계산하기
        while (st.hasMoreTokens()) {
            result -= getSum(st.nextToken());
        }

        System.out.println(result);
    }

    static int getSum(String input) {
        StringTokenizer st = new StringTokenizer(input, "+");
        int sum = 0;
        while (st.hasMoreTokens()) {
            sum += Integer.parseInt(st.nextToken());
        }

        return sum;
    }
}