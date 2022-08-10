import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n;		//Row
	static int m;		//Col
	static int rot;		//회전 횟수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		rot = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n+1][m+1];
		
		
		//백준에서 입력받아서 결과 출력할거
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=m; j++) {
				int[] rc = conv(i,j);
				arr[rc[0]][rc[1]] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				bw.write(arr[i][j]+" ");
			}
			bw.write("\n");
		}
		bw.flush();
		

		//conv() 맞게 반환하는지 테스트
//		int r_in =  Integer.parseInt(st.nextToken());
//		int c_in =  Integer.parseInt(st.nextToken());
//		System.out.println(Arrays.toString(conv(r_in,c_in)));
		
		//배열 맞게 돌아가나 테스트
//		for(int i=1; i<=n; i++) {
//			for(int j=1; j<=m; j++) {
//				bw.write(i+""+j+" ");
//			}
//			bw.write("\n");
//		}
//		bw.write("\n");
//		for(int i=1; i<=n; i++) {
//			for(int j=1; j<=m; j++) {
//				int[] rc = conv(i,j);
//				bw.write(rc[0]+""+rc[1]+" ");
//			}
//			bw.write("\n");
//		}
//		bw.flush();
		
	}
	
	public static int[] conv(int r_in, int c_in) {
		//회전할 숫자들 범위 지정
		int r_level=0;
		int sr=1;
		int er=n;
		int c_level=0;
		int sc=1;
		int ec=m;
		while(true) {
			if(r_in == sr || r_in == er) {
				break;
			} else {
				r_level++;
				sr++;
				er--;
			}
		}
		if(r_level!=0) {
			while(true) {
				if(c_in == sc || c_in == ec) {
					break;
				} else {
					c_level++;
					sc++;
					ec--;
				}
			}
		}
		
		int level = Math.min(r_level, c_level);
		sr = 1+level;
		er = n-level;
		sc = 1+level;
		ec = m-level;
		
		
		//rot만큼 회전
		for(int i=0; i<rot; i++) {
			if(r_in == sr && c_in > sc && c_in <= ec) {
				c_in--;
				continue;
			}
			if(r_in == er && c_in >= sc && c_in < ec) {
				c_in++;
				continue;
			}
			if(c_in == sc && r_in >= sr && r_in < er) {
				r_in++;
				continue;
			}
			if(c_in == ec && r_in > sr && r_in <= er) {
				r_in--;
				continue;
			}
		}
		
		int[] rc_out = new int[2];
		rc_out[0] = r_in;
		rc_out[1] = c_in;
		return rc_out;
	}
}