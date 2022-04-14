import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		boolean flag = true;
		int a = 2;
		int b = 0;
		int cnt = 0;
		int input = Integer.parseInt(br.readLine());
		
		while(cnt<input) {
			if(flag) {
				a--;
				b++;
				if(a==0 || b==0) {
					a++;
					flag = false;
				}
			} else {
				a++;
				b--;
				if(a==0 || b==0) {
					b++;
					flag = true;
				}
			}
			
			cnt++;
		}
		sb.append(a+"/"+b);
		System.out.println(sb);
	}
}
