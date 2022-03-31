import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		double S = sc.nextDouble();
		double I = 0;
		
		while (S>=0) {
			S=S-I;
			I++;
		}
		System.out.println(Math.round(I)-2);
	}
}