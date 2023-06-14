import java.util.*;
import java.math.*;
import java.io.*;


class Solution {
    
    String fullNumber;
    
    public String solution(String input, int k) {
        int size = input.length();
        fullNumber = input;
        Stack<Integer> stack = new Stack<>();
        stack.push(getNumber(0));   //처음엔 스택이 비어있으니 넣어놓기
        for (int i = 1; i < size; i++) {
            int curNumber = getNumber(i);

            //스택이 비어있지 않고, 지울 숫자가 남아있고, 스택의 상단에 있는 숫자가 현재 숫자보다 작다면
            while (!stack.empty() && k > 0 && stack.peek() < curNumber) {
                stack.pop();    //현재 스택 pop
                k--;
            }
            stack.push(curNumber);
        }

        //숫자 지울게 남아있다면 맨 아랫자리 부터 지워주기
        while(k>0 && !stack.isEmpty()){
            stack.pop();
            k--;
        }

        String maxNumStack = stack.toString(); //[7, 7, 5, 8, 4, 1]
        maxNumStack = maxNumStack.substring(1,maxNumStack.length()-1); //7, 7, 5, 8, 4, 1

        String maxNum = maxNumStack.replaceAll(", ", ""); //775841
        
        return maxNum;
    }
    
    int getNumber(int index) {
        return fullNumber.charAt(index)-'0';
    }
}