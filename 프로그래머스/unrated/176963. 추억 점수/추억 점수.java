import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photos) {
        
        //사진속 그리움점수 모두 합산 = 해당 사진 추억점수
        //그리워하는 사람이름 배열 name
        //사람별 그리움점수 yearning
        //사진에 찍힌 인물들의 이름을 담은 이차원 배열 photo
        //photo에 점수의 배열을 출력하시오.
        
        Map<String, Integer> map = new HashMap<>();
        
        //1. name과 yearning을 key, value로 map 생성
        for(int i=0, endi=name.length; i<endi; i++){
            map.put(name[i],yearning[i]);
        }
        
        //2. photo를 순회하면서 그리움 계산
        int[] answer = new int[photos.length];
        for(int i=0, endi=photos.length; i<endi; i++){
            String[] photo = photos[i];
            
            for(String n : photo){
                if(map.get(n) != null){
                    answer[i]+=map.get(n);
                }
            }
        }
        return answer;
    }
}