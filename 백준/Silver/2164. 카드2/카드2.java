import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Queue<Integer> queue = new LinkedList<>(); //int형 queue 선언, linkedlist 이용
		
		int num1 = Integer.parseInt(br.readLine()); 
		
		for(int i = 1; i <= num1; i++) {
			queue.offer(i);     // queue에 값 1 추가
		}

		
		boolean flag = true;
		int result = 0;
		Iterator iter = queue.iterator(); //Iterator 선언 
		while(iter.hasNext()){//다음값이 있는지 체크
			if(flag) {
				if(queue.size()==1) {
					result = queue.poll();
				} else {
					queue.poll();
				}
				flag = false;
			} else {
				result = queue.poll(); 
				queue.offer(result);
				flag = true;
			}
        }
		System.out.println(result);
	}
}