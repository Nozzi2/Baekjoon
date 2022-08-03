import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Solution {
	static char[][] map;
	static int curR;
	static int curC;
	static int dir;
	static int[] dr= {-1,1,0,0};
	static int[] dc= {0,0,-1,1};
	static String[] UDLR = {"U", "D", "L", "R"};
	static char[] tank= {'^', 'v', '<', '>'};
	
	static void move() {
		int tarR = curR + dr[dir];
		int tarC = curC + dc[dir];
		
		if(map[tarR][tarC] == '.') {
			map[curR][curC] = '.';
			curR = tarR;
			curC = tarC;
		}
		
		map[curR][curC] = tank[dir];
	}
	
	static void shoot() {
		int poR = curR;
		int poC = curC;
		boolean isHit = false;
		while(true) {
			poR += dr[dir];
			poC += dc[dir];
			//맵 벗어나면
			if(poR < 1 || poC < 1 || poR > (map.length-1) || poC > (map[0].length-1) ) {
				break;
			}
			char obj = map[poR][poC];
			switch(obj) {
			case('#'):
				isHit = true;
				break;
			case('*'):
				map[poR][poC] = '.';
				isHit = true;
				break;
			}
			if(isHit) break;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TC = Integer.parseInt(br.readLine());
		for(int T=1; T<=TC; T++) {
			String[] s = br.readLine().split(" ");
			int sizeR = Integer.parseInt(s[0]);
			int sizeC = Integer.parseInt(s[1]);
			map = new char[sizeR+2][sizeC+2];
			
			boolean isTank = false; //입력에서 탱크 찾았는지
			for(int i=1; i<=sizeR; i++) {
				char[] c = br.readLine().toCharArray();
				for(int j=1; j<=sizeC; j++) {
					map[i][j] = c[j-1];
					if(!isTank && (c[j-1]=='^' ||c[j-1]=='v' ||c[j-1]=='<' ||c[j-1]=='>') ) {
						curR = i;
						curC = j;
						switch(c[j-1]) {
						case('^'):
							dir=0;
							break;
						case('v'):
							dir=1;
							break;
						case('<'):
							dir=2;
							break;
						case('>'):
							dir=3;
							break;
						}//switch
						isTank = true;
					}//if
				}//for j
			}//for i
			
			
			int comNum = Integer.parseInt(br.readLine()); //명령어 갯수
			char[] coms = br.readLine().toCharArray();
			
			for(int i=0; i<comNum; i++) {
				char com = coms[i];
				switch(com) {
				case('S'):
					shoot();
					break;
				default:
					dir = Arrays.asList(UDLR).indexOf( Character.toString(com) );
					move();
					break;
				}
			}
			
			System.out.print("#"+T+" ");
			for(int i=1; i<=sizeR; i++) {
				for(int j=1; j<=sizeC; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			
		}
	}
}
