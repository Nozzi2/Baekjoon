import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int[] arr = new int[10];
		int sum=0;
		int cnt=0;
		while(true) {
			int a = System.in.read(); 
			if(48>a || a>57) {
				System.in.read(); 
				break;
			}
			a-=48; //0:48, 9:57 이라서 0~9로 바꿔줌
			sum+=a%3;//숫자들의 3으로 나눈 값들을 저장해준다.
			arr[a]++;
			cnt++;
		}
		
		if(arr[0]==0) { //0이 한번도 나오지 않으면 절대 30의 배수가 될 수 없음
			System.out.println(-1);
		} else { //0이 한번이라도 나왔으면 가장 큰 수를 탐색
			if(cnt>2) { //숫자가 3개 이상 나올때만 0을 맨 뒤에 출력하도록 한다.
				arr[0]--;
			}
			boolean exit = false; //출력할 숫자를 발견하면 종료할 boolean 변수
			for(int i=0; i<100; i+=10) {
				int num10 = i/10;
				if(arr[num10]>0) {
					arr[num10]--;
					for(int j=0; j<10; j++) {
						if(arr[j]>0) {
							//int tmpSum = sum - num10%3 -j%3;
							arr[j]--;
							//조건을 만족하는 경우 글자를 출력해야함.
							if( sum%3==0) { // || (i+j)%3 == tmpSum%3 
								for(int k=9; k>=0; k--) {
									while(arr[k]>0) {
										sb.append(k);
										arr[k]--;
									}
								}
								
								//조건을 만족하지만 j가 더 크다면 더 큰값을 먼저 출력해야함.
								if(i/10>j) {
									sb.append(i+j);
								} else {
									sb.append(j);
									int tmp = i/10;
									sb.append(tmp);
								}
								
								exit = true;
								break;
							}
							arr[j]++;
						}
					}
					if(exit) break;
					arr[num10]++;
				}
			}//for i
			if(sb.length()==0) {
				System.out.println(-1);
			} else {
				if(cnt>2) {
					sb.append(0);
				}
				System.out.println(sb);
			}
		}
	}
}