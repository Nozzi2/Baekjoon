import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		
		String s = br.readLine();
		String rs = "";
		ArrayList<String> strs = new ArrayList<String>();
		String word1 ="";
		String word2 = "";
		String word3 = "";
		
		
		if(s.length()<=3) {
			strs.add(s);
		} else {
			for(int a=1; a<s.length()-1;a++) {
				for (int b=a+1;b<s.length();b++) {
					word1 = s.substring(0,a);
					word2 = s.substring(a,b);
					word3 = s.substring(b,s.length());
					
					StringBuffer sb1 = new StringBuffer(word1);
					StringBuffer sb2 = new StringBuffer(word2);
					StringBuffer sb3 = new StringBuffer(word3);
					String reverse1 = sb1.reverse().toString();
					String reverse2 = sb2.reverse().toString();
					String reverse3 = sb3.reverse().toString();
					rs = reverse1+reverse2+reverse3;
					strs.add(rs);
				}
			}
			Collections.sort(strs);
		}
		System.out.println(strs.get(0));
	}
}