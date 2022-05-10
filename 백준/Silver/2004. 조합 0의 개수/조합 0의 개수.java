import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		int nSubm = n-m;
		int cnt5 =0;
		int cnt2 = 0;
		
		while(n>=5) {
			cnt5+=n/5;
			n/=5;
		}
		//System.out.println(cnt5);
		while(m>=5) {
			cnt5-=m/5;
			m/=5;
		}
		//System.out.println(cnt5);
		while(nSubm>=5) {
			cnt5-=nSubm/5;
			nSubm/=5;
		}
		
		
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		nSubm = n-m;
		
		while(n>=2) {
			cnt2+=n/2;
			n/=2;
		}
		//System.out.println(cnt5);
		while(m>=2) {
			cnt2-=m/2;
			m/=2;
		}
		//System.out.println(cnt5);
		while(nSubm>=2) {
			cnt2-=nSubm/2;
			nSubm/=2;
		}
		
		
		if(cnt2>cnt5) {
			System.out.println(cnt5);
		} else {
			System.out.println(cnt2);
		}
	}
}