import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static int N, R;
	static int[] numbers;
	static boolean[] selected;
	static int sumAll, sum2; //sumAll : 1~9 쌍둥이의 키 합, sum2 : 2개로 뽑은 난쟁이의 키 합
	static final int TARGET=100;
	static boolean isOut;
	
	static void combination(int cnt, int start) {
		if(cnt==R) {
			if(!isOut && sumAll-sum2 == TARGET) {
				for(int i=0; i<N; i++) {
					if(!selected[i]) {
						System.out.println(numbers[i]);
					}
				}
				isOut = true;
			}
			return;
		}
		
		for(int i=start; i<N; i++) {
			sum2 += numbers[i];
			selected[i] = true;
			combination(cnt+1,i+1);
			sum2 -= numbers[i];
			selected[i] = false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N=9;
		R=2;
		sumAll=0;
		sum2=0;
		numbers = new int[N];
		selected = new boolean[N];
		isOut = false;
		
		
		
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
			sumAll+=numbers[i];
		}
		Arrays.sort(numbers);
		
		combination(0,0);
		
		
	}
}