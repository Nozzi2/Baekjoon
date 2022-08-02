import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		//System.setIn(new FileInputStream("src/inputText/1954.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int TC = Integer.parseInt(br.readLine());
		
		//시계방향으로 움직이기 위한 방향
		int[] dr = {0,1,0,-1};
		int[] dc = {1,0,-1,0};
		int size;
		int[][] arr;
		int index;
		int maxNum;
		int cr;
		int cc;
		
		for(int T=1; T<=TC; T++) {
			size = Integer.parseInt(br.readLine());
			arr = new int[size+2][size+2];
			maxNum = size*size;
			index=0;
			cr=1;
			cc=1;
			
			for(int i=1; i<=maxNum; i++) {
				arr[cr][cc] = i;
				cr = cr+dr[index];
				cc = cc+dc[index];
				//배열 범위를 초과하거나, 이미 입력했던 숫자인지 확인
				if(cr<1 || cc<1 || cr>size || cc>size || arr[cr][cc]!=0 ) {
					cr = cr-dr[index];
					cc = cc-dc[index];
					index = (index+1)%4; //0~3 반복
					cr = cr+dr[index];
					cc = cc+dc[index];
				}
			}
			
			//출력
			bw.write("#"+T+"\n");
			for(int i=1; i<=size; i++) {
				for(int j=1; j<=size; j++) {
					bw.write(arr[i][j]+" ");
				}
				bw.write("\n");
			}
		}
		bw.flush();
	}
}