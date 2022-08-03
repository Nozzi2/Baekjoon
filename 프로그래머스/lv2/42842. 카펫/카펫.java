class Solution {
    public int[] solution(int brown, int yellow) {
        int sum=brown+yellow;
        int r=0;
        int c=0;
        for(int i=3; i<(sum/3)+1; i++){
            if(sum == (sum/i)*i){
                if( (i-2)*((sum/i)-2) == yellow ){
                    r = i;
                    c = sum/i;
                }
            }
        }
        int[] answer = {r, c};
        return answer;
    }
}