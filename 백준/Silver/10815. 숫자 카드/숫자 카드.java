import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

//1920번. 수찾기 문제와 완전히 동일한 문제임 출력만 조금 바꿔줬음.
public class Main {
	
	//이진탐색으로 크기가 n인 배열에서 key값을 찾아서 있다면 인덱스를 반환, 없다면 -1반환
	public static int binSearch(ArrayList<Integer> a, int n, int key) {
		int pl = 0;
		int pr = n-1;
		
		do {
			int pc = (pl+pr)/2;
			if(a.get(pc)==key) {
				return 1; //pc를 하면 인덱스 반환
			} else if(a.get(pc)<key) {
				pl = pc+1;
			} else {
				pr = pc-1;
			}
		} while (pl<=pr);
		
		return 0;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		//검색할 데이터 배열
		int num1 = Integer.parseInt(br.readLine()); 
		String[] s1 = br.readLine().split(" ");
		ArrayList<Integer> InputNum = new ArrayList<Integer>();
		for (int i = 0; i < num1; i++) {
			InputNum.add(Integer.parseInt(s1[i]));
		}
		
		//오름차순 정렬
		Collections.sort(InputNum);
		

		//검색할 key 배열
		int num2 = Integer.parseInt(br.readLine()); 
		String[] s2 = br.readLine().split(" ");
		ArrayList<Integer> FindNum = new ArrayList<Integer>();
		for (int i = 0; i < num2; i++) {
			FindNum.add(Integer.parseInt(s2[i]));
		}
				
		//검색해서 있으면 1저장, 없으면 0저장할 배열
		ArrayList<Integer> OutNum = new ArrayList<Integer>
									(Collections.nCopies(FindNum.size(), 0));
		
		
		int result = 0;
		for (int a = 0; a < FindNum.size(); a++) {
			result = binSearch(InputNum, InputNum.size(), FindNum.get(a));
			OutNum.set(a, result);
		}
		
		for (int i = 0; i < OutNum.size(); i++) {
			sb.append(OutNum.get(i)).append(' ');
		}
		
		System.out.println(sb.toString().trim()); //맨 마지막 공백 제거
	}
}