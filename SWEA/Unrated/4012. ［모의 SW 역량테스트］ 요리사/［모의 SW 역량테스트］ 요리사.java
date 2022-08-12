import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int R;
	static int sum;
	static int sumA;		//A음식의 합계
	static int sumB;		//B음식의 합계
	static int min;			//A,B음식간 차를 구했을 때의 최솟값
	static int[] numbers;	//선택한 인덱스를 담을 배열
	static int[] tmp;
	static int[][] map;
	static List<Integer[]> combs;
		
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("res/4012swex.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//br.readLine();
		//int TC = 1;
		int TC = Integer.parseInt(br.readLine());
		for(int T=1; T<=TC; T++) {
			N=Integer.parseInt(br.readLine());
			R = N/2;
			map = new int[N][N];
			combs = new ArrayList<Integer[]>();
			numbers = new int[R];
			min=Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}//for j
			}//for i
			
			comb(0,0);
			
			for(Integer[] dataInteger : combs) {
				//System.out.println("for문 시작");
				int[] data= Arrays.stream(dataInteger).mapToInt(i->i).toArray();
				sum=0;
				tmp = new int[2];
				comb2(data,0,0);
				//System.out.println("AAAA");
				sumA=sum;
				
				//0~N-1중에서 data에 속한 데이터를 뺴고 남은 배열
				int[] nMinusComb= new int[R];
				boolean[] isTrue = new boolean[N];
				for(int i=0; i<R; i++) {
					isTrue[data[i]] = true;
				}
				int idx=0;
				for(int i=0; i<N; i++) {
					if(!isTrue[i]) {
						nMinusComb[idx++] = i;
					}
				}
				
				sum=0;
				tmp = new int[2];
				comb2(nMinusComb,0,0);
				//System.out.println("BBBB");
				sumB=sum;
				
				min=Math.min(min, Math.abs(sumA-sumB) );
			}
			
			bw.write("#"+T+" "+min+"\n");
			
		}//for T
		bw.flush();
		
	}

	//R개 중에서 2개를 조합으로 뽑기
	private static void comb2(int[] data, int cnt, int start) {
		if(cnt == 2) {
			sum+=map[tmp[0]][tmp[1]];
			sum+=map[tmp[1]][tmp[0]];
			//System.out.println(Arrays.toString(tmp));
			return;
		}
		
		for(int i=start; i<R; i++) {
			tmp[cnt] = data[i];
			comb2(data, cnt+1, i+1);
		}
	}

	//N개 중에서 R 개를 조합으로 뽑기  
	private static void comb(int cnt, int start) {
		if(cnt == R) {
			combs.add( Arrays.stream(numbers).boxed().toArray(Integer[]::new) );
			return;
		}
		
		for(int i=start; i<N; i++) { //0,1,2, ... , N-1
			numbers[cnt] = i;
			comb(cnt+1, i+1);
		}
		
	}
	
}