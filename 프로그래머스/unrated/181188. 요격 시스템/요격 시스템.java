//15:44 ~ 17:00
//1시간 16분 소요

import java.util.*;


class Solution {
    public int solution(int[][] targets) {
        //2차원 배열을 끝 값 오름차순, 시작값 내림차순으로 정렬하고
        Arrays.sort(targets, (o1,o2) -> o1[1]-o2[1]);
        
        int endTarget = targets[0][1]; //시작값의 끝값을 저장
        int result = 1; //시작값을 하나 설정할 때마다 result++
        
        //targets배열 탐색
        for(int i=1, endi=targets.length; i<endi; i++){
            
            //끝값이 시작값보다 크다면?
            if(endTarget > targets[i][0]){
                continue;
            } else { //작다면?
                endTarget = targets[i][1];
                result++;
            }
        }
        
        return result;
    }
}