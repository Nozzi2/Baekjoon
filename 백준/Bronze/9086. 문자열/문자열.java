import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<size; i++){
            char[] input = br.readLine().toCharArray();
            int startIndex = 0;
            int endIndex = input.length-1;
            sb.append(input[startIndex]).append(input[endIndex]);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}