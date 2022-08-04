import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static int[] prices;		//0번배열은 비워놓고 1,2,3번 배열에 각각 1일, 1달, 3달 가격 저장
	static int[] plan;			//12개월 이용계획
	static int minFee;			//최소로 찾는 요금을 저장한다.
	static int fee;
	static int R = 12;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("res/1952.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		prices = new int[5];	
		plan = new int[12];
	
		for(int T=1; T<=TC; T++) {
			st=new StringTokenizer(br.readLine(), " ");
			for(int i=1; i<prices.length; i++) { //1일권, 1달권, 3달권만 입력
				prices[i] = Integer.parseInt(st.nextToken());
			}
			minFee = prices[4]; //1년권 가격 입력
			fee=0;
			st=new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			recursive(0);
			
			System.out.println("#"+(T)+" "+minFee);
		}//for T
	}//main
	
	private static void recursive(int cnt) {
		if(cnt >= R) {
			if(minFee>fee) {
				minFee = fee;
			}
			return;
		}

		if(plan[cnt] == 0) {
			recursive(cnt+1);
		} else {
			//1일권
			fee+=prices[1]*plan[cnt];
			recursive(cnt+1);
			fee-=prices[1]*plan[cnt];
			
			//1달권
			fee+=prices[2];
			recursive(cnt+1);
			fee-=prices[2];
			
			//3달권
			fee+=prices[3];
			recursive(cnt+3);
			fee-=prices[3];
		}
		
	}

}
