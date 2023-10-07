import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
주어진 노드들을 모두 연결하는 최소 비용을 구하는 문제이다.

전형적인 크루스칼 알고리즘 문제이다.
크루스칼 알고리즘이란?
    주어진 모든 간선의 비용을 오름차순으로 정렬하고
    최소값부터 차례대로 간선을 선택해주는데,
    만약 싸이클이 발생한다면
        해당 노드를 선택하지 않고
    발생하지 않는다면
        해당 노드를 선택해주면 됨
    이렇게 노드갯수-1 개의 간선을 선택하면 됨

모든 노드는 서로 연결되어있다고 봐야하기 때문에
초기에 노드별 간선의 비용을 계산해야한다.
간선의 비용은 double값이 나오는데,
간선의 갯수 500 500 개 * double메모리 8byte
= 4 004 000 byte = 4 MB
=> 메모리는 충분함

시간복잡도는 1000이기 때문에 N^2으로 해도 충분함

 */
public class Main {

    static int[] parents;
    static Node[] nodes;

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "["+x+","+y+"]";
        }
    }

    static class Edge {
        int nodeIdx1;
        int nodeIdx2;
        double cost;

        public Edge(int nodeIdx1, int nodeIdx2) {
            this.nodeIdx1 = nodeIdx1;
            this.nodeIdx2 = nodeIdx2;
            int x = getDistanceX();
            int y = getDistanceY();
            cost = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
        }

        int getDistanceX() {
            int dist1 = nodes[nodeIdx1].x;
            int dist2 = nodes[nodeIdx2].x;

            return Math.abs(dist1 - dist2);
        }

        int getDistanceY() {
            int dist1 = nodes[nodeIdx1].y;
            int dist2 = nodes[nodeIdx2].y;

            return Math.abs(dist1 - dist2);
        }

        @Override
        public String toString() {
            return "cost : "+cost+" "+nodes[nodeIdx1].toString()+" - "+nodes[nodeIdx2].toString();
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeSize = Integer.parseInt(st.nextToken());
        int linkedSize = Integer.parseInt(st.nextToken());
        int targetCount = nodeSize - 1 - linkedSize;

        nodes = new Node[nodeSize];
        double[][] edgeCosts = new double[nodeSize][nodeSize];

        parents = new int[nodeSize];
        for (int i = 0; i < nodeSize; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < nodeSize; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(x, y);
        }

        for (int i = 0; i < linkedSize; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            if (isSameParent(a, b)) { //연결되어 있는 통로가 이미 싸이클인 경우
                targetCount++; //목표 갯수 +1해줘야함
            }
            union(a, b);
        }

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < nodeSize - 1; i++) {
            for (int j = i + 1; j < nodeSize; j++) {
                edges.add(new Edge(i, j));
            }
        }

        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Double.compare(o1.cost, o2.cost);
            }
        });

        int edgeCount = 0;
        double result = 0;
        for (Edge e : edges) {
            if (isSameParent(e.nodeIdx1, e.nodeIdx2)) continue;
            result += e.cost;
            union(e.nodeIdx1, e.nodeIdx2);

            if (++edgeCount == targetCount) {
                break;
            }
        }

//        System.out.println(Arrays.toString(parents));
        System.out.println(String.format("%.2f", result));


        /*
        System.out.println(Arrays.toString(parents));
        union(0, 3);
        System.out.println(Arrays.toString(parents));
        union(3, 4);
        System.out.println(Arrays.toString(parents));
        union(6, 4);
        System.out.println(Arrays.toString(parents));


        System.out.println(isSameParent(1, 2));
        System.out.println(isSameParent(6, 5));
        System.out.println(isSameParent(0, 3));
        System.out.println(isSameParent(0, 4));
        System.out.println(isSameParent(0, 6));
        System.out.println(isSameParent(3, 4));
        System.out.println(isSameParent(3, 6));
        System.out.println(isSameParent(4, 6));
        */


    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA > parentB) {
            parents[parentA] = parentB;
        } else {
            parents[parentB] = parentA;
        }
    }

    static int find(int a) {

        if(a == parents[a]) {
            return a;
        } else {
            return parents[a] = find(parents[a]);
        }
    }

    static boolean isSameParent(int a, int b) {
        a = find(a);
        b = find(b);

        return a == b;
    }
}

/*
4 1
1 1
3 1
2 3
4 3
1 4


10 2
30 23
32 18
6 20
76 19
15 14
33 97
16 74
8 49
63 33
8 13
1 2
3 4

[o] 134.84

15 3
5 1
1 1
9 17
5 2
1 9
9 5
1 14
13 11
1 11
5 14
15 9
11 17
9 9
11 1
18 4
9 15
1 2
5 4

output:37.77


5 3
0 0
0 1
0 2
0 3
0 100
1 2
2 3
1 3

98.00
 */