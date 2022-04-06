import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int num1 = Integer.parseInt(br.readLine());
		Deque<Integer> Deque = new ArrayDeque<>();
		int input = 0;
		
		for (int i = 0; i < num1; i++) {
			input = Integer.parseInt(br.readLine());
			switch (input) {
			case 0:
				Deque.pollFirst();
				break;
			default:
				Deque.addFirst(input);
				break;
			}
		}
		
		int sum=0;
		Iterator iter = Deque.iterator();
		while(iter.hasNext()) {
			sum = sum+ (int)iter.next();
		}
		System.out.println(sum);
	}
}