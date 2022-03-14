import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int num1 = Integer.parseInt(br.readLine()); 
		boolean flg = true;

		char[] c1 = null;
		char[] c2 = null;
		
		
		if(num1 == 1) {
			String s1 = br.readLine();
	        sb.append(s1);
	    } else {
	    	for (int i = 0; i < num1; i++) {
	    		String s1 = br.readLine();
	    		if(flg) {
	    			c1 = s1.toCharArray();
	    		} else {
	    			c2 = s1.toCharArray();
	    		}
		        if(i>0) {
		        	if(flg) {
		        		for (int a = 0; a < c1.length; a++) {
			    			if(c1[a]=='?' || c1[a]!=c2[a]) {
			    				c1[a] = '?';
			    				c2[a] = '?';
			    			}
			    		}
		    		} else {
		    			for (int a = 0; a < c2.length; a++) {
			    			if(c2[a]=='?' || c1[a]!=c2[a]) {
			    				c1[a] = '?';
			    				c2[a] = '?';
			    			}
			    		}
		    		}
		        	
		        }
		        
		        flg = !flg;
		        
		        
			}
	    	if(!flg) {
	    		for (int a = 0; a < c2.length; a++) {
	    			sb.append(c2[a]);
	    		}
	    	} else {
	    		for (int a = 0; a < c1.length; a++) {
	    			sb.append(c1[a]);
	    		}
	    	}
	    }
		System.out.print(sb);
	}
}
