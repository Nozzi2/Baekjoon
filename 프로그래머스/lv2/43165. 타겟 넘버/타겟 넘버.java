import java.io.*;
import java.util.*;

//DFS (깊이우선탐색)
class Solution {
    
    static int result=0;
    static int[] arr;
    static int target;
    
    public int solution(int[] numbers, int t) {
        arr = numbers;
        target =t;
        //재귀로 배열에 한개씩 접근해서 target값과 같으면 result++해주면 됨
        //재귀함수에서 필요한 파라미터
            //현재 합산한 값, 현재 계산중인 index
        
        dfs(0,0);
        
        return result;
    }
    
    /**
    sum 현재까지 합산한 값
    cnt 현재 계산중인 index
    */
    public static void dfs(int sum, int cnt){
        //종료조건
        if(cnt>= arr.length){ //마지막까지 계산이 끝났는지?
            // System.out.printf("종료 sum:%d, cnt:%d / "+str+"\n",sum,cnt);
            result = target==sum?result+1:result;
            return;
        }
        
        int tmp = arr[cnt];
        //+계산
        dfs(sum+tmp,cnt+1);

        //-계산
        dfs(sum-tmp,cnt+1);
        
    }
}