import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge implements Comparable<Edge> {
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight -o.weight;
		}
	}
	
	static int[] parents;
	static int V,E;
	static Edge[] edgeList;
	
	static void make() {
		parents = new int[V];
		for(int i=0; i<V; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		edgeList = new Edge[E];
		
		boolean[] mw = new boolean[V];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<V; i++) {
			if(st.nextToken().equals("M")) {
				mw[i] = true;
			}
		}
		
		//System.out.println(Arrays.toString(mw));
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		
		make();
		Arrays.sort(edgeList);
		
		int count=0;
		int result = 0;
		for(Edge edge : edgeList) {
			//기존 크루스칼 알고리즘에 양쪽 노드가 서로 다른 (MW WM) 노드인지 검사만 해주도록 했음
			if(mw[edge.from] != mw[edge.to] && union(edge.from, edge.to)) {
				result += edge.weight;
				if(++count == V-1) break;
			}
		}
		if(count == V-1) {
			System.out.println(result);
		} else {
			System.out.println(-1);
		}
	}

}