import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int[] parents;
	static int V;
	static String s;
	
	static void make() {
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) { // a의 대표자 찾기
		if (parents[a] == a)
			return a; // 초기 설정과 같으므로 내가 root인 경우

		// return find(parents[a]); //root를 찾아 리턴 => 편향 트리인 경우 속도가 느림
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		// 두 노드의 root가 같으므로 같은 group => 같은 group의 두 노드를 연결하면 cycle이 됨. => 두 노드를 연결하면 안됨
		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

	static void isUnion(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		// 두 노드의 root가 같으므로 같은 group => 같은 group의 두 노드를 연결하면 cycle이 됨. => 두 노드를 연결하면 안됨
		if (aRoot == bRoot) {
			s+="1";
			return;
		}
		
		s+="0";
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TC1 =  Integer.parseInt(br.readLine());
		
		
		for(int T=1, endT=TC1; T<=endT; T++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V=Integer.parseInt(st.nextToken());
			s="";
			
			make();
			
			int TC2 = Integer.parseInt(st.nextToken());
			for(int T2=0, endT2=TC2; T2<endT2; T2++) {
				st = new StringTokenizer(br.readLine());
				int option = Integer.parseInt(st.nextToken());
				int a =Integer.parseInt(st.nextToken())-1;
				int b =Integer.parseInt(st.nextToken())-1;
				
				switch(option) {
				case 1:
					isUnion(a,b);
					break;
				case 0:
					union(a,b);
					break;
				}
			}
			bw.write("#"+T+" "+s+"\n");
		}
		bw.flush();
		
		
		
		
	}
	
	
}