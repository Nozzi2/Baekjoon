import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int target = Integer.parseInt(br.readLine());

        int count=0;
        for(int i=0; i<size; i++){
            int nextInput = Integer.parseInt(st.nextToken());
            if(nextInput == target){
                count++;
            }
        }

        System.out.println(count);
    }
}