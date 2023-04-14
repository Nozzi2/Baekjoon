//13:36 시작

//n미터의 벽
//페인트가 벗겨져서 덧칠할거임
//n개의 구역으로 나누고
//1~n번 번호를 붙임
//룰러의 길이 m
    //룰러가 벽을 벗어나면 안됨
    //구역의 일부분만 포함되도록 칠하면 안됨??
    //(경계선이나 벽의 끝부분에 맞춘 후 룰러를 위아래로 움직여서 칠함)
//페인트칠 구역으로 안전히 칠한 후 벽에서 룰러를 떼면 한번 칠했다고 함


class Solution {
    
    public int solution(int n, int m, int[] section) {
        
        //가장 먼저 룰러를 댈 곳을 찾는다.
        int endRuler = section[0]+m;
        int answer = 1;
        
        for(int i=1, endi=section.length; i<endi; i++) {
            if(section[i]>=endRuler){
                endRuler = section[i]+m;
                answer++;
            }
        }
        //찾았다면 result++
        //룰러를 댈 곳부터 룰러의 끝부분까지 계산해놓는다.
        //section을 탐색하면서 끝부분을 넘어가는지 검사한다.
        //끝부분을 넘어간다면 result++해주고 룰러의 끝점을 계산한다.
        
        
        
        
        
        return answer;
    }
}