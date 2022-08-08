import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int R=2;
	static int max;
	static int limit;
	static int[] inputs;
	static int[] numbers;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("res/9229.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int T=1; T<=TC; T++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			limit = Integer.parseInt(st.nextToken());
			numbers = new int[R];
			inputs = new int[N];
			max = -1;
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				inputs[i] = Integer.parseInt(st.nextToken());
			}// for i

			perm(0,0);
			bw.write("#"+T+" "+max+"\n");
		}//for T
		bw.flush();
	}//main method


	private static void perm(int cnt, int start) {
		if(cnt == R) {
			int tmp = numbers[0]+numbers[1];
			if(limit >= tmp && max < tmp) {
				max = tmp;
			}
			return;
		}
		
		for(int i=start; i<N; i++) {
			numbers[cnt] = inputs[i];
			perm(cnt+1, i+1);
		}
		
	}
}