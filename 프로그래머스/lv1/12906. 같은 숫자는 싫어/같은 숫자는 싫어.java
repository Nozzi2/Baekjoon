import java.util.*;

public class Solution {
    public Integer[] solution(int []arr) {
        
        List<Integer> list = new ArrayList<>();
        
        int recentNum = -1; //최근에 저장된 숫자를 저장할 변수
        
        for(int i=0, endi=arr.length; i<endi; i++){
            if(arr[i] != recentNum){
                recentNum = arr[i];
                list.add(recentNum);
            }
        }
        
        Integer[] answer = list.toArray(new Integer[list.size()]);
        
        return answer;
    }
}