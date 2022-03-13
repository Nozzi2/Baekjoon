import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String myString = br.readLine();
        char[] myChars = myString.toCharArray();
        int idx = 0;
        int sum = 0;
        boolean flg;
        while(idx < myChars.length) {
        	flg = false;
        	if(!flg && myChars[idx] == 'c' && idx < myChars.length-1) {
        		if(myChars[idx+1]=='=' || myChars[idx+1]=='-') {
        			sum++;
            		idx++;
            		idx++;
        			flg = true;
        		}
        	}
        	if(!flg && myChars[idx] == 'd' && idx < myChars.length-1) {
        		if(myChars[idx+1]=='-') {
        			sum++;
            		idx++;
            		idx++;

        			flg = true;
        		}
        	}
        	if (!flg && myChars[idx] == 'd' && idx < myChars.length-2) {
        		if(myChars[idx+1]=='z' && myChars[idx+2]=='=') {
        			sum++;
        			idx++;
            		idx++;
            		idx++;
        			flg = true;
        			//System.out.print("dz= 했슴다");
        		}
        	}
        	if(!flg && myChars[idx] == 'l' && idx < myChars.length-1) {
        		if(myChars[idx+1]=='j') {
        			sum++;
            		idx++;
            		idx++;
        			flg = true;
        		}
        	}
        	if(!flg && myChars[idx] == 'n' && idx < myChars.length-1) {
        		if(myChars[idx+1]=='j') {
        			sum++;
            		idx++;
            		idx++;
        			flg = true;
        		}
        	}
        	if(!flg && myChars[idx] == 's' && idx < myChars.length-1) {
        		if(myChars[idx+1]=='=') {
        			sum++;
            		idx++;
            		idx++;
        			flg = true;
        		}
        	}
        	if(!flg && myChars[idx] == 'z' && idx < myChars.length-1) {
        		if(myChars[idx+1]=='=') {
        			sum++;
            		idx++;
            		idx++;
        			flg = true;
        			//System.out.print("z= 했슴다");
        		}
        	}
        	if(flg) {
        		continue;
        	} else {
        		sum++;
        		idx++;
        	}
        	
        }
        sb.append(sum).append('\n');
		System.out.print(sb);
	}

}
