import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub.
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		
		String input;
		char[] arrCh;
		boolean balance;
		
		while(true) {
			balance = true;
			input = br.readLine();
			if(input.equals(".")) {
				break;
			}
			arrCh = input.toCharArray();
			Deque<Character> Deque = new ArrayDeque<>();
			for (int i = 0; i < arrCh.length; i++) {
				if(balance) {
					switch (arrCh[i]) {
					case '(' : 
						Deque.addFirst(arrCh[i]);
						//System.out.println("push : "+Deque.peekFirst());
						break;
					case '[' : 
						Deque.addFirst(arrCh[i]);
						//System.out.println("push : "+Deque.peekFirst());
						break;
					case ')' : 
						if(Deque.size() == 0) { //비어있으면 끝내기
							balance = false;
							break;
						} else {
							arrCh[i] = '(';
							if(Deque.pollFirst() != arrCh[i]) { //
								balance = false;
								//System.out.println("FALSE..");
							}
							//System.out.println("pop : )");
							//Deque.pollFirst();
							break;
						}
						
						
					case ']' : 
						if(Deque.size() == 0) { //비어있으면 끝내기
							balance = false;
							break;
						} else {
							arrCh[i] = '[';
							if(Deque.pollFirst() != arrCh[i]) {
								balance = false;
								//System.out.println("FALSE..");
							}
							//System.out.println("pop : ]");
							//Deque.pollFirst();
							break;
						}
					}
				} else {
					break;
				}
			}
			
			if(Deque.size() != 0) { //한줄을 다 끝냈는데 스택이 남아있으면 안됨
				balance = false;
			}
			
			if(balance) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}

	}

}