import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];
        int[] inputs=new int[N];
        
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        for(int i=0; i<N; i++) {
        	inputs[i]=i;
        }
        //Arrays.sort(inputs);
        
        int min=Integer.MAX_VALUE;
        do {
        	min = Math.min(min, calcMinCost(inputs));
        }while(np(inputs));

    	System.out.println(min);
        
    }

	private static int calcMinCost(int[] inputs) {
		int sum=0;
		for(int i=0; i<N-1; i++) {
			int tmp = map[inputs[i]][inputs[i+1]];
			if(tmp==0) { //길이 끊겨있다면 더 계산할 필요 없음
				return Integer.MAX_VALUE;
			}
			sum+=tmp;
		}
		int tmp = map[inputs[N-1]][inputs[0]];
		if(tmp==0) { //길이 끊겨있다면 더 계산할 필요 없음
			return Integer.MAX_VALUE;
		}
		sum+=tmp; //도착지에서 돌아오는 비용까지 더하기
		return sum;
	}

	private static boolean np(int[] inputs) {
		int N=inputs.length;
		int i=N-1;
		int j=N-1;
		int k=N-1;
		
		while(i>0 && inputs[i-1]>= inputs[i]) {
			i--;
		}
		
		if(i==0) {
			return false;
		}
		
		while(inputs[i-1] >= inputs[j]) {
			j--;
		}
		
		swap(inputs, i-1, j);
		
		while(i<k) {
			swap(inputs, i++, k--);
		}
		
		return true;
	}

	private static void swap(int[] inputs, int i, int j) {
		int tmp = inputs[i];
		inputs[i] = inputs[j];
		inputs[j] = tmp;
	}
}

/*
4
0 10 15 20
5 0 9 10
6 13 0 12
8 8 9 0

*/