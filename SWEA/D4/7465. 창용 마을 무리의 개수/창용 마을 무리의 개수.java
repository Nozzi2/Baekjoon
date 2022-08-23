import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
	static int[] parents;
	static int N;
	
	static void make() {
		parents = new int[N];
		for (int i=0; i<N; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a]==a) {
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
		
		parents[bRoot]= aRoot;
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int T=1, endT=TC; T<=endT; T++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			make();
			
			int M =  Integer.parseInt(st.nextToken());
			for(int i=0, endi=M; i<endi; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				union(a,b);
			}
			
			Set<Integer> set = new HashSet<Integer>(); //중복 제거를 위한 HashSet 선언
			int size=0;
			for(int i=0, endi=N; i<endi; i++) {
				if(find(i)==i) {
					size++;
				}
			}
			sb.append("#").append(T).append(" ").append(size).append("\n");
		}
		System.out.println(sb);
		
	}
}
