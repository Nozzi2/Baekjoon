import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int R;
	static int[] numbers;
	static boolean[] selected;
	
	static void printArr(int[] arr) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<arr.length; i++) {
			sb.append(arr[i]+" ");
		}
		System.out.println(sb);
	}
	
	static void permutation(int cnt) {
		if(cnt==R) {
			printArr(numbers);
			return;
		}
		
		for(int i=1; i<=N; i++) {
			if(selected[i]) {
				continue;
			}
			numbers[cnt] = i;
			selected[i] = true;
			permutation(cnt+1);
			selected[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		numbers = new int[R];
		selected = new boolean[N+1];
		
		permutation(0);
	}

}