import java.io.*;
import java.util.*;

class Solution {
    public String solution(String input) {
        String answer = recursion(input);
        
        while(!isRight(answer)){ //올바른 문자열이 나올때까지 반복
            answer = recursion(answer);
        }
        
        return answer;
    }
    
    //아래 과정은 모두 재귀함수로 호출해야하므로 메소드로 선언
    //char배열로 변환
    //u가 될 index 구하기 (0부터 괄호 좌우 갯수 동일할 떄까지)
    //0~uindex까지 "올바른지" 검사
        //올바르면 결과문자열에 더하기
        //올바르지 않으면 4-1~4-5 수행

    //4-1. 빈문자열 tmpStr에 "(" 더하기
    //4-2. 재귀할 메소드 호출. 파라미터는 문자열v. return타입은 string
    //4-3. ( + 4-2 + ) 이렇게 빈문자열 tmpStr에 저장됨
    //4-4. u를 toCharArray해주고 1~length-1까지 반복해가며 괄호방향 바꿔주어서 tmpStr 뒤에 더해줌
    //4-5 문자열 반환
    
    public static String recursion(String input) {
        //입력 문자열 길이가 0이면 즉시 종료
        if(input.length()==0) return "";
        
        String answer = "";
        int tmpIdx = getBalanceIndex(input)+1; //어디 기준으로 자를지 저장
        String u = input.substring(0,tmpIdx); //자른 문자열 u
        String v = input.substring(tmpIdx,input.length()); //자른 문자열 v
        
        //문제에서 주어진 조건과 똑같이 구현
        if(isRight(u)) { //3.
            answer += u; //3-1.
            answer += recursion(v); //v도 반환해야하므로 재귀 호출
        } else { //4.
            String tmpStr = "("; //4-1.
            tmpStr += recursion(v); //4-2.
            tmpStr += ")"; //4-3.
            tmpStr += reverse(u); //4-4.
            answer += tmpStr; //4-5.
        }
        
        return answer;
    }
    
    //올바른지 검사하는 메소드
    public static boolean isRight(String input){
        char[] inputs = input.toCharArray();
        int balancePoint = 0;
        
        //모든 배열 다 돌아서 balancePoint가 음수가 되는 순간 종료
        //음수의 의미는 올바르지 않다는 의미
        for(char c : inputs){
            if(c=='(') balancePoint++;
            else if(c==')') balancePoint--;
            
            if(balancePoint<0) break;
        }
        
        if(balancePoint==0) return true;
        else return false;
    }
    
    //균형잡힌 최소한의 index를 반환하는 메소드
    public static int getBalanceIndex(String input){
        char[] inputs = input.toCharArray();
        int balancePoint = 0;
        
        for(int i=0, endi=inputs.length; i<endi; i++){
            if(inputs[i]=='(') balancePoint++;
            else if(inputs[i]==')') balancePoint--;
            
            if(balancePoint==0) return i;
        }
        
        return inputs.length;
    }
    
    //4-4. 앞뒤 문자 제거하고 나머지 문자열의 괄호방향 뒤집는 메소드
    public static String reverse(String input){
        char[] inputs = input.toCharArray();
        char[] outputs = new char[inputs.length-2];
        
        for(int i=1, endi=inputs.length-1; i<endi; i++){
            if(inputs[i]=='('){
                outputs[i-1]=')';
            } else {
                outputs[i-1]='(';
            }
        }
        
        return String.valueOf(outputs);
    }
    
}