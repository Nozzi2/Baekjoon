import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] memo = new int[n+1];
		
		for(int i=2; i<=n; i++) {
			if(i%6==0) {
				memo[i] = Math.min(Math.min(memo[i-1]+1, memo[i/3]+1), memo[i/2]+1);
			} else if(i%3==0) {
				memo[i] = Math.min(memo[i-1]+1, memo[i/3]+1);
			} else if(i%2==0) {
				memo[i] = Math.min(memo[i-1]+1, memo[i/2]+1);
			} else {
				memo[i] = memo[i-1]+1;
			}
		}
		
		System.out.println(memo[n]);
	}

}