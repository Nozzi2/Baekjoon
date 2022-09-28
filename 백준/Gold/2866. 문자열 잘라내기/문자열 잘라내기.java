import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int R;
	static int C;
	
	public static void main(String[] args) throws IOException {
		int count=0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		//C개의 문자열을 만들 StringBuilder 선언
		StringBuilder[] sb = new StringBuilder[C];
		for(int i=0; i<C; i++) {
			sb[i] = new StringBuilder();
		}
		
		//C개의 StringBuilder에 문자열 저장
		for(int i=0; i<R; i++) {
			char[] chars = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				sb[j].append(chars[j]);
			}
		}
		
		//String배열에 StringBuilder 저장
		String[] strs = new String[C];
		for(int i=0; i<C; i++) {
			strs[i] = sb[i].toString();
		}
		
		while(true) {
			subStringArray(strs); //문자열을 1개씩 줄이기
			if(checkOverlap(strs)) { //중복이 있다면?
				break;
			} else {
				count++;
			}
			if(R==1) break; //더이상 줄일 문자열이 없다면 종료
		}//while

		System.out.println(count);
	}

	private static void printStrings(String[] strs) {
		for(String s : strs) {
			System.out.println(s);
		}
	}

	private static boolean checkOverlap(String[] strs) {
		Set<String> set = new HashSet<>();
		for(String s : strs) {
			set.add(s);
		}
		if(set.size()!=C) { //중복제거한 set의 size가 C와 같지 않다면 중복이 있는 것임
			return true;
		} else {
			return false;			
		}
	}

	private static void subStringArray(String[] strs) {
		for(int i=0; i<C; i++) {
			strs[i] = strs[i].substring(1, R);
		}
		R--;
	}
}