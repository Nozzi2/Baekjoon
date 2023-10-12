import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TEST_CASE = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int ballIdx = 1;
		for(int T=0; T<TEST_CASE; T++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(a==ballIdx) {
				ballIdx = b;
			} else if(b==ballIdx) {
				ballIdx = a;
			}
		}
		
		System.out.println(ballIdx);
	}

}
/*
4
3 1
2 3
3 1
3 2

*/