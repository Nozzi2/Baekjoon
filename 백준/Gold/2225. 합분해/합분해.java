import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int k = Integer.parseInt(s[1]);
		long[][] dp = new long[k+1][n+1];
		
		for(int i=0; i<dp.length; i++) {
			dp[i][0]=1;
		}
		for(int i=0; i<dp[1].length; i++) {
			dp[1][i]=1;
		}
		
		for (int i = 2; i <= k; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
				dp[i][j] %= 1000000000;
			}
		}
		
		System.out.println(dp[k][n]);
	}
}