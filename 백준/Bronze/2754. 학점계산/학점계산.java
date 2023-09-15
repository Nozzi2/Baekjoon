import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        if(input.length==1) {
            System.out.println("0.0");
            return;
        }


        int[][] ills = new int[5][3];
        int[][] sosus = new int[5][3];

        int ill = 4;
        for(int i=0; i<4; i++){
            for(int j=0; j<3; j++){
                ills[i][j] = ill;
                if(j==1) ill--;
            }
            sosus[i][0] = 3;
            sosus[i][1] = 0;
            sosus[i][2] = 7;
        }

        ill = input[0]-'A';
        int sosu = 1;
        switch(input[1]){
            case '+': sosu = 0;
            break;
            case '-' : sosu = 2;
            break;
        }

        System.out.print(ills[ill][sosu]);
        System.out.print('.');
        System.out.print(sosus[ill][sosu]);
    }
}