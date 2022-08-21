import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb;

	static class Picture implements Comparable<Picture> {
		int value;
		int age;
		int vote;

		Picture(int value) {
			this.value = value;
			this.age = 1;
			this.vote = 1;
		}

		public void voting() {
			this.vote++;
		}

		public void aging() {
			this.age++;
		}

		public void print() {
			sb.append(value + " ");
			//sb.append(age+",");
			//sb.append(vote+" ");
		}

		@Override
		public int compareTo(Picture o) {
			int num = this.value - o.value;
			return num;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("res/1713boj.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int listSize = Integer.parseInt(br.readLine());
		int TC = Integer.parseInt(br.readLine());

		List<Picture> list = new LinkedList<Picture>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int T = 0; T < TC; T++) {
			Picture p = new Picture(Integer.parseInt(st.nextToken()));
			
			
			if (list.size() < listSize) { //사진 최대 개수보다 후보사진이 적으면 삭제할 인덱스를 찾을 필요가 없다
				boolean isVote = false;
				for (int i = 0, endi = list.size(); i < endi; i++) {
					Picture tmp = list.get(i);
					if (tmp.value == p.value) {
						tmp.voting();
						isVote = true;
					}
					tmp.aging();
				}
				if(!isVote) { //득표가 올라가지 않은경우만 사진을 추가한다
					list.add(p);
				}
				
			} else {
				// 여기서 만약 시간초과가 걸린다면 iterator를 써서 list.get의 시간복잡도를 낮출 수 있다.
				int maxAge = 0;
				int minVote = Integer.MAX_VALUE;
				int delIndex = 0;
				boolean isVote = false;
				for (int i = 0, endi = list.size(); i < endi; i++) {
					Picture tmp = list.get(i);
					
				
					if(minVote > tmp.vote) { //최소 득표보다 현재 득표가 작으면
						minVote = tmp.vote; //최소 득표 갱신
						maxAge = tmp.age; //최고 age 갱신
						delIndex = i;		//지워야할 인덱스로 설정
					} else if(minVote == tmp.vote) { // 같다면
						if(maxAge < tmp.age) { //최고 age를 삭제해야함
							maxAge = tmp.age;
							delIndex = i;
						}
					}
					
					//값이 똑같다면 득표수를 올려야함
					if (tmp.value == p.value) {
						tmp.voting();
						isVote = true;
					}
					tmp.aging();
				}
				if (!isVote) {
					list.remove(delIndex);
					list.add(p);
				}
			}
			
		} // for T
		
		Collections.sort(list);
		printList(list);
	}// main end

	private static void agingAll(List<Picture> list) {
		for (int i = 0, endi = list.size(); i < endi; i++) {
			list.get(i).aging();
		}
	}

	private static void printList(List<Picture> list) {
		for (Picture pp : list) {
			pp.print();
		}
		System.out.println(sb.toString().trim());
		sb.setLength(0);
	}

}