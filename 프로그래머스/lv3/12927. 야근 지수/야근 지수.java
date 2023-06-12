import java.util.*;
import java.io.*;

class Solution {
    
    /*
    works를 정렬하고
    가장 큰 값부터 줄여줘야함
    1. 1번째 인덱스에서 1빼주고 뺸 후 값을 저장한다.
    2. 만약 2번째 인덱스가 뺀 후 값과 동일하다면
            1.로 돌아간다.
        동일하지 않다면?
            2번째 인덱스도 빼주고 3번째 인덱스로 넘어가는 2번을 반복한다.
    3. 2.를 n이 0이 될때까지 반복해준다.
    4. 모든 배열을 돌면서 제곱해주고 합계에 더해준다.
    
    원소의 값이 50_000이므로 제곱하면 2_500_000_000이라서 int 범위를 초과하므로
    결과값을 출력할 땐 꼭 long을 사용해야함
    */
    
    public long solution(int n, int[] works) {
        //Arrays.sort(works, (o1,o2)->o2-o1); 에러 발생
        //(o1,o2)->o2-o1는 Integer에서만 사용할 수 있고,
        //int형에 대해서는 사용할 수 없으므로 박싱해줘야함
        
        Integer[] boxedWorks = Arrays.stream(works)
                                     .boxed()
                                     .toArray(Integer[]::new);
        Arrays.sort(boxedWorks, (o1,o2)-> o2-o1);
        // System.out.println(Arrays.toString(boxedWorks));
        
        int index=0;            //빼줄 인덱스를 저장할 변수
        int max = boxedWorks[0];
        int size = works.length;
        
        while(n!=0){
            
            // System.out.println(Arrays.toString(boxedWorks) + "  최대값 : "+max+", index : "+index);
            n--;
            if(--boxedWorks[index]<0){ //작업이 마이너스가 된다면 더이상 처리할 작업이 없다는 것이므로 끝내야함
                boxedWorks[index]=0;
                break;
            }
            if(index+1==size) {        //맨끝까지 간다면 다시 처음부터
                index=0;
                max=boxedWorks[0]; //더빼줘야함
                continue;
            }
            if(max-1 == boxedWorks[index+1]){ //다음 값과 뺀값이 같을경우
                max=boxedWorks[0];
                index=0;
            } else if(max-1 > boxedWorks[index+1]){ //다음 값보다 더 클경우
                index=0;
                max=boxedWorks[0]; //더빼줘야함
            } else {    //다음값보다 작을경우
                // System.out.println("3번 경우");
                index++;
            }
        }
        // System.out.println(Arrays.toString(boxedWorks));
        
        long answer = 0;
        for(int a : boxedWorks){
            answer+=a*a;
        }
        return answer;
    }
}

/*

10 7 5  7
7  7 5  4
5  5 5  0

*/