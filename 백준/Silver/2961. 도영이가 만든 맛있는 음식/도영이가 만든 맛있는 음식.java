import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static long min; //맛의 차이를 저장할 변수
					 //1,000,000,000 이 10번 등장하면 int범위를 초과한다.
	static int[] sours; //신맛 저장할 변수
	static int[] bitters; //쓴맛 저장할 변수
	static boolean[] selected;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine());
		min = Long.MAX_VALUE;
		
		sours = new int[N];
		bitters = new int[N];
		selected = new boolean[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			sours[i] = Integer.parseInt(st.nextToken());
			bitters[i] = Integer.parseInt(st.nextToken());
		}
		
		subset(0,0);
		
		System.out.println(min);
	}

	private static void subset(int cnt, int start) {
		if(cnt==N) {
			long bsum=0; //쓴맛의 합계.
			long ssum=1; //신맛의 합계. 합계는 곱으로 계산되므로 1로 초기화
			boolean isCalced = false; //공집합일떈 계산하면 안되므로
			for(int i=0; i<N; i++) {
				if(selected[i]) {
					bsum += bitters[i]; //쓴맛에 합계에 더해준다
					ssum *= sours[i];  //신맛의 합계에 곱해준다
					isCalced = true; //계산 수행했으니 최소값에 반영할 수 있다
				}
			}
			if(isCalced) { //계산했었는지?
				min = Math.min(min, Math.abs(ssum-bsum));
			}
			return;
		}
		
		for(int i=start; i<N; i++) {
			selected[cnt] = true;
			subset(cnt+1, i+1);
			selected[cnt] = false;
			subset(cnt+1, i+1);
		}
	}
}