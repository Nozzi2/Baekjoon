import java.util.*;
import java.io.*;

/*
가장 흔한 분리집합문제임
입력마다 union을 반복하고,
만약 두 입력받은 두 값의 parents가 같게 된다면
    둘의 부모가 같은 형태는 곧 사이클이기 때문에 해당 index를 출력
*/
public class Main
{
    static int[] parents;
    
    public static void main(String[] args) throws Exception {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int nodeSize = Integer.parseInt(st.nextToken());
        int roundSize = Integer.parseInt(st.nextToken());
        parents = new int[nodeSize];
 
        for (int i = 0; i < nodeSize; i++) {
            parents[i] = i;
        }

        int result = 0;
        for (int i = 1; i <= roundSize; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(!union(a, b)) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }
 
    private static int find(int n) {
        if(n == parents[n]) return n;

        return parents[n] = find(parents[n]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a); 
        int bRoot = find(b);

        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }
 
}