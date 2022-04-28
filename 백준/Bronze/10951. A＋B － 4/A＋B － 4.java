import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num1 = 99 ;
		int num2 = 99;
		Scanner sc = new Scanner(System.in);
		
		//int a=0;
		while(sc.hasNextInt()) {
			num1 = sc.nextInt();
			num2 = sc.nextInt();
			/*if(num1 == 0 && num2 == 0) {
				break;
			}*/
			//a++;	
			System.out.println(num1+num2);
		}
		
	}

}