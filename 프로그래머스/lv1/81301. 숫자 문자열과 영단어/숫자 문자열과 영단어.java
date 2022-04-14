import java.util.ArrayList;

class Solution {
    public int solution(String input) {
        ArrayList<Character> chars = new ArrayList<Character>();
		for (char c : input.toCharArray()) {
			  chars.add(c);
		}
        
        String answerStr = "";
		for(int i = 0; i<chars.size();i++) {
			if(!(chars.get(i)>='0' && chars.get(i)<='9')) {
				answerStr+= Character.toString(chars.remove(i));
				switch(answerStr) {
				case "one" :	chars.add(i, '1'); answerStr = ""; break;
				case "two" : 	chars.add(i, '2'); answerStr = ""; break;
				case "three" : 	chars.add(i, '3'); answerStr = ""; break;
				case "four" : 	chars.add(i, '4'); answerStr = ""; break;
				case "five" : 	chars.add(i, '5'); answerStr = "";  break;
				case "six" : 	chars.add(i, '6'); answerStr = ""; break;
				case "seven" : 	chars.add(i, '7'); answerStr = ""; break;
				case "eight" : 	chars.add(i, '8'); answerStr = ""; break;
				case "nine" : 	chars.add(i, '9'); answerStr = ""; break;
				case "zero" : 	chars.add(i, '0'); answerStr = ""; break;
				}
				i--;
			}
		}
		
		for (int i = 0; i < chars.size(); i++) {
			answerStr+= Character.toString(chars.get(i));
		}
        
		int answer = Integer.parseInt(answerStr);
        return answer;
    }
}