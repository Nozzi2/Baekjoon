import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int reqCount = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] nujeokSum = new int[size+1];
        nujeokSum[0] = 0;
        for (int i = 1; i <= size; i++) {
            nujeokSum[i] = nujeokSum[i - 1] + Integer.parseInt(st.nextToken());
        }
//        System.out.println(Arrays.toString(nujeokSum));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < reqCount; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken());
            sb.append(nujeokSum[e] - nujeokSum[s]).append("\n");
        }

        System.out.print(sb.toString());
    }
}

/*
5 3
5 4 3 2 1
1 3
2 4
5 5
 */