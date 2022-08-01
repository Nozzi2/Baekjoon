import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int size = Integer.parseInt(br.readLine());
		int[] arr = new int[size];
		
		//배열 입력시 StringTokenizer 사용
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<size; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int TC = Integer.parseInt(br.readLine());
		for(int test=0; test<TC; test++) {
			String[] s =br.readLine().split(" ");
			int input = Integer.parseInt(s[0]);
			int index = Integer.parseInt(s[1]);
			
			if(input == 1) { //남학생일 경우
				//index의 배수만큼 스위치를 바꿔준다.
				for(int i=1; i*index<=size; i++) {
					arr[i*index-1] = Math.abs(arr[i*index-1]-1);
				}
			} else if(input == 2) { //여학생일 경우
				//먼저 index의 스위치를 바꿔주고
				arr[index-1] = Math.abs(arr[index-1]-1);
				
				int indexL = index-2;
				int indexR = index;
				//index의 양쪽의 스위치가 같은지 비교해주면서 
				while(indexL>=0 && indexR<=size-1 && arr[indexL]==arr[indexR]) {
					//같을 경우에만 스위치를 바꾼다.
					arr[indexL] = Math.abs(arr[indexL--]-1);
					arr[indexR] = Math.abs(arr[indexR++]-1);
				}
			}
		}
		
		//출력
		for(int i=0; i<size; i++) {
			sb.append(arr[i]);
			if( (i+1)%20 == 0 ) {
				sb.append("\n");
			} else {
				sb.append(" ");
			}
		}
		System.out.println(sb.toString().trim());
		
		
	}
}