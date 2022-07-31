import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

/*
push X: 정수 X를 큐에 넣는 연산이다.
pop: 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
size: 큐에 들어있는 정수의 개수를 출력한다.
empty: 큐가 비어있으면 1, 아니면 0을 출력한다.
front: 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
back: 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.

*/
/*
class Queue {
	private ArrayList<Integer> que;
	public static int size;
	
	public Queue() {
		que = new ArrayList<Integer>();
		size=0;
	}
	
	public void push(int in) {
		que.add(in);
		size++;
	}
	
	public int pop() { 
		if(size==0) {
			return -1;
		} else {
			int tmp = que.get(0);
			que.remove(0); //여기서 O(n)이 발생하는듯?
			size--;
			return tmp;
		}
	}
	
	public int size() {
		return que.size();
	}
	
	public int empty() {
		if( size ==0 ) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public int front() {
		if(size==0) {
			return -1;
		} else {
			return que.get(0);
		}
	}
	
	public int back() {
		if(size==0) {
			return -1;
		} else {
			return que.get(size-1);
		}
	}
}
*/
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Deque<Integer> que = new ArrayDeque<>();
		int T = Integer.parseInt(br.readLine());
		String[] s;
		for(int i=0; i<T; i++) {
			s=br.readLine().split(" ");
			int output=0;
			int input=0;
			boolean isOut = true;
			switch(s[0]) {
			case("push"):
				input = Integer.parseInt(s[1]);
				que.add(input);
				isOut = false;
				break;
			case("pop"):
				if(que.size()==0) {
					output =-1;
				} else {
					output=que.poll();
				}
				break;
			case("size"):
				output=que.size();
				break;
			case("empty"):
				if(que.size()==0) {
					output =1;
				} else {
					output = 0;
				}
				break;
			case("front"):
				if(que.size()==0) {
					output =-1;
				} else {
					output=que.peek();
				}
				break;	
			case("back"):
				if(que.size()==0) {
					output =-1;
				} else {
					output=que.peekLast();
				}
				break;				
			}
			if(isOut) {
				bw.write(output+"\n");
			}
		}
		bw.flush();
	}
}