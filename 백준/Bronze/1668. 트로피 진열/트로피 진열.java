import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class Main {
 
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> arr = new ArrayList<Integer>();
        
        int n = Integer.parseInt(br.readLine());
        
        for(int i=0; i<n; i++) {
            arr.add(Integer.parseInt(br.readLine()));
        }
        
        int count1 = 1;
        int max1 = Integer.MIN_VALUE;
        int count2 = 1;
        int max2 = Integer.MIN_VALUE;
        
        //1번: 왼쪽에서 볼 때
        //각 과정마다 최댓값을 갱신해주고, 그 다음의 트로피가 최댓값보다 작거나 같다면
        //continue를 통해 넘어간다.
        for(int i=0; i<n-1; i++) {
            if(max1 < arr.get(i)) {
                max1 = arr.get(i);
            }
            
            if(max1 >= arr.get(i+1)) {
                continue;
            }
            count1++;
        }
        
        //2번 과정: 오른쪽에서 볼 때
        //방법은 동일하다.
        for(int i=n-1; i>0; i--) {
            if(max2 < arr.get(i)) {
                max2 = arr.get(i);
            }
            
            if(max2 >= arr.get(i-1)) {
                continue;
            }
            count2++;
        }
        
        System.out.println(count1 + " " + count2);
    }
}