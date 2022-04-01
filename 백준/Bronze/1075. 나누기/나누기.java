import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num1 = Integer.parseInt(br.readLine());
		num1 = num1-(num1%100);
		int num2 = Integer.parseInt(br.readLine());
		
		int divide = num1/num2;
		
		int result = num2*divide;
		
		
		if(num1 == result-(result%100)) {
			if(result%100<10) {
				System.out.println("0"+result%100);
			} else {
				System.out.println(result%100);
			}
		} else {
			divide++;
			result = num2*divide;
			if(num1 == result-(result%100)) {
				if(result%100<10) {
					System.out.println("0"+result%100);
				} else {
					System.out.println(result%100);
				}
			}
		}
	}
}