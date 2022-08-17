import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int[] dr = {0, -1, 0, 1, 0};
	static int[] dc = {0, 0, 1, 0, -1};
	static int[][] map=new int[10][10];
	static int[] pows; 				//충전기들의 성능이 담겨있는 배열
	static int sum;					//마지막에 출력할 A,B의 합계
	static int chargerSize;			//충전기 갯수
	
	static class Position {
		int r;
		int c;
		
		Position(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public void move(int dr, int dc) {
			this.r += dr;
			this.c += dc;
		}
		
		public static int getDist(int cr, int cc, int nr, int nc){
			int distR = Math.abs(cr-nr);
			int distC = Math.abs(cc-nc);
			
			return distR+distC;
		}
	}
	
	static class Charger implements Comparable<Charger> {
		int r;
		int c;
		int level;
		int pow;
		
		Charger(int r, int c, int level, int pow){
			this.r = r;
			this.c = c;
			this.level = level;
			this.pow = pow;
		}

		@Override
		public int compareTo(Charger o) {
			int level = this.pow-o.pow;
			return level;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("res/5644jungol.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TC = Integer.parseInt(br.readLine());
		for(int T=1, endT=TC; T<=endT; T++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int moveSize = Integer.parseInt(st.nextToken());
			chargerSize = Integer.parseInt(st.nextToken());
			//System.out.println(moveSize+" "+chargerSize);
			int[] moveA = new int[moveSize];
			int[] moveB = new int[moveSize];
			sum=0;
			st = new StringTokenizer(br.readLine());
			for(int i=0, end=moveSize; i<end; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
				//System.out.print(moveA[i]);
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0, end=moveSize; i<end; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
				//System.out.print(moveB[i]);
			}
			
			List<Charger> chargers = new ArrayList<>(chargerSize);
			for(int i=0, end=chargerSize; i<end; i++) {
				st = new StringTokenizer(br.readLine());
				int tmp = Integer.parseInt(st.nextToken())-1;
				chargers.add(new Charger(
											Integer.parseInt(st.nextToken())-1,
											tmp,
											Integer.parseInt(st.nextToken()),
											Integer.parseInt(st.nextToken())
										 ));
			}
			
			
			pows = new int[chargerSize+1];
			pows[0] = 0;
			for(int i=1, end=chargerSize; i<=end; i++) {
				pows[i] = chargers.get(i-1).pow;
			}
			
			//0, 40, 70, 100 이런식으로 정렬돼야함
			Arrays.sort(pows);
			Collections.sort(chargers);
			
			map = new int[10][10];
			for(int i=0, endi=chargers.size(); i<endi; i++) {
				mapSetting(chargers.get(i), i+1); //비트연산에서 0은 아무 충전기가 없다는 의미이므로 1부터 시작해야한다.
			}
			
//			System.out.println(T+"번째 map");
//			for(int i=0, endi=10; i<endi; i++) {
//				for(int j=0, endj=10; j<endj; j++) {
//					System.out.printf("%2d ",map[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println("---------------------\n\n");
			
			Position A = new Position(0,0);
			Position B = new Position(9,9);
			sum+=abSum(A,B);
//			bw.write("0번째 A:"+A.r+","+A.c+"\n");
//			bw.write("0번째 B:"+B.r+","+B.c+"\n");
//			bw.write("0번 합계 : "+abSum(A,B)+"\n\n");
			for(int i=0, endi=moveSize; i<endi; i++) {
				A.move(dr[ moveA[i] ], dc[ moveA[i] ]);
				B.move(dr[ moveB[i] ], dc[ moveB[i] ]);
				int tmp=abSum(A,B);
				sum+=tmp;
//				bw.write((i+1)+"번째 A:"+A.r+","+A.c+" = "+map[A.r][A.c]+"\n");
//				bw.write((i+1)+"번째 B:"+B.r+","+B.c+" = "+map[B.r][B.c]+"\n");
//				bw.write((i+1)+"번 합계 : "+tmp+"\n\n");
			}
			bw.write("#"+T+" "+sum+"\n");
		}
		bw.flush();
	}

	private static int abSum(Position a, Position b) {
		int score=0;
		if(map[a.r][a.c] == map[b.r][b.c]) { //A,B가 같은 위치에 있다면 같은 충전기 값을 나눠가짐
			score=calcSameScore(a);
		} else {
			boolean flagA = true;
			boolean flagB = true;
			int addCnt=0;	//2개의 충전량을 더하면 끝내기 위한 변수
			
			for(int i=chargerSize, endi=1; i>=endi; i--) {
				int curA =map[a.r][a.c] & (1<<i);
				int curB =map[b.r][b.c] & (1<<i);
				if(curA!=0 && curB!=0) {
					score+=pows[i];
					addCnt++;
					continue;
				}
				if(flagA && addCnt!=2 && curA !=0) {
					score+=pows[i];
					addCnt++;
					flagA=false;
				} else if(flagB && addCnt!=2 && curB !=0) {
					score+=pows[i];
					addCnt++;
					flagB=false;
				}
				if((!flagA && !flagB) || addCnt==2) {
					break;
				}
			}
		}
		return score;
	}

	private static int calcSameScore(Position a) {
		int bit = map[a.r][a.c];
		int score = 0;
		int addCnt=0;
		for(int i=chargerSize, endi=1; i>=endi; i--) {
			if( (bit & 1<<i) != 0) {
				score += pows[i];
				addCnt++;
			}
			if(addCnt==2) break;
		}
		return score;
	}

	private static void mapSetting(Charger c, int bit) {
		int sr = (c.r-c.level)<0?0:c.r-c.level;
		int er = (c.r+c.level)>=10?10:c.r+c.level+1;
		int sc = (c.c-c.level)<0?0:c.c-c.level;
		int ec = (c.c+c.level)>=10?10:c.c+c.level+1;
		
		for(int i=sr, endi=er; i<endi; i++) {
			for(int j=sc, endj=ec; j<endj; j++) {
				int dist = Position.getDist(i,j, c.r, c.c);
				if(dist<=c.level) {
					map[i][j]+=1<<bit; //중복되지 않는 비트 연산자 추가
				}
			}
		}
	}

	
	
	
}