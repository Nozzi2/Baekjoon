import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
		String[] str = br.readLine().split(" ");
		int jinsu = Integer.parseInt(str[1]);
		char[] chars = str[0].toCharArray();
		int sum=0;
		int num=0;
		int cnt=-1;
		
		for(int i=chars.length-1; i>=0; i--) {
			cnt++;
			if(chars[i]>='A') {
				num=(int)chars[i]+10-'A';
			} else {
				num=(int)chars[i]-48;
			}
			//System.out.println("1 - "+num);
			for(int j=0; j<cnt; j++) {
				num=num*jinsu;
			}
			//System.out.println("2 - "+num);
			sum+=num;
			//System.out.println("sum : "+sum);
		}
		System.out.println(sum);
	}
}