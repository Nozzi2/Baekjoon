import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static Work[] works;
	static int[] maxPay;
	static int dDay;
	
	static class Work{
		int sdate;	//상담 시작일
		int edate;	//상담 종료일
		int term;	//상담 기간
		int pay;	//상담 보수
		
		Work(int sdate,int term,int pay){
			this.sdate = sdate;
			this.term = term;
			this.pay = pay;
			this.edate = sdate+term-1;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("res/14501boj.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dDay = Integer.parseInt(br.readLine());
		
		works = new Work[dDay+1];
		maxPay = new int[dDay+1];
		
		
		for(int i=1, endi=dDay; i<=endi; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			works[i] = new Work(i,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		}
		
		for(int i=dDay, endi=1; i>=endi; i--) {
			calcMaxPay(i, works[i]);
		}
		
		System.out.println(maxPay[1]);
	}

	private static void calcMaxPay(int date, Work work) {
		if(!isPossible(work)) { //퇴사 전에 끝낼 수 없는 일이면
			if(date == dDay) { //맨 마지막 날이라면 이익은 0원임
				maxPay[date] = 0;
			} else { //마지막 날이 아니라면 해당일 다음날의 최대이익값임
				maxPay[date] = maxPay[date+1]; 
			}
		} else {
			int noSelPay = 0;
			int selPay = 0;
			if(date == dDay) {
				selPay = work.pay;
			} else {
				noSelPay = maxPay[date+1];//선택하지 않으면 그 다음날의 최대이익과 같음
				selPay = work.pay;//선택하면 해당일의 이익과
				if(work.edate+1<=dDay) {//그일이 끝났을 떄의 최대이익을 더하면 됨
					selPay+=maxPay[work.edate+1];
				}
			}
			
			maxPay[date] = Math.max(selPay, noSelPay);
		}
	}

	//퇴사일 전까지 끝낼 수 있는 일인지 검사
	private static boolean isPossible(Work work) {
		if(work.edate > dDay) {
			return false;
		}
		return true;
	}
	
	
}