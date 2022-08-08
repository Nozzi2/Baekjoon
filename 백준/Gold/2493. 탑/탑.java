import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int index = 1;
		Deque<Integer[]> stk = new ArrayDeque<>();
		
		for(int i=0; i<N; i++) {
			int input = Integer.parseInt(st.nextToken());
			while(stk.size()!=0) {
				if(stk.peek()[1]>input) {
					bw.write(stk.peek()[0]+" ");
					break;
				} else {
					stk.pop();
				}
			}
			if(stk.size()==0) {
				bw.write(0+" ");
			}
			Integer[] arr = {index++, input};
			stk.push(arr);
		}
		bw.flush();

	}

}