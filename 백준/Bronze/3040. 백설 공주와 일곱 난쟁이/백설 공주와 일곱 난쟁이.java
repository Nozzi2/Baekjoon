import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int N=9;
	static int R=2;
	static int[] inputs= new int[N];
	static int[] numbers = new int[R];
	static int sum;
	static boolean isOut = false;
	static final int HEIGHT_SUM = 100;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		sum=0;
		
		for(int i=0; i<N; i++) {
			inputs[i] = Integer.parseInt(br.readLine());
			sum += inputs[i];
		}
		
		comb(0,0);
	}

	private static void comb(int cnt, int start) {
		if(cnt == R) {
			if(!isOut && sum-HEIGHT_SUM == inputs[numbers[0]]+inputs[numbers[1]]) {
				isOut = true;
				for(int i=0; i<N; i++) {
					if(i==numbers[0] || i==numbers[1]) {
						continue;
					}
					System.out.println(inputs[i]);
				}
			}
			return;
		}
		
		if(isOut) return;
		for(int i=start; i<N; i++) {
			numbers[cnt] = i;
			comb(cnt+1, i+1);
		}
	}

}