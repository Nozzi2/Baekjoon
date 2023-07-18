import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
시작전 2+, 1301
 */
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> prique = new PriorityQueue<>();
        for (int i = 0; i < size; i++) {
            int input = Integer.parseInt(br.readLine());
            prique.offer(input);
        }

        int top15p = (int) Math.round((double) size * 0.15);

        for (int i = 0; i < top15p; i++) {
            prique.poll();
        }

        int sum =0;
        int count = size - (top15p * 2);
        for (int i = 0; i < count; i++) {
            sum += prique.poll();
        }

        int result = (int)Math.round((double) sum / (double) count);
        System.out.println(result);
    }
}