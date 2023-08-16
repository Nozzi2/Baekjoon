import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
보조 PD들이 출연 가수들의 출연 순서를 제시했을 때
메인 PD가 모든 보조 PD들의 출연 순서를 만족하는 순서를 반환해야하는 문제이다.
만약, 모든 보조 PD의 순서를 만족하지 못한다면 0을 반환한다.

최악의 경우 가수 1000명, PD 100명
1000*1000*100 = 1억

가수 6명, PD 3명
1. 1 4 3
2. 6 2 5 4
3. 2 3

1. 1 4 3
 1 2 3 4 5 6
[1,0,3,2,0,0]
2. 6 2 5 4
 1 2 3 4 5 6
[1.1,1.2,3,2,1.2,1.2]
3. 2 3
 1 2 3 4 5 6
[1,1,3,2,1,1]

첫번쨰 입력은 순서대로 가수별 우선순위가 저장되어 있는 배열에 저장한다.
입력을 검사하고 우선순위에 저장되어있

반례중에서 이미 저장된 순서와 겹치는 순서가 하나도 없는 입력이 들어왔을 떄도 고려해줘야함.

고민해도 잘 모르겠어서 검색해보니 위상정렬을 사용하면 된다고 함.
위상정렬이란?
싸이클이 존재하지 않는 유향그래프에서만 사용할 수 있다.
노드로 들어오는 다른 노드들의 갯수를 진입차수라고 하고,
이러한 진입차수가 0인 것부터 que에서 넣고, 빼면서 또 0인 것들을 que에 넣어주면서
정답이 될 수 있는 순서로 정렬을 하는 것이다.

위상정렬에서 중요한 것은 사이클이 존재하면 안된다는 것인데,
보조PD들의 순서에 서로 괴리가 발생한다는 것은 곧 사이클이 발생한다는 의미이다.
그래서 que가 비어버리는 경우가 발생한다면 사이클이 발생하는 것이라서 바로 0을 출력하면 됨.
 */
public class Main {

    static class Singer {
        int num;
        int count; //진입차수
        Set<Integer> nextSet; //다음가수 목록

        public Singer(int num) {
            this.num = num;
            this.count = 0;
            nextSet = new HashSet<>();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int pdCount = Integer.parseInt(st.nextToken());

        Singer[] singers = new Singer[size + 1];
        for (int i = 0; i <= size; i++) {
            singers[i] = new Singer(i);
        }

        for (int p = 0; p < pdCount; p++) {
            st = new StringTokenizer(br.readLine());
            int orderSize = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());
            for (int o = 1; o < orderSize; o++) {
                int next = Integer.parseInt(st.nextToken());
                if (!singers[prev].nextSet.contains(next)) { //다음 가수 목록에 없다면
                    singers[prev].nextSet.add(next); //다음 가수 목록에 추가
                    singers[next].count++; //다음 가수의 진입차수 증가
                }
                prev = next;
            }
        }

        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 1; i <= size; i++) {
            if (singers[i].count == 0) { //진입차수 0인것만 que에 넣기
                que.offer(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        int outputSize = 0; //사이클을 판별할 정수값
        while (!que.isEmpty()) {
            int num = que.poll();
            Singer s = singers[num];
            outputSize++;
            sb.append(num).append(' ');

            for (Integer i : s.nextSet) { //다음 차례 가수들을 순회하며
                if (--singers[i].count == 0) { //진입차수 줄이고, 0인 것만 que에 넣어주기
                    que.offer(singers[i].num);
                }
            }
        }

        if (outputSize != size) { //모든 가수들을 출력하지 않는다면
            System.out.println(0); //사이클이 존재하는 것이므로 순서를 정할 수 없음
        } else {
            System.out.println(sb.toString());
        }
    }
}

/*
6 3
3 1 4 3
4 6 2 5 4
2 2 3

 */