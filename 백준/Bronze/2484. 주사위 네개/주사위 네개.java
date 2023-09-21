import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        int[] counts = new int[7];
        
        StringTokenizer st = null;
        int maxReward = 0;
        for(int t=0; t<testCase; t++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(counts,0);
            for(int i=0; i<4; i++){
                int num = Integer.parseInt(st.nextToken());
                counts[num]++;
            }

            int maxCount = 0;
            int maxIndex = 0;
            for(int i=1; i<=6; i++){
                if(maxCount <= counts[i]) {
                    maxCount = counts[i];
                    maxIndex = i;
                }
            }
            
            int reward = 0;
            if(maxCount==4){
                reward = 50000;
                reward += 5000*maxIndex;
            } else if(maxCount==3){
                reward = 10000;
                reward += 1000*maxIndex;
            } else if(maxCount == 1){
                reward = 100*maxIndex;
            } else {
                int pairCount = 0;
                int[] pair = new int[2];
                for(int i=1; i<=6; i++){
                    if(counts[i] != maxCount) continue;
                    pair[pairCount] = i;
                    pairCount++;
                }

                if(pairCount==1){
                    reward = 1000;
                    reward += 100*pair[0];
                } else if(pairCount==2){
                    reward = 2000;
                    reward += 500*pair[0];
                    reward += 500*pair[1];
                }
            }

            maxReward = Math.max(maxReward, reward);
            // System.out.printf("maxCount : %d, reward : %d\n",maxCount, reward);
        }

        System.out.println(maxReward);
    }
}