import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int num1 = Integer.parseInt(br.readLine());
		
		ArrayList<String> words = new ArrayList<String>();
		
		//한줄씩 단어 입력받아서 ArrayList에 입력
		for (int i = 0; i < num1; i++) {
			words.add(br.readLine());
		}
		
		/*
		for (int i = 0; i < words.size(); i++) {
			System.out.println(words.get(i));
		}*/
		
		//오름차순 정렬
		Collections.sort(words);
		
		/*
		System.out.println("정렬 후다이아이아이");
		for (int i = 0; i < words.size(); i++) {
			System.out.println(words.get(i));
		}*/
		
		ArrayList<String> NoSameWords = new ArrayList<String>();
		
		for (int i = 0; i < words.size(); i++) {
			if(i==0 || !words.get(i).equals(words.get(i-1))) {
				NoSameWords.add(words.get(i));
			}
		}
		
		/*
		System.out.println("중복 없앴다잉?????????");
		for (int i = 0; i < NoSameWords.size(); i++) {
			System.out.println(NoSameWords.get(i));
		}*/
		
		Comparator<String> c = new Comparator<String>() {
			public int compare(String s1, String s2) {
				return Integer.compare(s1.length(), s2.length()); 
			} 
		};

		Collections.sort(NoSameWords, c);

		//System.out.println("단어길이대로 정렬했다이좌식아^^&&%$@#");
		for (int i = 0; i < NoSameWords.size(); i++) {
			sb.append(NoSameWords.get(i)).append('\n');
		}
		System.out.println(sb);
	}

}
