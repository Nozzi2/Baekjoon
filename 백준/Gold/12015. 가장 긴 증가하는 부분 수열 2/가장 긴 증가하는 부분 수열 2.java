import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N]; //
		int[] C = new int[N]; 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int size = 0;
		
		for (int k = 0; k < N; k++) {
			int pos = Arrays.binarySearch(C, 0, size, arr[k]);
			if(pos>=0) continue;
			
			int insertPos = Math.abs(pos)-1; 
			C[insertPos] = arr[k];
			
			if(insertPos == size) size++;
		}
		
		System.out.println(size);
	}

}