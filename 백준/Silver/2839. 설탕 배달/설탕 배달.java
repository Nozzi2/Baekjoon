import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
설탕봉지 무게 3,5가 있는데,
입력받은 N의 값을 최소한의 설탕봉지 갯수를 구하는 문제이다.

당연히 5가 더 무게가 많이 나가기 떄문에 최대한 5를 많이 활용하고,
3으로 남은걸 처리하면 가장 좋을 것.

5로 전체 나눠주고 결과가 나오면 출력
결과가 안나오면 5 한봉지 덜쓰고 3으로 나눠서 결과가 나오면 출력
...

위 결과를 반복해서 결과가 나오면 출력하고, 안나오면 -1 출력하면 된다.


 */
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());

        int fiveBongi = input/5;
        int threeBongi = 0;
        int result = 0;
        for (int i = fiveBongi; i >= 0; i--) {
            fiveBongi = i;
            int namerge = input-(fiveBongi*5);
//            System.out.printf("i:%d, namerge:%d\n",i,namerge);
            if(namerge>0){
                if(namerge%3==0){
                    threeBongi = namerge/3;
                    result = fiveBongi+threeBongi;
                    break;
                }
            } else if(namerge==0){
                result = fiveBongi;
                break;
            }
        }

        System.out.println(result>0?result:-1);
    }
}