import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sizeA = Integer.parseInt(st.nextToken());
		int sizeB = Integer.parseInt(st.nextToken());
		int sizeTotal = sizeA+sizeB;
		
		char[] inputA = br.readLine().toCharArray();
		char[] charsA = new char[sizeA];
		for(int i=sizeA-1; i>=0; i--) {
			charsA[i] = inputA[sizeA-1-i];
		}
		char[] charsB = br.readLine().toCharArray();
		
		int targetTime = Integer.parseInt(br.readLine());
		targetTime = targetTime>sizeTotal?sizeTotal:targetTime;
		
		int[] arrA = new int[sizeA];
		
		for(int i=0; i<sizeA; i++) {
			arrA[i] = i;
		}
		
		int curTime = -1;
		while(++curTime < targetTime) {
			for(int i=0; i<sizeA-1; i++) {
				if(arrA[i]+1 != arrA[i+1]) {
					arrA[i]++;
				}
			}
			if(arrA[sizeA-1]<sizeTotal-1) {
				arrA[sizeA-1]++;
			}
		}
		
		char[] output = new char[sizeTotal];
		int idxA = 0;
		int idxB = 0;
		for(int i=0; i<sizeTotal; i++) {
			if(idxA < sizeA && i==arrA[idxA]) {
				output[i] = charsA[idxA++];
			} else {
				output[i] = charsB[idxB++]; 
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(char c : output) {
			sb.append(c);
		}
		
		System.out.println(sb.toString());
		
		
		
		/**
		123 456
		
		3 56
		2 4 6
		1 3 5
		12 4
		123
		
		
		124
		135
		246
		356
		456
		
		
		 */
		
		

	}

}
/*
3 3
ABC
DEF
6

*/