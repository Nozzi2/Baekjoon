import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = new int[21];
        for (int i = 1; i <= 20; i++) {
            arr[i] = i;
        }

        StringTokenizer st;
        for (int t = 0; t < 10; t++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            reverse(arr, start, end);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 20; i++) {
            sb.append(arr[i]).append(' ');

        }

        System.out.println(sb.toString());
    }

    static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int tmp = arr[start];

            arr[start] = arr[end];
            arr[end] = tmp;

            start++;
            end--;
        }
    }
}
/*
5 10
9 13
1 2
3 4
5 6
1 2
3 4
5 6
1 20
1 20

 */