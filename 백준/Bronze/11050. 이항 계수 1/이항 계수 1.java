import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static int factorial(int a) {
		if(a==0) {
			return 1;
		}
		int cnt = a-1;
		int result = a;
		for(int i = 0; i < a-2; i++) {
			result = result*cnt;
			cnt--;
		}
		
		return result;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub.
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int num1 = Integer.parseInt(s[0]);
		int num2 = Integer.parseInt(s[1]);
		
		System.out.println(factorial(num1)/(factorial(num2)*factorial(num1-num2)));

	}

}