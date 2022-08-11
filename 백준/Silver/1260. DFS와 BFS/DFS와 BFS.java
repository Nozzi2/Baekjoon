import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[][] arr;
	static boolean[] selected;
	//static BufferedWriter bw;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int TC = Integer.parseInt(st.nextToken());
		int root = Integer.parseInt(st.nextToken());
		selected = new boolean[N+1];
		arr = new boolean[N+1][N+1];
		
		for(int T=0; T<TC; T++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[r][c] = true;
			arr[c][r] = true;
		}
		
		dfs(root);
		selected = new boolean[N+1];
		System.out.println(sb.toString().trim());
		sb.setLength(0);
		//bw.write("\n");
		sb.append("\n");
		bfs(root);
		System.out.println(sb.toString().trim());
		
		//bw.flush();
	}

	//깊이 우선 탐색
	private static void dfs(int cur) throws IOException {
		//bw.write(cur+" ");
		sb.append(cur+" ");
		selected[cur] = true;
		
		for(int i=1; i<=N; i++) {
			if(!selected[i] && arr[cur][i]) {
				dfs(i);
			}
		}
		
	}
	
	//너비 우선 탐색
	private static void bfs(int cur) throws IOException {
		Queue<Integer> queue = new LinkedList<Integer>();
		int cnt=0;
		queue.offer(cur);
		
		while(!queue.isEmpty()) {
			int next = queue.poll();
			//bw.write(next+" ");
			if(!selected[next]) {
				sb.append(next+" ");
				selected[next] = true;
				if(++cnt==N) break;
			}
			
			for(int i=1; i<=N; i++) {
				if(!selected[i] && arr[next][i]) {
					queue.offer(i);
				}
			}
			
		}
	}
	
}