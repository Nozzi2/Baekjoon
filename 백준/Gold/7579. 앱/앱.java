import java.util.*;
import java.io.*;

/*
누가봐도 DP문제이다.
어제 동전 문제를 풀어봤어서
어제 푼거랑 약간 비슷하다는 느낌이 든다.

전형적인 배낭 문제인 것 같은데, 이러한 유형의 문제를 더 풀어봐야할 것 같다..
*/

public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int appSize = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int[] bytes = new int[appSize+1];
        int[] costs = new int[appSize+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=appSize; i++){
            bytes[i] = Integer.parseInt(st.nextToken());
        }

        int costSum=0; //모든 비용을 합친 값
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=appSize; i++){
            costs[i] = Integer.parseInt(st.nextToken());
            costSum += costs[i];
        }

        int[][] dp = new int[appSize+1][costSum+1]; //[어플별로][비용을 사용하여] 얻을 수 있는 최대의 메모리를 저장

        //첫번째 앱부터 마지막-1번째 앱까지 사용하면서 갱신
        for(int a=1; a<appSize; a++){ //모든 어플을 1개(0), 2개(0,1), 3개(0,1,2)씩 늘려가면서
            for(int c=0; c<=costSum; c++){ //모든 비용에서 얻을 수 있는 최대의 메모리값 갱신
                if(c-costs[a]>=0){
                    dp[a][c] = Math.max(dp[a][c], dp[a-1][c-costs[a]]+bytes[a]); //이번에 순회하는 앱을 사용했을 때 메모리와 비교하여 갱신
                }
                dp[a][c] = Math.max(dp[a][c], dp[a-1][c]); //이전에 사용한 값이 더 크면 이전값으로 갱신
            }
        }

        //마지막앱까지 사용하며 갱신해주며 목표 바이트만큼 사용할 수 있는지 검사 후 출력
        int a = appSize; //가장 마지막 앱
        for(int c=0; c<=costSum; c++) { //1번앱부터 마지막 앱까지 전부 사용했을 경우의 비용별 메모리 갱신
            if(c-costs[a]>=0){
                dp[a][c] = Math.max(dp[a][c], dp[a-1][c-costs[a]]+bytes[a]); //이번에 순회하는 앱을 사용했을 때 메모리와 비교하여 갱신
            }
            dp[a][c] = Math.max(dp[a][c], dp[a-1][c]); //이전에 사용한 값이 더 크면 이전값으로 갱신

            if(dp[a][c]>=target){ //모든앱을 갱신했을 때 목표 바이트만큼 사용할 수 있다면
                System.out.println(c);
                break;
            }
        }

        // for(int[] arr : dp){
        //     System.out.println(Arrays.toString(arr));
        // }
    }
}