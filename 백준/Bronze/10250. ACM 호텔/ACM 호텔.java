import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		String[] s;
		
		int testCase  = Integer.parseInt(br.readLine());
		int numFlr;
		int numRm;
		int cnt;
		
		for (int i = 0; i < testCase; i++) {
			s = br.readLine().split(" ");
			numFlr = Integer.parseInt(s[0]);
			numRm = Integer.parseInt(s[1]);
			cnt = Integer.parseInt(s[2]);
			if((cnt/numFlr+1)<10) {
				if(cnt%numFlr==0) {
					//System.out.println("case1  "+(cnt/numFlr+1));
					System.out.println((numFlr)+"0"+(cnt/numFlr));
				} else {
					//System.out.println("case2  "+(cnt/numFlr+1));
					System.out.println((cnt%numFlr)+"0"+((cnt/numFlr)+1));
				}
				
			} else {
				if(cnt%numFlr==0) {
					//System.out.println("case3  "+(cnt/numFlr+1));
					if((cnt/numFlr)==9) {
						System.out.println((numFlr)+"0"+((cnt/numFlr)));
						
					} else {
						System.out.println((numFlr)+""+((cnt/numFlr)));
						
					}
				} else {
					//System.out.println("case4  "+(cnt/numFlr+1));
					System.out.println((cnt%numFlr)+""+((cnt/numFlr)+1));
				}
			}
		}
	}
}