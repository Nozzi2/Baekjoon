class Solution {
    
    /*
    1. 두 큐를 모두 하나의 배열로 만들어준다.
    2. 하나의 배열로 만들어준 후 첫번째 큐의 시작인덱스, 끝 인덱스를 저장한다.
    3. 두 큐의 합을 구한 후 절반을 저장해놓는다.
    4. 시작인덱스, 끝인덱스의 합계를 구한다.
    5. 만약 절반보다 작다면
        5-1. 끝인덱스를 +1한다.
    else
        5-2. 첫 인덱스를 +1한다.
    6. 절반과 같다면 끝내고
        같지 않다면 5번을 반복한다.
        만약 시작인덱스, 끝인덱스가 배열의 맨 끝에 도착한다면 종료하고 -1출력
    */
    
    int[] que1;
    int[] que2;
    
    public int solution(int[] queue1, int[] queue2) {
        que1 = queue1;
        que2 = queue2;
        
        //첫 인덱스는 0, 끝 인덱스는 que1의 길이.
        int idxStart=0;
        int idxEnd=queue1.length-1;
        int idxFinish = queue1.length+queue2.length-1;
        
        //듀큐의 합 계산
        long sumCurrent = 0; //1번큐의 합계
        long sumTarget =0; //총합계/2
        for(int i=0, endi=queue1.length; i<endi; i++){
            sumTarget+=queue1[i];
        }
        sumCurrent = sumTarget;
        for(int i=0, endi=queue2.length; i<endi; i++){
            sumTarget+=queue2[i];
        }
        sumTarget /=2;
        // System.out.println("타겟 값 : "+sumTarget);
        
        
        int answer=0;
        while( idxStart < idxFinish || idxEnd < idxFinish)  {
            if(idxStart>idxEnd) break;
            // System.out.print("현재 값 : "+sumCurrent+" / answer : "+answer);
            // System.out.println(" / 인덱스 : "+idxStart+" ~ "+idxEnd);
            
            if(sumCurrent < sumTarget){
                if(++idxEnd>idxFinish) break;
                sumCurrent+=getValue(idxEnd);
            } else if( sumCurrent > sumTarget){
                sumCurrent-=getValue(idxStart);
                if(++idxStart>idxFinish) break;
            } else {
                return answer;
            }
            answer++;
        }
        return -1;
        
        
        //System.out.println(idxStart);
        //System.out.println(idxEnd);
        //System.out.println(sumTarget);
        
    }
    
    int getValue(int idx){
        if(idx>=que1.length){
            idx-=que1.length;
            //idx--;
            // System.out.println("조회할 인덱스 : 배열2-"+idx);
            return que2[idx];
            // return 0;
        } else {
            //idx--;
            // System.out.println("조회할 인덱스 : 배열1-"+idx);
            return que1[idx];
        }
    }
}