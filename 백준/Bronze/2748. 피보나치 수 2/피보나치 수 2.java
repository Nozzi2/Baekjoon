import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num=Integer.parseInt(br.readLine());
		long[] fibNum;
		if(num<2) {
			fibNum =new long[2];
		} else {
			fibNum=new long[num+1];
		}
		fibNum[0] = 0;
		fibNum[1] = 1;
		for(int i=2; i<=num; i++) {
			fibNum[i]=fibNum[i-1]+fibNum[i-2];
		}
		System.out.println(fibNum[num]);
	}
}