import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		if(n<3) {
			switch(n) {
			case 1:
				System.out.println(1);
				break;
			case 2:
				System.out.println(3);
				break;
			}
		} else {
			BigInteger[] memo = new BigInteger[n+1];
			memo[1] = BigInteger.valueOf(1);
			memo[2] = BigInteger.valueOf(3);
			for(int i=3; i<=n; i++) {
				memo[i] = memo[i-2].multiply(BigInteger.valueOf(2)).add(memo[i-1]);
			}
			
			System.out.println(memo[n].remainder(BigInteger.valueOf(10007)));
		}
	}
}