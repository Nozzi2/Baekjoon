import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());
		int[][] KeyNum = new int [testCase][2]; //{나이1, 번호1}, {나이2, 번호2}
		String[] s; //입력용
		String[] name = new String [testCase]; //{이름1}, {이름2}
		
		for (int i = 0; i < testCase; i++) {
			s = br.readLine().split(" ");
			name[i] = s[1];
			KeyNum[i][0] = Integer.parseInt(s[0]);
			KeyNum[i][1] = i;
		}
		
		Arrays.sort(KeyNum, Comparator.comparingInt(o1 -> o1[0])); // 정렬
		
		//출력
		for (int i = 0; i < testCase; i++) {
			sb.append(KeyNum[i][0]+" "+name[KeyNum[i][1]]).append('\n');
		}
		System.out.println(sb);
	}
}