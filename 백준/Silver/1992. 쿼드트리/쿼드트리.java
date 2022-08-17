import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
재귀함수 호출시 괄호 출력하고 리턴하기 전에 괄호 닫기
재귀 호출하여 모든 숫자가 같다면 해당 숫자 출력
*/

public class Main {
	static int[] dr= {0,0,1,1};
	static int[] dc= {0,1,0,1};
	static int[][] map;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			char[] chars = s.toCharArray();
			for(int j=0; j<N; j++) {
				map[i][j] = chars[j]-'0';
			}
		}
		
		recursion(0,0,N);
		//bw.flush();
		System.out.println(sb);
	}

	private static void recursion(int r, int c, int size) throws IOException {
		//bw.write("(");
		
		if(isSame(r, c, size)) {
			//bw.write(map[r][c]);
			sb.append(map[r][c]);
		} else {
			sb.append("(");
			size = size/2;
			
			for(int i=0; i<4; i++) {
				recursion(r+(size*dr[i]),c+(size*dc[i]),size);
			}
			sb.append(")");
		}
		//bw.write(")");
		
	}

	private static boolean isSame(int r, int c, int size) {
		int num = map[r][c];
		boolean same = true;
		for(int i=r, endi=r+size; i<endi; i++) {
			for(int j=c, endj=c+size; j<endj; j++) {
				if(map[i][j]!=num) {
					same = false;
					break;
				}//if
			}//for j
			if(!same) break;
		}//for i
		return same;
	}
}