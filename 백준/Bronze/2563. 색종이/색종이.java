import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		boolean[][] bgPaper = new boolean[100][100];
		int cnt = 0;
		int TC = Integer.parseInt(br.readLine());
		String[] s;
		for(int T=0; T<TC; T++) {
			s=br.readLine().split(" ");
			int r=Integer.parseInt(s[0]);
			int c=Integer.parseInt(s[1]);
			for(int i=r; i<r+10; i++) {
				for(int j=c; j<c+10; j++) {
					if(!bgPaper[i][j]) {
						cnt++;
						bgPaper[i][j] = true;
					}
				}
			}
		}
		
		System.out.println(cnt);
		
	}

}