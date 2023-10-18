import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int reqSize = Integer.parseInt(br.readLine());
        int[] cows = new int[11];
        final int INIT = -1;
        Arrays.fill(cows, INIT);

        StringTokenizer st;
        int count = 0;
        for (int i = 0; i < reqSize; i++) {
            st = new StringTokenizer(br.readLine());
            int cowIdx = Integer.parseInt(st.nextToken());
            int road = Integer.parseInt(st.nextToken());
            if (cows[cowIdx] == INIT) {
                cows[cowIdx] = road;
                continue;
            }
            if (cows[cowIdx] != road) {
                count++;
                cows[cowIdx] = road;
            }
        }

        System.out.println(count);
    }
}
/*
8
3 1
3 0
6 0
2 1
4 1
3 0
4 0
3 1

 */