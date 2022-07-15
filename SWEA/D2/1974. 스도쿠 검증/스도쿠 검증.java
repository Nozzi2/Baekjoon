import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("src/chapter01/input.txt"));
        Scanner sc = new Scanner(System.in);
         
        int testCase = Integer.parseInt(sc.nextLine());
        for(int a=0; a<testCase; a++) {
        	int[][] arr = new int[9][9];
        	for(int i=0; i<9; i++) {
        		String[] s = sc.nextLine().split(" ");
                for(int j=0; j<9; j++) {
                    arr[i][j] = Integer.parseInt(s[j]);
                }//for j
        	}//for i
        	
        	//스도쿠 출력
//        	System.out.println("스도쿠 출력한다");
//        	for(int x=0; x<arr.length; x++) {
//				for (int y = 0; y < arr[x].length; y++) {
//					System.out.print(arr[x][y] + " ");
//				}
//				System.out.println();
//			}
//        	System.out.println("스도쿠 끝났다.");
        	
        	boolean[] checkSudoku = new boolean[9];
        	boolean checkResult = true; //검사결과
        	
        	//가로검사
        	for(int i=0; i<9; i++) {
        		if(!checkResult) {
        			break;
        		}
        		
        		for(int j=0; j<9; j++) {
        			if(!checkSudoku[ arr[i][j]-1 ]) {
        				checkSudoku[ arr[i][j]-1 ] = true;
        			} else {
        				checkResult = false;
        				break;
        			}
        		}
        		
        		checkSudoku = new boolean[9];
        	}
        	
        	//세로검사
        	for(int i=0; i<9; i++) {
        		if(!checkResult) {
        			break;
        		}
        		
        		for(int j=0; j<9; j++) {
        			if(!checkSudoku[ arr[j][i]-1 ]) {
        				checkSudoku[ arr[j][i]-1 ] = true;
        			} else {
        				checkResult = false;
        				break;
        			}
        		}
        		
        		checkSudoku = new boolean[9];
        	}
        	//9칸 검사
        	for(int i=0; i<7; i=i+3) {
            	if(!checkResult) {
        			break;
        		}
        		for(int j=0; j<7; j=j+3) {
                	if(!checkResult) {
            			break;
            		}
        			checkSudoku = new boolean[9];
        			for(int x=i; x<=i+2; x++) {
        	        	if(!checkResult) {
        	    			break;
        	    		}
        				for(int y=j; y<=j+2; y++) {
                			//System.out.print(x+""+y+" ");
                			if(!checkSudoku[ arr[x][y]-1 ]) {
                				checkSudoku[ arr[x][y]-1 ] = true;
                			} else {
                				checkResult = false;
                				break;
                			}
        				}
        				//System.out.println();
        			}
        		}
        	}
        	
        	
        	
//        	for(int i=0; i<checkSudoku.length; i++) {
//        		System.out.println(checkSudoku[i]);
//        	}
        	
        	if(checkResult) {
        		System.out.printf("#%d %d%n", (a+1), 1);
        	} else {
        		System.out.printf("#%d %d%n", (a+1), 0);
        	}
        }//for a
        
	}//main

}//class