import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        int[] cows = new int[26];
        final int INIT = -1;
        final int DONE = -99;
        Arrays.fill(cows, INIT);

        int totalCount = 0;
        for (int i = 0; i < 52; i++) {
//            System.out.println(Arrays.toString(cows));
            int idx = (int) (input[i] - 'A');

            if (cows[idx] == INIT) {
                cows[idx] = i;
                continue;
            }

            int count = 0;
            for (int j = 0; j < 26; j++) {
                if(cows[j]>cows[idx]){
                    count++;
                }
            }
            totalCount += count;
            cows[idx] = DONE;
        }
        System.out.println(totalCount);
    }
}

/*
ABCCABDDEEFFGGHHIIJJKKLLMMNNOOPPQQRRSSTTUUVVWWXXYYZZ
 */