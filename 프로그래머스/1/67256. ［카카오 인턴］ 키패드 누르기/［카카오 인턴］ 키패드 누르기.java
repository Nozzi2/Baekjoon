import java.io.*;
import java.util.*;

class Pos {
    int r;
    int c;
    
    public Pos (int r, int c){
        this.r = r;
        this.c = c;
    }
    
    int getDistance(Pos pos){
        int distR = Math.abs(this.r-pos.r);
        int distC = Math.abs(this.c-pos.c);
        return distR+distC;      
    }
}

class Solution {
    public String solution(int[] numbers, String hand) {
        boolean isRight = hand.equals("right");
        
        Pos left = new Pos(3,0);
        Pos right = new Pos(3,2);
        
        StringBuilder sb = new StringBuilder();
        for(int num : numbers){
            Pos nextPos = getPos(num);
            // System.out.printf("왼왼손 : (%d,%d)\n",left.r,left.c);
            // System.out.printf("오른손 : (%d,%d)\n",right.r,right.c);
            
            if(num == 1 || num==4 || num==7){
                left = nextPos;
                sb.append('L');
                continue;
            } else if(num==3 || num==6 || num==9){
                right = nextPos;
                sb.append('R');
                continue;
            }
            
            
            int distLeft = left.getDistance(nextPos);
            int distRight = right.getDistance(nextPos);
            
            if(distLeft==distRight){
                if(isRight){
                    right = nextPos;
                    sb.append('R');
                } else {
                    left = nextPos;
                    sb.append('L');
                }
            } else if(distLeft > distRight){
                right = nextPos;
                sb.append('R');
            } else {
                left = nextPos;
                sb.append('L');
            }
            // System.out.println("=============================");
        }
        
        return sb.toString();
    }
    
    
    
    Pos getPos(int num){
        switch(num){
            case 1: 
                return new Pos(0,0);
            case 2: 
                return new Pos(0,1);
            case 3: 
                return new Pos(0,2);
            case 4: 
                return new Pos(1,0);
            case 5: 
                return new Pos(1,1);
            case 6: 
                return new Pos(1,2);
            case 7: 
                return new Pos(2,0);
            case 8: 
                return new Pos(2,1);
            case 9: 
                return new Pos(2,2);
            case 0: 
                return new Pos(3,1);
            
        }
        
        return null;
    }
}