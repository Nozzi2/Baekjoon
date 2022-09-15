import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int max;
	static int N;
	static List<Egg> eggs;
	
	static class Egg {
		int num;
		int dur;
		int weight;
		
		public Egg(int num, int dur, int weight) {
			this.num = num;
			this.dur = dur;
			this.weight = weight;
		}
		
		//계란이 깨졌는지 검사
		public boolean isBreak() {
			if(dur<=0) return true;
			return false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		max = Integer.MIN_VALUE;
		N = Integer.parseInt(br.readLine());
		eggs = new ArrayList<Egg>();
		
		//계란 정보 저장
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int dur = Integer.parseInt(st.nextToken());
			int weight= Integer.parseInt(st.nextToken());
			eggs.add(new Egg(i,dur, weight));
		}
		
		dfs(0);
		
		System.out.println(max==Integer.MIN_VALUE? 0 : max);
	}

	private static void dfs(int cnt) {
		if(cnt==N) {
			int bcnt=0;
			String s = "";
			for(Egg e : eggs) { //몇개 깨졌는지 검사
				if(e.isBreak()) bcnt++;
			}
			max = Math.max(max, bcnt); //깨진 최대값 갱신
			return;
		}
		
		//손에 들기
		Egg e1 = eggs.get(cnt);
		if(e1.isBreak()) {
			dfs(cnt+1);
			return;
		}
		
		Egg e2=null;
		//dfs를 수행해서 손에 들고 있는 계란으로 모든 계란을 쳐보는 경우를 계산할 수 있음
		for(int i=0; i<N; i++) {
			e2 = eggs.get(i); //깨려는 계란 설정
			if(cnt == i) continue; //손에 들고있는 것과 같은 계란을 깰 수 없음
			if(e2.isBreak()) continue; //깨져있으면 다른 계란 골라야함 
			hitTwoEgg(e1,e2);
			dfs(cnt+1);
			returnTwoEgg(e1,e2);
		}
		
		if(cnt!=0) { //첫번째는 무조건 반영되야 하므로 복원된 결과를 호출하면 안된다!
			dfs(cnt+1); //칠것이 없는경우에는 다음 것을 치도록한다
		}
		
	}

	private static void hitTwoEgg(Egg e1, Egg e2) {
		e1.dur -= e2.weight;
		e2.dur -= e1.weight;
	}
	
	private static void returnTwoEgg(Egg e1, Egg e2) {
		e1.dur += e2.weight;
		e2.dur += e1.weight;
	}
}