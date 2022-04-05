import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

	public static Object BinarySearch(Object key) {
		BigInteger lo = new BigInteger("0");
		BigInteger hi = (BigInteger) key;
		BigInteger mid = new BigInteger("0");
		
		boolean a = false;
		boolean b = false;
		if(hi.compareTo(lo) == 1) { //hi가 더 크면 1
			a = true;
		} else { //hi가 작으면 -1, 같으면 0
			a = false;
		}
		while(a) {
			mid = lo.add(  hi.subtract(lo).divide(BigInteger.TWO)  );
			
			if(mid.multiply(mid).compareTo((BigInteger)key) == 1) { //hi가 더 크면 1
				b = true;
			} else { //hi가 작으면 -1, 같으면 0
				b = false;
			}
			
			
			if(b) {
				hi = mid;
			} else {
				lo = mid.add(BigInteger.ONE);
			}
			
			if(hi.compareTo(lo) == 1) { // while문을 반복하기 위하 lo<hi
				a = true;
			} else {
				a = false;
			}
			
		}
		
		
		if(lo.compareTo(BigInteger.ONE) == 0) { // lo 가 1과 같을 경우
			return lo;
		} else {
			return lo.subtract(BigInteger.ONE); // lo-1
		}
		
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		String  s = br.readLine();
		BigInteger value = new BigInteger(s);
		
		value = (BigInteger)BinarySearch(value);
		System.out.println(value);
	}
}