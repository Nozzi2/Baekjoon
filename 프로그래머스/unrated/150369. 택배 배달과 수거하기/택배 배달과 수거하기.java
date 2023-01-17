import java.io.*;
import java.util.*;

class Solution {
    
    static int lastDeliPoint, lastPickPoint;
    static int CAP;
    
    //첫 배달지 계산
    static int findStart(int[] arr){
        for(int i=arr.length-1, endi=0; i>=endi; i--){
            if(arr[i]>0) return i;
        }
        return -1;
    }
    
    //배달 또는 수거 수행
    static int doDelivery(int[] arr, int lastPoint){
        if(lastPoint<0){ //배달을 완료한 경우
            return -1;
        }
        int curCap = CAP;
        
        //배달할 갯수가 남았고, 마지막 배달지가 0보다 같거나 크다면 반복
        while(curCap>0 && lastPoint>=0){
            if(arr[lastPoint]>0){
                if(curCap<arr[lastPoint]) { //현재 있는 택배 갯수보다 배달할 택배 갯수가 크다면 
                    arr[lastPoint]-=curCap;
                    curCap=0;
                } else if(curCap>arr[lastPoint]){ //현재 택배 갯수가 배달할 택배보다 크다면
                    curCap-=arr[lastPoint];
                    arr[lastPoint]=0;
                    lastPoint--;
                } else { //택배갯수와 배달할 택배가 같다면
                    curCap=0;
                    arr[lastPoint]=0;
                    lastPoint = findStart(arr);
                }
            } else {
                lastPoint--;
            }
        }
        
        return lastPoint;
    }
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        /*
        마지막 배달지, 수거지를 저장한다.
        1. 배달
        마지막 배달지의 저장된 값에서 cap만큼 빼준 후 값이 0이 되면 이전 배달지로 간다
        (cap이 0이 될때까지 반복)
        
        2. 수거
        마지막 수거지의 저장된 값에서 cap만큼 빼준 후 값이 0이 되면 이전 배달지로 간다.
        (cap이 0이 될때까지 반복)
        
        1.배달, 2.수거를 반복해가며 계산하는데, 마지막 배달지, 수거지중 큰 값*2을 결과값에 더해준다.
        마지막 배달지,수거지 모두다 0이 될때까지 반복한다.
        */
        
        lastDeliPoint = findStart(deliveries);
        lastPickPoint = findStart(pickups);
        CAP = cap;
        
        while(lastDeliPoint>=0 || lastPickPoint>=0) {
            answer+= (Math.max(lastDeliPoint,lastPickPoint)+1)*2;
            lastDeliPoint = doDelivery(deliveries,lastDeliPoint);
            lastPickPoint = doDelivery(pickups,lastPickPoint);
        }
        
        
        return answer;
    }
}