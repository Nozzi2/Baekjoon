//15:44 ~

import java.util.*;

// [[0, 4], [1, 2], [1, 3], [3, 4]] => 2
// [[0, 4], [0, 1], [2, 3]] => 2


class Solution {
    public int solution(int[][] targets) {
        
        
        
        
        //2차원 배열을 시작 값 오름차순, 끝값 내림차순으로 정렬하고
        Arrays.sort(targets, (o1,o2) -> o1[1] != o2[1] ? o1[1]-o2[1]:o2[0]-o1[0]);
        
        int endTarget = targets[0][1]; //시작값의 끝값을 저장
        int result = 1; //시작값을 하나 설정할 때마다 result++
        
        //targets배열 탐색
        for(int i=1, endi=targets.length; i<endi; i++){
            // System.out.print("현재 값 : ");
            // for(int t : targets[i]){
            //     System.out.print(t);
            // }
            // System.out.println();
            
            //끝값이 시작값보다 크다면?
            if(endTarget > targets[i][0]){
                // System.out.println("포함됨");
                continue;
            } else { //작다면?
                //시작값, 끝값을 해당 index의 값으로 설정
                endTarget = targets[i][1];
                result++;
                // System.out.println("초과함! ++");
            }
        }
        
        // for(int[] target : targets){
        //     for(int t : target){
        //         System.out.print(t);
        //     }
        //     System.out.println();
        // }
        
        return result;
    }
}