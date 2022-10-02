import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int size = Integer.parseInt(br.readLine());
		int[] scores = new int[size+1];
		int[] memoO = new int[size+1]; //연속된 계단 밟는 최대점수
		int[] memoX = new int[size+1]; //연속된 계단 밟지 않는 최대점수
		for(int i=1; i<=size; i++) {
			scores[i] = Integer.parseInt(br.readLine());
		}
		if(size==1) {
			System.out.println(scores[1]);
		} else if(size==2){
			System.out.println(scores[1]+scores[2]);
		} else {
			memoX[1] = scores[1];
			memoO[1] = scores[1];
			memoX[2] = scores[2];
			memoO[2] = scores[1]+scores[2];
			for(int i=3; i<=size; i++) {
				memoO[i] = memoX[i-1]+scores[i];
				memoX[i] = Math.max(memoO[i-2], memoX[i-2])+scores[i];
			}
			System.out.println(Math.max(memoO[size], memoX[size]));
		}
	}

}