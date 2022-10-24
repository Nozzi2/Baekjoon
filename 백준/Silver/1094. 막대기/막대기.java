import java.io.BufferedReader;
import java.io.InputStreamReader;
//모든 줄에 주석달아라~~
//모든 줄에 주석달아라~~
//모든 줄에 주석달아라~~
//모든 줄에 주석달아라~~
//모든 줄에 주석달아라~~
//모든 줄에 주석달아라~~
//모든 줄에 주석달아라~~
//모든 줄에 주석달아라~~
//모든 줄에 주석달아라~~
//모든 줄에 주석달아라~~
//모든 줄에 주석달아라~~
//모든 줄에 주석달아라~~
//모든 줄에 주석달아라~~
//모든 줄에 주석달아라~~
//모든 줄에 주석달아라~~
//모든 줄에 주석달아라~~
//모든 줄에 주석달아라~~
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//빠른입력을 위해 버퍼리더 선언
		
		int cnt=0; //출력할 막대기 갯수
		int[] sticks = {64,32,16,8,4,2,1}; //나올 수 있는 막대기 종류들 입력
		
        cnt=0; //막대기 갯수 초기화
        int target = Integer.parseInt(br.readLine()); //목적 숫자 입력받기
        for(int i=0, endi=sticks.length; i<endi; i++) { //긴 막대기 부터 작은막대기까지 반복하여 계산
            if(target/sticks[i]==1) { //해당 막대기보다 길다면
                cnt++; //막대기 갯수 증가
                target%=sticks[i]; //남은 막대기 길이 갱신
                if(target==0) break; //남은 막대기 길이가 0이면 종료
            }
        }
        
		System.out.print(cnt); //스트링빌더 출력
	}
}