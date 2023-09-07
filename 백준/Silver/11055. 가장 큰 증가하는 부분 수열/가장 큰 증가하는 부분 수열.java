import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws Exception
    {  
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[] arr = new int[size];
        int[] dp = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<size; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = arr[i];
        }
        // System.out.println(Arrays.toString(arr)+"\n");

        int max = 0;
        for(int i=0; i<size; i++){
            // System.out.println(Arrays.toString(dp));
            for(int j=0; j<=i; j++){
                if(arr[i]>arr[j]){
                    dp[i] = Math.max(dp[i], dp[j]+arr[i]);
                }
            }

            max = Math.max(max, dp[i]);
        }


        System.out.println(max);
    }
}
/*
10
1 100 2 50 60 3 5 6 7 8




*/