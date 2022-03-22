import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int card = Integer.parseInt(s[0]);
		int blackjack = Integer.parseInt(s[1]);
		int max = 0;
		int num = 0;
		boolean brk = false;
		
		s = br.readLine().split(" ");
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i < card; i++) {
			nums.add(Integer.parseInt(s[i]));
		}
		
		for (int a = 0; a < card-2; a++) {
			for (int b = a+1; b < card-1; b++) {
				for (int c = b+1; c < card; c++) {
					num = nums.get(a)+nums.get(b)+nums.get(c);
					if(num<=blackjack && num>max) {
						max = num;
						if(max == blackjack) {
							brk = true;
						}
					}
					if(brk) break;
				}
				if(brk) break;
			}
			if(brk) break;
		}
		System.out.println(max);
	}
}