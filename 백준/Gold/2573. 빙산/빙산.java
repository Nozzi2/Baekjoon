import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int R,C,iceCnt;
	static int[][][] map;
	static boolean[][] visited;
	static Pos lastIce;
	
	static class Pos {
		int r;
		int c;
		public Pos(int r, int c ) {
			this.r = r;
			this.c = c;
		}
		
		boolean isIn() {
			return r<R && c<C && r>=0 && c>=0;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Pos [r=").append(r).append(", c=").append(c).append("]");
			return builder.toString();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[2][R][C];
		visited = new boolean[R][C];
		int yearCnt=0;
		iceCnt=0;
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[1][i][j] = Integer.parseInt(st.nextToken());
				map[0][i][j] = map[1][i][j];
				if(map[1][i][j] !=0) {
					iceCnt++;
					if(lastIce==null) {
						lastIce = new Pos(i,j);
					}
				}
			}
		}
//		System.out.println("초기 얼음 갯수 "+iceCnt);

		boolean isOnePiece = true;
		
		//이미 두조각 이상으로 나눠져 있는지 검사
		if(iceCnt != bfsIceCnt(lastIce, 0)) {
			isOnePiece = false;
		}
		
		
		/*
		 * 일단 반복문을 진행하긴 하는데 이러한 경우도 고려해야한다.
		 * 1. 처음부터 2조각 이상으로 되어있는 경우
		 * 2. 처음부터 다 녹아있는 경우
		 */
		//int tmp = 3;
		while(iceCnt!=0 && isOnePiece) {
			yearCnt++;
		
			//값 초기화
			int no = yearCnt%2; //반복마다 탐색할 배열의 칸을 바꿔주는 변수
			//1일경우 1을 탐색하고 탐색결과를 0에 저장
			//0일경우 0을 탐색하고 탐색결과를 1에 저장
			visited = new boolean[R][C]; //방문 초기화
			lastIce = new Pos(0,0); //마지막 얼음 위치 초기화
			
//			for(int i=0; i<R; i++) {
//				for(int j=0; j<C; j++) {
//					System.out.print(visited[i][j]?1:0);
//				}
//				System.out.println();
//			}
			
			//녹일 빙하 탐색
			Pos start = new Pos(0,0);
			bfs(start, no);
//			for(int i=0; i<R; i++) {
//				for(int j=0; j<C; j++) {
//					System.out.print(visited[i][j]?1:0);
//				}
//				System.out.println();
//			}
			
			for(int i=1; i<R; i++) { //빙하 내부에도 
				for(int j=1; j<C; j++) { 
					if(!visited[i][j] && map[no][i][j] == 0) { //방문하지 않았고 호수라면 탐색 진행 
						bfs(new Pos(i,j),no);
					}
				}
			}
			
//			for(int i=0; i<R; i++) {
//				for(int j=0; j<C; j++) {
//					System.out.print(visited[i][j]?1:0);
//				}
//				System.out.println();
//			}
			
//			printMap();
//			System.out.println("얼음갯수 : " + iceCnt);
//			//빙하 녹이고, 남은 빙하 갯수 구하기
//				//만약 0이라면 break
//			
//			//빙하 조각이 한조각인지 구하기
			int no2=(no+1)%2;
			boolean isLastFind = false;
			for(int i=1; i<R-1; i++) {
				for(int j=1; j<C-1; j++) {
					if(map[no2][i][j] !=0) {
						lastIce = new Pos(i,j);
						isLastFind = true;
						break;
					}
				}
				if(isLastFind) break;
			}
			int tmpCnt = bfsIceCnt(lastIce, no);
//			System.out.println(iceCnt+"얼음갯수"+tmpCnt);
//			System.out.println("마지막 남은 얼음 위치 "+lastIce);
			if(iceCnt != tmpCnt) {//만약 한조각이 아니라면 break
				if(iceCnt==0) break;
				isOnePiece = false;
			}
//				
		}//while end
		
		System.out.println(isOnePiece?0:yearCnt);
		
	}//main end

	//녹지않은 빙하의 갯수를 반환하는 BFS 
	private static int bfsIceCnt(Pos start, int no) {
		int no2 = (no+1)%2;
		int cnt=0;
		Queue<Pos> que = new ArrayDeque<>();
		visited[start.r][start.c] = true;
		que.offer(start);
		cnt++;
		
		while(!que.isEmpty()) {
			Pos cur = que.poll();
			for(int i=0; i<4; i++) {
				Pos next = new Pos(cur.r+dr[i], cur.c+dc[i]);
				if(!next.isIn()) continue;
				if(visited[next.r][next.c]) continue;
				if(map[no2][next.r][next.c]==0) continue;
				visited[next.r][next.c] = true;
				cnt++;
//				System.out.println("녹지 않은 빙하 : "+next);
				que.offer(next);
			}
		}
		
		return cnt;
	}


	//녹일 빙하 탐색
	private static void bfs(Pos start, int no) {
		int no2 = (no+1)%2;
		Queue<Pos> que = new ArrayDeque<>();
		visited[start.r][start.c] = true;
		que.offer(start);
		
		while(!que.isEmpty()) {
			Pos cur = que.poll();
			for(int i=0; i<4; i++) {
				Pos next = new Pos(cur.r+dr[i], cur.c+dc[i]);
				if(!next.isIn()) continue;
				if(visited[next.r][next.c]) continue;
				if(map[no][next.r][next.c]==0) { //호수라면
					map[no2][next.r][next.c] = 0; //다른맵을 호수로 갱신
					visited[next.r][next.c] = true;
					que.offer(next);
//					System.out.print("여긴 호수야  ");
//					System.out.println(next);
				} else { //빙산이라면
					//다른맵에서 -1해주기.
					//-1을 한 값이 0보다 작아진다면 0 저장하기
					
					//여기서 값을 이전맵에서 -1씩 빼주면 안된다잉!! 다시 생각해봐
					
					int tmp1 = map[no][next.r][next.c]; //현맵값
					int tmp2 = map[no2][next.r][next.c]; //전맵값
					if(tmp1 < tmp2) {
						map[no2][next.r][next.c] = tmp1-1;
						if(map[no2][next.r][next.c]==0) iceCnt--;  //0이 됐을때 빙하갯수 빼주기
					}else {
						if(tmp2-1>=0) {
							map[no2][next.r][next.c]=tmp2-1;
							if(map[no2][next.r][next.c]==0) iceCnt--;  //0이 됐을때 빙하갯수 빼주기
							
							//마지막 얼음 위치 저장
//							lastIce.r = next.r;
//							lastIce.c = next.c;
						}
					}
//					System.out.print("여긴 빙산이야  ");
//					System.out.println(next);
				}
			}
		}
	}

	private static void printMap() {
		System.out.println("================================");
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[0][i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("-  -  -  -  -  -  -  -  -  -  -");

		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[1][i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("================================");
	}
}