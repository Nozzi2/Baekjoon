import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Deque<Integer> Deque = new ArrayDeque<>();
		
		int testCount = Integer.parseInt(br.readLine()); 
		int meaninglessNumber;
		String myString;
		boolean empty; //큐가 비어있거나, 입력할 값이 없는지 확인할 변수
		boolean forward;
		
		
		for(int x =0; x < testCount; x++) {
			
			empty = false;
			forward = true;
			myString = br.readLine();
			char[] myChars = myString.toCharArray();
			
			meaninglessNumber = Integer.parseInt(br.readLine());
			
			myString = br.readLine();
	        myString = myString.substring(1,myString.length()-1);
	        
	        String[] numbers = myString.split(",");
	        
	        if(numbers[0]!="") {
	        	for(int i = 0; i < numbers.length; i++) {
	        		Deque.add(Integer.parseInt(numbers[i]));
				}
	        }
        	
	        for (int i = 0; i < myChars.length; i++) {
	        	if(myChars[i]=='R') {
	        		forward = !forward;
	        	} else if(myChars[i]=='D') {
	        		if(numbers[0]=="") { //입력된 것이 없으면
	    	        	empty = true;
	    	        } else {
	    	        	if(forward) {
		        			if(Deque.pollFirst()==null) {
			        			empty = true;
			        			break;
			        		}
		        		} else {
		        			if(Deque.pollLast()==null) {
			        			empty = true;
			        			break;
			        		}
		        		}	
	    	        }
	        		
	        		
	        	}
	        	
	        }
	        
	        
	        
    		Iterator<Integer> Forward = Deque.iterator();
    		Iterator<Integer> Reverse = Deque.descendingIterator(); //역방향으로 읽는다.
	        
	        
	        //출력문
	        
	        if(empty) {
	        	sb.append("error");
	        	System.out.println(sb);
	        } else {

	        	if(forward) {
	        		while(Forward.hasNext()){//다음값이 있는지 체크
						sb.append(Forward.next()).append(",");
			        }
	        	} else {
	        		while(Reverse.hasNext()){//다음값이 있는지 체크
						sb.append(Reverse.next()).append(",");
			        }
	        	}
	        	if(sb.length()<1) {
					System.out.println("["+sb+"]");
	        	} else {
					sb.deleteCharAt(sb.length()-1);
					System.out.println("["+sb+"]");
	        	}
	        }
    		
    		//System.out.println(Deque.size());
			
	        //덱,StringBuilder 초기화
			Deque.clear();
			sb.setLength(0); 
		}
	}

}