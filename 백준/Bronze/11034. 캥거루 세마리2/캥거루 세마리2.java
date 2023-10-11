import java.util.*;
import java.io.*;

public class Main {
    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String S;
        
        while((S = br.readLine()) != null) {
            st = new StringTokenizer(S);
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int max = Math.max((B-A), (C-B));
            System.out.println(max-1);
        }
    }
}