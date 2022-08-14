import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int N;
	static int R=3;
	static int min;				//화단 대여의 최소비용을 저장
	static int[][] map;
	static boolean[][] visited;
	static position[] selects;
	
	static class position{
		int r;
		int c;
		
		position(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		selects = new position[R];
		min = Integer.MAX_VALUE;
		StringTokenizer st;
		
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0,0);
		
		System.out.print(min);
	}

	private static void comb(int cnt, int start) {
		if(cnt==R) {
			int sum = calcSum();
			min = Math.min(min, sum);
			return;
		}
		
		for(int i=1; i<N-1; i++) {//첫칸, 마지막칸은 꽃을 심을 수 없음
			for(int j=1; j<N-1; j++) {//첫칸, 마지막칸은 꽃을 심을 수 없음
				if(!isSelected(i,j)) {
					checkVisit(i,j, true);
					selects[cnt] = new position(i,j);
					comb(cnt+1, start+1);
					checkVisit(i,j, false);
				}
			}
		}
	}

	//선택하여 해당 칸과 상하좌우 모두 선택 체크할 메소드 
	private static void checkVisit(int r, int c, boolean tf) {
		visited[r][c] = tf;
		for(int j=0; j<4; j++) {
			int nr = r+dr[j];
			int nc = c+dc[j];
			visited[nr][nc] = tf;
		}
	}

	//선택할 수 있는지 확인할 메소드
	private static boolean isSelected(int r, int c) {
		if(visited[r][c]) {
			return true;
		}
		for(int j=0; j<4; j++) {
			int nr = r+dr[j];
			int nc = c+dc[j];
			if(visited[nr][nc]) {
				return true;
			}
		}
		
		return false;
	}

	//세개의 좌표를 선택했을 떄 비용을 계산할 메소드
	private static int calcSum() {
		int sum=0;
		
		for(int i=0; i<R; i++) {
			int r=selects[i].r;
			int c=selects[i].c;
			sum+=map[r][c];
			for(int j=0; j<4; j++) {
				int nr = r+dr[j];
				int nc = c+dc[j];
				sum+=map[nr][nc];
			}
		}
		
		return sum;
	}
	
	
}