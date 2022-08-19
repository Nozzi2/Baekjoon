import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
10 10 8
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1 1 1
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
30
범위가 지금 제대로 안먹는것같음
확인요망!!!!!!!!!!!!!!!!!!!1


  1 2 3 4 5 6 7 8 9 10
1 1 1 1 1 1 1 1 1 1 1
2 1 1 1 1 1 1 1 1 1 1
3 1 1 1 1 1 1 1 1 1 1
4 0 0 0 0 0 0 0 0 0 0
5 0 0 0 0 0 0 0 0 0 0
6 0 0 0 0 0 0 0 0 0 0
7 0 0 0 0 0 0 0 0 0 0
8 0 0 0 0 0 0 0 0 0 0
9 0 0 0 0 0 0 0 0 0 0
100 0 0 0 0 0 0 0 0 0
*/

public class Main {
	static int[] dr = {0, 0, -1, 0}; //현위치, 좌 상 우
	static int[] dc = {0, -1, 0, 1}; //현위치, 좌 상 우
	
	static int ENEMY_CNT;
	static int maxKill;
	//static int turn;
	static int N,M,D;
	static int R=3;
	static boolean[][] map;
	static boolean[][] cmap;
	static boolean[][] visited;
	static int[] shooterComb;
	
	static class Position{
		int r;
		int c;
		
		Position(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		public boolean isOut() {
			if(r==0 || c==0 || r>N || c>M) {
				return true;
			}
			return false;
		}
		
		//복사한 map에서 적인지 확인해야한다.
		public boolean isEnemy() {
			return cmap[this.r][this.c];
		}
	}
	
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("res/17135boj.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		
		map = new boolean[N+2][M+2];
		visited = new boolean[N+2][M+2];
		shooterComb = new int[3];
		maxKill = 0;
		//turn = 0;
		
		//map 입력
		for(int i=1, endi=N; i<=endi; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1, endj=M; j<=endj; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					ENEMY_CNT++;
					map[i][j] = true;
				} else {
					map[i][j] = false;
				}//if else
			}//for j
		}//for i
		
//		int[] arr = {2,4,5};
//		int tmp = game(arr);
//		maxKill = Math.max(maxKill, tmp);
		
		combination(0,1);
		
		System.out.println(maxKill);
	}//main

	private static void combination(int cnt, int start) {
		if(maxKill == ENEMY_CNT) return;
		
		if(cnt==R) {
			//여기서 궁수조합으로 동작을 수행해야함.
			int tmp = game(shooterComb);
			maxKill = Math.max(maxKill, tmp);
			//System.out.println(Arrays.toString(shooterComb));
			return;
		}
		
		for(int i=start; i<=M; i++) {
			shooterComb[cnt] = i;
			combination(cnt+1, i+1);
		}
	}
	
	//배열을 깊은 복사해준다. (얕은 복사하면 원래의 값을 바꾸게 됨)
	private static boolean[][] copyArray(boolean[][] arr){
		boolean[][] cmap = new boolean[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) { // 반복문 + ArrayCopy
			System.arraycopy(arr[i], 0, cmap[i], 0, cmap[i].length);
		}
		
		return cmap;
	}
	
	private static int game(int[] shooters) {
		int killCnt=0;
		cmap = copyArray(map); //조합이 끝난 뒤 기존의 배열에는 영향을 주면 안되므로 깊은 복사를 해야한다.
		for(int cursor=N, endc=1; cursor>=endc; cursor--) { //총 N개의 턴이 진행되야한다.
//			System.out.println(cursor+"칸 실행");
			//Set<Position> killedEnemy = new HashSet<Position>(); //턴마다 죽인 적을 초기화해야함
			Set<String> killedEnemy = new HashSet<String>();
			for(int shoot=0, ends=R; shoot<ends; shoot++) { //궁수 1~3 반복한다.
//				System.out.println("  "+(shoot+1)+"번 궁수 시작");
				for(int dist=1, endd=D; dist<=endd; dist++) { //가까운 거리부터 먼저 탐색을 시작한다.
					//탐색한 적의 위치를 저장
					Position enemy = bfs(shooters[shoot], cursor , dist);
					if(enemy != null) {
//						System.out.println("    "+(shoot+1)+"번 : "+enemy.r+","+enemy.c);
						String enemyRC = enemy.r+","+enemy.c;
						killedEnemy.add(enemyRC);
						//killedEnemy.add(enemy);
						break;
					}//if
				}//for dist
			}//for shoot
			
			//한턴이 끝나면 죽인 적의 횟수를 저장하고
			killCnt+=killedEnemy.size();
			
			//맵에 죽은 적들은 없애줘야함.
			//Iterator<Position> iter = killedEnemy.iterator();
			Iterator<String> iter = killedEnemy.iterator();
			while(iter.hasNext()) {
				int r = 0,c=0;
				String s = iter.next();
//				System.out.println(s);
				String[] p = s.split(",");
				r = Integer.parseInt(p[0]);
				c = Integer.parseInt(p[1]);
				cmap[r][c] = false; //적의 좌표에 없애주기 
			}//while
		}//for cursor
//		System.out.println(Arrays.toString(shooters)+"게임 끝");
		return killCnt;
	}//game

	//주어진 범위 내에서 적을 만나면 위치를 반환하는 메소드
	private static Position bfs(int sc, int sr, int range) {
		Queue<Position> que = new LinkedList<>();
		Position start = new Position(sr,sc);
		visited = new boolean[N+2][M+2];
		que.offer(start);
		int bfsCnt=1;
		while(!que.isEmpty()) {
			bfsCnt++;
			int size = que.size();
			for(int i=0; i<size; i++) {
				Position cur = que.poll();
				if(range==1) { //범위가 1일 경우에는 현재 칸만 탐색
					Position next = new Position(cur.r+dr[0], cur.c+dc[0]);
					if(next.isOut()) continue;
					if(visited[next.r][next.c]) continue;
					if(next.isEnemy()) return next;
					que.offer(next);
					visited[next.r][next.c] = true;
				} else { //범위가 2이상일 경우 삼방탐색
					for(int j=0; j<4; j++) { //현위치, 좌, 상, 우 탐색
						Position next = new Position(cur.r+dr[j], cur.c+dc[j]);
						if(next.isOut()) continue;
						if(visited[next.r][next.c]) continue;
						if(next.isEnemy()) return next;
						que.offer(next);
						visited[next.r][next.c] = true;
					}//for j
				}
			}//for i
			
			//bfs횟수는 range만큼만 진행해야함
			if(bfsCnt == range) return null;
		}//while
		return null;
	}//bfs
}