import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static boolean[] visited;
	static int[] map;
	static int diceCnt;
	static final int TARGET = 100;
	
	public static void main(String[] args) throws IOException {
		visited = new boolean[101];
		map = new int[101];
		diceCnt=0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int jumpCnt = Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken());
		
		for(int i=0; i<jumpCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a] = b;
		}
		
		bfs(1); //1칸부터 시작
		
		System.out.println(diceCnt);
	}

	private static void bfs(int start) {
		Queue<Integer> que = new LinkedList<>();
		que.offer(start);
		visited[start] = true;
		while(!que.isEmpty()) {
			diceCnt++;
			for(int i=0, endi=que.size(); i<endi; i++) {
				int cur = que.poll();
				
				for(int j=1; j<=6; j++) {
					int next = cur+j;
					if(visited[next] || next>TARGET) continue; //방문했거나 도착지보다 크면 탐색 중지
					if(next == TARGET) return; //도착했다면 끝낸다.
					visited[next] = true; //현재칸은 방문체크
					if(map[next]!=0) { //뱀 또는 사다리라면
						que.offer( map[next] ); //다음 탐색은 이동할 칸
						visited[map[next]] = true; //이동할 칸도 방문체크
					} else {
						que.offer(next);
					}
				}
			}
		}
		
	}

}