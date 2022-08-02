import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//2.2초까지 줄일 수 있음
public class Solution {
	static int[] arrA; 
	static int[] arrB;
	static int[] numbers; //arrB로 만든 순열
	static boolean[] selectedA; //A 배열에서 선택된 숫자 > arrB 만들때 사용
	static boolean[] selectedNums; //numbers 배열에서 선택된 index > numbers 만들때 사용
	static int scoreA;
	static int scoreB;
	static final int sumWin = 86; //(1~18의 총합계/2)+1 즉, 이기기 위한 최소의 점수
	static int N=18;
	static int R=9;
	
	public static void game(int cnt) {
		if(cnt==R) { //종료조건
			int sum=0; //A의 점수 합계
			for(int i=0; i<R; i++) {
				if(arrA[i] > numbers[i]) { //A가 이겼을 경우
					sum+=arrA[i]+numbers[i];
				}
			}
			if(sum>=sumWin) {
				scoreA++;
			} else {
				scoreB++;
			}
			return;
		}
		
		for(int i=0; i<R; i++) {
			if(selectedNums[i]) {
				continue;
			}
			numbers[cnt] = arrB[i];
			selectedNums[i] = true;
			game(cnt+1);
			selectedNums[i] = false;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		
		int TC = Integer.parseInt(br.readLine());
		for(int T=1; T<=TC; T++) {
			long start = System.currentTimeMillis();
			StringTokenizer st = new StringTokenizer(br.readLine());
			arrA = new int[R];
			arrB = new int[R];
			numbers = new int[R];
			selectedA = new boolean[N+1];
			selectedNums = new boolean[R+1];
			scoreA=0;
			scoreB=0;
			
			for(int i=0; i<R; i++) {
				arrA[i] = Integer.parseInt(st.nextToken());
				selectedA[ arrA[i] ] = true;
			}
			
			int idx=0;
			for(int i=1; i<=N; i++) {
				if(!selectedA[i]) { //A에 포함되지 않은 숫자일 경우
					arrB[idx++] = i;
				}
			}
			
			game(0);
			bw.write("#"+T+" "+scoreA+" "+scoreB+"\n");
			//System.out.printf("#%d %d %d%n", T, scoreA, scoreB); //시간:35ms 시간:15ms
//			long end = System.currentTimeMillis();
//			System.out.printf("시간:%dms%n", end-start );
		}
		bw.flush(); //시간:656ms
		
	}

}