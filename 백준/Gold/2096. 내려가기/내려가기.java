import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        int minVal, maxVal;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine()); //줄 수 입력
        StringTokenizer st;
        int[] input = new int[3];
        int[][] minArr = new int[2][3];
        int[][] maxArr = new int[2][3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            int val = Integer.parseInt(st.nextToken());
            input[i] = minArr[0][i] = maxArr[0][i] = val;
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                input[j] = Integer.parseInt(st.nextToken());
            }

            //0번째 값의 최소값 = 이전 최소값의 0번째값 or 1번째값 + 0번째 값
            minArr[i%2][0] = Math.min(minArr[(i-1)%2][0],minArr[(i-1)%2][1])+input[0];
            minArr[i%2][2] = Math.min(minArr[(i-1)%2][2],minArr[(i-1)%2][1])+input[2];
            minArr[i%2][1] = Math.min(Math.min(minArr[(i-1)%2][0],minArr[(i-1)%2][1]),minArr[(i-1)%2][2])+input[1];
            
            maxArr[i%2][0] = Math.max(maxArr[(i-1)%2][0],maxArr[(i-1)%2][1])+input[0];
            maxArr[i%2][2] = Math.max(maxArr[(i-1)%2][2],maxArr[(i-1)%2][1])+input[2];
            maxArr[i%2][1] = Math.max(Math.max(maxArr[(i-1)%2][0],maxArr[(i-1)%2][1]),maxArr[(i-1)%2][2])+input[1];
        }
        
        int tmp = (N-1)%2;
        minVal = Math.min(Math.min(minArr[tmp][0],minArr[tmp][1]),minArr[tmp][2]);
        maxVal = Math.max(Math.max(maxArr[tmp][0],maxArr[tmp][1]),maxArr[tmp][2]);

        System.out.printf("%d %d",maxVal, minVal);

    }


}