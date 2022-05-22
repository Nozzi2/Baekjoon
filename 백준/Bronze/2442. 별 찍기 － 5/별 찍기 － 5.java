import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num=Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int space=num-1;
		int star=1;
		for(int i=0; i<num; i++) {
			for(int j=0; j<space; j++) {
				sb.append(" ");
			}
			for(int j=0; j<star; j++) {
				sb.append("*");
			}
			space--;
			star+=2;
			sb.append("\n");
		}
		sb.delete(sb.length()-1, sb.length());
		System.out.print(sb);
	}
}