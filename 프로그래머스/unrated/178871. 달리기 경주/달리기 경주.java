import java.util.*;

class Solution {
    
    Map<String, Integer> players;
    
    public String[] solution(String[] ranks, String[] callings) {
        //선수 목록을 먼저 map에 index와 같이 넣어놓는다.
        players = new HashMap<String, Integer>();
        for(int i=0, endi=ranks.length; i<endi; i++){
            players.put(ranks[i], i);
        }
        
        //1. map에서 순서 확인 및 변경
        for(int i=0, endi=callings.length; i<endi; i++){
            //callings에서 부르는 이름이 map에 몇번 인덱스에 저장되어있는지 확인
            //해당 인덱스을 저장하고
            int curRank = players.get(callings[i]);

            //해당인덱스-1에 있는 이름도 확인한다.
            String curPlayer = ranks[curRank];
            String prevPlayer = ranks[curRank-1];
            
            //둘의 인덱스(순위)를 서로 변경해준다.
            players.put(curPlayer, curRank-1);
            players.put(prevPlayer, curRank);

            //2. 배열에서 변경
            //맵에서도 바꿔줬으니 출력할 배열에서도 순서를 변경해준다.
            ranks[curRank] = prevPlayer;
            ranks[curRank-1] = curPlayer;
        }
        
        return ranks;
    }
}