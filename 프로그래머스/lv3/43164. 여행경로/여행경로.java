import java.util.*;
import java.io.*;

/*
모든 tickets에서 DFS탐색
사전순으로 가장 먼저 오는 건 무시하고 그냥 탐색하여 도착하면 List에 저장
탐색이 완료된 경우 방문했던 경로들 모두 ' '로 구분하여 List에 저장
DFS 탐색 끝난 후 List 정렬 후 첫번째 요소 출력
*/
class Solution {
    
    static boolean[] isUsed;
    static ArrayList<String> routes;
    public String[] solution(String[][] tickets) {
        routes = new ArrayList<String>();
        isUsed = new boolean[tickets.length];
        dfs("ICN", "ICN", tickets, 0);
        
        // System.out.println("방문 리스트 : "+routes);
        // 방문 리스트 : [ICN SFO ATL ICN ATL SFO, ICN ATL ICN SFO ATL SFO, ICN ATL SFO ATL ICN SFO]
        // 위 방문 리스트를 정렬하면 사전순으로 정렬이 되고, 가장 첫번째 배열을 출력하면 된다.
        Collections.sort(routes); //문제의 핵심!!
        
        String[] answer = routes.get(0).toString().split(" ");
        return answer;
    }
    
    public void dfs(String start, String route, String[][] tickets, int cnt){
        if(cnt == tickets.length){
            routes.add(route);
            return;
        }
        
        for(int i=0, endi=tickets.length; i<endi; i++){
            if(start.equals(tickets[i][0]) && !isUsed[i]){
                String next = tickets[i][1];
                // System.out.println(next);
                isUsed[i]=true;
                dfs(tickets[i][1], route+" "+next, tickets, cnt+1);
                isUsed[i]=false;
            }
        }
        
    }
}