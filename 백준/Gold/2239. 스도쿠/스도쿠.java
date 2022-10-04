import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[][] map;
	static boolean isPrinted;
	
	public static void main(String[] args) throws IOException {
		isPrinted = false;
		map = new int[9][9];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<9; i++) {
			String s = br.readLine();
			for(int j=0; j<9; j++) {
				map[i][j] = Integer.parseInt(s.substring(j, j+1));
			}
		}
		
		dfs(0);
	}
	
	private static void dfs(int cnt) {
		if(isPrinted) return;
		if(cnt==81) {
			print();
			isPrinted = true;
			return;
		}
		int r = cnt/9;
		int c = cnt%9;
		
		if(map[r][c]!=0) {
			dfs(cnt+1);
		} else {
			boolean[] impoNum = new boolean[10]; //불가능한 숫자를 저장하는 배열
			
			//가로방향 검사
			for(int i=0; i<9; i++) {
				if(map[r][i]!=0) { //0이 아닌 숫자들은
					impoNum[map[r][i]] = true; //값을 넣어볼 수 없음
				}
			}
			//세로방향 검사
			for(int i=0; i<9; i++) {
				if(map[i][c]!=0) { //0이 아닌 숫자들은
					impoNum[map[i][c]] = true; //값을 넣어볼 수 없음
				}
			}
			
			//9칸 검사
			int startR = 3*(r/3);
			int startC = 3*(c/3);
			for(int i=startR, endi=startR+3; i<endi; i++) {
				for(int j=startC, endj=startC+3; j<endj; j++) {
					if(map[i][j]!=0) { //0이 아닌 숫자들은
						impoNum[map[i][j]] = true; //값을 넣어볼 수 없음
					}
				}
			}
			
			//작은 숫자부터 넣어보면서 모든 경우의수 탐색
			for(int i=1; i<=9; i++) {
				if(!impoNum[i]) {
					map[r][c] = i;
					dfs(cnt+1);
					map[r][c] = 0;
				}
			}
		}
	}

	private static void print() {
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print(map[i][j]);
			}//for j
			System.out.println();
		}//for i
	}
}