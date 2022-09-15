import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	
	static class Person {
		int height;
		int weight;
		int rank;
		
		public Person(int weight, int height) {
			this.height = height;
			this.weight = weight;
			this.rank = 0;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		List<Person> ps = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			ps.add(new Person(w,h));
		}
		
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				battle(ps.get(i), ps.get(j));
			}
		}
		
		for(Person p : ps) {
			sb.append(p.rank+1).append(" ");
		}
		System.out.println(sb.toString().trim());
		
	}

	private static void battle(Person p1, Person p2) {
		if(p1.height > p2.height && p1.weight > p2.weight ) {
			p2.rank++;
		} else if(p2.height > p1.height && p2.weight > p1.weight ){
			p1.rank++;
		}
		
	}
}