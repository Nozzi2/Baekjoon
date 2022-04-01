import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int numBR = Integer.parseInt(br.readLine());
		int a = numBR;
		int b = numBR;
		
		for (int i = 0; i < numBR; i++) {
			for (a = 0; a < b; a++) {
				System.out.print("*");
			}
			b--;
			System.out.println("");
		}

	}

}