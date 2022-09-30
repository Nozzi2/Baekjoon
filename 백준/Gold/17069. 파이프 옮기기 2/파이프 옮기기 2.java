import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static long[][][] pipes;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		pipes = new long[3][N][N]; //ㅡ0 N1 ㅣ2
		
		for(int i=0; i<N; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}//for j
		}//for i
		
		pipes[0][0][1] = 1;
		for(int i=2; i<N; i++) {
			if(map[0][i]==1) break;
			pipes[0][0][i] =1;
		}

		//왼쪽 2열은 모두 0이 맞음.
		
		for(int r=1; r<N; r++) {
			for(int c=2; c<N; c++) {
				if(map[r][c]!=1) { //벽이 없다면
					pipes[0][r][c] = pipes[1][r][c-1]+pipes[0][r][c-1];
					if(map[r-1][c]!=1 && map[r][c-1]!=1) {
						pipes[1][r][c] = pipes[1][r-1][c-1]+pipes[0][r-1][c-1]+pipes[2][r-1][c-1];
					}
					pipes[2][r][c] = pipes[1][r-1][c]+pipes[2][r-1][c];
				}
			}//for c
		}//for r
		
		
		long sum=0;
		for(int i=0; i<3; i++) {
			sum+=pipes[i][N-1][N-1];
		}
		System.out.println(sum);
	}
}