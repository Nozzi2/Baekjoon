import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dr= {-1, 1, 0, 0};
	static int[] dc= {0, 0, -1, 1};
	
	static int R,C,T,air1,air2;
	static int mapT[][];
	static int mapF[][];
	
	
	
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("res/17144boj.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		air1 = Integer.MAX_VALUE;
		air2 = Integer.MAX_VALUE;
		
		mapT = new int[R+2][C+2];
		mapF = new int[R+2][C+2];
		
		for(int i=1; i<=R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=C; j++) {
				mapF[i][j] = Integer.parseInt(st.nextToken());
				if(mapF[i][j]==-1) {
					if(air1 == Integer.MAX_VALUE) {
						air1 = i;
					} else {
						air2 = i;
					}
				}
			}
		}
		
		for(int n=0; n<T; n++) {
			mapT = copyArray(mapF);
			boolean flag = false;
			//먼지확산 (mapT에 저장)
			for(int i=1; i<=R; i++) {
				for(int j=1; j<=C; j++) {
					if(mapT[i][j]>=5) {
						expandDirt(i,j, flag);					
					}
				}
			}

			//회전 (mapF에 저장)
			for(int i=1; i<=R; i++) {
				for(int j=1; j<=C; j++) {
					int[] rc = rotate(i,j);
					mapF[rc[0]][rc[1]] = mapT[i][j];
				}
			}
			mapF[air1][2]=0;
			mapF[air2][2]=0;
		}
		
		printSum();
	}//main end

	private static void printSum() {
		int sum=0;
		for(int i=1, endi=R; i<=endi; i++) {
			for(int j=1, endj=C; j<=endj; j++) {
				sum+=mapF[i][j];
			}
		}
		System.out.println(sum+2);
	}

	private static int[] rotate(int r, int c) {
		int nr=r;
		int nc=c;
		if((r==1&&(1<c && c<=C)) || (r==R&&(1<c && c<=C))){
			nc--;
		} else if((r==air1&&(1<c && c<C)) || (r==air2&&(1<c && c<C))){
			nc++;
		} else if((c==C&&(1<r && r<=air1)) || (c==1&&(air2+1<r && r<=R))){
			nr--;
		} else if((c==1&&(1<=r && r<air1)) || (c==C&&(air2<=r && r<R))){
			nr++;
		}
		
		int[] arr = {nr, nc};
		
		return arr;
	}

	
	
	
	
	private static int expandDirt(int r, int c, boolean flag) {
		int cnt=0;
		int sum=0;
		if(flag) {
			for(int i=0; i<4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(nr==0 || nc==0 || nr>R || nc>C || mapT[nr][nc]==-1) {
					continue;
				}
				mapF[nr][nc] += mapT[r][c]/5;
				sum+=mapT[r][c]/5;
			}
			mapF[r][c]-=sum;
		} else {
			for(int i=0; i<4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(nr==0 || nc==0 || nr>R || nc>C || mapF[nr][nc]==-1) {
					continue;
				}
				mapT[nr][nc] += mapF[r][c]/5;
				sum+=mapF[r][c]/5;
			}
			mapT[r][c]-=sum;
		}
		
		return cnt;
	}



	private static int[][] copyArray(int[][] arr) {
		int[][] carr = new int[arr.length][arr[0].length];
		for(int i=0, endi=carr.length; i<endi; i++) {
			for(int j=0, endj=carr[0].length; j<endj; j++) {
				carr[i][j] = arr[i][j];
			}
		}
		
		return carr;
	}



	private static void printMap(boolean flag) {
		System.out.println("mapT");
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				System.out.printf("%3d",mapT[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("mapF");
		for(int i=1; i<=R; i++) {
			for(int j=1; j<=C; j++) {
				System.out.printf("%3d",mapF[i][j]);
			}
			System.out.println();
		}
		
	}
}
