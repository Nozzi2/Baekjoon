import java.util.ArrayList;

class Solution {
    public String solution(String new_id) {
        ArrayList<Character> chars = new ArrayList<Character>();
		for (char c : new_id.toCharArray()) {
			  chars.add(c);
		}
		
		//1단계 대문자 > 소문자 전환
		for(int i = 0; i<chars.size();i++) {
			if(chars.get(i)>=65 && chars.get(i)<=90) {
				chars.set(i, (char)(chars.get(i)+32));
			}
		}
		
		//2단계 소문자 숫자 - _ . 빼고 다 삭제
		for(int i = 0; i<chars.size();i++) {
			if((chars.get(i)>='a' && chars.get(i)<='z') || 
				(chars.get(i)>='0' && chars.get(i)<='9') ||
				chars.get(i) == '-' ||
				chars.get(i) == '_' ||
				chars.get(i) == '.') {
			} else {
				chars.remove(i);
				i--;
			}
		}
		
		//3단계 두번 연속으로 된 .은 한번만 나오도록
		boolean conti=false;
		for(int i = 0; i<chars.size();i++) {
			if(chars.get(i) == '.') {
				if(conti) {
					chars.remove(i);
					i--;
				} else {
					conti = true;
				}
			} else {
				conti = false;
			}
		}
		
		//4단계 마침표가 처음이나 끝에 있으면 제거
		if(chars.size()!=0) {
			if(chars.get(0)=='.') {
				chars.remove(0);
			}
		}
		if(chars.size()!=0) {
			if(chars.get(chars.size()-1)=='.') {
				chars.remove(chars.size()-1);
			}
		}
		
		//5단계 빈문자열이라면 'a'삽입
		if(chars.size()==0) {
			chars.add('a');
		}
		
		//6단계 길이가 16자 이상이면 나머지 모두 제거 후 마지막에 . 있으면 제거
		if(chars.size()>=16) {
			for(int i=15; i<chars.size();i++) {
				chars.remove(i);
				i--;
			}
			if(chars.get(chars.size()-1)=='.') {
				chars.remove(chars.size()-1);
			}
		}
		
		//7단계 길이가 2자 이하이면 마지막 문자를 길이가 3이 될때까지 채우기
		for(int i = chars.size(); i<3;i++) {
			chars.add( chars.get(chars.size()-1) );
		}
		
		String answer = "";
        
		for (int i = 0; i < chars.size(); i++) {
			answer+= Character.toString(chars.get(i));
		}
		
		
        return answer;
    }
}