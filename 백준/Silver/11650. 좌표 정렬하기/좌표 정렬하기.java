import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		int[][] numbers = new int[testCase][2];
		String[] s;
		
		for (int i = 0; i < numbers.length; i++) {
			s = br.readLine().split(" ");
			numbers[i][0] = Integer.parseInt(s[0]);
			numbers[i][1] = Integer.parseInt(s[1]);
		}
		
		/*
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i][0]+", "+numbers[i][1]);
		}
		
		Arrays.sort(numbers, Comparator.comparingInt(o1 -> o1[0]));
		*/
		
		
		Arrays.sort(numbers, (o1, o2) -> {
			if(o1[0] == o2[0]) {
				return Integer.compare(o1[1],  o2[1]);
			} else {
				return Integer.compare(o1[0],  o2[0]);
			}
		});
		
		for (int i = 0; i < numbers.length; i++) {
			sb.append(numbers[i][0]+" "+numbers[i][1]).append('\n');
		}
		System.out.println(sb);
	}
}
