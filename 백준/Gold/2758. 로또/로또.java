import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static long[][] memo;
	static int[] minArr, maxArr;
	static int n,m;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		
		for(int T=0; T<TC; T++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			long cnt=0;
			
			//로또로 뽑을수 있는 가장 낮은 값이 m의 범위보다 클때
//			if(1 << (n-1) > m) {
//				//경우의 수는 한개도 없음
//				sb.append(0+"\n");
//				continue;
//			}
			
			memo = new long[n+1][m+1];
			minArr = new int[n+1];
			maxArr = new int[n+1];
			
			//경우의 수 중에서 각 자릿수별로 가장 작은 값들의 배열 저장
			for(int i=1; i<=n; i++) {
				minArr[i] = 1<<(i-1);
			}
			
			//경우의 수 중에서 각 자릿수별로 가장 큰 값들의 배열 저장
			int tmpM = m;
			for(int i=n; i>0; i--) {
				maxArr[i] = tmpM;
				tmpM /=2;
			}
			
//			for(int a : minArr) {
//				System.out.println(a);
//			}
//			System.out.println();
//			for(int a : maxArr) {
//				System.out.println(a);
//			}
			
			recursive(n,m);
			
//			for(int i=1; i<memo.length; i++) {
//				for(int j=1; j<memo[i].length; j++) {
//					System.out.print(memo[i][j]+" ");
//				}
//				System.out.println();
//			}
			
			for(int i=1; i<=maxArr[1]; i++) {
				cnt+=memo[1][i];
			}
			
			sb.append(cnt+"\n");
		}
		System.out.print(sb);
	}

	private static void recursive(int pn, int pm) {
//		System.out.println("호출됨"+pn+","+pm);
//		System.out.println(minArr[pn]+"~"+maxArr[pn]);
		//종료조건
		if(pn<1) return;
		
		//가장 마지막 로또번호들의 경우의 수 1로 갱신
		if(pn==n) {
			//최소값 ~ 최대값 사이값이라면
			if(minArr[pn] <= pm && pm <= maxArr[pn]) {
				memo[pn][pm]=1;
				recursive(pn,pm-1);
			} else { //다음 자릿수 계산하기
				recursive(pn-1,maxArr[pn-1]);
			}
		} else {
			if(minArr[pn] <= pm && pm <= maxArr[pn]) {
				for(int i=pm*2; i<=maxArr[pn+1]; i++) {
//					System.out.print("  "+memo[pn][pm]);
					memo[pn][pm]+=memo[pn+1][i];
//					System.out.println("+"+memo[pn+1][i]);
				}
//				System.out.println("  => "+memo[pn][pm]);
				recursive(pn,pm-1);
			} else { //다음 자릿수 계산하기
				recursive(pn-1,maxArr[pn-1]);
			}
		}
		
	}
}