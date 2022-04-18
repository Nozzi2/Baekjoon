import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	public int wayout(int[][] miro, int x, int y) {
		if(x<0 || x>=miro.length || y<0 || y>=miro[x].length ) { //범위가 벗어나는지?
			return 0;
		} else if (miro[x][y] != 1) { //방문한 위치, 벽, 방문했다가 아닌 위치 인지?
			return 0;
		} else {
			miro[x][y] = 2; // 방문한 길로 설정
			return 1+wayout(miro, x+1, y)+wayout(miro, x, y+1)
			+wayout(miro, x-1, y)+wayout(miro, x, y-1);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] miro = new int[n][n];
		String path;
		
		for(int a = 0; a<miro.length;a++) {
			path = br.readLine();
			for(int b=0; b<path.length(); b++) {
				miro[a][b] = Integer.parseInt(path.substring(b, b+1));
			}
		}
		
		/* 출력
		for(int a = 0; a<miro.length;a++) {
			for(int b=0; b<miro[a].length; b++) {
				System.out.print(miro[a][b]+" ");
			}
			System.out.println();
		}*/
		
		Main hn = new Main();
		int tmp=0;
		ArrayList<Integer> nums = new ArrayList<Integer>();
		for(int a = 0; a<miro.length;a++) {
			for(int b=0; b<miro[a].length; b++) {
				tmp = hn.wayout(miro, a, b);
				if(tmp>0) {
					nums.add(tmp);
				}
			}
		}
		
		Collections.sort(nums); //오름차순
		
		System.out.println(nums.size());
		for(int i = 0; i<nums.size();i++) {
			System.out.println(nums.get(i));
		}
	}
}