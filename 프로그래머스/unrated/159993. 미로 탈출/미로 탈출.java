import java.util.*;
import java.io.*;

class Solution {
    int[] nr = {-1, 1, 0, 0};
    int[] nc = {0, 0, -1, 1};
    int lengthR;    //행 갯수
    int lengthC;    //열 갯수
    int answer = -1; //움직인 횟수
    boolean isArrived;
    char[][] map;
    boolean[][][] visitedMap;
    
    class Pos {
        int r;
        int c;
        int pulled; //레버를 당겼는지 여부 (0안당김, 1당김)
        int step;   //소요 시간
        
        public Pos(int r, int c, int pulled, int step){
            this.r = r;
            this.c = c;
            this.pulled = pulled;
            this.step = step;
        }
        
        public boolean isOut() {
            return r<0 || r>=lengthR || c<0 || c>=lengthC;
        }
        
        public boolean isWall() {
            return map[r][c]=='X';
        }
        
        public boolean isEnd() {
            return pulled == 1 && map[r][c]=='E';
        }
        
        public boolean isLever() {
            return map[r][c]=='L';
        }
        
        public boolean isVisited() {
            return visitedMap[pulled][r][c];
        }
        
        public void check(){
            this.step++;
            visitedMap[pulled][r][c] = true;
        }
        
    }
    
    public int solution(String[] inputs) {
        lengthR = inputs.length;
        lengthC = inputs[0].toCharArray().length;
        
        map = new char[lengthR][lengthC];
        visitedMap = new boolean[2][lengthR][lengthC]; //방문여부 3차원 배열
        //[0][r][c] 레버를 안 당겼을 때 방문여부
        //[1][r][c] 레버를 당겼을 때 방문여부
        
        for(int i=0; i<lengthR; i++){
            map[i] = inputs[i].toCharArray();
        }
        
        Pos start = null; //시작위치를 담을 객체
        
        //시작위치 탐색
        for(int i=0; i<lengthR; i++){
            for(int j=0; j<lengthC; j++){
                if(map[i][j]=='S'){
                    start = new Pos(i,j,0,0);
                    break;
                }
            }
            if(start != null) break;
        }
        
        dfs(start); //dfs 탐색
        
        return answer;
    }
    
    public void dfs(Pos start){
        Queue<Pos> que = new LinkedList<>();
        que.offer(start);
        visitedMap[0][start.r][start.c] = true;
        
        while(!que.isEmpty()){
            Pos cur = que.poll();
            for(int i=0; i<4; i++){
                Pos next = new Pos(cur.r+nr[i], cur.c+nc[i], cur.pulled, cur.step);
                //여기 아래 순서 중요함!
                if(next.isOut()) continue;
                if(next.isWall()) continue;
                if(next.isLever()){
                    next.pulled = 1; //당김 처리
                }
                if(next.isVisited()) continue;
                
                next.check();
                if(next.isEnd()) {
                    answer = next.step;
                    return;
                }
                
                que.offer(next);
            }
        }
    }
    
    
    
    
}