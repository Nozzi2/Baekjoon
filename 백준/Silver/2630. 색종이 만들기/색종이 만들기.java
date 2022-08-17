import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int whiteCnt;	//0이면 하얀색
	static int blueCnt;		//1이면 파란색
	
	static class Position{
		int r;
		int c;
		
		Position(int r, int c){
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N=Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Position start = new Position(0,0);
		
		slice(start, N);
		bw.write(whiteCnt+"\n");
		bw.write(blueCnt+"\n");
		bw.flush();
	}

	private static void slice(Position start, int n) {
		boolean allW = true; //모두 0인 경우
		boolean allB = true; //모두 1인 경우
		
		for(int i=start.r, endi=start.r+n; i<endi; i++) {
			for(int j=start.c, endj=start.c+n; j<endj; j++) {
				if(map[i][j] == 0) {
					allB = false;
				} else if(map[i][j] == 1) {
					allW = false;
				}
				if(!allB&&!allW) {
					break;
				}
			}
			if(!allB&&!allW) {
				break;
			}
		}
		
		if(allW && !allB) {
			whiteCnt++;
		} else if(!allW && allB) {
			blueCnt++;
		} else {
			slice(new Position(start.r,start.c), n/2);
			slice(new Position(start.r+n/2,start.c), n/2);
			slice(new Position(start.r,start.c+n/2), n/2);
			slice(new Position(start.r+n/2,start.c+n/2), n/2);
		}
		
	}

}