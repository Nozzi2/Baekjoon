import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> prique = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < T; i++) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0) {
                if (prique.size() == 0) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(prique.poll()).append("\n");
                }
                continue;
            }
            prique.add(input);
        }

        System.out.print(sb.toString());
    }
}