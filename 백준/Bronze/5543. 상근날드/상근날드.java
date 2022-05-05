import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		int[] a = new int[3];
		int[] b = new int[2];
		int minA = 2001;
		int minB = 2001;
		
		for(int i=0; i<a.length; i++) {
			a[i] = Integer.parseInt(br.readLine());
			if(minA > a[i]) {
				minA = a[i];
			}
		}
		
		for(int i=0; i<b.length; i++) {
			b[i] = Integer.parseInt(br.readLine());
			if(minB > b[i]) {
				minB = b[i];
			}
		}
		
		System.out.println(minA+minB-50);
	}
}