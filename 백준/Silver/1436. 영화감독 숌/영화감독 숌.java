import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int targetIdx = Integer.parseInt(br.readLine());
		
		int idx=0;
		int num=0;
		
		while(idx < targetIdx) {
			if( Integer.toString(++num).contains("666") ) {
				idx++;
			}
		}
		
		System.out.println(num);
		
	}
	
}