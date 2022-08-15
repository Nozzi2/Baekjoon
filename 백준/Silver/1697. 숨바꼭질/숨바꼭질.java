import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dn = {-1, 1, 2};
	static int N;			//수빈이 위치
	static int target;		//동생 위치
	static int sec;			//수빈이가 이동한 시간
	static boolean[] visited;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		
		//visited = new boolean[Math.max(N+2, target+2)];
		visited = new boolean[200010];
		
		bfs(N);
		
		System.out.println(sec);
	}

	private static void bfs(int cur) {
		if(target==cur) return;
		Queue<Integer> que = new LinkedList<>();
		int size = visited.length;
		que.offer(cur);
		visited[cur] = true;
		
		while(!que.isEmpty()) {
			sec++;
			for(int i=0, end=que.size(); i<end; i++) {
				int next = que.poll();
				
				for(int j=0, endj=3; j<endj; j++) {
					int nn;
					if(j<2) {
						nn = next+dn[j];
					} else {
						nn = next*dn[j];
					}
					if(nn==target) {
						return;
					}
					if(nn<=0 || nn>=size || visited[nn]) {
						continue;
					}
					que.offer(nn);
					visited[nn] = true;
				}
			}
		}
	}
}