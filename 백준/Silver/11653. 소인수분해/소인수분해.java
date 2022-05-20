import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	//에라토스테네스의 채
	public static int[] arrPrime(int num1, int num2) {
		int cnt=0;
		boolean prime[] = new boolean[num2+1];
		prime[0] = prime[1] = true; //소수는 false
		
		for(int i=2; i*i<=num2; i++){
        	// prime[i]가 소수라면
            if(!prime[i]){
            	// prime[j] 소수가 아닌 표시
            	for(int j=i*i; j<=num2; j+=i) {
            		prime[j] = true;          
            	}//for j
            }//if     
        }//for i
       
        for(int i=num1; i<=num2;i++){
        	if(!prime[i]) cnt++;        
        }//for i

        int a=0;
		int[] numbers = new int[cnt];
        for(int i=num1; i<=num2;i++){
        	if(!prime[i]) {
        		numbers[a] = i;
        		a++;
        	}//if
        }//for i
        return numbers;
    }//isPrimeFastest()
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int num = Integer.parseInt(br.readLine());
		int[] primes = arrPrime(2,num+1);
		
		int index=0;
		while(num>1) {
			if(num%primes[index]==0) {
				num = num/primes[index];
				sb.append(primes[index]).append("\n");
			} else {
				index++;
			}
		}
		System.out.println(sb);
	}

}