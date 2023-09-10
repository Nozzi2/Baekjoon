import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
출발지에서 목적지후보로 지나가는데,
특정 도로를 무조건 지나가는 목적지들을 출력하는 문제이다.
여기서 출발지에서 목적지까지는 항상 최소비용으로 이동한다.

그렇다면 출발지로부터 목적지까지 최단경로를 구하고,
    최단경로는 다익스트라 알고리즘 2차원 배열사용를 사용하는데
    2차원배열의 알고리즘은 O(N^2)의 시간 복잡도가지지만
    문제 범위가 2000이므로 시간 복잡도를 계산하면
    2000*2000*100 = 400 000 000
    4억으로 제한시간 3초보단 쪼끔 넘는다.
    만약 시간초과가 뜬다면?
        해당 정점에서 그다음 최소비용 정점을 선택할때
        우선순위큐 같은걸 써서 바로 꺼낼 수 있도록 수정해줘야할듯
최단경로를 구하는 과정에서 특정 도로를 포함하는지 여부를 검사하면 됨.

특정도로 포함 여부 검사?
    2차원 boolean배열을 선언하고
    처음에는 g,h에 해당하는 부분만 true처리를 하고,
    최단경로로 갱신될 때 g,h를 통과하였다면
        갱신되는 두 시작,끝점의 boolean값도 true로 갱신시켜주면 된다.
    이렇게 계속 반복되면 처음에는 g,h만 true였지만,
    g,h를 지나는 최단경도는 모두 true로 갱신된다.


 */
public class Main {

    static final int INF = 987654321;
    static int nodeSize;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            nodeSize = Integer.parseInt(st.nextToken());
            int edgeSize = Integer.parseInt(st.nextToken());
            int targetSize = Integer.parseInt(st.nextToken());
            int[][] costMatrix = new int[nodeSize][nodeSize];
            boolean[][] isMusts = new boolean[nodeSize][nodeSize];
            boolean[] isVisits = new boolean[nodeSize];

            for (int i = 0; i < nodeSize; i++) {
                Arrays.fill(costMatrix[i], INF);
                costMatrix[i][i] = 0;
            }

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int mustA = Integer.parseInt(st.nextToken()) - 1;
            int mustB = Integer.parseInt(st.nextToken()) - 1;

            isVisits[start] = true; //시작값은 이미 입력만으로 갱신이 된 것이므로
            isMusts[mustA][mustB] = true;
            isMusts[mustB][mustA] = true;

            for (int i = 0; i < edgeSize; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()) - 1;
                int e = Integer.parseInt(st.nextToken()) - 1;
                int cost = Integer.parseInt(st.nextToken());

                costMatrix[s][e] = cost;
                costMatrix[e][s] = cost;
            }

//            System.out.println("다익스트라 전.............................");
//            for (int[] arr : costMatrix) {
//                System.out.println(Arrays.toString(arr));
//            }
//            System.out.println();

            dijkstra(start, costMatrix, isMusts, isVisits);

//            System.out.println("다익스트라 후 -----------------------------");
//            for (boolean[] arr : isMusts) {
//                System.out.println(Arrays.toString(arr));
//            }
//            System.out.println();

            List<Integer> rightTargets = new ArrayList<>();
            for (int i = 0; i < targetSize; i++) {
                int target = Integer.parseInt(br.readLine()) - 1;
//                System.out.printf("start %d, target %d\n", start, target);
                if (isMusts[start][target]) {
                    rightTargets.add(target);
                }
            }

            Collections.sort(rightTargets);

