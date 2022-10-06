import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int T=1; T<=TC; T++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N]; // 수열의 수들
			int[] C = new int[N];   // 동적테이블 C[k] : 해당(k) 길이를 만족하는 자리(k자리)에 오는 수의 최소값 
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int size = 0;
			
			for (int k = 0; k < N; k++) {
//				k번째 원소가 위치할 곳을 이진 탐색으로 찾는다         
//				arr[k] 데이타: 이진 탐색의  키 
//				binarySearch(key) :  key가 있다면 key의 위치(index)를 리턴      
//									 못찾은 경우 : -마지막까지 찾은 위치-1이 전달됨 ===> insert point
				int pos = Arrays.binarySearch(C, 0, size, arr[k]);
				if(pos>=0) continue;
				
				int insertPos = Math.abs(pos)-1; 	// insert point로 변환 
				C[insertPos] = arr[k];				// 맨뒤 또는 기존원소 대체자리
				
				if(insertPos == size) size++;		// 맨 뒤에 추가한 상황이므로 size 증가
			}
			sb.append("#").append(T).append(" ").append(size).append("\n");
		}
		System.out.println(sb);
		
	}

}