import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		int[] memo = new int[11];
		memo[1] = 1;
		memo[2] = 2;
		memo[3] = 4;
		
		for(int i=4; i<=10; i++) {
			memo[i] = memo[i-1]+memo[i-2]+memo[i-3];
		}
		for(int i=0; i<TC; i++) {
			int n = Integer.parseInt(br.readLine());
			System.out.println(memo[n]);
		}
	}
}