//            sb.append("정답 : ");//////////////////////////
//            sb.append('\n');/////////////////////
            for (int n : rightTargets) {
                sb.append(n + 1);
                sb.append(' ');
            }
            sb.append("\n");
        }//for t end

        System.out.print(sb.toString());
    }//main end

    static void dijkstra(int start, int[][] costMatrix, boolean[][] isMusts, boolean[] isVisits) {
        int[] costs = new int[nodeSize];
        for (int i = 0; i < nodeSize; i++) {
            costs[i] = costMatrix[start][i];
        }
//        System.out.println("start > " + start);
//        for (int[] arr : costMatrix) {
//            for (int n : arr) {
//                System.out.print(n==INF?"X ":n+" ");
//            }
//            System.out.println();
//        }

        for (int i = 0; i < nodeSize - 2; i++) {
//            System.out.println();
//            System.out.println("costs : "+Arrays.toString(costs));
//            for (boolean[] arr : isMusts) {
//                System.out.println(Arrays.toString(arr));
//            }
//            System.out.println();

            int currentIndex = getMinIndex(costs, isVisits);
            int currentCost = costs[currentIndex];
            isVisits[currentIndex] = true;
//            System.out.printf("curIndex : %d, curCost : %d\n", currentIndex, currentCost);

            for (int next = 0; next < nodeSize; next++) {
                if(isVisits[next]) continue;

                int nextCost = currentCost + costMatrix[currentIndex][next];
//                System.out.printf("  nextIndex : %d, nextCost : %d\n", next, nextCost);
//                System.out.printf("  기존 cost : %d, nextCost : %d\n", costs[next], nextCost);

                if (costs[next] > nextCost) { //최단경로가 갱신될때
//                    System.out.println("    갱신된닷!");
//                    System.out.printf("    %d -?- %d --- %d\n",start, currentIndex, next);

                    isMusts[start][next] = isMusts[currentIndex][next]; //해당경로에 특정 도로 포함여부 갱신
                    isMusts[next][start] = isMusts[currentIndex][next]; //해당경로에 특정 도로 포함여부 갱신

                    if(isMusts[start][currentIndex]) { //해당 경로내에 특정 도로가 포함된다면?
//                        System.out.println("    특정 도로를 지나는 경로임!");
                        isMusts[start][next] = true; //해당경로에 특정 도로 포함여부 갱신
                        isMusts[next][start] = true; //해당경로에 특정 도로 포함여부 갱신
                    }
                    costs[next] = nextCost;
                } else if (costs[next] == nextCost) {
                    if(!isMusts[start][next]){
                        isMusts[start][next] = isMusts[currentIndex][next]; //해당경로에 특정 도로 포함여부 갱신
                        isMusts[next][start] = isMusts[currentIndex][next]; //해당경로에 특정 도로 포함여부 갱신
                    }
                    if(isMusts[start][currentIndex]) { //해당 경로내에 특정 도로가 포함된다면?
//                        System.out.println("    특정 도로를 지나는 경로임!");
                        isMusts[start][next] = true; //해당경로에 특정 도로 포함여부 갱신
                        isMusts[next][start] = true; //해당경로에 특정 도로 포함여부 갱신
                    }
                }
            }
        }
//        System.out.println();
//        System.out.println("costs : "+Arrays.toString(costs));
//        for (boolean[] arr : isMusts) {
//            System.out.println(Arrays.toString(arr));
//        }
//        System.out.println();
    }

    static int getMinIndex(int[] costMatrix, boolean[] isVisits) {
        int index = 0;
        int min = INF;

        for (int i = 0; i < nodeSize; i++) {
            if (!isVisits[i] && min > costMatrix[i]) {
                min = costMatrix[i];
                index = i;
            }
        }

        return index;
    }
}

/*
1
5 4 2 //교차로, 도로, 목적지 후보의 개수
1 2 3 //출발지, 지나간 도로 (2>3 or 3>2)
1 2 6 //목적지1, 목적지2, 도로 길이
2 3 2 //목적지1, 목적지2, 도로 길이
3 4 4 //목적지1, 목적지2, 도로 길이
3 5 3 //목적지1, 목적지2, 도로 길이
5 //목적지후보1
4 //목적지후보2

#1
1
5 5 2
1 2 3
1 4 3
4 5 3
1 2 2
2 3 2
3 5 2
3
5

output: 3 5
answer: 3 5

#2
3
5 5 1
1 3 5
1 2 1
2 4 2
2 3 2
3 5 3
4 5 3
5
5 5 1
1 4 5
1 2 1
2 4 2
2 3 2
3 5 3
4 5 3
5
6 7 3
1 4 5
1 2 1
2 4 2
2 3 2
3 5 3
4 5 3
5 6 4
2 6 9
5
3
6

ans
5
5
5 6

out
5
5
5 6

#3
1
6 6 2
1 2 3
1 2 1
2 3 2
3 4 1
4 5 2
1 6 4
6 5 1
4
5

answer:
4
output:
4

#4
1
6 9 2
2 3 1
1 2 1
1 3 3
2 4 4
2 5 5
3 4 3
3 6 2
4 5 4
4 6 3
5 6 7
5
6

ans : 6
out : 6

#5
1
5 5 1
1 3 5
1 2 1
2 4 2
2 3 2
3 5 3
4 5 3
5

answer : 5
output : 5

wrong : #3 #4
 */