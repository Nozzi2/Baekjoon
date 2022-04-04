import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
		
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int treeCnt = Integer.parseInt(s[0]);
		int treeLength = Integer.parseInt(s[1]);
		
		
		s = br.readLine().split(" ");
		int[] treeHeight = new int[treeCnt];
		int max = 0;
		for(int i =0; i<treeCnt; i++) {
			treeHeight[i] = Integer.parseInt(s[i]);
			if(max < treeHeight[i]) {
				max = treeHeight[i];
			}
		}
		
		long lo = 0;
		long hi = max+1;
		long mid = 0;
		
		
		while(lo < hi) {
			mid = lo+((hi-lo)/2);
			
			long sum = 0;
			for(int i = 0; i<treeHeight.length; i++) {
				if(treeHeight[i]-mid>0) {
					sum = sum+treeHeight[i]-mid;
				}
			}
			
			if(sum < treeLength) { //count < key 이것도 중요함 
				//System.out.println("hi : "+mid);
				hi = mid;
			} else {
				//System.out.println("lo : "+mid);
				lo = mid +1;
			}
			
		}
		System.out.println(lo-1);
	}
}