import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine(); //문자열 입력
		char[] Chars = str.toCharArray();
		int sum = 0;
		
		for (int i = 0; i < Chars.length; i++) {
			if (Chars[i] == 'A' || Chars[i] == 'B' || Chars[i] == 'C') {
				sum = sum+3;
			} else if (Chars[i] == 'D' || Chars[i] == 'E' || Chars[i] == 'F') {
				sum = sum+4;
			} else if (Chars[i] == 'G' || Chars[i] == 'H' || Chars[i] == 'I') {
				sum = sum+5;
			} else if (Chars[i] == 'J' || Chars[i] == 'K' || Chars[i] == 'L') {
				sum = sum+6;
			} else if (Chars[i] == 'M' || Chars[i] == 'N' || Chars[i] == 'O') {
				sum = sum+7;
			} else if (Chars[i] == 'P' || Chars[i] == 'Q' || Chars[i] == 'R' || Chars[i] == 'S') {
				sum = sum+8;
			} else if (Chars[i] == 'T' || Chars[i] == 'U' || Chars[i] == 'V') {
				sum = sum+9;
			} else if (Chars[i] == 'W' || Chars[i] == 'X' || Chars[i] == 'Y' || Chars[i] == 'Z') {
				sum = sum+10;
			}
		}
		
		System.out.println(sum);

	}

}