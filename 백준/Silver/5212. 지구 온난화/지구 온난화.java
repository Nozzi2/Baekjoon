import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static int R,C;
	static boolean[][] map;
	static boolean[][] laterMap;
	static StringBuilder sb;
	
	static class Position {
		int r;
		int c;
		
		Position(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R+2][C+2];
		laterMap = new boolean[R+2][C+2];
		List<Position> landList = new ArrayList<>(); 
		
		for(int i=1, endi=R; i<=endi; i++) {
			char[] chars = br.readLine().toCharArray();
			for(int j=1, endj=C; j<=endj; j++) {
				if(chars[j-1]=='X') {
					map[i][j] = true;
					landList.add(new Position(i,j));
				}
			}//for j
		}//for i
		
		int minR = Integer.MAX_VALUE;
		int maxR = Integer.MIN_VALUE;
		int minC = Integer.MAX_VALUE;
		int maxC = Integer.MIN_VALUE;
		for(Position p : landList) {
			if(isSinked(p)) {
				laterMap[p.r][p.c] = true;
				minR = Math.min(minR, p.r);
				maxR = Math.max(maxR, p.r);
				minC = Math.min(minC, p.c);
				maxC = Math.max(maxC, p.c);
			}
		}
		printLaterMap(minR, maxR, minC, maxC);
	}

	private static void printLaterMap(int minR, int maxR, int minC, int maxC) {
		for(int i=minR, endi=maxR; i<=endi; i++) {
			for(int j=minC, endj=maxC; j<=endj; j++) {
				sb.append(laterMap[i][j]?'X':'.');
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
	}

	private static boolean isSinked(Position p) {
		boolean isLand=false;
		for(int i=0; i<4; i++) {
			if(map[p.r+dr[i]][p.c+dc[i]]) {
				if(isLand) {
					return true;
				}
				isLand=true;
			}
		}
		return false;
	}
}