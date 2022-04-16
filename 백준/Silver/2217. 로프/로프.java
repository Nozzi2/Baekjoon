import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		
		int num1 = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> nums = new ArrayList<Integer>();
		
		for (int i = 0; i < num1; i++) {
			nums.add(Integer.parseInt(br.readLine()));
		}
		
		Collections.sort(nums); //오름차순
		
		ArrayList<Integer> maxWeight = new ArrayList<Integer>();

		int max = 0;
		for (int i = 0; i < num1; i++) {
			maxWeight.add(nums.get(i)*(num1-i));
			if(max<maxWeight.get(i)) {
				max = maxWeight.get(i);
			}
		}
		
		System.out.println(max);
	}
}