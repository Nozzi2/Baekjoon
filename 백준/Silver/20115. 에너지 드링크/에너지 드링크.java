import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		long sum=0;
		long max=Integer.MIN_VALUE;
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int T=0; T<TC; T++) {
			int tmp = Integer.parseInt(st.nextToken());
			sum+=tmp;
			max = Math.max(max, tmp);
		}
		sum= sum-max;
		
		Double output = sum/2.0+max;
		DecimalFormat format = new DecimalFormat("0.#####"); 
		System.out.println(format.format(output));
	}

}