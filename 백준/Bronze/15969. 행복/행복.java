import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[] arr = new int[size];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<size; i++){
            int input = Integer.parseInt(st.nextToken());
            arr[i] = input;
        }

        Arrays.sort(arr);

        System.out.println(arr[size-1]-arr[0]);
    }
}

/*
8
85 42 79 95 37 11 72 32

*/