import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        int size = input.length;

        int leftSide = 0;
        int rightSide = 0;

        for(int i=0; i<size/2; i++){
            int num = (int)(input[i]-'0');
            leftSide += num;
        }

        for(int i=size/2; i<size; i++){
            int num = (int)(input[i]-'0');
            rightSide += num;
        }

        System.out.println(leftSide == rightSide?"LUCKY":"READY");
    }
}
/*
123402
*/