import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num1;
		int num2;
		int sum;
		
		
        
        while(true) {
        	num1 = Integer.parseInt( br.readLine() );
        	if(num1 == 0) {
        		break;
        	}
    		num2 = (num1*2);
    		sum=0;
    		
    		boolean prime[] = new boolean[num2+1];
    		prime[0] = prime[1] = true; //소수는 false
    		
    		for(int i=2; i*i<=num2; i++){
            	// prime[i]가 소수라면
                if(!prime[i]){
                	// prime[j] 소수가 아닌 표시
                	for(int j=i*i; j<=num2; j+=i) {
                		prime[j] = true;          
                	}
                }        
            }    
            
            // 소수 출력
            for(int i=num1+1; i<=num2;i++){
            	if(i!=1 && !prime[i]) {
            		sum++;
            		//System.out.println(i);
            	}
            }
            

            System.out.println(sum);
        }
	}
}