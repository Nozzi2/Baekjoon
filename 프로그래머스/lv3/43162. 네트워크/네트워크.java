import java.util.*;
import java.io.*;

class Solution {
    
    static boolean[] isChecked; //방문여부 저장
    
    public int solution(int n, int[][] computers) {
        isChecked = new boolean[n];
        int netCnt = 0;
        
        for(int i=0; i<n; i++){
            if(!isChecked[i]){ //방문하지 않았다면?
                System.out.println(i+"번째 탐색 ---");
                netCnt++; //네트워크 갯수++
                bfs(i, computers); //탐색 진행
            }
        }
        
        return netCnt;
    }
    
    public void bfs(int start, int[][] computers){
        Queue<Integer> que = new ArrayDeque<>();
        que.add(start);
        
        while(!que.isEmpty()){
            int next = que.poll();
            if(isChecked[next]) {
                continue;
            } else {
                System.out.println(start+"와 "+next+"는 연결되어있음.");
                isChecked[next] = true;
            }
            
            for(int i=0, endi=computers.length; i<endi; i++){
                if(isChecked[i]) continue; //방문했었다면 넘기기
                if(computers[next][i]==0) continue; //연결되어 있지 않다면 넘기기
                que.add(i);
            }
        }
    }
}