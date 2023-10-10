import java.io.*;
import java.util.*;

/* 231010 14:50 시작
16진수 세자리수를 10진수로 바꾸고, 나올 수 있는 모든 숫자의 10번째 값이 비밀번호가 된다.
!! 같은 숫자는 나오면 안됨 !!
	ex) 1 2 2 3 > 1 2 3

N이 28일때 16진수 숫자는 7개가 나온다.
16진수 7개일떄 최대값은 int 값의 범위를 넘는다.
그렇기 때문에 long 으로 저장해야함.


n 회전마다 나올 수 있는 16진수 반환하는 메소드
	N/4 -1 회전까지 가능함.

16진수 > 10진수 메소드

10진수 > 16진수 메소드 //만들필요 없을듯?

16진수 배열을 Set에 저장하는 메소드

Set배열 정렬

arr[9]값 출력



 */
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TEST_CASE = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for(int T=1; T<=TEST_CASE; T++) {
			st = new StringTokenizer(br.readLine());
			int totalSize = Integer.parseInt(st.nextToken());
			int targetRank = Integer.parseInt(st.nextToken());
			int size = totalSize/4;
			
			char[] words = br.readLine().toCharArray();
//			System.out.println(Arrays.toString(words));
			
			Set<Long> jinsuTens = new HashSet<>();
			for(int s=0; s<totalSize; s+=size) {
				for(int i=s; i<size+s; i++) {
					char[] jinsu16 = new char[size];
					int idx16 = 0;
					for(int j=i; j<i+size; j++) {
						int idxWords = j%totalSize;
						jinsu16[idx16++] = words[idxWords];
					}
//					System.out.print(Arrays.toString(jinsu16));
					long jinsuTen = getJinsuTen(jinsu16);
//					System.out.println(" "+jinsuTen);
					jinsuTens.add(jinsuTen);
				}
			}
			
			int rankSize = jinsuTens.size();
			long[] ranks = new long[rankSize];
			int idx=0;
			for(Long l : jinsuTens) {
				ranks[idx++] = l;
			}
			Arrays.sort(ranks);
			
//			System.out.println(Arrays.toString(ranks));
			
			sb.append('#').append(T).append(' ');
			sb.append(ranks[rankSize-targetRank]);
			sb.append("\n");
			
			
//			System.out.println("==================================END");
		}
		
		System.out.println(sb.toString());
		
		
		
	}

	private static long getJinsuTen(char[] arr) {
		long num = 0;
		long jaritsu = 1;
		int size = arr.length;
		
		for(int i=size-1; i>=0; i--) {
			long num16 = 0;
			if(arr[i] <= '9' && arr[i] >= '0') {
				num16 = (long) (arr[i]-'0');
			} else {
				num16 = (long)(arr[i]-'A')+10;
			}
			num+= num16*jaritsu;
			jaritsu*=16;
		}
		return num;
	}

}
/*
5
12 10
1B3B3B81F75E
16 2
F53586D76286B2D8
20 14
88F611AE414A751A767B
24 16
044D3EBA6A647B2567A91D0E
28 11
8E0B7DD258D4122317E3ADBFEA99

1
8 1
1B3B3B81

1
4 1
1B3B

1
12 10
1B3B3B81F75E

4
28 1
FFFFFFFFFFFFFFFFFFFFFFFFFFFF
8 1
00000000
28 1
0FFFFFFFFFFFFFFFFFFFFFFFFFFF
8 1
FFFFFFFF


*/