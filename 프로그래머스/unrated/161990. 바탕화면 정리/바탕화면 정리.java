//13:08 시작.
//아마 5분?

import java.util.*;

class Solution {
    public int[] solution(String[] wallpaper) {
        
       //바탕화면은 각칸이 정사각형인 격자판
        //바탕화면 상태를 나타낸 문자열 배열 wallpaper
        //파일들은 바탕화면 격자칸에 위치
        //좌상단은 0,0
        //빈칸은 .
        //파일 있으면 #
        //드래그 하면 파일 선택
        //선택된 파일들은 삭제할 수 있음
        //최소한의 이동거리를 갖는 한번의 드래그로 모든 파일을 선택해서 한번에 지우려고 함
        
        //드래그 시작 S(lux, luy)
        //드래그 끝   E(rdx, rdy)
        
        //드래그 거리 = |rdx - lux| + |rdy - luy|
        
        //#이 나올떄 r,c의 최소, 최대값을 계속 갱신해나가면 될듯
        
        
        int minR=Integer.MAX_VALUE;
        int minC=Integer.MAX_VALUE;
        int maxR=Integer.MIN_VALUE;
        int maxC=Integer.MIN_VALUE;
        
        //1. wallpaper에서 #찾기
        for(int i=0, endi=wallpaper.length; i<endi; i++){
            //문자열을 짤라준다.
            char[] files = wallpaper[i].toCharArray();
            for(int j=0, endj=files.length; j<endj; j++){
                //짤라준 문자열에서 #여부를 검사한다.
                if(files[j]=='#'){ //#이라면
                    //r,c의 최대최소값 갱신
                    minR = Math.min(i, minR);
                    minC = Math.min(j, minC);
                    maxR = Math.max(i, maxR);
                    maxC = Math.max(j, maxC);
                }
            }
        }
        //r,c의 최소값은 그대로 출력,
        //r,c의 최대값은 +1하여 출력
        int[] answer = {minR, minC, maxR+1, maxC+1};
        return answer;
    }
}