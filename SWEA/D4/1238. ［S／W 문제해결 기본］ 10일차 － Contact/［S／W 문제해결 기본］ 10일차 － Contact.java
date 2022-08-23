import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static boolean[][] map;
	static boolean[] visited;
	static List<Integer> lastNum;
	
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("res/1238swea.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for(int T=1; T<=10; T++) {
			map = new boolean[101][101];
			visited = new boolean[101];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int TC = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i=0, endi=TC/2; i<endi; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				map[from][to] = true;
			}
			
			bfs(start);
			
			Collections.sort(lastNum);
			
			sb.append("#").append(T).append(" ").append(lastNum.get(lastNum.size()-1)).append("\n");
		}
		System.out.println(sb);
	}

	private static void bfs(int start) {
		Queue<Integer> que = new LinkedList<>();
		lastNum = new ArrayList<Integer>();
		que.offer(start);
		visited[start] = true;
		while(!que.isEmpty()) {
			
			//마지막으로 큐에 담겨있는 숫자들을 리스트에 저장해놓아야함
			lastNum.clear();
			for(Integer a : que) {
				lastNum.add(a);
			}
			
			int size = que.size();
			for(int i=0; i<size; i++) {
				int next = que.poll();
				for(int j=1; j<=100; j++) {
					if(map[next][j] && !visited[j]) {
						visited[j] = true;
						que.offer(j);
					}
				}//for j
			}
		}
		
	}
}
