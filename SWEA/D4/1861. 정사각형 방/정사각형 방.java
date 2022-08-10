import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static int[][] visited;
	static int[][] map;
	static int max;
	static int size;

	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("res/1861swex.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int r;
		int c;
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int T=1; T<=TC; T++) {
			r=-1;
			c=-1;
			boolean isInit = true;
			size = Integer.parseInt(br.readLine());
			map = new int[size+2][size+2];
			visited = new int[size+2][size+2];
			max = 1;
			for(int i=1; i<=size; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=size; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}//for j
			}//for i
			
			for(int i=1; i<=size; i++) {
				for(int j=1; j<=size; j++) {
					if(visited[i][j] == 0) {
						visited[i][j] = 1;
						bfs(i,j);
						
						//bfs를 마치고 해당 좌표의 값이 max보다 큰지 검사하고, 크다면 최소값을 갖는 좌표로 초기화
						if(max == visited[i][j]) {
							if(isInit) {
								isInit = false;
								r=i;
								c=j;
							}
							if(map[r][c] > map[i][j]) {
								r=i;
								c=j;
							}//if
						} else if(max <= visited[i][j]) {
							max = visited[i][j];
							if(isInit) {
								isInit = false;
								r=i;
								c=j;
							}
							r=i;
							c=j;
						}//if
					}//if
				}//for j
			}//for i
			
			if(r==-1) {
				System.out.println("#"+T+" "+1+" "+1);
				//bw.write("#"+T+" "+1+" "+1+"\n");
			} else {
				System.out.println("#"+T+" "+map[r][c]+" "+(max+1));
				//bw.write("#"+T+" "+map[r][c]+" "+(max+1)+"\n");
			}
		}//for T
		//bw.flush();

	}//main

	private static void bfs(int r, int c) {
		Queue<Integer[]> que = new LinkedList<Integer[]>();
		int sum=0;
		Integer[] tmp = new Integer[2];
		tmp[0] = r;
		tmp[1] = c;
		que.offer(tmp);
		int nc=0;
		int nr=0;
		while(!que.isEmpty()) {
			Integer[] cur = que.poll();
			for(int i=0; i<4; i++) {
				nr = cur[0] + dr[i];
				nc = cur[1] + dc[i];
				if(nr == 0 || nc ==0 || nr > size || nc > size) {
					continue;
				}//if
				if(map[cur[0]][cur[1]]+1 == map[nr][nc]) {
					visited[nr][nc]=++sum;
					tmp[0] = nr;
					tmp[1] = nc;
					que.offer(tmp);
				}//if
			}//for i
		}//while
		visited[r][c] = sum;
	}

}