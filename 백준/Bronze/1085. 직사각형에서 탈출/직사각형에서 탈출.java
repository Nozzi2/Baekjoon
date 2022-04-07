import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int numX = Integer.parseInt(s[0]);
		int numY = Integer.parseInt(s[1]);
		int numW = Integer.parseInt(s[2]);
		int numH = Integer.parseInt(s[3]);
		
		int result = 0;
		if(numX>=numW && numY<=numH) {
			result = numX-numW;
		} else if (numX<=numW && numY>=numH) {
			result = numY-numH;
		} else if (numX>numW && numY>numH) {
			result = (int)Math.pow((numX-numW), 2) + (int)Math.pow((numY-numH), 2);
			result = (int)Math.sqrt(result);
		} else if (numX<numW && numY<numH) {
			int[] min = new int[4];
			min[0] = numX;
			min[1] = numY;
			min[2] = numW-numX;
			min[3] = numH-numY;
			Arrays.sort(min);
			result = min[0];
		}
		System.out.println(result);
	}
}