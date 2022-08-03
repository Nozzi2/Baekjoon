class Solution {
    public int[] solution(int[] answers) {
        int length = answers.length;
        int[] answerA = new int[length];
        int[] answerB = new int[length];
        int[] answerC = new int[length];
        
        //A답안
        for(int i=0; i<length; i++){
            answerA[i] = ( (i)%5 )+1;
        }
        
        //B답안
        int[] tmpB = {1,3,4,5};
        int idx = 0;
        for(int i=0; i<length; i++){
            if(i%2==0){
                answerB[i] = 2;
            } else {
                answerB[i] = tmpB[idx];
                idx = (idx+1)%4;
            }
        }
        
        //C답안
        int[] tmpC = {3,1,2,4,5};
        idx=0;
        for(int i=0; i<length; i++){
            answerC[i] = tmpC[idx];
            if( (i+1)%2==0 ){
                idx = (idx+1)%5;
            }
        }
        
        //채점
        int[] score=new int[3];
        for(int i=0; i<length; i++){
            if(answers[i] == answerA[i]) score[0]++; //A점수
            if(answers[i] == answerB[i]) score[1]++; //B점수
            if(answers[i] == answerC[i]) score[2]++; //C점수
        }
        
        int max=0; //최고 점수
        int maxP=0; //최고 점수 인원
        boolean[] maxIdx = new boolean[score.length]; //몇번 인원이 최고 점수인지 표시
        for(int i=0; i<score.length; i++){
            if(max<score[i]){ //최고점수가 새로 나온다면
                max = score[i];
                maxIdx = new boolean[score.length]; //이전 배열 초기화해야함
                maxP=1; //최고 점수 인원은 1명
                maxIdx[i] = true;
            } else if(max==score[i]) { //최고점수와 같으면
                maxP++; //최고 점수 인원 +1명
                maxIdx[i] = true;
            }
        }
        
        
        int[] answer = new int[maxP]; //최고점수인원만 출력할 배열
        idx = 0;
        for(int i=0; i<score.length; i++) {
            if(maxIdx[i]){ //최고점수 인원인지 검사
                answer[idx] = i+1;
                idx++;
            }
        }
        
        return answer;
    }
}