import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class Main {
	
	//31의 제곱을 출력해주는 메소드
	public static Object square(int num) {
		BigInteger num31 = new BigInteger("31");
		if(num == 0) {
			BigInteger bigNumber1 = new BigInteger("1");
			return bigNumber1;
		} else {
			BigInteger bigNumber2 = new BigInteger("31");
			for(int i = 1; i < num; i++) {
				bigNumber2 = bigNumber2.multiply(num31);
			}		
			
			return bigNumber2;
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		final int modN = 1234567891;
		
		BufferedReader br = new BufferedReader
		           (new InputStreamReader(System.in));
		int numBR = Integer.parseInt(br.readLine()); 
		String input = br.readLine();
		byte[] bytes = input.getBytes(StandardCharsets.US_ASCII); //string to ascii byte
		int ByteToInt = 0;
		BigInteger[] arrBigInt = new BigInteger[numBR];
		BigInteger sum = new BigInteger("0");
		
		for(int i = 0; i<numBR; i++) {
			ByteToInt = (int)bytes[i]-96; //a의 출력값이 97이기 때문에 1로 출력해야함
			arrBigInt[i] = BigInteger.valueOf(ByteToInt); //알파벳의 값 입력 ex)a = 1, b = 2
			arrBigInt[i] = arrBigInt[i].multiply((BigInteger)square(i)); // 31의 제곱을 곱한다
			sum=sum.add(arrBigInt[i]); //마지막에 1234567891로 나눠줄 합계
		}
		//1234567891로 나눈 나머지 출력
		System.out.println(sum.remainder(BigInteger.valueOf(modN))); 

	}

}
