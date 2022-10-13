import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, K, P=1234567891;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		for(int T=1; T<=TC; T++) {
			st = new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			long r=nCr(N,K);
			sb.append("#"+T+" "+((r)%P)+"\n");
		}
		
		System.out.println(sb);
	}
	
	static long fact(long n) {
		long re=1L;
		for (int i = 1; i <= n; i++) {
			re*=i;
			re%=P;
		}
		return re%P;
	}
	static long nCr(long n, long k) {
		long re=1L;
		re*=fact(n);
		re%=P;
		re*=power(fact(n-k),P-2);
		re%=P;
		re*=power(fact(k),P-2);
		re%=P;
		return re;
	}
	static long power(long x, long y) {
		long re=1L;
		while(y>0) {
			if(y%2==1) {
				re*=x;
				re%=P;
			}
			x*=x;
			x%=P;
			y/=2;
		}
		return re%P;
	}
}