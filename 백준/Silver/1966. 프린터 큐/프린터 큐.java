import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Doc {
		int index;
		int prior;
		
		public Doc(int index, int prior) {
			this.index = index;
			this.prior = prior;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for(int T=0; T<TC; T++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			int[] priors = new int[10];
			Queue<Doc> que = new LinkedList<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				int p = Integer.parseInt(st.nextToken());
				priors[p]++;
				que.add(new Doc(i, p));
			}
			
			int cnt=0;
			int topPrior = getTopPrior(priors);
			while(true) {
				Doc d = que.poll();
				if(d.prior == topPrior) {
					priors[topPrior]--;
					topPrior = getTopPrior(priors);
					cnt++;
					if(d.index == target) {
						sb.append(cnt).append("\n");
						break;
					}
				} else {
					que.add(d);
				}//if
			}//while
		}//for T
		
		System.out.print(sb);
	}

	private static int getTopPrior(int[] priors) {
		for(int i=priors.length-1; i>0; i--) {
			if(priors[i]>0) return i;
		}
		return 0;
	}

}