import java.io.*;
import java.util.*;

class Solution {
    int[] dr = {-1, 0, 1, 0};
    int[] dc = {0, -1, 0, 1};
    int sizeR, sizeC;
    
    class Pos {
        int r;
        int c;
        
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
        
        boolean isOut(){
            return r<1 || c<1 || r>sizeR || c>sizeC;
        }
        
        boolean isNextOut(int direction){
            int nr = r+dr[direction];
            int nc = c+dc[direction];
            return nr<1 || nc<1 || nr>sizeR || nc>sizeC;
        }
        
        boolean isVisited(boolean[][] matrixVisited){
            return matrixVisited[r][c];
        }
        
        boolean isWall(char[][] matrix){
            return matrix[r][c] == 'D';
        }
        
        boolean isNextWall(char[][] matrix, int direction){
            int nr = r+dr[direction];
            int nc = c+dc[direction];
            
            return matrix[nr][nc] == 'D';
        }
        
        boolean isNextGround(char[][] matrix, int direction){
            int nr = r+dr[direction];
            int nc = c+dc[direction];
            
            return matrix[nr][nc] == '.' || matrix[nr][nc] == 'R' || matrix[nr][nc] == 'G';
        }
        
        boolean isArrived(char[][] matrix){
            return matrix[r][c] == 'G';
        }
        
        void setVisited(boolean[][] matrixVisited){
            matrixVisited[r][c] = true;
        }
        
        

    }

    
    public int solution(String[] board) {
        sizeR = board.length;
        sizeC = board[0].length();
        char[][] matrix = new char[sizeR+2][sizeC+2];
        Pos start = null;
        for(int i=1; i<=sizeR; i++){
            char[] chars = board[i-1].toCharArray();
            for(int j=1; j<=sizeC; j++){
                if(chars[j-1] == 'R'){
                    start = new Pos(i,j);
                }
                matrix[i][j] = chars[j-1];
            }
        }
        
        // printMatrix(matrix);
        
        // int answer = bfs(start, matrix);
        // return answer;
        return bfs(start, matrix);
    }
    
    int bfs(Pos start, char[][] matrix){
        boolean[][] matrixVisited = new boolean[sizeR+2][sizeC+2];
        Queue<Pos> que = new ArrayDeque<>();
        que.offer(start);
        start.setVisited(matrixVisited);
        int dist = 0;
        
        while(!que.isEmpty()){
            dist++;
            // System.out.println("현재 거리 : "+dist);
            for(int q=0, endq=que.size(); q<endq; q++){
                Pos cur = que.poll();
                // System.out.printf("cur : (%d, %d)\n", cur.r,cur.c);
                for(int d=0; d<4; d++){
                    Pos next = new Pos(cur.r+dr[d], cur.c+dc[d]);
                    if(next.isOut()) continue;
                    if(next.isWall(matrix)) continue;
                    
                    while(!next.isNextOut(d) && next.isNextGround(matrix,d) ){
                        next = new Pos(next.r+dr[d], next.c+dc[d]);
                    }
                    if(next.isOut()) continue;
                    if(next.isWall(matrix)) continue;
                    if(next.isVisited(matrixVisited)) continue;
                    if(next.isArrived(matrix)){
                        // printMatrix(matrixVisited);
                        return dist;
                    }
                    // System.out.printf("   next : (%d, %d)\n", next.r,next.c);
                    next.setVisited(matrixVisited);
                    que.offer(next);
                }
            }
        }
        
        // printMatrix(matrixVisited);
        return -1; /////////////////////////////
    }
    
    void printMatrix(char[][] matrix){
        for(int i=1; i<=sizeR; i++){
            for(int j=1; j<=sizeC; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    void printMatrix(boolean[][] matrix){
        for(int i=1; i<=sizeR; i++){
            for(int j=1; j<=sizeC; j++){
                System.out.print(matrix[i][j]?"T":"F");
            }
            System.out.println();
        }
        System.out.println();
    }
}

/*

...D..R
.D.G...
....D.D
D....D.
..D....

*/