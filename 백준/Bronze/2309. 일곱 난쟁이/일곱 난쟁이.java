import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int numBR;
		int sum = 0;
		ArrayList<Integer> nums9 = new ArrayList<Integer>();
		
		for (int i = 0; i < 9; i++) {
			numBR = Integer.parseInt(br.readLine());
			nums9.add(numBR);
			sum = sum+numBR;
		}
		
		Collections.sort(nums9);
		
		int sumMinusOne = 0;
		for (int i = 0; i < 9; i++) {
			sumMinusOne = sum - nums9.get(i) - 100;
			if(sumMinusOne > 0 && nums9.contains(sumMinusOne)) {
				nums9.remove(i);
				nums9.remove((int)nums9.indexOf(sumMinusOne));
				break;
			}
		}
		
		
		Iterator iter = nums9.iterator();
		while(iter.hasNext()){
			sb.append((int) iter.next()).append('\n');
		}
		sb.deleteCharAt(sb.lastIndexOf("\n"));
		System.out.println(sb);
	}
}