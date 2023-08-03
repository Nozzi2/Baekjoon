import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
문자열을 입력받고 공백은 다없애준다음에

입력받는 것마다 stringbuilder로 뒤집고 확인하는 방법
과연 시간초과가 날지?
시간초과가 난다!
문자열 뒤집는건 생각보다 많은 시간이 소요되는듯?

길이가 3이상일 경우 → (시작점 + 1) ~ (끝 - 1) 인덱스 까지 펠린드롬 && 시작점의 값 = 끝의 값 이면 펠린드롬
1 2 1 3 1 2 1 → 위 두조건에 만족하므로 펠린드롬
1 2 1 3 1 2 1 -> 두 번째 조건을 만족하지 않으므로 펠린드롬이 아니다
1 2 1 3 1 2 1 -> 둘 다 만족
1 2 1 3 1 2 1 -> 두 번째 조건 만족 x
1 2 1 3 1 2 1 -> 둘 다 만족

(시작점 + 1) ~ (끝 - 1) 인덱스 까지 펠린드롬
위 팰린드롬 여부는 바텀업이기 때문에 이전에 이미 dp배열에 반영되어있음.

 */
public class Main {

    static final int MAX_VALUE = 2001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int arrSize = Integer.parseInt(br.readLine());
        boolean[][] dp = new boolean[arrSize+1][arrSize+1]; //시작부터 끝까지 팰린드롬인지 여부 저장하는 배열
        int[] arr = new int[arrSize+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= arrSize; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //1개는 무조건 팰린드롬임
        for (int i = 1; i <= arrSize; i++) {
            dp[i][i] = true;
        }

        //2개일때는 앞뒤 숫자만 같으면 펠린드롬임
        for (int i = 1; i <= arrSize - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                dp[i][i + 1] = true;
            }
        }

        //3개 이상일 때는
        //맨앞 맨뒤 숫자가 같고   ex) 1 ?? 1 , 3 ???? 3
        //앞+1 부터 뒤-1까지의 숫자들이 팰린드롬이라면  ex) ?? = 11, ???? = 1331
        //해당 숫자는 팰린드롬임
        for (int dist = 2; dist <= arrSize - 1; dist++) { //숫자 사이의 거리를 점점 늘려가면서
            for (int start = 1; start <= arrSize-dist; start++) {
                int end = start+dist;

                //맨 아래 주석에 for문 예제 써놓음

                if (arr[start] == arr[end] //맨앞, 맨뒤 숫자가 같고
                    && dp[start + 1][end - 1]) { //사이의 숫자가 팰린드롬이라면
                    dp[start][end] = true; //그 숫자는 펠린드롬이다.
                }
            }
        }

        int reqSize = Integer.parseInt(br.readLine());
        for (int i = 0; i < reqSize; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (dp[start][end]) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}

/*
start : 1, end : 3, dist : 2
start : 2, end : 4, dist : 2
start : 3, end : 5, dist : 2
start : 4, end : 6, dist : 2
start : 5, end : 7, dist : 2

start : 1, end : 4, dist : 3
start : 2, end : 5, dist : 3
start : 3, end : 6, dist : 3
start : 4, end : 7, dist : 3

start : 1, end : 5, dist : 4
start : 2, end : 6, dist : 4
start : 3, end : 7, dist : 4

start : 1, end : 6, dist : 5
start : 2, end : 7, dist : 5

start : 1, end : 7, dist : 6

 */