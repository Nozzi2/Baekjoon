import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int cutSize;
	static Set<Integer> set;
	static int length;

	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("res/5658swea.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for(int T=1; T<=TC; T++) {
			StringTokenizer st = new StringTokenizer(br.readLine());		
			length = Integer.parseInt(st.nextToken());
			cutSize = length/4;
			int K = Integer.parseInt(st.nextToken()); //출력할 K번째 수
			char[] chars = br.readLine().toCharArray();
			set = new HashSet<>();
			
			for(int i=0; i<cutSize; i++) {
				for(int j=0; j<length; j+=cutSize) {
					int start=i+j;
					cutChars(chars, start);
				}
			}
			
			List<Integer> list = new ArrayList<>();
			Iterator<Integer> iter = set.iterator();
			while(iter.hasNext()) {
				list.add(iter.next());
			}
			Collections.sort(list);
			
			System.out.printf("#%d %d\n",T,list.get(list.size()-K));
		}
	}

	//1B3 이 3B1이 출력되도록
	private static void cutChars(char[] chars, int start) {
		int sum=0;
		int jarisu=1;
		for(int i=start+cutSize-1, endi = start; i>=endi; i--) {
			int index = i%length;
			int tmp=0;
			switch(chars[index]) {
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
			case 'F':
				tmp = chars[index]-'A'+10;
				break;
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				tmp = chars[index]-'0';
				break;
			}
			sum+=tmp*jarisu;
			jarisu *= 16;
			
			//System.out.print(chars[index]);
		}
		set.add(sum);
		//System.out.println();
		//System.out.println(sum);
	}

}
