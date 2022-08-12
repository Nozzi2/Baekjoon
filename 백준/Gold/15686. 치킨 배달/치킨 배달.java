import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;			//맵 사이즈
	static int R;			//뽑을 치킨집 갯수
	static int[][] map;
	static position[] selchicks;	//뽑은 치킨집들의 위치 배열
	static int homesCnt;			//집 갯수
	static List<position> homes;	//집들의 위치 배열
	static int chicksCnt;			//치킨집 갯수
	static List<position> chicks;	//치킨집들의 위치 배열
	static int min;
	
	static class position {
		int r;
		int c;
		
		position(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		//현재위치와 인자로 넘어온 위치 x의 거리를 반환해준다.
		public int getDist(position x) {
			//|r1-r2| + |c1-c2|
			int dist = Math.abs(this.r-x.r)+Math.abs(this.c-x.c);
			return dist;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		homes = new ArrayList<position>();
		chicks = new ArrayList<position>();
		map = new int[N+1][N+1];
		selchicks = new position[R];
		min = Integer.MAX_VALUE;
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				switch(map[i][j]) {
				case 1:
					homes.add(new position(i,j));
					break;
				case 2:
					chicks.add(new position(i,j));
					break;
				}//switch
			}//for j
		}//for i
		homesCnt = homes.size();
		chicksCnt = chicks.size();
		
		comb(0,0);
		
		System.out.println(min);
	}
	
	//치킨집을 R 개 선택하는 조합
	private static void comb(int cnt, int start) {
		if(cnt==R) {
			min = Math.min(min, getDistanceSum(selchicks));
			//여기서 치킨집들과 집들의 최소 거리를 구해야한다.
			return;
		}
		
		for(int i=start; i<chicksCnt; i++) {
			selchicks[cnt] = chicks.get(i);
			comb(cnt+1, i+1);
		}
	}

	private static int getDistanceSum(position[] chks) {
		//한개의 집을 기준으로 조합으로 선택된 모든 치킨집와의 거리를 구하고 가장 작은 값을 합계(sum)에 더해줘야한다.
		int tmp=0;
		for(position p : homes) { 
			int sum=Integer.MAX_VALUE;
			for(position c: chks) {
				sum = Math.min(sum, p.getDist(c));
			}
			tmp+=sum;
		}
		return tmp;
	}
	

}