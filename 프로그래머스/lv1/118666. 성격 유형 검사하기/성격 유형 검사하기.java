import java.io.*;
import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<String, Integer> map = new HashMap<>();
        map.put("R",0);
        map.put("T",0);
        map.put("C",0);
        map.put("F",0);
        map.put("J",0);
        map.put("M",0);
        map.put("A",0);
        map.put("N",0);
        
        for(int T=0, TC=survey.length; T<TC; T++){
            String key;
            int choice = choices[T]-4;
            if(choice==0) continue;
            if(choice < 0){
                key = survey[T].substring(0,1);
            } else {
                key = survey[T].substring(1,2);
            }
            map.put(key, map.get(key)+Math.abs(choice));
        }
        
        // System.out.println("R"+map.get("R")+" / "+"T"+map.get("T"));
        // System.out.println("C"+map.get("C")+" / "+"F"+map.get("F"));
        // System.out.println("J"+map.get("J")+" / "+"M"+map.get("M"));
        // System.out.println("A"+map.get("A")+" / "+"N"+map.get("N"));
        
        String answer = "";
        if(map.get("T")>map.get("R")){
            answer+="T";
        } else {
            answer+="R";
        }
        if(map.get("F")>map.get("C")){
            answer+="F";
        } else {
            answer+="C";
        }
        if(map.get("M")>map.get("J")){
            answer+="M";
        } else {
            answer+="J";
        }
        if(map.get("N")>map.get("A")){
            answer+="N";
        } else {
            answer+="A";
        }
        
        return answer;
    }
}