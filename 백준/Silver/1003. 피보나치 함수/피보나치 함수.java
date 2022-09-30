import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		int[] memo = new int[41];
		memo[0] = 0;
		memo[1] = 1;
		int lastIdx = 2;
		for(int T=0; T<TC; T++) {
			int n = Integer.parseInt(br.readLine());
			for(int i=lastIdx; i<n+1; i++) {
				memo[i] = memo[i-1]+memo[i-2];
			}
			lastIdx = n>2 ? n-1: 2;
			
			int cnt1 = memo[n];
			int cnt0;
			if(n==0) {
				cnt0=1;
			} else if(n==1) {
				cnt0=0;
			} else {
				cnt0 = memo[n-1];
			}
			sb.append(cnt0).append(" ").append(cnt1).append("\n");
		}
		System.out.println(sb);
	}
	
}