import java.io.*;
import java.util.*;

/*
progress 순서에 맞게 끝나는 일자가 저장되는 배열 days 선언
    progresses의 배열의 값에서 -100을 하고 0이 될 수 있게 speed 배열의 값을 더해준 값.
    ex) progress 93, speed 1 => day 7
days 배열 처음부터 끝까지 순회하면서 list에 저장
    선언할 변수 : 전번째 배열의 값, 배포할 수 있는 작업의 가짓수를 저장할 값 
    다음 번째 배열의 값과 전번쨰 값을 비교해서 전번째가 더 크면 배포가능 가짓수++
    아니라면 작업 가짓수값을 list에 추가 전번째 값은 다음 배열 값으로 초기화, 작업 가짓수 1로 초기화
순회가 끝나면 list를 배열로 변환
*/
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        int progCnt = progresses.length; //작업의 갯수
        int[] days = new int[progCnt]; //작업들의 소요일 배열
        
        //소요일 계산
        for(int i=0; i<progCnt; i++){
            days[i] = (100-progresses[i])/speeds[i]; 
            System.out.print("(100-"+progresses[i]+")/"+speeds[i]+"="+((100-progresses[i])/speeds[i]));
            if((100-progresses[i])%speeds[i]>=1){ //소요일이 n.xxx일이라면
                days[i]++; //n+1이 저장되도록
                System.out.print("+1");
            }
            System.out.print("="+days[i]+", ");
        }
        System.out.println();
        
        List<Integer> deployCnts = new ArrayList<>();
        int prevDay = days[0]; //다음번째 작업에 배포에 소요된 일수
        int deployCnt = 1; //한번에 배포 가능한 갯수
        
        for(int i=1; i<progCnt; i++){
            System.out.print(i+"번째 : "+days[i]+" / ");
            if(prevDay>=days[i]){
                deployCnt++;
            } else {
                System.out.print(deployCnt+", ");
                deployCnts.add(deployCnt);
                deployCnt = 1;
                prevDay = days[i];
            }
            System.out.println();
        }
        deployCnts.add(deployCnt); //마지막에 저장된 배포일까지 list에 추가
        
        int[] answer = new int[deployCnts.size()];
        System.out.println("\n길이는 "+answer.length);
        
        // list > array 변환
        for(int i=0, endi=answer.length; i<endi; i++){
            answer[i] = deployCnts.get(i);
            System.out.print(deployCnts.get(i)+", ");
        }
        
        return answer;
    }
}