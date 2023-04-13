class Solution {
    
    int H,W;
    boolean[][] map;
    
    public int[] solution(String[] park, String[] routes) {
        
        //지나다니는 길 'O'
        //장애물 'X'
        //["방향 거리", ... ]
        
        //공원을 벗어나는지? || 장애물을 만나는지?
            //안움직여
        //else
            //해당 위치로 이동
        
        //1. park에서 문자열로 map을 생성
        int[] curLoc = new int[2];
        
        //park배열을 통해 H와 W를 구한다.
        H = park.length;
        W = park[0].toCharArray().length;
        
        //이동 가능 여부를 판별할 2차원 int 배열 선언
        map = new boolean[H][W];
        
        for(int i=0; i<H; i++){
            //park에서 얻은 String을 char 배열로 바꿔준다.
            char[] chars = park[i].toCharArray();
            for(int j=0; j<W; j++){
                if(chars[j]=='S'){
                    curLoc[0] = i;
                    curLoc[1] = j;
                    map[i][j] = true;
                } else {
                    //char배열을 순회하여 이동 가능 여부를 2차원 배열에 넣어준다.
                    map[i][j] = chars[j]=='O'?true:false;
                }
                // System.out.print(map[i][j]?1:0);
            }
            // System.out.println();
        }
        
        //2. routes에서 이동 처리
        for(String r : routes){
            System.out.printf("현위치 : [%d, %d]",curLoc[0], curLoc[1]);
            System.out.println("  > 명령 : "+r);
            
            //문자열에서 띄어쓰기로 구분하여 방향과 칸수를 저장
            String[] route = r.split(" ");
            
            int[] nextLoc = move(curLoc, route);
            System.out.printf("이동할 위치 : [%d, %d]\n",nextLoc[0], nextLoc[1]);
            
            //2-1. 최종적으로 도착할 칸수가 map을 벗어나는지 검사
            if(isOut(nextLoc)) continue;
            //2-2. 이동할 칸이 이동 불가능칸이 있는지 검사
            if(isBlock(curLoc, nextLoc)) continue;
            
            //2-1 && 2-2 둘다 만족하면 현재 위치를 갱신시켜준다.
            System.out.printf("이동했읍니다. > [%d, %d]\n",nextLoc[0], nextLoc[1]);
            curLoc[0] = nextLoc[0];
            curLoc[1] = nextLoc[1];
        }
        
        return curLoc;
    }
    
    public boolean isOut(int[] nextLoc){
        if(0<=nextLoc[0] && nextLoc[0]<H && 0<=nextLoc[1] && nextLoc[1]<W){
            System.out.println("범위 안에 있습니다.");
            return false;
        }else {
            System.out.println("범위를 벗어났습니다.");
            return true;
        }
    }
    
    public boolean isBlock(int[] curLoc, int[] nextLoc){
        int startR = Math.min(curLoc[0], nextLoc[0]);
        int endR = Math.max(curLoc[0], nextLoc[0]);
        int startC = Math.min(curLoc[1], nextLoc[1]);
        int endC = Math.max(curLoc[1], nextLoc[1]);
        
        for(int i=startR; i<=endR; i++){
            for(int j=startC; j<=endC; j++){
                if(!map[i][j]) {
                    System.out.printf("[%d, %d]칸은 막혀있습니다.\n",i,j);
                    return true;
                } 
            }
        }
        
        return false;
    }
    
    public int[] move(int[] curLoc, String[] route){
        int r = curLoc[0];
        int c = curLoc[1];
        
        switch(route[0]){
            case "N":
                r-=Integer.parseInt(route[1]);
                break;
            case "S":
                r+=Integer.parseInt(route[1]);
                break;
            case "E":
                c+=Integer.parseInt(route[1]);
                break;
            case "W":
                c-=Integer.parseInt(route[1]);
                break;
        }
        
        int[] nextLoc = new int[2];
        nextLoc[0] = r;
        nextLoc[1] = c;
        
        return nextLoc;
    }
}