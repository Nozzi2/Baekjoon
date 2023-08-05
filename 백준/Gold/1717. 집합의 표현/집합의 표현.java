import java.util.*;
import java.io.*;


public class Main
{
    static int[] arr;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeSize = Integer.parseInt(st.nextToken());
        int reqSize = Integer.parseInt(st.nextToken());
        arr = new int[nodeSize+1];
        for(int i=1; i<=nodeSize; i++){
            arr[i] = i;
        }

        for(int i=0; i<reqSize; i++){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(command == 0){
                union(a,b);
            } else {
                sb.append(isSameParent(a,b)?"YES\n":"NO\n");
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

        return parentA == parentB;
    }

}