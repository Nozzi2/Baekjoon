import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		int N=Integer.parseInt(br.readLine());
		int[] numbers = new int[N];
		int[] opers = new int[N-1]; //연산자의 순서를 저장할 배열
		
		//숫자 저장
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		//연산자 저장
		st = new StringTokenizer(br.readLine());
		int idx=0;
		for(int i=0; i<4; i++) { //연산자 4개를
			int cnt = Integer.parseInt(st.nextToken());
			for(int j=0; j<cnt; j++) { //연산자 횟수만큼 반복하여
				opers[idx++] = i; //배열에 0~3의 값을 넣는다.
			}
		}
		
		//Arrays.sort(numbers); //이미 정렬되어있음
		
		do {
			//넥퍼로 구한 배열로 합계 계산하기
			int sum=numbers[0]; //첫번째 숫자는 합계에 더해주고 시작. 
			for(int i=1; i<N; i++) {
				switch(opers[i-1]) { //0+ 1- 2* 3/ 연산하기
				case 0:
					sum+=numbers[i];
					break;
				case 1:
					sum-=numbers[i];
					break;
				case 2:
					sum*=numbers[i];
					break;
				case 3:
					sum/=numbers[i];
					break;
				}
			}
			
			min = Math.min(min, sum);
			max = Math.max(max, sum);
			
		}while(np(opers));
		
		System.out.println(max);
		System.out.println(min);
	}

	private static boolean np(int[] numbers) {
		int N = numbers.length;
		int i = N-1;
		int j = N-1;
		int k = N-1;
		
		while(i>0 && numbers[i-1]>=numbers[i]) {
			i--;
		}
		
		if(i==0) return false;
		
		while(numbers[i-1] >= numbers[j]) {
			j--;
		}
		
		swap(numbers, i-1, j);
		
		while(i<k) {
			swap(numbers, i++, k--);
		}
		
		return true;
	}

	private static void swap(int[] numbers, int i, int j) {
		int tmp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = tmp;
	}

}