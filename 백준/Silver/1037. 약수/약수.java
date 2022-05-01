import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		
		int[] arr = new int[testCase];
		for(int i=0; i<testCase; i++) {
			arr[i] = Integer.parseInt(s[i]);
		}
		Arrays.sort(arr);
		if(arr.length == 1) {
			System.out.println(arr[0]*arr[0]);
		} else {
			System.out.println(arr[0]*arr[arr.length-1]);
		}
	}
}