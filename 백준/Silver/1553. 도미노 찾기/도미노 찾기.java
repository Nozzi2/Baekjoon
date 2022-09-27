import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

	static int R = 8;
	static int C = 7;
	static int cnt;
	static Domino[] doms;				//도미노들을 담을 배열
	static boolean[] used;				//리스트 vs 불린배열 뭐가 더 빠를까?
	static List<Integer> usedList;		//불린배열 vs 리스트 뭐가 더 빠를까?
	static int[][] map;
	static boolean[][] visited;
	
	static class Domino {
		int ri;
		int le;
		
		public Domino(int ri, int le) {
			this.ri = ri;
			this.le = le;
		}

		@Override
		public String toString() {
			return "Domino [ri=" + ri + ", le=" + le + "]";
		}
		
		public void returnTrue(int r, int c, int cnt) {
			switch(cnt) {
			case 0:
				visited[r][c]= false;
				visited[r][c+1]= false;
				break;
			case 1:
				visited[r][c]= false;
				visited[r+1][c]= false;
				break;
			case 2:
				visited[r][c]= false;
				visited[r][c+1]= false;
				break;
			case 3:
				visited[r][c]= false;
				visited[r+1][c]= false;
				break;
			}
		}
		
		public boolean isFit(int r, int c, int cnt) {
			//정방향 가로> 정방향 세로> 역방향 가로> 역방향 세로
			switch(cnt) {
			case 0:
				return isFitHorFor(r,c);
			case 1:
				return isFitVerFor(r,c);
			case 2:
				return isFitHorRev(r,c);
			case 3:
				return isFitVerRev(r,c);
			}
			return false;
		}
		
		//가로 정방향 체크
		public boolean isFitHorFor(int r, int c) {
			//범위를 벗어나면
			if(c+1>C) return false; 
			
			//블럭 검사하려는 두 칸이 모두 도미노가 놓여있으면 안됨
			if(!visited[r][c] && !visited[r][c+1]) {
				//값들이 일치한다면
				if(map[r][c] == ri && map[r][c+1] == le) {
					visited[r][c]= true;
					visited[r][c+1]= true;
					return true;
				}
			}
			return false;
		}

		//세로 정방향 체크
		public boolean isFitVerFor(int r, int c) {
			//범위를 벗어나면
			if(r+1>R) return false; 
			
			//블럭 검사하려는 두 칸이 모두 도미노가 놓여있으면 안됨
			if(!visited[r][c] && !visited[r+1][c]) {
				//값들이 일치한다면
				if(map[r][c] == ri && map[r+1][c] == le) {
					visited[r][c]= true;
					visited[r+1][c]= true;
					return true;
				}
			}
			return false;
		}
		
		//가로 역방향 체크
		public boolean isFitHorRev(int r, int c) {
			//범위를 벗어나면
			if(c+1>C) return false; 
			
			//블럭 검사하려는 두 칸이 모두 도미노가 놓여있으면 안됨
			if(!visited[r][c] && !visited[r][c+1]) {
				//값들이 일치한다면
				if(map[r][c] == le && map[r][c+1] == ri) {
					visited[r][c]= true;
					visited[r][c+1]= true;
					return true;
				}
			}
			return false;
		}
		
		//세로 역방향 체크
		public boolean isFitVerRev(int r, int c) {
			//범위를 벗어나면
			if(r+1>R) return false; 
			
			//블럭 검사하려는 두 칸이 모두 도미노가 놓여있으면 안됨
			if(!visited[r][c] && !visited[r+1][c]) {
				//값들이 일치한다면
				if(map[r][c] == le  && map[r+1][c] == ri) {
					visited[r][c]= true;
					visited[r+1][c]= true;
					return true;
				}
			}
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		doms = new Domino[28];
		usedList = new LinkedList<Integer>(); //리스트 vs 불린배열 뭐가 더 빠를까?
		used = new boolean[28]; //불린배열 vs 리스트 뭐가 더 빠를까?
		map = new int[8+2][7+2];
		visited = new boolean[8+2][7+2];
		cnt=0;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//도미노 배열에 도미노 담기
		int idx=0;
		for(int i=0; i<=6; i++) {
			for(int j=i; j<=6; j++) {
				doms[idx++] = new Domino(i,j);
			}//for j
		}//for i
		
		//List에 사용 안한 도미노의 index 담기
		for(int i=0; i<28; i++) {
			usedList.add(i);
		}
		
//		//도미노를 놓을 map 저장
//		for(int i=1; i<=8; i++) {
//			for(int j=1; j<=7; j++) {
//				map[i][j] = System.in.read()-48;
//			}
//			System.in.read(); //개행처리
//			System.in.read(); //개행처리
//		}
		
		//도미노를 놓을 map 저장
		for(int i=1; i<=8; i++) {
			String s = br.readLine();
			for(int j=1; j<=7; j++) {
				map[i][j] = s.charAt(j-1)-48;
			}
		}
		
//		for(int i=1; i<=8; i++) {
//			for(int j=1; j<=7; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}
//		
		dfs(0);
		
		System.out.println(cnt);
	}

	private static void dfs(int rc) {
		if(rc==56) { //모든 칸을 순회했다면
			cnt++;
			return;
		}
		
		int r = (rc/C)+1;
		int c = (rc%C)+1;
		if(visited[r][c]) {
			dfs(rc+1);
		}
		for(int i=0, endi=used.length; i<endi; i++) { //모든 블럭을 놓아보면서 맞는지 탐색
			if(!used[i]) { //사용된 블럭이 아니라면
				Domino d = doms[i];
				int max=0;
				if(d.ri == d.le) { //도미노 양쪽 숫자가 같으면 역방향은 하지 않아도 됨
					max=2; //정가로, 정세로
				} else {
					max=4; //정가로, 정세로, 역가로, 역세로
				}//if else
				for(int j=0; j<max; j++) { //max 만큼 진행하기 때문에 불필요한 탐색 줄일 수 있음
					if(d.isFit(r, c, j)) { //j의 값만큼 도미노 블럭 가로세로, 정역방향 놓아보기
						used[i] = true; //해당 블록 사용 처리
						dfs(rc+1);
						d.returnTrue(r, c, j); //visited배열 되돌려놓기
						used[i] = false; //해당 블록 사용 되돌려 놓기
					}//if
				}//for j
			}//if
		}//for i
	}
}