import java.util.*;

class Solution {
    
    final int MAX = 987654321;
    
    public int[] solution(String[] keymap, String[] targets) {
        int[] alphabets = new int[26];
        Arrays.fill(alphabets, MAX);
        
        for(int i=0, endi=keymap.length; i<endi; i++) {
            char[] chars = keymap[i].toCharArray();
            for(int j=0, endj=chars.length; j<endj; j++) {
                int alpha = chars[j]-'A';
                alphabets[alpha] = Math.min(alphabets[alpha], j+1);
            }
        }
        
        int size = targets.length;
        int[] answer = new int[size];
        
        for(int i=0 ; i<size; i++) {
            int result = 0;
            char[] chars = targets[i].toCharArray();
            for(int j=0, endj=chars.length; j<endj; j++){
                int alpha = chars[j]-'A';
                int count = alphabets[alpha];
                if(count == MAX) {
                    result = -1;
                    break;
                } else {
                    result += count;
                }
            }
            answer[i] = result;
            
        }
        
        return answer;
    }
}