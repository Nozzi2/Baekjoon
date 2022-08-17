import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] inputs;
	static int output;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int T=1, endT=TC; T<=endT; T++) {
			N = Integer.parseInt(br.readLine());
			inputs = new int[N];
			output=0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0, endi=N; i<endi; i++) {
				inputs[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(inputs);
			
			do {
				recursion(0,0,0);
			}while(np(inputs));
			
			bw.write("#"+T+" "+output+"\n");
		}
		bw.flush();
		
	}

	private static void recursion(int left, int right, int cnt) {
		if(cnt==N) {
			output++;
			return;
		}
		if(right+inputs[cnt] <= left) {
			recursion(left, right+inputs[cnt], cnt+1);
		}
		recursion(left+inputs[cnt], right, cnt+1);
	}

	private static boolean np(int[] arr) {
		int i=N-1;
		int j=N-1;
		int k=N-1;
		
		while(i>0 && arr[i-1]>=arr[i]) {
			i--;
		}
		
		if(i==0) return false;
		
		while(arr[i-1]>=arr[j]) {
			j--;
		}
		
		swap(arr, i-1, j);
		
		while(i<k) {
			swap(arr, i++, k--);
		}
		
		return true;
	}

	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;		
	}
}