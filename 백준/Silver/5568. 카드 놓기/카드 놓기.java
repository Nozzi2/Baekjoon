import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static int N;
	static int R;
	static String[] inputs;
	static String[] numbers;
	static boolean[] selected;
	static Set<String> set;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		set = new HashSet<String>();
		
		N = Integer.parseInt(br.readLine());
		R = Integer.parseInt(br.readLine());
		inputs = new String[N];
		selected = new boolean[N];
		numbers = new String[R];
		
		for(int i=0; i<N; i++) {
			inputs[i] = br.readLine();
		}
		
		comb(0);
		
		System.out.println(set.size());
	}

	private static void comb(int cnt) {
		if(cnt == R) {
			String s="";
			for(int i=0; i<R; i++) {
				s+=numbers[i];
			}
			set.add(s);
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(selected[i]) continue;
			numbers[cnt] = inputs[i];
			selected[i] = true;
			comb(cnt+1);
			selected[i] = false;
		}
	}
}