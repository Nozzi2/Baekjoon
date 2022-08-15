import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R;
	static int min;
	static int[][] map;
	static int[] numbers;
	static boolean[] selected;
	static position[] inputs;
	static int index=0;
		
	static class position{
		int r;
		int c;
		int s;
		
		position(int r, int c){
			this.r=r;
			this.c=c;
		}
		
		position(int r, int c, int s){
			this.r=r;
			this.c=c;
			this.s=s;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int sizeR = Integer.parseInt(st.nextToken());
		int sizeC = Integer.parseInt(st.nextToken());
		R =Integer.parseInt(st.nextToken());
		map = new int[sizeR+1][sizeC+1];
		inputs = new position[R];
		numbers = new int[R];
		selected = new boolean[R];
		min = Integer.MAX_VALUE;
		
		for(int i=1, end=sizeR; i<=end; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1, endj=sizeC; j<=endj; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int T=0, end=R; T<end; T++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			inputs[T] = new position(r,c,s);
		}
		
		comb(0);
		
		System.out.println(min);
	}

	//조합구하고, 나온 조합으로 최소값 구하는 메소드
	private static void comb(int cnt) {
		if(cnt==R) {
			//나온 조합으로 최소값 구하기
			int[][] tmpArr = copyArray(map);
			for(int i=0, end=R; i<end; i++) {
				tmpArr = spin(inputs[ numbers[i] ], tmpArr);
			}
			int tempMin = minMap(tmpArr);
			min = Math.min(tempMin, min);
			return;
		}
		
		for(int i=0, end=R; i<end; i++) {
			if(selected[i]) continue;
			numbers[cnt] = i;
			selected[i] = true;
			comb(cnt+1);
			selected[i] = false;
		}
		
	}

	//배열의 최소값을 구하는 메소드
	private static int minMap(int[][] arr) {
		int min_local = Integer.MAX_VALUE;
		for(int i=1, endi=arr.length; i<endi; i++) {
			int sum=0;
			for(int j=1, endj=arr[0].length; j<endj; j++) {
				sum+=arr[i][j];
			}
			min_local=Math.min(min_local, sum);
		}
		return min_local;
	}

	//회전시킨 배열을 복사한 배열에 저장하는 메소드
	private static int[][] spin(position p, int[][] paramArr) {
		int[][] cmap = copyArray(paramArr);
		for(int i=p.r-p.s, endi=p.r+p.s; i<=endi; i++) {
			for(int j=p.c-p.s, endj = p.c+p.s; j<=endj; j++) {
				position pos = rotate(new position(i,j), new position(p.r,p.c));
				cmap[pos.r][pos.c] = paramArr[i][j];
			}
		}
		
		return cmap;
	}
	
	//알고리즘에 따라 배열의 index를 회전시켜주는 메소드
	private static position rotate(position cur, position cent) {
		int cr = cur.r;
		int cc = cur.c;
		
		int levelR = Math.abs(cr-cent.r);
		int levelC = Math.abs(cc-cent.c);
		int level = Math.max(levelR, levelC);
		
		int sr = cent.r-level;
		int sc = cent.c-level;
		int er = cent.r+level;
		int ec = cent.c+level;
		
		if(sr==cr && sc<=cc && cc<ec) {
			cc++;
		} else if(er==cr && sc<cc && cc<=ec) {
			cc--;
		} else if(sc==cc && sr<cr && cr<=er) {
			cr--;
		} else if(ec==cc && sr<=cr && cr<er) {
			cr++;
		}
		
		position rot = new position(cr,cc);
		
		return rot;
	}

	//배열을 깊은 복사해준다. (얕은 복사하면 원래의 값을 바꾸게 됨)
	private static int[][] copyArray(int[][] arr){
		int[][] cmap = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) { // 반복문 + ArrayCopy
			System.arraycopy(arr[i], 0, cmap[i], 0, cmap[i].length);
		}
		
		return cmap;
	}
}


/* 배열은 주소값을 갖는 객체이기 때문에 얕은 복사를 하면 복사한 배열은 원래 배열과 항상 같은 값을 갖는다.
 * 따라서 깊은 복사를 하여 배열이 갖고 있는 값을 복사해야한다.
		int[][] arr1 = new int[2][2];
		int a=1;
		for(int i=0, endi=2; i<endi; i++) {
			for(int j=0, endj=2; j<endj; j++) {
				arr1[i][j] = a++;
			}
		}
		int[][] arr2 = new int[arr1.length][arr1[0].length];
		for (int i = 0; i < arr1.length; i++) { // 반복문 + ArrayCopy
			System.arraycopy(arr1[i], 0, arr2[i], 0, arr2[i].length);
		}

*/