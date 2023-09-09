import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int bit=0;
        for(int i=0; i<28; i++){
            int input = Integer.parseInt(br.readLine());
            bit |= 1<<input;
        }

        for(int i=1; i<=30; i++){
            if((bit & (1<<i)) != 1<<i){
                System.out.println(i);
            }
        }
        
    }
}

/*
3
1
4
5
7
9
6
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
*/