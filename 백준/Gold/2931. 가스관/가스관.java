import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int[][] roadBlocks = {
									{1,0,1,0}, //5 |
									{0,1,0,1}, //10 -
									{1,1,1,1}, //15 +
									{0,1,1,0}, //6 1
									{1,1,0,0}, //3 2
									{1,0,0,1}, //9 3
									{0,0,1,1}, //12 4
									{0,0,0,0}  //0
								};
	
	static class Block {
		int[] roads = new int[4];
		
		public Block(int[] arr) {
			roads[0] = arr[0];
			roads[1] = arr[1];
			roads[2] = arr[2];
			roads[3] = arr[3];
		}

		@Override
		public String toString() {
			return "Block [roads=" + Arrays.toString(roads) + "]";
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		char[][] map;
		int R,C,TC, outR, outC, sr, sc, er, ec;
		sr=sc=er=ec=0;
		 
		
		outR=outC=-1;
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map=new char[R][C];
		Block[][] blocks = new Block[R][C];
		
		//블럭 입력받기
		for(int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<C; j++) {
				switch(map[i][j]) {
				case'|':
					blocks[i][j] = new Block(roadBlocks[0]);
					break;
				case'-':
					blocks[i][j] = new Block(roadBlocks[1]);
					break;
				case'+':
					blocks[i][j] = new Block(roadBlocks[2]);
					break;
				case'1':
					blocks[i][j] = new Block(roadBlocks[3]);
					break;
				case'2':
					blocks[i][j] = new Block(roadBlocks[4]);
					break;
				case'3':
					blocks[i][j] = new Block(roadBlocks[5]);
					break;
				case'4':
					blocks[i][j] = new Block(roadBlocks[6]);
					break;
				case'M':
					sr = i;
					sc = j;
					blocks[i][j] = new Block(roadBlocks[2]);
					break;
				case'Z':
					er = i;
					ec = j;
					blocks[i][j] = new Block(roadBlocks[2]);
					break;
				default:
					blocks[i][j] = new Block(roadBlocks[7]);
					break;
				}
			}
		}
		
//		//M,Z 칸의 상하좌우는 길이 뚫려있다고 가정하고 block 갱신
//		for(int k=0; k<4; k++) { //M칸 갱신
//			int nr = sr+dr[k];
//			int nc = sc+dc[k];
//			int nk = (k+2)%4;
//			
//			if(nr<0 || nc<0 || nr>=R || nc>=C) continue; 
//			if(blocks[nr][nc].roads[nk]==1) {
//				blocks[nr][nc].roads[nk]=3;
//			} else {
//				blocks[nr][nc].roads[nk]=-1;
//			}//if else
//		}//for k
//		for(int k=0; k<4; k++) { //Z칸 갱신
//			int nr = er+dr[k];
//			int nc = ec+dc[k];
//			int nk = (k+2)%4;
//			
//			if(nr<0 || nc<0 || nr>=R || nc>=C) continue; 
//			if(blocks[nr][nc].roads[nk]==1) {
//				blocks[nr][nc].roads[nk]=3;
//			} else {
//				blocks[nr][nc].roads[nk]=-1;
//			}//if else
//		}//for k
		
		
		
		//모든칸 길이 통하는지 검사
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]!='.' && map[i][j]!='M' && map[i][j]!='Z') {
					for(int k=0; k<4; k++) {
						if(blocks[i][j].roads[k]==1) {
							int nr = i+dr[k];
							int nc = j+dc[k];
							int nk = (k+2)%4;
							if( (nr==sr && nc==sc) || (nr==er && nc==ec))
								continue;
							
							if(blocks[nr][nc].roads[nk]==1) {
								blocks[nr][nc].roads[nk]=3;
							} else {
								if(outR==-1) {
									outR = nr;
									outC = nc;
								}
								blocks[nr][nc].roads[nk]=-1;
							}//if else
						}//if
					}//for k
				}//if
			}//for j
		}//for i
		
		//탐색이 끝나고도 지워진 블럭을 찾지 못했다면
		if(outR==-1) {
			//M,Z 칸의 상하좌우는 길이 뚫려있다고 가정하고 block 갱신
			for(int k=0; k<4; k++) { //M칸 갱신
				int nr = sr+dr[k];
				int nc = sc+dc[k];
				int nk = (k+2)%4;
				
				if(nr<0 || nc<0 || nr>=R || nc>=C) continue; 
				if(blocks[nr][nc].roads[nk]==1) {
					blocks[nr][nc].roads[nk]=3;
				} else {
					blocks[nr][nc].roads[nk]=-1;
				}//if else
			}//for k
			for(int k=0; k<4; k++) { //Z칸 갱신
				int nr = er+dr[k];
				int nc = ec+dc[k];
				int nk = (k+2)%4;
				
				if(nr<0 || nc<0 || nr>=R || nc>=C) continue; 
				if(blocks[nr][nc].roads[nk]==1) {
					blocks[nr][nc].roads[nk]=3;
				} else {
					blocks[nr][nc].roads[nk]=-1;
				}//if else
			}//for k
			
			
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					if(map[i][j]!='.') continue;
					int cnt=0;
					for(int k=0; k<4; k++) {
						cnt +=blocks[i][j].roads[k]==-1?1:0; 
					}
					if(cnt>=2) {
						outR = i;
						outC = j;
						break;
					}
				}//for j
			}//for i
		}
		
		int tmp=1;
		int out=0;
		for(int i=0; i<4; i++) {
			if(blocks[outR][outC].roads[i]==-1) {
				out+=tmp;
			}
			tmp*=2;
		}
		
		char result='-';
		switch(out) {
		case 5:
			result='|';
			break;
		case 10:
			result='-';
			break;
		case 15:
			result='+';
			break;
		case 6:
			result='1';
			break;
		case 3:
			result='2';
			break;
		case 9:
			result='3';
			break;
		case 12:
			result='4';
			break;
		default :
			System.out.println("여기에 결과값 없는데?");
			break;
		}
//		System.out.println(out);
		sb.append((outR+1)+" "+(outC+1)+" "+((char)result)+"\n");
		System.out.print(sb);
	}

	private static void pringMap(char[][] map, int R,int C) {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		
	}
}