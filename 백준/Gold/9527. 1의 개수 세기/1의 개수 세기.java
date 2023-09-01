import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
A,B 숫자가 주어졌을 때
숫자를 이진수로 변환했을 때 1이 등장하는 횟수의 총합을 구해야한다.
(B까지의 1 등장횟수) - (A-1까지의 1 등장하는 횟수)

숫자를 쓰다가 규칙을 발견했는데,
n>0일 때, 2^n까지의 1등장횟수는 2^(n-1)*n+1이다.
그래서 공식을 대입해서보면
2^1 => n=1, 2^(1-1)*1+1 = 1*1+1 = 2
2^2 => n=2, 2^(2-1)*2+1 = 2*2+1 = 5
2^3 => n=3, 2^(3-1)*3+1 = 4*3+1 = 13
...

그럼 이제 2^n부터 구하려는 숫자 A까지의 합계를 구하려면 어떻게 해야할까
맨 앞에 1이 붙어있는 것만 생각하면 2^n을 구하는 방법은 동일하다.
그렇기 때문에 2^n부터 A까지의 값만큼만 더해주고
A-2^n에서의 최대 n을 또 찾아주면 된다.
이렇게 A'를 0이 될떄까지 구해주면 될듯?

1 << bit 와 같은 연산은 long일때 최대 62비트까지만 연산할 수 있다!

 */
public class Main {

    static long countNum1, countNum2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long num1 = Long.parseLong(st.nextToken());
        long num2 = Long.parseLong(st.nextToken());

        setOneCount(num1 - 1, 0, 1); // num1의 1갯수는 포함되어야 하므로
        setOneCount(num2, 0, 2);

        long result = countNum2 - countNum1;
        System.out.println(result);
    }

    static void setOneCount(long num, long count, int flag) {
        if (num == 0) {
            if (flag == 1) {
                countNum1 = count;
            } else {
                countNum2 = count;
            }
            return;
        }

        if (num <= 2) {
            setOneCount(0, count + num, flag);
            return;
        }

        int bit = 0;
        while (num / ((long) 1 << (bit + 1)) > 0) {
            bit++;
        }

        //n>1일때, 2^(n-1)*n+1
        long maxTwoSquareValue = (long) 1 << bit;
        long preCount = (long) Math.pow(2,bit-1)*bit + 1; //1부터 2^n까지의 합계

        long firstOneCount = num - maxTwoSquareValue; // 맨 앞에있는 1의 갯수만 2^n부터 현재 값까지 합친 것
        setOneCount(firstOneCount, count + preCount + firstOneCount, flag);




    }
}

/*
1 10000000000000000
10000000000000000
최대값임
 */