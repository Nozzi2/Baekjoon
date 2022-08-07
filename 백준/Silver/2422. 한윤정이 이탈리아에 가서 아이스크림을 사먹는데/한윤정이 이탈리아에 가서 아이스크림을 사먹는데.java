import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int R=3;
	static int[] numbers;
	static boolean[][] badComb;
	static int count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int TC = Integer.parseInt(st.nextToken());
		numbers = new int[R];
		badComb = new boolean[N+1][N+1];
		count=0;
		for(int T=0; T<TC; T++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(r>c) {
				badComb[c][r] = true;
			} else {
				badComb[r][c] = true;
			}
			
			
			
		}
		
		perm(0,1);
		
		System.out.println(count);
	}

	private static void perm(int cnt, int start) {
		if(cnt==R) {
			if(!badComb[ numbers[0] ][numbers[2]]) {
				count++;
				//System.out.println(Arrays.toString(numbers));
			}
			return;
		}
		
		for(int i=start; i<=N; i++) {
			if(cnt!=0 && badComb[ numbers[cnt-1] ][i]) continue;
			numbers[cnt] = i;
			perm(cnt+1, i+1);
		}
	}
}