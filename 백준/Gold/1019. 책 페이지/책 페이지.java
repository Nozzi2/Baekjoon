import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
	
	static int cur=-1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int target = Integer.parseInt(br.readLine());
		int[] jariNum = new int[10];
		int tmp=target;
		int idx=0;
		while(tmp!=0) {
			jariNum[idx++] = tmp%10;
			tmp/=10;
		}
		
		int[] cnt = new int[10];
		int zero = -1;
		int oneToNine = -1;
		int jarisu = 1;
		int recentNum=0;
		tmp=target;
		idx=0;
		
		while(tmp!=0) {
			//step2
			for(int i=1; i<=jariNum[idx]; i++) {
				if(i==jariNum[idx]) {
					cnt[i] += recentNum;
				} else {
					cnt[i] += jarisu;
				}
			}
			
			//step1,3
			if(jarisu==1) {
				if(jariNum[idx]!=0) {
					cnt[ jariNum[idx] ]++;
				}
			} else {
				//step1
				int oneone = cur/9; //1, 11, 111을 얻기위한 변수
				int out = oneone*jariNum[idx];
				if(jariNum[idx]==0) { //해당 자릿수가 0일때
					out -= cur-recentNum+1;
				}
				cnt[0] += out;
				
				//step3
				cnt[0] += zero*jariNum[idx];
				for(int i=1; i<=9; i++) {
					cnt[i] += oneToNine*jariNum[idx];
				}
			}

			idx++;
			jarisu *=10;
			recentNum = (target%jarisu)+1;
			tmp/=10;
			
			zero = nextZero(zero);
			oneToNine = nextOneToNine(oneToNine);
			cur = nextJarisu(cur);
		}
		
		for(int i=0; i<10; i++) {
			sb.append(cnt[i]+" ");
		}
		
		System.out.println(sb.toString().trim());
	}

	private static int nextJarisu(int cur2) {
		if(cur2 == -1) {
			return 9;
		} else {
			return cur2*10+9;
		}
	}

	private static int nextOneToNine(int oneToNine) {
		if(oneToNine == -1) {
			return 1; 
		} else {
			return oneToNine*10+cur+1;
		}
	}

	private static int nextZero(int zero) {
		if(zero == -1) {
			return 0;
		} else {
			return zero*10+cur;
		}
	}
}