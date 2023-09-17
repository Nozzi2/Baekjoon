import java.util.*;
import java.io.*;


/*
A＠B = (A+B)×(A-B)
*/
public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        System.out.println(method(a,b));
    }

    static int method(int a, int b){
        return (a+b)*(a-b);
    }
}
/*
4 3
*/