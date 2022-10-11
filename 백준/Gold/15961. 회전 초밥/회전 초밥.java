import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int size = Integer.parseInt(st.nextToken());
		int maxValue = Integer.parseInt(st.nextToken());
		int streakSize = Integer.parseInt(st.nextToken());
		int couponValue = Integer.parseInt(st.nextToken());
		int[] values = new int[size];			//스시 입력받을 배열
		int[] valueCnts = new int[maxValue+1];	//스시 종류 별로 큐에 몇개 들어있는지 저장할 배열
		int sameCntMax = Integer.MIN_VALUE;		//스시 가짓수 최대 갯수
		int sameCnt=0;							//스시 가짓수 카운트
		
		Queue<Integer> que = new ArrayDeque<>();
		Set<Integer> set = new HashSet<>();
		
		
		
		for(int i=0; i<size; i++) {
			values[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=0; i<size+streakSize; i++) {
			int insVal = values[i%size];
			
			que.offer(insVal); //큐에 추가
			
			valueCnts[ insVal ]++;
			if(valueCnts[ insVal ] == 1) {//값을 추가하여 한개라면
				sameCnt++;
			}
			//set.add(values[idx]);

			if(que.size()>streakSize) { //연속으로 담을 수 있는 최대 크기라면
				int delVal = que.poll(); //큐에서 제거
				valueCnts[ delVal ]--;
				if(valueCnts[ delVal ] == 0) {//값을 삭제하여 한개도 없다면
					sameCnt--;
				}
				
				if(valueCnts[ couponValue ]>0) { //que에 쿠폰 스시가 있다면
					sameCntMax = Math.max(sameCntMax, sameCnt); //가짓수 카운트 그대로 비교
				} else { //없다면
					sameCntMax = Math.max(sameCntMax, sameCnt+1); //가짓수 카운트+1과 비교
				}
			}
		}
		
		System.out.println(sameCntMax);
		
	}
}