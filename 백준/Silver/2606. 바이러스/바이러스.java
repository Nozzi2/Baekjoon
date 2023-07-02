import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] parents;
	static int V;
	
	static void make() {
		parents = new int[V];
		for(int i=0; i<V; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) {
			return a;
		}
		
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return false;
		}
		
		parents[aRoot] = bRoot;
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		V=Integer.parseInt(br.readLine());
		make();
		
		int TC = Integer.parseInt(br.readLine());
		for(int T=0; T<TC; T++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			union(a,b);
		}
		
		int cnt=0;
		int p = find(0);
		for(int i=1; i<V; i++) {
			int out = find(i);
			//System.out.println((i+1)+" : "+(out+1));
			if(out==p) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
		
	}

}