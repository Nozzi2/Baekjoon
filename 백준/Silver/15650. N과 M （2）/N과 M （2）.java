import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int c;
    static StringBuilder sb;
    static int[] numbers;
    static boolean[] selected;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        selected = new boolean[n];

        perm(1,0);

        System.out.print(sb);
    }

    private static void perm(int start, int cnt) {
        //System.out.println(start+","+cnt);
        if(cnt==c){
            for(int i=0; i<n; i++){
                if(selected[i]){
                    sb.append(i+1).append(" ");
                }
            }
            sb.append("\n");
            return;
        }


        for(int i=start; i<=n; i++){
            if(!selected[i-1]){
                selected[i-1] = true;
                perm(i+1, cnt+1);
                selected[i-1] = false;
            }
        }
    }
}