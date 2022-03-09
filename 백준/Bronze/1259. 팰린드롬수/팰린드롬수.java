import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*다좋다 같이 꺼꾸로해도 같은 단어나 숫자를 팰린드롬 이라고 함.
숫자를 입력받아서 팰린드롬 수 인지 알려주는 프로그램
계속 입력받아서 알려주고 입력값이 0이라면 프로그램 종료*/
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //선언
		String str = br.readLine();
		StringBuffer sb = new StringBuffer(str);
		String reverse = sb.reverse().toString();
		
		while(!str.equals("0")) {
			if(str.equals(reverse)) {
				System.out.println("yes");
				//System.out.println(sb);
			} else {
				System.out.println("no");
				//System.out.println(sb);
			}
			str = br.readLine();
			sb = new StringBuffer(str);
			reverse = sb.reverse().toString();
		}
	}
}