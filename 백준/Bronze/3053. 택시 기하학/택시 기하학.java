import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double R = Double.parseDouble(br.readLine());
		
		System.out.println(String.format("%.6f", (Math.pow(R, 2) * Math.PI)));
		System.out.println(String.format("%.6f", (2 * Math.pow(R, 2))));
	}

}