import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		String s = br.readLine();
		boolean one=false;
		int oneCnt=0;
		ArrayList<Character> chars = new ArrayList<Character>();
		char c=s.charAt(0);
		chars.add(c);
		
		for(int i=1; i<s.length();i++) {
			c= s.charAt(i);
			if(chars.get(chars.size()-1)!=c) { //이전숫자와 중복된 것 없애주기
				chars.add(c);
				if(one) {
					oneCnt++; //2번중 1번에 숫자가 1씩 올라감
				}
				one = !one;
			}
		}
		
		//1이든지 0이든지 문자열에 있는 갯수중에 적은 것 출력하면 됨.
		if(oneCnt > (chars.size()-oneCnt)) {
			System.out.println(oneCnt-1);
		} else {
			System.out.println((chars.size()-oneCnt)-1);
		}
	}
}