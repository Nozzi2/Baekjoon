import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] s;
		int numIn;
		int numOut;
		int peopleCnt=0;
		int max = 0;
		
		for (int i = 0; i < 9; i++) {
			s = br.readLine().split(" ");
			numOut = Integer.parseInt(s[0]);
			numIn = Integer.parseInt(s[1]);
			peopleCnt = peopleCnt +numIn-numOut;
			if(max < peopleCnt) {
				max = peopleCnt;
			}
		}
		sb.append(max).append('\n');
		System.out.println(sb);
	}
}