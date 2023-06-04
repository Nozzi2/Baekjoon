import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    /*
    문제에서 요구하는 비교횟수는 '최소'이다.
    10,30,20 과 같은 숫자를 '정렬'한 후
    앞에서부터 한개 빼고 (A)
    다음 하나 뺀 값 (B)과 더해주고 (C=A+B)
    계속 하나씩 빼서(B') 결과값에 합쳐주면 됨 (C=C+C+B)

    비교횟수의 숫자 범위가 조금 수상한데,
    카드뭉치 갯수 N은 최대 10만.
    카드뭉치의 카드장수는 최대 1000.
    둘이 곱하면 10억이지만,
    단순이 1000이 10만번이 나오는 것이 아니라
    합친것을 계속 [누적] 시키기때문에 분명 int의 범위인 10억을 초과할 것

    그렇기 떄문에 누적할 합계는 long으로 선언해야함
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());             //카드뭉치 갯수

        PriorityQueue<Long> priQue = new PriorityQueue<>();  //입력받은 숫자를 정렬하기 위한 큐
        for(int i=0; i<size; i++){
            Long input = Long.parseLong(br.readLine());
            priQue.offer(input);
        }
        long answer = 0;

        //큐가 1개 남을 때까지 반복 (2개 이상 빼야하므로)
        while (priQue.size()>1) {
            long a = priQue.poll();
            long b = priQue.poll();
            long c = a+b;
            priQue.offer(c);

            answer += c; //비교횟수 더하기
        }

        System.out.println(answer);
    }
}