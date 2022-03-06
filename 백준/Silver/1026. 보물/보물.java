import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//1026번. 보물 (실버4)
public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //선언
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int num1 = Integer.parseInt(br.readLine()); //Int

		String[] s1 = br.readLine().split(" "); //띄어쓰기 구분
		String[] s2 = br.readLine().split(" "); //띄어쓰기 구분

		ArrayList<Integer> numsA = new ArrayList<Integer>();
		ArrayList<Integer> numsB = new ArrayList<>();
		
		for (int i = 0; i < num1; i++) { //빠른 입력
			numsA.add(Integer.parseInt(s1[i]));
			numsB.add(Integer.parseInt(s2[i]));
		}
		
		// 오름차순으로 정렬
		Collections.sort(numsA);
		// 내림차순으로 정렬
		Collections.sort(numsB, Collections.reverseOrder());
		
		//합계 계산
		int sum=0;
		for (int i = 0; i < numsA.size(); i++) {
			sum = sum+(numsA.get(i)*numsB.get(i));
		}
		
		System.out.println(sum);
	}

}