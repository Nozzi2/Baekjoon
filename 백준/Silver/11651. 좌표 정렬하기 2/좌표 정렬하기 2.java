import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		int numBR = Integer.parseInt(br.readLine());
		int[][] doubleNumbers = new int[numBR][2];
		String[] s;
		for(int i = 0; i<doubleNumbers.length;i++) {
			s = br.readLine().split(" ");
			doubleNumbers[i][1] = Integer.parseInt(s[0]);
			doubleNumbers[i][0] = Integer.parseInt(s[1]);
		}
		
		Arrays.sort(doubleNumbers, (o1, o2) -> {
			if(o1[0] == o2[0]) {
				return Integer.compare(o1[1],  o2[1]);
			} else {
				return Integer.compare(o1[0],  o2[0]);
			}
		});
		
		for(int i = 0; i<doubleNumbers.length;i++) {
			System.out.println(doubleNumbers[i][1]+" "+doubleNumbers[i][0]);
		}
	}

}