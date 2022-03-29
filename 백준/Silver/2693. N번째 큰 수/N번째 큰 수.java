import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase= Integer.parseInt(br.readLine());
		String[] s;
		
		for (int i = 0; i < testCase; i++) {
			ArrayList<Integer> nums = new ArrayList<Integer>();
			s = br.readLine().split(" ");
			for(int a = 0; a < 10; a++) {
				nums.add(Integer.parseInt(s[a]));
			}
			
			Collections.sort(nums);
			
			sb.append(nums.get(7));
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}