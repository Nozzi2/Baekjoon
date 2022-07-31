import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

/*
push_front X: 정수 X를 덱의 앞에 넣는다.
push_back X: 정수 X를 덱의 뒤에 넣는다.
pop_front: 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
pop_back: 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
size: 덱에 들어있는 정수의 개수를 출력한다.
empty: 덱이 비어있으면 1을, 아니면 0을 출력한다.
front: 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
back: 덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
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
			case("push_front"):
				input = Integer.parseInt(s[1]);
				que.addFirst(input);
				isOut = false;
				break;
			case("push_back"):
				input = Integer.parseInt(s[1]);
				que.add(input);
				isOut = false;
				break;
			case("pop_front"):
				if(que.size()==0) {
					output =-1;
				} else {
					output=que.poll();
				}
				break;
			case("pop_back"):
				if(que.size()==0) {
					output =-1;
				} else {
					output=que.pollLast();
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