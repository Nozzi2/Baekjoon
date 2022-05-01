import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		long input;
		long onlyOne;
		int count;
		
		while((s = br.readLine()) != null) {
			onlyOne=1;
			count=1;
			input = Long.parseLong(s);
			while(true) {
				//System.out.print(onlyOne+" % "+input);
				if(onlyOne%input==0) {
					//System.out.println(" = 0");
					System.out.println(count);
					break;
				}
				onlyOne = onlyOne % input;
				//System.out.println("1만 있는 수 : "+onlyOne);
				onlyOne = (onlyOne*10)+1;
				count++;
			}
		}
	}
}