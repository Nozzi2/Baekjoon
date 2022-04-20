import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static char[][] arr;
	
	public void drawStar(int x, int y, int N, boolean blank) {
		if(blank) {
			arr[x][y] = ' ';
			return;
		}
		if(N==1) {
			arr[x][y] = '*';
			return;
		}
		
		int size = N/3;
		int count = 0;
		
		for(int i=x; i<N+x; i+=size) {
			for(int j=y; j<N+y; j+=size) {
				count++;
				if(count == 5) {
					drawStar(i,j,size,true);
				} else {
					drawStar(i,j,size,false);
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		arr =new char[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				arr[i][j] = ' ';
			}
		}
		
		Main drawStar10 = new Main();
		drawStar10.drawStar(0,0,N,false);
		
		for(int i=0; i<arr.length;i++) {
			for(int j=0; j<arr[i].length;j++) {
				sb.append(arr[i][j]);
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}