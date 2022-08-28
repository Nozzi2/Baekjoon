import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static int size;
	static int[] combs;
	static boolean[] selected;
	static int minSum, maxSum;
	static List<Integer> ops;

	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("res/4008swea.txt"));
		long start = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		
		for(int T=1; T<=TC; T++) {
			size = Integer.parseInt(br.readLine());
			int[] numbers = new int[size];
			combs = new int[size-1];
			selected = new boolean[size-1];
			minSum = Integer.MAX_VALUE;
			maxSum = Integer.MIN_VALUE;
			StringTokenizer st= new StringTokenizer(br.readLine());
			
			//0+ 1- 2* 3/
			ops = new ArrayList<>();
			
			for(int j=0; j<4; j++) {
				int opEA = Integer.parseInt(st.nextToken());
				for(int i=0; i<opEA; i++) {
					ops.add(j);
				}
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<size; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0, endi=combs.length; i<endi; i++) {
				//여기서 입력되는 숫자들을 고쳐주면 np에서 중복제거가 될 수 있음.
				combs[i] = ops.get(i);
			}
			//Arrays.sort(combs);
			
			do {
				//System.out.println(Arrays.toString(combs));
				calcMinMax(numbers, combs);
			}while(np(combs));
			
			System.out.printf("#%d %d\n",T,Math.abs(maxSum-minSum));
		}

		long end = System.currentTimeMillis();
		System.out.println("소요시간 : "+(end-start)+"ms");
	}

	private static void calcMinMax(int[] numbers, int[] combs2) {
		int sum=numbers[0];
		for(int i=0, endi=combs2.length; i<endi; i++) {
			int op = combs[i];
			switch(op) {
			case 0: // +
				sum +=numbers[i+1];
				break;
			case 1: // -
				sum -=numbers[i+1];
				break;
			case 2: // *
				sum *=numbers[i+1];
				break;
			case 3: // /
				sum /=numbers[i+1];
				break;
			}
		}
		minSum = Math.min(minSum, sum);
		maxSum = Math.max(maxSum, sum);
	}

	private static boolean np(int[] arr) {
		int N = arr.length;
		int i = N-1;
		int j = N-1;
		int k = N-1;
		
		while(i>0 && arr[i-1] >= arr[i]) {
			i--;
		}
		
		if(i==0) return false;

		while(arr[i-1] >= arr[j]) {
			j--;
		}
		
		swap(arr, i-1, j);
		
		while(i<k) {
			swap(arr, i++, k--);
		}
		
		return true;
	}

	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
		
	}

	


}