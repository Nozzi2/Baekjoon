import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int R;
	static int[] numbers;
	static StringBuilder sb;
	
	static void printArr(int[] arr) {
		
		
		for(int i=0; i<arr.length; i++) {
			sb.append(arr[i]+" ");
		}
		sb.append("\n");
	}
	
	static void permutation(int cnt) {
		if(cnt==R) {
			printArr(numbers);
			return;
		}
		
		for(int i=1; i<=N; i++) {
			numbers[cnt] = i;
			permutation(cnt+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		//long start = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		numbers = new int[R];
		
		permutation(0);
		System.out.println(sb);
		//long end = System.currentTimeMillis();
		//System.out.println("걸린 시간(ms) : "+(end-start));
	}

}