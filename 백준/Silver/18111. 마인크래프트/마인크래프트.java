import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int max;
	static int min;
	static int FULL;
	static int[] blocks;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[] heights = new int[257];
		blocks = new int[257];
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		FULL = N*M;
		int sum = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int height = Integer.parseInt(st.nextToken());
				heights[height]++;
				min = Math.min(min, height);
				max = Math.max(max, height);
				sum+=height;
			}
		}
		
		for(int i=min; i<=max; i++) {
			if(heights[i]>0) {
				for(int j=0; j<=i; j++) {
					blocks[j] += heights[i];
				}
			}
		}
		
		//갖고 있는 블럭기준으로 가장 높게 쌓을 수 있는 높이
		int highest = (B+sum)/(N*M) >= max ? max : (B+sum)/(N*M);
		
		int time = calcTime(highest);
		
		
		while(highest-1>=0) {
			int tmp = calcTime(highest-1);
			if(time <= tmp) {
				break;
			} else {
				highest--;
				time = tmp;
			}
		}
		System.out.printf("%d %d\n", time,highest);
		
//		for(int i=max; i>=min; i--) {
//			System.out.printf("%4d %3d\n",i,blocks[i]);
//		}

	}

	private static int calcTime(int highest) {
		int time = 0;
		
		//가장 높은 높이부터 꼭대기 까지 블럭을 빼는 계산
		for(int i=max; i>highest; i--) {
			time += blocks[i]*2;
		}
		
		//바닥부터 가장 높은 높이 까지 블럭을 채우는 계산
		for(int i=min; i<=highest; i++) {
			time += FULL-blocks[i];
		}
		
		return time;
	}

}