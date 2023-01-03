import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        //방법1
        //1~n까지 소수 구하여 map에 저장
        //단, 소수에 0이 있는지 검사하여 없는 것만 저장하기
        //=> 메모리 낭비가 너무 많을 것 같음..
        
        //방법2
        //정수 n을 먼저 k진수화 시키는데, StringBuilder를 이용해서 0마다 짤라가면서 숫자를 저장한다.
        //숫자가 뒤에서부터 나오기 때문에 앞으로 숫자를계속 추가시켜주거나, sb.insert(0,"숫자")
        //숫자들을 조합한 후 뒤집어줘야한다. sb.reverse()
        
        List<Long> list = new ArrayList<>(); //0으로 짤라가면서 소수인지 판별할 숫자들
                                                //숫자가 클 수도 있으므로 long으로 바꿔야함
        
        StringBuilder sb = new StringBuilder();
        // StringBuilder fullNum = new StringBuilder();
        long num=0;
        while(n!=0){
            num = n%k;
            if(num==0){
                if(sb.toString().length() > 0) { //문자열에 숫자가 있을 경우
                    //여기서 최대값 저장해도 됨
                    list.add(Long.parseLong(sb.toString())); //리스트에 숫자 추가
                    sb.setLength(0); //문자열 삭제
                }
            } else {
                sb.insert(0,Long.toString(num));
                if(n/k==0){ //진수 변환이 끝났다면
                    list.add(Long.parseLong(sb.toString())); //마지막 숫자 추가
                }
            }
            // fullNum.insert(0,Integer.toString(num));
            n/=k;
        }
        // System.out.println("마지막 숫자 : "+num);
        
        // for(int out : list){
        //     System.out.println(out);
        // }
        // System.out.println(fullNum);
        
        
        //방법2-1
        //저장한 숫자중 가장 큰 값 만큼 boolean배열을 선언하여 에라토스테네스의 체를 적용한다.
        //(메모리가 낭비될 수 있음..)
        
        //방법2-2
        //저장한 숫자 하나씩 2부터 시작해서 해당 숫자까지 나눠보면서 소수를 판별한다.
        //소수가 맞다면 map에 저장하여 다음 중복되는 연산을 하지 않도록 한다.
        Map<Long, Long> primeNumbers = new HashMap<>();
        for(long out : list){
            if(out==1) continue;
            if(primeNumbers.containsKey(out)) {
                answer++;
                continue;
            }
            boolean isPrime = true;
            for(long i=2, endi=(long) Math.sqrt(out); i<=endi; i++){
                if(out%i==0){
                    isPrime = false;
                    break;
                }
            }
            if(isPrime) {
                answer++;
                primeNumbers.put(out,(long)0);
            } 
        }
        
        
        return answer;
    }
}