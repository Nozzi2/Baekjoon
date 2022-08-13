import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int sizeR;
	static int sizeC;
	static int cnt;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean[][] map;
	static boolean[][] visited;
	static position[] posArr;
	
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
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int T=0; T<TC; T++) {
			st = new StringTokenizer(br.readLine());
			sizeR = Integer.parseInt(st.nextToken());
			sizeC = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			cnt =0;
			map = new boolean[sizeR+2][sizeC+2];
			visited = new boolean[sizeR+2][sizeC+2];
			posArr = new position[N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken())+1;
				int c = Integer.parseInt(st.nextToken())+1;
				posArr[i] = new position(r,c);
				map[r][c] = true;
			}
			
			for(int i=0; i<N; i++) {
				bfs(posArr[i]);
			}
			System.out.println(cnt);
		}
	}

	private static void bfs(position cur) {
		if(visited[cur.r][cur.c]) return;
		
		cnt++;
		Queue<position> que = new LinkedList<position>();
		
		que.offer(cur);
		visited[cur.r][cur.c]= true;
		
		while(!que.isEmpty()) {
			position next = que.poll();
			
			for(int i=0; i<4; i++) {
				int nr = next.r+dr[i];
				int nc = next.c+dc[i];
				
				if(isOut(nr,nc) || visited[nr][nc] || !map[nr][nc]) {
					continue;
				}
				que.offer(new position(nr,nc));
				visited[nr][nc]= true;
			}
		}
	}

	private static boolean isOut(int r, int c) {
		if(r==0 || c==0 || r>sizeR || c>sizeC) {
			return true;
		}
		return false;
	}
}