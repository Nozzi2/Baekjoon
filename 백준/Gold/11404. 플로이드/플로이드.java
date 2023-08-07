import java.util.*;
import java.io.*;

/*
플루이드 워셜 알고리즘을 몰라서 인터넷에서 보고 풀긴했는데,
사실 2차원 배열 그림 그려가면서 풀어보면 DP와 같은 것을 알 수 있었다.

간선 하나를 거쳤을 때 최소비용, 두개 거쳤을 때 최소비용..
이렇게 노드 갯수 N개만큼 간선을 거쳤을 때 최소비용을 계속 갱신해주면 된다.

그래서 3중 반복문이 나오는 것임
(노드갯수 N번, 시작위치 N번, 끝위치 N번)
그래서 N^3이 나오게 됨!

N의 범위는 최대 100이기 때문에
N^3이여도 풀 수 있음. 1_000_000


*/

public class Main
{

    static int[][] matrix;
    static final int MAX_VALUE = 987654321;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int size = Integer.parseInt(br.readLine());
        matrix = new int[size][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                matrix[i][j] = i==j?0:MAX_VALUE;
            }
        }

        int edgeCount = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0; i<edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int cost = Integer.parseInt(st.nextToken());

            matrix[start][end] = Math.min(matrix[start][end], cost); //두 노드 사이에 여러 간선이 들어올 수 있으므로 최솟값 저장장
        }

        for(int n=0; n<size; n++){
            for(int s=0; s<size; s++){
                for(int e=0; e<size; e++){
                    if(s==e) continue;
                    matrix[s][e] = Math.min(matrix[s][e], matrix[s][n]+matrix[n][e]);
                    //이전에 저장된 바로가는 비용과, n을 거쳐서 가는 비용을 비교해서 최소값 갱신신
                }
            }
        }

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                //갈수 없는 비용이 저장되어있다면 0을 출력해야함
                sb.append(matrix[i][j]==MAX_VALUE?0:matrix[i][j]).append(' ');
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}