import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] map;
	
	static boolean[] selected;
	static int[] combis;
	
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		selected = new boolean[N];
		combis = new int[N/2];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		combination(0,0);
		
		System.out.println(min);
	}

	private static void combination(int cnt, int start) {
		if(cnt==N/2) {
			for(int i=0, endi=combis.length; i<endi; i++) {
				selected[ combis[i] ] = true;
			}
			
			int sum = calcSum();
			min = Math.min(min, sum);
			
			//다쓴 selected배열 초기화
			Arrays.fill(selected, false);
			return;
		}
		
		for(int i=start; i<N; i++) {
			combis[cnt] = i;
			if(combis[0] != 0) return;
			combination(cnt+1, i+1);
		}
		
	}

	private static int calcSum() {
		int sumA=0;
		int sumB=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				if(selected[i] && selected[j]) {
					sumA += map[i][j];
				}
				if(!selected[i] && !selected[j]) {
					sumB += map[i][j];
				}
			}
		}
		
		return Math.abs(sumA-sumB);
	}

}