import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
O(NlogN)으로만 풀어낼 수 있다.
오름차순을 만족하는 값만 저장하는 배열(dp)을 선언하고,
입력된 배열(arr)의 첫번째부터 끝까지 순회하면서
    arr[i] 값을 넣을 수 있는 인덱스를 Arrays.binarySearch를 통해서 받아서
    만약 넣을 수 있는 인덱스가 있다면
        해당 값을 dp에 저장한다.
    다음 arr[i+1]을 진행한다.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int size=0;

        for(int i=0; i<N; i++) {
//            System.out.print("기준 : "+arr[i]+" => ");
//            System.out.println(Arrays.toString(dp));

            int pos = Arrays.binarySearch(dp, 0, size, arr[i]); //해당 값보다 큰 첫번째 인덱스 * (-1) -1
//            System.out.println("  pos : "+pos);
            if(pos>=0) {
//                System.out.println("  최대값보다 작습니다.");
                continue; //현재 최대값보다 작은 경우
            }

            int insertIndex = Math.abs(pos)-1;
            dp[insertIndex] = arr[i];
//            System.out.println("    insertPos : "+insertIndex);

            if(insertIndex == size) {
                size++;
//                System.out.println("    이거 호출 되냐?");
            }
        }

//        System.out.println(Arrays.toString(dp));

        System.out.println(size);
    }
}

/*
6
10 20 10 30 20 50

6
1 1 1 1 1 1

 */