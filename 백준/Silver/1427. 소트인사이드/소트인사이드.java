import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String myString = br.readLine();
        char[] myChars = myString.toCharArray();
        
        ArrayList<Integer> InputNum = new ArrayList<Integer>();
        int CharToInt = 0;
        for (int i = 0; i < myChars.length; i++) {
        	CharToInt = Character.getNumericValue(myChars[i]);
			InputNum.add(CharToInt);
		}
        
        Collections.sort(InputNum, Collections.reverseOrder());
        
        Iterator iter = InputNum.iterator(); //Iterator 선언 
        while(iter.hasNext()){//다음값이 있는지 체크
        	sb.append(iter.next()); //값 출력
        }
        System.out.print(sb);
	}

}