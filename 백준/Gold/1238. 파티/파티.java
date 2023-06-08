import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    /*
    N개의 마을(1,2,...,N)에 학생이 한명씩 있음
    M개의 단방향 도로가 있음
    각 도로별로 소요되는 시간들이 각각 적혀있음.
    학생들은 항상 어떤 마을로 갈떄 최소의 시간을 소요해서 마을로 이동함.

    문제에서 주어지는 X번 마을까지 갔다가 돌아오는데 시간이 많이 걸리는 학생의 소요시간을 구해야함.

    이거 다익스트라 알고리즘이였나

    1번 노드부터 N번 노드까지 X번 노드로 가는 최소 비용을 계산한다.
    그렇다면 시간 복잡도는
    다리 탐색 갯수 10000 *2 (돌아오는 거까지)
    마을 탐색 갯수 1000
    10_000*1_000 = 10_000_000
    이렇게 돼서 시간이 터지진 않을거같음.

    다익스트라 까먹어서 일단 다익스트라 알고리즘 숙지하고 써야겠음.
    https://sskl660.tistory.com/59

     */
    static int n,m,x;
    static ArrayList<ArrayList<Node>> graphGo;
    static ArrayList<ArrayList<Node>> graphBack;

    static class Node {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        graphGo = new ArrayList<ArrayList<Node>>();
        graphBack = new ArrayList<ArrayList<Node>>();
        for (int i = 0; i < n + 1; i++) { //입력 그대로 사용하기 위해 n+1개 생성
            graphGo.add(new ArrayList<Node>());
            graphBack.add(new ArrayList<Node>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graphGo.get(start).add(new Node(end, cost)); //출발할 노드에 도착지, 비용을 리스트에 추가
            graphBack.get(end).add(new Node(start, cost)); //도착할 노드에 출발지, 비용을 리스트에 추가
            //갔다가 돌아와야하므로 start, end를 바꿔서 인접리스트를 선언해야함
        }

        int[] distBack = new int[n + 1]; //최소 비용을 저장할 배열
        int[] distGo = new int[n + 1]; //최소 비용을 저장할 배열

        distBack = dijkstra(graphBack);
        distGo = dijkstra(graphGo);

        int maxCost = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            int cost = distGo[i] + distBack[i];
            maxCost = Math.max(maxCost, cost);
        }

//        System.out.println(Arrays.toString(distBack));
//        System.out.println(Arrays.toString(distGo));
        System.out.println(maxCost);
    }

    private static int[] dijkstra(ArrayList<ArrayList<Node>> graph) {

        int[] dist = new int[n+1];
        for (int i = 0; i < n + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        //x번에서 자신의 마을로 돌아가는 비용 구하기
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost); //오름차순으로 정렬
        pq.offer(new Node(x, 0)); //도착지는 곧 비용이 없는 것과 같음
        dist[x] = 0;
        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int curIdx = curNode.idx;

            //현재 가장 최소의 비용을 가지고 있는 도착지 노드가 기존 최소값보다 크다면
            if (dist[curIdx] < curNode.cost) {
                continue; //최소 비용이 아니니까 다음 노드를 탐색해야함
            }

            //우선순위 큐에서 뺸 노드와 연결된 노드들을 탐색한다
            int size = graph.get(curIdx).size(); //뺀 노드와 연결된 노드 갯수
            for (int i = 0; i < size; i++) {
                Node nextNode = graph.get(curIdx).get(i);
                int nextIdx = nextNode.idx;

                //만약 바로 가는 비용보다 통해서 가는 비용이 더 낮다면
                if (dist[nextIdx] > curNode.cost + nextNode.cost) {
                    dist[nextIdx] = curNode.cost + nextNode.cost; //갱신
                    pq.offer(new Node(nextIdx, dist[nextIdx])); //우선수위 큐에 노드 넣어줌
                }
            }
        }

        return dist;
    }
}