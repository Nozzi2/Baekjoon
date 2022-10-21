import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		final long MOD = 1000000000; 
		
		long[][] arr = new long[n+1][k+1];
		
		// arr[n][1]은 항상 1임
		for(int i=1; i<=n; i++) {
			arr[i][1] = 1;
		}
		// arr[0][1~k] 은 항상 1임
		for(int i=1; i<=k; i++) {
			arr[0][i] = 1;
		}
		if(k>1) {
			//k가 2 이상일때만 아래가 수행되야함
			for(int i=1; i<=n; i++) {
				for(int j=2; j<=k; j++) {
					for(int l=0; l<=i; l++) { 
						arr[i][j] += arr[l][j-1];
						arr[i][j] %= MOD;
					}
				}
			}
		}
//		printArr(arr, n, k);
//		System.out.println();
		System.out.println((arr[n][k]));
	}

	private static void printArr(long[][] arr, int n, int k) {
		for(int i=0; i<=n; i++) {
			System.out.println("N="+i);
			for(int j=0; j<=k; j++) {
				System.out.printf("  arr[%d][%d] : %d%n",i,j,arr[i][j]);
			}
		}
	}
	
	private static void printArr(BigInteger[][] big, int n, int k) {
		for(int i=0; i<=n; i++) {
			System.out.println("N="+i);
			for(int j=0; j<=k; j++) {
				System.out.printf("  big[%s][%s] : %d%n",i,j,big[i][j]);
			}
		}
	}
}