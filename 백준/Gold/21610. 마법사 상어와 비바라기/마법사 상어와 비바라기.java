import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {0,-1,-1,-1,0,1,1,1};
	static int[] dc = {-1,-1,0,1,1,1,0,-1};

	static int[] drx = {-1,-1,1,1};
	static int[] dcx = {1,-1,-1,1};
	
	static int SIZE;
	static int[][] map;
	static boolean[][] isCloudy;
	static List<Position> clouds;

	static class Position {
		int r;
		int c;
		
		Position(int r, int c){
			this.r =r;
			this.c =c;
		}
		
		//방향, 칸수만큼 객체의 r,c를 바꾸는 메소드
		public void move(int dir, int block) {
			dir--;
			this.r = (SIZE*block+(this.r-1)+(dr[dir]*block))%SIZE+1;
			this.c = (SIZE*block+(this.c-1)+(dc[dir]*block))%SIZE+1;			
		}
		
		public boolean isOut() {
			if(r==0 || c==0 || r>SIZE || c>SIZE) {
				return true;
			}
			return false;
		}
		
		public void print() {
			System.out.println("r:"+this.r+", c:"+this.c);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("res/21610boj.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		SIZE = Integer.parseInt(st.nextToken());
		map = new int[SIZE+2][SIZE+2];
		isCloudy = new boolean[SIZE+2][SIZE+2];
		int TC = Integer.parseInt(st.nextToken());
		
		for(int i=1, endi=SIZE; i<=endi; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1, endj=SIZE; j<=endj; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		clouds = new ArrayList<>();
		clouds.add(new Position(SIZE,1));
		clouds.add(new Position(SIZE-1,1));
		clouds.add(new Position(SIZE,2));
		clouds.add(new Position(SIZE-1,2));
		
		for(int i=0, endi=TC; i<endi; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int block = Integer.parseInt(st.nextToken());
			
			//방향, 칸수 입력받으면 위치 바꾸고, 해당 칸에 +1하는메소드
			moveClouds(dir, block);
			
			//구름 대각으로 검사하여 1이상인지 검사해서 갯수 반환하는 메소드 (for문 4번 돌고, 좌상,우상,우하,좌하)
			//구름지역을 2차원 boolean배열에 저장하는 메소드
			plusX();
			
			//구름지역 제외하고 2이상 지역 -2해주고, 구름 리스트에 add하는 메소드
			makeCloud();
			
			//printMap();
		}
		printSum();
	}
	
	private static void printSum() {
		int sum=0;
		for(int i=1, endi=SIZE; i<=endi; i++) {
			for(int j=1, endj=SIZE; j<=endj; j++) {
				sum+=map[i][j];
			}
		}
		System.out.println(sum);
	}

	//구름지역 제외하고 2이상 지역 -2해주고, 구름 리스트에 add하는 메소드
	//리스트에 add가 끝나면 기존 구름들은 전부 삭제해야함.
	private static void makeCloud() {
		clouds.clear(); //기존 구름들 전부 삭제
		for(int i=1, endi=SIZE; i<=endi; i++) {
			for(int j=1, endj=SIZE; j<=endj; j++) {
				if(!isCloudy[i][j] && map[i][j] >=2) {
					map[i][j] -=2;
					clouds.add(new Position(i,j));
				}
			}
		}
		isCloudy = new boolean[SIZE+2][SIZE+2]; //기존 구름지역 전부 삭제
	}

	
	private static void setCloudBlock(Position p) {
		isCloudy[p.r][p.c] = true;
	}

	//X방향 (대각방향)으로 숫자를 검사하여 갯수만큼 증가시켜주는 메소드
	//그리고 해당 지역을 구름지역으로 설정하는 메소드
	private static void plusX() {
		for(int i=0, endi=clouds.size(); i<endi; i++) {
			Position p = clouds.get(i);
			int num=0; //대각방향으로 몇개의 숫자가 있는지 저장할 변수
			for(int j=0, endj=drx.length; j<endj; j++) {
				Position next=new Position(p.r+drx[j], p.c+dcx[j]);
				if(next.isOut() || map[next.r][next.c]==0) continue;
				num++;
			}
			plus(num, p);
			
			//현재 지역을 구름지역으로 설정
			setCloudBlock(p);
		}
		
	}

	//방향, 칸수 입력받으면 위치를 바꾸고, 해당 칸에 +1하는 메소드
	private static void moveClouds(int dir, int block) {
		for(int j=0, endj=clouds.size(); j<endj; j++) {
			clouds.get(j).move(dir, block);
			plus(1, clouds.get(j));
		}
	}

	//해당 칸에 인자만큼 더해주는 메소드
	private static void plus(int num, Position p) {
		map[p.r][p.c] += num;
	}

	private static void printMap() {
		System.out.println();
		for(int i=1, endi=SIZE; i<=endi; i++) {
			for(int j=1, endj=SIZE; j<=endj; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("\n");
	}
}