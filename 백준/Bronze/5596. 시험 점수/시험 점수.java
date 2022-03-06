import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //선언
		String[] s1 = br.readLine().split(" "); //띄어쓰기 구분
		String[] s2 = br.readLine().split(" "); //띄어쓰기 구분
		

		int sumS1=0;
		int sumS2=0;
		for (int i = 0; i < s1.length; i++) {
			sumS1 = sumS1+Integer.parseInt(s1[i]);
			sumS2 = sumS2+Integer.parseInt(s2[i]);
		}
		
		if(sumS1>sumS2) {
			System.out.println(sumS1);
		} else {
			System.out.println(sumS2);
		}
	}
}