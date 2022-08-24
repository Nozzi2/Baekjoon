import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

//큐 쓸대 링ㅇ크드 리스트보다 어레이디큐가 빠르다

public class Main {
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	static char[][] map;
	static boolean[][] visited;
	static int R,C;
	static int waterCnt,stepCnt;
	static Deque<Position> que;
	
	static class Position {
		int r;
		int c;
		
		Position(int r, int c){
			this.r = r;
			this.c = c;
		}

		public boolean isOut() {
			if(r==0 || c==0 || r>R || c>C) {
				return true;
			}
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("res/3055boj.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R+2][C+2];
		visited = new boolean[R+2][C+2];
		
		que = new ArrayDeque<Position>();
		Position start=null;
		
		for(int i=1, endi=R; i<=endi; i++) {
			char[] chars = br.readLine().toCharArray();
			for(int j=1, endj=C; j<=endj; j++) {
				map[i][j] = chars[j-1];
				switch(chars[j-1]) {
				case 'S' :
					start = new Position(i,j);
					break;
				case '*' :
					que.add(new Position(i,j));
					break;
				}
			}
		}
		
		waterCnt=que.size();//물 먼저 bfs돌려야 되는데 몇번 돌릴지 설정
		que.add(start); //물먼저 돌고 나서 사람 돌리기
		stepCnt=1;//큐를 몇번 비울지
		
		
		int dist=0;
		while(true) { //while문 한번 돌 때마다 한칸씩 간것과 같음
			dist++;
			bfsWater(waterCnt);
			boolean result = bfsEscape(stepCnt);
			if(result) { //도착지에 가면
				System.out.println(dist); //거리 출력
				break;
			}
			if(stepCnt==0) { //더이상 움직일 곳이 없다면
				System.out.println("KAKTUS");
				break;
			}
		}
		
		
		//printMap();
	}

	private static void bfsWater(int count) {
		waterCnt=0;
		for(int j=0; j<count; j++) {
			Position cur = que.poll();
			
			for(int i=0; i<4; i++) {
				Position next = new Position(cur.r+dr[i], cur.c+dc[i]);
				//맵을 벗어나거나, 장애물이거나, 도착지거나, 이미 물이 찼다면 더 물이 진행되지 않는다.
				if(next.isOut() 
						|| map[next.r][next.c]=='X' 
						|| map[next.r][next.c]=='D' 
						|| map[next.r][next.c]=='*') continue;
				que.offer(next);
				map[next.r][next.c] = '*'; //갈수 없는 곳은 *로 체크
				waterCnt++;
			}
		}
	}

	private static boolean bfsEscape(int count) {
		stepCnt=0;
		for(int j=0; j<count; j++) {
			Position cur = que.poll();
			
			for(int i=0; i<4; i++) {
				Position next = new Position(cur.r+dr[i], cur.c+dc[i]);
				//맵을 벗어나거나, 장애물이거나, 도착지거나, 이미 물이 찼다면 더 물이 진행되지 않는다.
				if(next.isOut() 
						|| map[next.r][next.c]=='X' 
						|| map[next.r][next.c]=='*') continue;
				
				if(map[next.r][next.c]=='D') return true; //도착지에 도착했다면
				
				que.offer(next);
				map[next.r][next.c] = '*';  //방문했던 칸은 *로 체크
				stepCnt++;
			}
		}
		return false; //횟수만큼 돌아도 도착하지 못했다면
	}

	private static void printMap() {
		System.out.println("--시작--");
		for(int i=1, endi=R; i<=endi; i++) {
			for(int j=1, endj=C; j<=endj; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		System.out.println("-- 종료 --");
	}
}