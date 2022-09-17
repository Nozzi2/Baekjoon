import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

//풀기전 1037
public class Main {
	
	static int N;
	static int sum;
	static int min,max;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map;
	static boolean[][] visited;
	
	static class Position{
		int r;
		int c;
		
		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public boolean isOut() {
			if(r==0 || c==0 || r>N || c>N) {
				return true;
			}
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		min = Integer.parseInt(st.nextToken());
		max = Integer.parseInt(st.nextToken());
		
		map = new int[N+2][N+2];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt=0; //인구이동 횟수
		boolean isMoved; //인구이동 발생 여부
		
		while(true) {//인구 이동이 없을 때까지 반복
			isMoved = false;
			visited = new boolean[N+2][N+2];
			List<Position> list;
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					list = bfs(new Position(i,j)); //BFS 실행
					if(list.size()>1) {
						boolean isSame= false; //동맹국가에서 인구이동이 발생했는지 검사할 변수
						int tmp = sum/list.size();
						for(Position p : list) {
							if(tmp != map[p.r][p.c]) {//평균인구와 동일한지?
								map[p.r][p.c] = tmp;
								isSame = true;
							}
						}//for
						if(!isMoved && isSame) {
							isMoved = true;
							cnt++; //횟수는 1번순회에서 1번만 증가
						}//if
					}//if
				}//for j
			}//for i
			if(!isMoved) break;
		}
		System.out.println(cnt);
	}

	private static List<Position> bfs(Position pos) {
		List<Position> list = new ArrayList<>(); //BFS하며 방문한 칸들을 저장한 리스트
		if(visited[pos.r][pos.c]) return list; //이미 방문했었다면 BFS 종료
		
		sum =map[pos.r][pos.c]; //연합국가의 모든 인구수를 저장할 값
		Queue<Position> que = new LinkedList<>();
		que.offer(pos);
		visited[pos.r][pos.c] = true;
		list.add(pos);
		while(!que.isEmpty()) {
			Position cur = que.poll();
			int cvalue = map[cur.r][cur.c];
			for(int i=0; i<4; i++) {
				Position next = new Position(cur.r+dr[i], cur.c+dc[i]);
				int diff = Math.abs(map[next.r][next.c]-cvalue);
				if(next.isOut() || visited[next.r][next.c]) continue;
				if(diff<min || diff>max) continue; //최소 최대 인구수 차이를 만족하는지 검사
				sum+=map[next.r][next.c]; //총인구수에 더하기
				que.offer(next);
				visited[next.r][next.c] = true;
				list.add(next);
			}
		}
		return list; 
	}
}