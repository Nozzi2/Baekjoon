import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] s = br.readLine().split(" ");
		int numS = Integer.parseInt(s[0]);
		int numE = Integer.parseInt(s[1]);
		
		ArrayList<Integer> nums = new ArrayList<Integer>();
		
		int max = numE;
		int cnt = 0;
		while(max>0) {
			cnt++;
			max = max-cnt;
		}
		
		for (int i = 1; i <= cnt; i++) {
			for(int a = 1; a <= i; a++) {
				nums.add(i);
			}
		}
		
		int sum = 0;
		for (int i = numS-1; i <= numE-1; i++) {
			sum = sum+nums.get(i);
		}
		
		System.out.println(+sum);
	}
}