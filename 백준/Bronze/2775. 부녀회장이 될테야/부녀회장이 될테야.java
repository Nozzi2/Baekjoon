import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine());

		int[][] dongHosu = new int[15][14];
		
		//1층 초깃값은 직접 설정해줘야함.
		for(int i =0; i<14;i++) {
			dongHosu[0][i] = i+1;
		}
		
		//2층부터 14층까지 미리 배열 만들어놓기
		int sum = 0;
		for(int a=1; a<15;a++) {
			for(int b=0; b<14;b++) {
				for(int c = 0; c<=b; c++) {
					sum = sum+dongHosu[a-1][c];
				}
				dongHosu[a][b] = sum;
				sum=0;
			}
		}
		
		/*
		for(int a=13; a>=0;a--) {
			for(int b=0; b<14;b++) {
				System.out.print(dongHosu[a][b]+" ");
			}
			System.out.println("");
		}*/
		
		//testCase만큼 반복하여 읽어와서 출력
		for(int i = 0; i<testCase; i++) {
			int dong = Integer.parseInt(br.readLine());
			int hosu = Integer.parseInt(br.readLine());
			sb.append(dongHosu[dong][hosu-1]).append('\n');
			System.out.print(sb);
			sb.setLength(0);
		}
	}
}