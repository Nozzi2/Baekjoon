import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
두 학생의 키를 비교한 결과를 나타낸 입력이 여러개 주어졌을 떄
모든 학생의 키 순서를 출력하는 문제임

위상정렬의 대표적인 문제이다.
위상정렬이란?
싸이클이 존재하지 않는 유향그래프에서만 사용할 수 있다.
노드로 들어오는 다른 노드들의 갯수를 진입차수라고 하고,
이러한 진입차수가 0인 것부터 que에서 넣고, 빼면서 또 0인 것들을 que에 넣어주면서
정답이 될 수 있는 순서로 정렬을 하는 것이다.


그래서 문제에서 주어지는 입력은 오직 2개뿐이고
이러한 입력이 여러개가 주어지니까
두개의 입력을 int배열에 진입차수로 갱신시켜주고
진입차수가 0 인것들을 계속 돌려주면서 정렬을 해주면 될 것 같다.
 */
public class Main {

    static class Node implements Comparable<Node>{
        int num;
        int count; //진입차수
        Set<Integer> goSet;

        public Node(int num) {
            this.num = num;
            this.count = 0;
            goSet = new HashSet<>();
        }

        //진입차수가 적은것부터 출력할 수 있도록 정렬
        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }

        @Override
        public String toString() {
            return "num : "+num+", count : "+count+", goSet : "+ goSet;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int reqCount = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[size + 1];
        for (int i = 0; i <= size; i++) {
            nodes[i] = new Node(i);
        }

        for (int r = 0; r < reqCount; r++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (!nodes[start].goSet.contains(end)) { //같은 입력이 여러번 들어올 수도 있음
                nodes[start].goSet.add(end);
                nodes[end].count++;
            }
        }

        Queue<Node> que = new ArrayDeque<>(); //진입차수 기준 오름차순
        for (int i = 1; i <= size; i++) {
            if (nodes[i].count == 0) {
                que.offer(nodes[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!que.isEmpty()) {
            Node n = que.poll();
//            System.out.println(n);
            sb.append(n.num).append(' ');

            for (Integer i : n.goSet) {
                nodes[i].count--;
                if (nodes[i].count == 0) {
                    que.offer(nodes[i]);
                }
            }
        }

        System.out.println(sb.toString());


    }
}
/*
3 2
1 3
2 3

4 2
4 2
3 1

4 2
3 1
4 2

28645
12345

5 5
1 4
2 3
3 5
4 5
2 4


10 8
1 2
3 2
5 2
8 1
4 3
7 8
10 8
9 8
num : 4, count : 0, goSet : [3]
num : 5, count : 0, goSet : [2]
num : 9, count : 0, goSet : [8]
num : 10, count : 0, goSet : [8]
num : 7, count : 0, goSet : [8]
num : 3, count : 0, goSet : [2]
num : 6, count : 0, goSet : []
num : 8, count : 0, goSet : [1]
num : 2, count : 1, goSet : [] <<<<<<<<<<< ??????????뭐임
num : 1, count : 0, goSet : [2]
4 5 9 10 7 3 6 8 2 1

 */