import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[][] matrix;
	static boolean[] availRoute;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int routeCnt;
		int[] route;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		routeCnt = Integer.parseInt(br.readLine());
		matrix = new int[N+2][N+2];
		availRoute = new boolean[N+1];
		route = new int[routeCnt];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				matrix[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<routeCnt; i++) {
			route[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean isComplete = true;
		if(routeCnt>1) {
			for(int i=1; i<routeCnt; i++) {
				int start=route[i-1];
				int end=route[i];
				if(!availRoute[start] || !availRoute[end]) { //둘중 하나라도 방문하지 못했다면
					List<Integer> list = bfs(start, end); //start로부터 할 수 있는 모든 탐색 진행
					if(!list.contains(end)) { //탐색실패하면 (방문한 모든 경로에서 end가 없으면 탐색 실패임)
						isComplete = false;
						break;
					} else { //탐색 성공하면
						setRouteTrue(list); //방문한 경로 모두 true처리
					}//if
				}//if 
			}//for i
		}
		
		
		if(isComplete) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	private static void setRouteTrue(List<Integer> list) {
		for(int n : list) {
			availRoute[n] = true;
		}
	}

	private static List<Integer> bfs(int start, int end) {
		boolean[] visited = new boolean[N+1];
		Queue<Integer> que = new LinkedList<>();
		List<Integer> list = new ArrayList<>();
		que.offer(start);
		list.add(start);
		visited[start] = true;
		while(!que.isEmpty()) {
			int r=que.poll();
			for(int i=1; i<=N; i++) {
				if(visited[i]) continue;
				if(matrix[r][i]==0) continue;
				que.add(i);
				list.add(i);
				visited[i] = true;
			}
		}
		return list;
	}

}