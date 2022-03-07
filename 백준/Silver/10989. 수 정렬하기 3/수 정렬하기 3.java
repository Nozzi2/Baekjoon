import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//10989번. 수정렬하기3 (실버5)
public class Main {
	public static int[] countingSort(int[] a, int MaxNum) {
		int[] numbers = new int[MaxNum];
		int[] sortedArr = new int[a.length];
		
		for (int i = 0; i < a.length; i++) {
			numbers[a[i]-1]++;
		}
		
		for (int i = 1; i < numbers.length; i++) {
			numbers[i] = numbers[i]+numbers[i-1];
		}
		
		for (int i = 0; i < a.length; i++) {
			//System.out.println(i+"번째");
			int a1 = a[a.length-1-i]-1;
			//System.out.println("a1 : "+a1);
			int numbers1 = numbers[a1]-1;
			//System.out.println("numbers1 : "+numbers1);
			sortedArr[numbers1]=a1+1;
			//System.out.println("sortedArr[numbers1] : "+sortedArr[numbers1]);
			numbers[a1]--;
			//System.out.println(numbers[a1]);
		}
		
        return sortedArr;
    }
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //선언
		StringBuilder sb = new StringBuilder();
		int num1 = Integer.parseInt(br.readLine()); //Int
		int[] a = new int[num1];
		int MaxNum = 0;
		for (int i = 0; i < a.length; i++) {
			a[i] = Integer.parseInt(br.readLine());
			if(MaxNum < a[i]) {
				MaxNum = a[i];
			}
		}
		a = countingSort(a,MaxNum);
		
		for (int i = 0; i < a.length; i++) {
			sb.append(a[i]).append('\n');
		}
		System.out.println(sb);
	}

}