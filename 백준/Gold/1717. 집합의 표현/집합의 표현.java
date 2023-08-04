import java.util.*;
import java.io.*;



public class Main
{
    static int[] arr;

    public static void main(String args[]) throws Exception
    {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int TCASE = Integer.parseInt(st.nextToken());

        arr = new int[size+1];
        
        for(int i=1; i<=size; i++){
            arr[i] = i;
        }

        for(int T=0; T<TCASE; T++){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            if(command==0){
                //union
                union(num1, num2);
            } else {
                //isSameParent
                if(isSameParent(num1, num2)){
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }

        System.out.print(sb.toString());
    }

    static void union(int a, int b){
        int parentA = find(a);
        int parentB = find(b);

        if(parentA < parentB){
            arr[parentB] = parentA;
        } else {
            arr[parentA] = parentB;
        }
    }

    static int find(int a){
        if(a == arr[a]){
            return a;
        } else {
            return arr[a] = find(arr[a]);
        }
    }

    static boolean isSameParent(int a, int b){

        int parentA = find(a);
        int parentB = find(b);

        if(parentA == parentB) {
            return true;
        } else {
            return false;
        }
    }
}