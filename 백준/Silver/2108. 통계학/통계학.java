import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {

	//ArrayList의 평균값을 정수로 반환해주는 메소드
	public static int AvgArrayList(ArrayList<Integer> nums) {
		double avg = 0; //산술평균
		int sum = 0;
		
		for (int i = 0; i < nums.size(); i++) {
			sum = sum+nums.get(i);
		}
		
		//산술평균 첫째자리에서 반올림
		avg = (double)sum / (double)nums.size();
		avg = Math.round(avg);
		
        return (int)avg;
    }
	
	//정렬된 배열에서 중복되지 않는 숫자의 합을 구함
	public static int NumCntArrayList(ArrayList<Integer> nums) {
		int cnt = 0; //nums 배열에서 중복되지 않는 숫자들의 합
		for (int i = 0; i < nums.size(); i++) {
			//0번째이거나, i, i-1인덱스 값이 같지 않으면 숫자가 하나 더 있는거임
			if(i==0 || nums.get(i).intValue()!=nums.get(i-1).intValue()) {
				cnt++;
			}
		}
        return cnt;
    }
	
	//빈도수가 높은 수를 반환하는데, 같은 빈도수라면 두번째로 작은 값을 반환하는 메소드 
	public static int FreqArrayList(ArrayList<Integer> nums, int cnt) {
		int[][] freq = new int[cnt][2];
		int a =0;
		for (int i = 0; i < nums.size(); i++) {
			if(i==0 || nums.get(i).intValue()!=nums.get(i-1).intValue()) {
				freq[a][1]=nums.get(i).intValue();
				freq[a][0]++;
				a++;
			} else if (freq[a-1][1] == nums.get(i).intValue()) {
				freq[a-1][0]++;
			}
		}
		
		//이중배열 0번째 인덱스 기준 오름차순 정렬. 0번이 같다면 1번 인덱스도 오름차순 정렬
		Arrays.sort(freq, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) { 
				//return o1[0]!=o2[0] ? o1[0]-o2[0] : o1[1]-o2[1]; // 첫번째 기준 오름차순 > 두번째 기준 오름차순 : {1,30}{2,10}{3,50}{4,20}{5,40}{6,10}{6,20}{6,30}{6,40}{6,50}
				return o1[0]!=o2[0] ? o1[0]-o2[0] : o2[1]-o1[1]; // 첫번째 기준 오름차순 > 두번째 기준 내림차순 : {1,30}{2,10}{3,50}{4,20}{5,40}{6,50}{6,40}{6,30}{6,20}{6,10}
			} 
		});
		
		if(cnt == 1) {
			return freq[cnt-1][1];
		} else if(freq[cnt-2][0]==freq[cnt-1][0]){
			return freq[cnt-2][1];
		} else {
			return freq[cnt-1][1];
		}
    }
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //선언
		StringBuilder sb = new StringBuilder();
		ArrayList<Integer> nums = new ArrayList<Integer>();
		int num1 = Integer.parseInt(br.readLine());
		for (int i = 0; i < num1; i++) {
			nums.add(Integer.parseInt(br.readLine()));
		}
		
		 //산술평균
		int avg = AvgArrayList(nums);
		
		
		//중앙값을 위한 정렬
		Collections.sort(nums);
		
				
		//정렬된 배열에서 중복되지 않는 숫자의 숫자를 정해서 이중배열의 사이즈를 줄여준다.
		int cnt = NumCntArrayList(nums);
		//가장 많은 빈도수의 숫자를 출력하지만, 같은 빈도수가 있다면 같은 빈도수에서 두번째로 작은 숫자를 출력한다. 
		int secondFreq = FreqArrayList(nums,cnt);
		
		
		//범위 (오름차순 정렬된 배열에서 첫번째 인덱스와 마지막 인덱스의 차를 구하면 됨) 
		int range = nums.get(nums.size()-1)-nums.get(0);
		

		sb.append(avg).append('\n'); //산술평균
		sb.append(nums.get(num1/2)).append('\n'); //중앙값
		sb.append(secondFreq).append('\n'); //최빈값
		sb.append(range).append('\n'); //범위
		
		
		System.out.print(sb);
	}

}