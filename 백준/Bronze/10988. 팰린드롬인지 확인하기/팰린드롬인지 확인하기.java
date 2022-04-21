import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		String str = br.readLine();
		String reverseStr = "";
		
		for(int i=str.length()-1; i>=0;i--) {
			reverseStr = reverseStr+str.charAt(i);
		}
		
		if(str.equals(reverseStr)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

}