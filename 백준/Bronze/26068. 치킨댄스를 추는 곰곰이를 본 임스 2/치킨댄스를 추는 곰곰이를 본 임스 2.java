import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int limit = 90;
        int result = 0;
        for(int i=0; i<size; i++){
            String dDay = br.readLine();
            dDay = dDay.substring(2,dDay.length());
            // System.out.println(dDay);
            int day = Integer.parseInt(dDay);
            if(day<=90){
                result++;
            }
        }

        System.out.println(result);
    }
}