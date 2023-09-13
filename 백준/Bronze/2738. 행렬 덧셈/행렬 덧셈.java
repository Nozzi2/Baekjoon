import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sizeR = Integer.parseInt(st.nextToken());
        int sizeC = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[sizeR][sizeC];

        for (int i = 0; i < sizeR; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < sizeC; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sizeR; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < sizeC; j++) {
                int input = Integer.parseInt(st.nextToken());
                sb.append(matrix[i][j]+input).append(' ');
            }
            sb.append("\n");
        }

        System.out.print(sb.toString());
    }
}
/*
3 3
1 1 1
2 2 2
0 1 0
3 3 3
4 4 4
5 5 100
 */