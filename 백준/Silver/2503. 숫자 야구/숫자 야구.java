import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
배열을 선언하고, 나올 수 있는 범위인 123부터 999까지 돌면서
나올수 없는 숫자인 같은 숫자가 여러번 나오는 경우나, 0이 나오는 경우를 배제시킨다.

그리고 입력받은 질문의 갯수만큼 또 돌면서
S,B에 따라 나올 수 있는 숫자일 경우 true로 하고,
나올 수 없는 숫자일 경우 false로 처리한다.

123부터 999까지 돌리는 것이기때문에 브루트포스로 해도
입력이 최대 100번까지 밖에 안들어와서 시간이 터지진 않을듯


 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int questionCnt = Integer.parseInt(br.readLine());
        boolean[] availableNumbers = new boolean[1000];

        for (int i = 123; i <= 999; i++) {
            int n1 = i/100;
            int n2 = (i-(n1*100))/10;
            int n3 = i-(n1*100)-(n2*10);

            if(n2==0 || n3==0) continue;

            if(n1==n2 || n1==n3 || n2==n3) continue;

            availableNumbers[i] = true;
        }

        StringTokenizer st;
        for (int i = 0; i < questionCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int n1 = number/100;
            int n2 = (number-(n1*100))/10;
            int n3 = number-(n1*100)-(n2*10);

            for (int j = 123; j <= 999; j++) {
                if(!availableNumbers[j]) continue;

                int c1 = j/100;
                int c2 = (j-(c1*100))/10;
                int c3 = j-(c1*100)-(c2*10);

                int curS = 0;
                int curB = 0;

                if(n1==c1) curS++;
                else if(n1==c2 || n1==c3) curB++;
                if(n2==c2) curS++;
                else if(n2==c1 || n2==c3) curB++;
                if(n3==c3) curS++;
                else if(n3==c1 || n3==c2) curB++;

                if (s != curS || b != curB) {
                    availableNumbers[j] = false;
                }
            }
        }


        int output = 0;
        for (int i = 123; i <= 999; i++) {
            if (availableNumbers[i]) {
                output++;
//                System.out.println(i);
            }
        }
        System.out.println(output);
    }
}