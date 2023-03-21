/*
방법1.
    skill의 배열을 입력받아서 처리하는 메소드를만든다.
    [공/회, 시작r, 시작c, 끝r, 끝c, 내구도+-값]

    skill배열을 순회하면서 board 값을 갱신

    board에서 양수값이 몇개인지 확인
    => 효율성 똥망!

방법2.
    skill의 배열을 입력받아서 처리하는 메소드를만든다.
    [공/회, 시작r, 시작c, 끝r, 끝c, 내구도+-값]
    
    배열의 0,0부터 r,c까지
        skill의 첫번째~마지막 번째까지 순회하면서 값을 계산한다.
    
    계산이 완료되면 양수값인지 계산한다.
    => 효율성 똥망!

방법1,2 둘다 시간복잡도가 굉장히 높다.
    O(N*M*skill) = O(N^3)
이를 해결하기 위해선 누적합이라는 것을 사용해야함.


*/
class Solution {
    
    
    public int solution(int[][] board, int[][] skills) {
        int[][] sum = new int[board.length+1][board[0].length+1];
        
        // for(int i=1, endi=sum.length; i<endi; i++){
        //     for(int j=1, endj=sum[0].length; j<endj; j++){
        //         sum[i][j] = board[i-1][j-1];
        //     }
        // }
        
        //누적합 4칸만 계산
        for(int[] skill : skills){
            int y1 = skill[1];
            int x1 = skill[2];
            int y2 = skill[3];
            int x2 = skill[4];
            int degree = skill[5] * (skill[0]==1?-1:1);
            
            sum[y1][x1] += degree;
            sum[y1][x2 + 1] += (degree * -1);
            sum[y2 + 1][x1] += (degree * -1);
            sum[y2 + 1][x2 + 1] += degree;
        }
        
        //완성된 누적합 값을 가로 세로 합쳐서 누적합 행렬 완성시키기
        // 가로
        for (int i = 1; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sum[i][j] += sum[i - 1][j];
            }
        }
        // 세로
        for (int i = 1; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                sum[j][i] += sum[j][i - 1];
            }
        }
        
        
        
        int answer = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] + sum[i][j] > 0) answer++;
            }
        }
        return answer;
    }
}