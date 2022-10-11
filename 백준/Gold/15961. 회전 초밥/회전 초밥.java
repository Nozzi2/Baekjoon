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
		int[] values = new int[size];
		int[] valueCnts = new int[maxValue+1];
		int sameCntMax = Integer.MIN_VALUE;
		
		Queue<Integer> que = new ArrayDeque<>();
		Set<Integer> set = new HashSet<>();
		
		for(int i=0; i<size; i++) {
			values[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=0; i<size+streakSize; i++) {
			int idx = i%size;
			que.offer(values[idx]); //큐에 추가
			
			valueCnts[ values[idx] ]++;
			set.add(values[idx]);

			if(que.size()>streakSize) { //연속으로 담을 수 있는 최대 크기라면
				int delVal = que.poll(); //큐에서 제거
				valueCnts[ delVal ]--;
				if(valueCnts[ delVal ] == 0) {//값을 삭제하여 한개도 없다면
					set.remove(delVal);
				}
				
				if(set.contains(couponValue)) { //set에 쿠폰 스시가 있다면
					sameCntMax = Math.max(sameCntMax, set.size()); //셋 크기 그대로 비교
				} else {
					sameCntMax = Math.max(sameCntMax, set.size()+1); //셋크기+1과 비교
				}
			}
		}
		
		System.out.println(sameCntMax);
		
	}
}