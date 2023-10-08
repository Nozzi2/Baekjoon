import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int n;
	static int[] cow;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int answer=0;
		n=sc.nextInt();
		cow=new int[11];
		Arrays.fill(cow, -1);
		
		for (int i = 0; i < n; i++) {
			int num=sc.nextInt();
			int pos=sc.nextInt();
			if(cow[num]==-1) {
				cow[num]=pos;
			}else if(cow[num]!=pos) {
				cow[num]=pos;
				answer+=1;
			}
		}
		

		System.out.println(answer);
	}
}