import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map;
	static int maxMove;
	static int move;
	static int sizeR;
	static int sizeC;
	
	static boolean[] visited = new boolean[26]; //'A'~'Z'까지 방문했었는지
	//static Position mal;
	
	static class Position{
		int r;
		int c;
		
		Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public boolean isOut() {
			if(this.r ==0 || this.c == 0 || this.r >sizeR || this.c>sizeC) {
				return true;
			}
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sizeR = Integer.parseInt(st.nextToken());
		sizeC = Integer.parseInt(st.nextToken());
		map = new int[sizeR+2][sizeC+2];
		maxMove = 0;
		move = 0;
		for(int i=1, endi=sizeR; i<=endi; i++) {
			char[] chars = br.readLine().toCharArray();
			for(int j=1, endj=sizeC; j<=endj; j++) {
				map[i][j] = chars[j-1]-'A';
			}
		}
		
		Position mal = new Position(1,1);
		
		dfs(mal);
		
		System.out.println(maxMove);
	}

	private static int getValue(Position cur) {
		return map[cur.r][cur.c];
	}

	private static void dfs(Position cur) {
		//현재 위치에 있는 알파벳을 true
		visited[ getValue(cur) ] = true;
		move++;
		maxMove = Math.max(maxMove, move);
		
		//인접 노드 탐색
		for(int i=0, endi=4; i<endi; i++) {
			int nr = cur.r+dr[i];
			int nc = cur.c+dc[i];
			Position next = new Position(nr,nc);
			if(!next.isOut() && !visited[ getValue(next) ]) {
				dfs(next);
				visited[ getValue(next) ] = false;
				move--;
			}
		}
	}
}