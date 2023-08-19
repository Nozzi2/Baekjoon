import java.util.*;
import java.io.*;


public class Main
{

    static int[][] globalMatrix;
    static int size;
    static int maxValue;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        globalMatrix = new int[size][size];
        maxValue = 0;

        StringTokenizer st;
        for(int r=0; r<size; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<size; c++){
                globalMatrix[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // swipe(3, globalMatrix);
        // swipe(1, globalMatrix);

        game(0, globalMatrix);

        System.out.println(maxValue);
    }

    static int getMaxValue(int[][] matrix){
        int max = Integer.MIN_VALUE;
        for(int[] arr : matrix){
            for(int n : arr){
                max = Math.max(max,n);
            }
        }

        return max;
    }

    static void game(int depth, int[][] matrix){
        if(depth == 5) {
            int thisMaxValue = getMaxValue(matrix);
            maxValue = Math.max(maxValue, thisMaxValue);
            // System.out.printf("%d, %d \n", thisMaxValue, maxValue);
            return;
        }

        for(int i=0; i<4; i++){
            // System.out.printf("depth : %d, command : %d \n", depth, i);
            int[][] recursiveMatrix = swipe(i,matrix);
            game(depth+1, recursiveMatrix);
        }

    }

    static int[][] swipe(int command, int[][] matrix){
        switch(command){
            case 0 : 
                return up(matrix);
            case 1 : 
                return down(matrix);
            case 2 :
                return left(matrix);
            case 3 :
                return right(matrix);
        }
        return null;
    }



    static int[][] up(int[][] matrix) {
        int[][] outputMatrix = new int[size][size];
        Deque[] deque = new ArrayDeque[size];
        for(int i=0; i<size; i++){
            deque[i]= new ArrayDeque<Integer>();
        }

        for(int c=0; c<size; c++){
            boolean isUnion = false;
            for(int r=0; r<size; r++){
                if(matrix[r][c] == 0){
                    continue;
                }

                if(deque[c].size()==0){
                    deque[c].addFirst(matrix[r][c]);
                    isUnion = false;
                    continue;
                }

                if(!isUnion && (int)deque[c].peekFirst() == matrix[r][c]){
                    int tmp = (int)deque[c].pollFirst();
                    tmp *= 2;
                    deque[c].addFirst(tmp);
                    isUnion = true;
                } else {
                    deque[c].addFirst(matrix[r][c]);
                    isUnion = false;
                }
            }

            for(int d=0, endd=deque[c].size(); d<endd; d++){
                outputMatrix[d][c] = (int)deque[c].pollLast();
            }
        }//for c end
        
        // printMatrix(outputMatrix);

        return outputMatrix;
    }

    static int[][] down(int[][] matrix) {
        int[][] outputMatrix = new int[size][size];
        Deque[] deque = new ArrayDeque[size];
        for(int i=0; i<size; i++){
            deque[i]= new ArrayDeque<Integer>();
        }

        for(int c=0; c<size; c++){
            boolean isUnion = false;
            for(int r=size-1; r>=0; r--){
                if(matrix[r][c] == 0){
                    continue;
                }

                if(deque[c].size()==0){
                    deque[c].addFirst(matrix[r][c]);
                    isUnion = false;
                    continue;
                }

                if(!isUnion && (int)deque[c].peekFirst() == matrix[r][c]){
                    int tmp = (int)deque[c].pollFirst();
                    tmp *= 2;
                    deque[c].addFirst(tmp);
                    isUnion = true;
                } else {
                    deque[c].addFirst(matrix[r][c]);
                    isUnion = false;
                }
            }

            for(int d=0, endd=deque[c].size(); d<endd; d++){
                int r = size-1-d;
                outputMatrix[r][c] = (int)deque[c].pollLast();
            }
        }//for c end

        // printMatrix(outputMatrix);

        return outputMatrix;
    }

    static int[][] left(int[][] matrix) {
        int[][] outputMatrix = new int[size][size];
        Deque[] deque = new ArrayDeque[size];
        for(int i=0; i<size; i++){
            deque[i]= new ArrayDeque<Integer>();
        }

        for(int r=0; r<size; r++){
            boolean isUnion = false;
            for(int c=0; c<size; c++){
                if(matrix[r][c] == 0){
                    continue;
                }

                if(deque[r].size()==0){
                    deque[r].addFirst(matrix[r][c]);
                    isUnion = false;
                    continue;
                }

                if(!isUnion && (int)deque[r].peekFirst() == matrix[r][c]){
                    int tmp = (int)deque[r].pollFirst();
                    tmp *= 2;
                    deque[r].addFirst(tmp);
                    isUnion = true;
                } else {
                    deque[r].addFirst(matrix[r][c]);
                    isUnion = false;
                }
            }

            for(int d=0, endd=deque[r].size(); d<endd; d++){
                outputMatrix[r][d] = (int)deque[r].pollLast();
            }
        }//for c end
        
        // printMatrix(outputMatrix);

        return outputMatrix;
    }

    static int[][] right(int[][] matrix) {
        int[][] outputMatrix = new int[size][size];
        Deque[] deque = new ArrayDeque[size];
        for(int i=0; i<size; i++){
            deque[i]= new ArrayDeque<Integer>();
        }

        for(int r=0; r<size; r++){
            boolean isUnion = false;
            for(int c=size-1; c>=0; c--){
                if(matrix[r][c] == 0){
                    continue;
                }

                if(deque[r].size()==0){
                    deque[r].addFirst(matrix[r][c]);
                    isUnion = false;
                    continue;
                }

                if(!isUnion && (int)deque[r].peekFirst() == matrix[r][c]){
                    int tmp = (int)deque[r].pollFirst();
                    tmp *= 2;
                    deque[r].addFirst(tmp);
                    isUnion = true;
                } else {
                    deque[r].addFirst(matrix[r][c]);
                    isUnion = false;
                }
            }

            for(int d=0, endd=deque[r].size(); d<endd; d++){
                int c = size-1-d;
                outputMatrix[r][c] = (int)deque[r].pollLast();
            }
        }//for c end
        
        // printMatrix(outputMatrix);

        return outputMatrix;
    }

    static void printMatrix(int[][] matrix){
        for(int[] arr : matrix) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
    }
}

/*
4
2 4 2 2
2 4 8 2
4 8 8 2
8 2 8 2

*/