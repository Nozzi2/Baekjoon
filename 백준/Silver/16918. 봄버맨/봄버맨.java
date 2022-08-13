import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static char[][] map;
	
	static class position{
		int r;
		int c;
		
		position(int r, int c){
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sizeR = Integer.parseInt(st.nextToken());
		int sizeC = Integer.parseInt(st.nextToken());
		int option = Integer.parseInt(st.nextToken());
		map = new char[sizeR+2][sizeC+2];
		
		
		if(option%2 == 0) { //모든칸에 'O'출력
			for(int i=1; i<=sizeR; i++) {
				br.readLine(); //입력받아도 저장할 필요 없음
				for(int j=1; j<=sizeC; j++) {
					bw.write('O');
				}//for j
				bw.write("\n");
			}
		} else if(option%4==1) {
			if(option == 1) { //초기 입력과 동일하게 출력
				for(int i=1; i<=sizeR; i++) {
					bw.write(br.readLine()+"\n");
				}//for i
			} else { // 상하좌우가 모두 '.'인 '.'을 찾아서 출력할 때 그 칸과 그 주변만 '.'을 출력해야함.
				
				//입력
				for(int i=1; i<=sizeR; i++) {
					char[] chars = br.readLine().toCharArray();
					for(int j=1; j<=sizeC; j++) {
						map[i][j] = chars[j-1];
					}//for j
				}//for i
				
				//상하좌우 모두 '.'이거나 배열의 초기값인 '0'인 '.' 찾기
				List<position> safeList = new ArrayList<position>();
				for(int i=1; i<=sizeR; i++) {
					for(int j=1; j<=sizeC; j++) {
						if(isSafe(i,j)) {
							safeList.add(new position(i,j));
						}
					}//for j
				}//for i
				
				map = new char[sizeR+2][sizeC+2];
				
				//안전한 땅만 '.' 입력
				for(int i=0, end=safeList.size(); i<end; i++) {
					bomb(safeList.get(i)); //안전한 자리와 상하좌우에 '.'입력
				}
				
				for(int i=1; i<=sizeR; i++) {
					for(int j=1; j<=sizeC; j++) {
						if(map[i][j] == '.') {
							bw.write('.');
						}else {
							bw.write('O');
						}
					}//for j
					bw.write("\n");
				}//for i
			}
		} else if(option%4==3) { //폭발 후 상하좌우칸만 '.'출력 후 나머지는 전부 'O'출력
			//입력
			List<position> bombList = new ArrayList<position>();
			for(int i=1; i<=sizeR; i++) {
				char[] chars = br.readLine().toCharArray();
				for(int j=1; j<=sizeC; j++) {
					map[i][j] = 'O';	//전부 'O'로 채워넣고
					if(chars[j-1] == 'O') { //'O' 인덱스만 처리하기 위해 리스트에 저장
						bombList.add(new position(i,j));
					}
				}//for j
			}//for i
			
			//폭탄 처리
			for(int i=0, end=bombList.size(); i<end; i++) {
				bomb(bombList.get(i)); //폭탄자리와 상하좌우에 '.'입력
			}
			
			//출력
			for(int i=1; i<=sizeR; i++) {
				for(int j=1; j<=sizeC; j++) {
					bw.write(map[i][j]);
				}//for j
				bw.write("\n");
			}//for i
		}//if else
		bw.flush();
	}

	private static boolean isSafe(int r, int c) {
		if( map[r][c] == 'O') {
			return false;
		}
		boolean tmp = true;
		for(int i=0; i<4; i++) {
			if(!tmp) break;
			int nr = r+dr[i];
			int nc = c+dc[i];
			if(map[nr][nc]=='O') {
				tmp = false;
			}
		}
		return tmp;
	}

	private static void bomb(position pos) {
		map[pos.r][pos.c] = '.';
		
		for(int i=0; i<4; i++) {
			int nr = pos.r+dr[i];
			int nc = pos.c+dc[i];
			map[nr][nc] = '.';
		}
	}
}

/*
OOO.OOO
OO...OO
OOO...O
..OO.OO
...OOOO
...OOOO
*/