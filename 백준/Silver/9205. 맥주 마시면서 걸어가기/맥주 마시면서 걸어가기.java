import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int size;
	static Position dest;
	static Position[] supSpot;
	static boolean[] visited;
	static boolean isHappy;
	

	static class Position {
		int r;
		int c;
		
		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		//현재 좌표와 목표좌표의 거리가 1000미만인지 확인하는 메소드
		boolean canGo(Position p) {
			if(Math.abs(p.r-this.r)+Math.abs(p.c-this.c)<=1000) {
				return true;
			}
			return false;
		}

		@Override
		public String toString() {
			return "Position [r=" + r + ", c=" + c + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int T=1; T<=TC; T++) {
			size = Integer.parseInt(br.readLine());
			isHappy = false;
			supSpot = new Position[size];
			visited = new boolean[size];
			StringTokenizer st = new StringTokenizer(br.readLine());
			Position start = new Position(
											Integer.parseInt(st.nextToken()),
											Integer.parseInt(st.nextToken())
										);
			for(int i=0; i<size; i++) {
				st = new StringTokenizer(br.readLine());
				supSpot[i] = new Position(
											Integer.parseInt(st.nextToken()),
											Integer.parseInt(st.nextToken())
										);
			}
			st = new StringTokenizer(br.readLine());
			dest = new Position(
											Integer.parseInt(st.nextToken()),
											Integer.parseInt(st.nextToken())
										);
			dfs(start);

			sb.append(isHappy?"happy\n":"sad\n");
		}
		System.out.println(sb);
	}

	private static void dfs(Position start) {
		if(!isHappy) {
			if(start.canGo(dest)) { 
				isHappy = true;
				return;
			}
			
			for(int i=0; i<size; i++) {
				if(start.canGo(supSpot[i]) && !visited[i]) {
					visited[i] = true;
					dfs(supSpot[i]);
				}
			}
		}
	}
}