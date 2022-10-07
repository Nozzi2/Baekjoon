import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1, 0,1,0};
	static int[] dc = {0, 1,0,-1};
	static int[][] map;
	
	static class Robot{
		int r;
		int c;
		int dir;
		int streak;
		
		public Robot(String s) {
			StringTokenizer st= new StringTokenizer(s);
			this.r = Integer.parseInt(st.nextToken());
			this.c = Integer.parseInt(st.nextToken());
			this.dir = Integer.parseInt(st.nextToken());
			this.streak = 0;
		}
		
		//4방향 다 돌았는지 검사
		boolean check4Way() {
			if(streak==4) return true;
			else return false;
		}
		
		//왼쪽 청소할 영역이 있다면 true를 반환
		boolean checkLeft() {
			int tmpDir = dir;
			if(tmpDir==0) tmpDir=3;
			else tmpDir--;
			
			int tmpR = r+dr[tmpDir];
			int tmpC = c+dc[tmpDir];
			if(map[tmpR][tmpC] == 0) {
				streak=0;
				return true;
			} else {
				streak++; //몇회전했는지 저장
				return false;
			}
		}
		
		void rotate() {
			if(dir==0) dir=3;
			else dir--;
		}
		
		void go() {
			this.r +=dr[dir];
			this.c +=dc[dir];
			map[r][c] = 2;
		}
		
		//일단 뒤로 이동하고 벽이라면 false반환
		boolean back() {
			streak=0;
			this.r -=dr[dir];
			this.c -=dc[dir];
			
			if(map[r][c]==1) return false;
			return true;
		}
	}

	public static void main(String[] args) throws IOException {
		int R,C,dir, blockCnt;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		
		Robot bot = new Robot(br.readLine());
		
		for(int i=0; i<R; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//첫 블럭은 무조건 청소되어있지 않음
		blockCnt=1;
		map[bot.r][bot.c] = 2;
		
		while(true) {
			if(!bot.check4Way()) { //4방향 다 돌지 않았으면
				if(bot.checkLeft()) {
					bot.rotate();
					bot.go();
					blockCnt++;
				} else {
					bot.rotate();
				}
			} else {
				if(!bot.back()) break;
			}
			
//			for(int i=0; i<R; i++) {
//				for(int j=0; j<C; j++) {
//					if(bot.r == i && bot.c == j) {
//						switch(bot.dir) {
//						case 0:
//							System.out.print("A ");
//							break;
//						case 1:
//							System.out.print("> ");
//							break;
//						case 2:
//							System.out.print("V ");
//							break;
//						case 3:
//							System.out.print("< ");
//							break;
//						}
//					} else {
//						System.out.print(map[i][j]+" ");
//					}
//				}//for j
//				System.out.println();
//			}//for i
//
//			System.out.println("----------------------------");
		}//while

		System.out.println(blockCnt);
	}

}