import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	static int N;				//재료 갯수
	static int L;				//제한 칼로리
	static int[] subset;		//재료 조합 (index를 저장)
	static int[] kcal;			//재료 칼로리
	static int[] score;			//재료 점수
	static int max;				//제한 칼로리를 만족하는 최고 점수
	static boolean[] selected;	//선택 여부
	
	static void perm(int cnt) {
		//종료 조건
		if(cnt == N) {
			int kcalTmp = 0;
			int scoreTmp = 0;
			
			for(int i=0; i<N; i++) {
				if(selected[i]) {
					kcalTmp += kcal[i];
					scoreTmp += score[i];
				}
			}
			
			if(L>=kcalTmp) {
				max = Math.max(max,  scoreTmp);
			}
			return;
		}
		
		//원소를 선택한 경우
		selected[cnt] = true;
		perm(cnt+1);
		
		//원소를 선택하지 않은 경우
		selected[cnt] = false;
		perm(cnt+1);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TC = Integer.parseInt(br.readLine());
		String[] s;
		for(int T=1; T<=TC; T++) {
			s=br.readLine().split(" ");
			N = Integer.parseInt(s[0]);
			L = Integer.parseInt(s[1]);
			max = 0;
			subset = new int[N];
			selected = new boolean[N];
			score = new int[N];
			kcal = new int[N];
			
			for(int i=0; i<N; i++) {
				s=br.readLine().split(" ");
				score[i] = Integer.parseInt(s[0]);
				kcal[i] = Integer.parseInt(s[1]);
			}
			
			perm(0);
			
			bw.write("#"+T+" "+max+"\n");
		}
		bw.flush();
	}
}