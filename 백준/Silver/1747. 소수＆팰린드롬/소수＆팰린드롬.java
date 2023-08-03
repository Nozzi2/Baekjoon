import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
먼저 소수배열을 먼저 구한다.
boolean배열을 선언하고 최대값이 1,000,000이니까
그것보다 큰 팰린드롬수는 1000001이라서
배열의 크기는 1000002로 선언해준다.

그다음 에라토스테네스의 체를 이용해서
소수를 구하고

boolean배열에서 주어진 N값보다 큰 값부터 탐색해서
해당 값이 팰린드롬이면 출력한다.
 */
public class Main {

    static final int MAX_VALUE = 1003003; //1000003

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(2);
            System.exit(0);
        }

        boolean[] isPrimeArr = new boolean[MAX_VALUE+1]; //false라면 소수임


        //2부터 제일 큰 값까지 소수 판별
        for (int i = 2; i*i <= MAX_VALUE; i++) {
            if (!isPrimeArr[i]) {
                for (int j = i * i; j <= MAX_VALUE; j += i) {
                    isPrimeArr[j] = true;
                }
            }
        }


        for (int i = N; i < MAX_VALUE; i++) {
            if(isPrimeArr[i]) continue;

            String forward = i+"";
            String reverse = new StringBuilder().append(forward).reverse().toString();

            if (forward.equals(reverse)) {
                System.out.println(forward);
                break;
            }
        }


    }
}