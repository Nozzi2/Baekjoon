import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
보석도둑이 가방을 여러개 들고 있고,
가방 별로 보석을 1개 담을 수 있는데,
이 가방에는 무게 제한이 있음.
보석 여러개의 무게와 가치가 주어졌을 때
보석도둑이 훔칠 수 있는 보석들의 가치의 최대값은?

뭔가 자료구조를 써야될 것 같은 문제같음.

일단, 가방의 무게가 낮을수록 담을 수 있는 보석의 종류가 적어지니까
낮은 무게의 가방에 먼저 높은 가치의 보석을 담는 것이 우선일 것임.

그럼 가방의 무게는 일단 오름차순으로 정렬해놓고
보석들은 무게 기준으로 오름차순 정렬해놓는다.

그리고 우선순위 큐를 사용하는데, 이것은 보석의 가치 기준으로 정렬하게 해서
가장 먼저 poll되야할 것은 가장 높은 가치를 가진 보석이 나오도록 하기

모든 가방들을 순회하면서
    선택한 가방의 무게를 기준으로
    모든 보석을 순회하고
        해당 보석이 선택한 가방의 무게보다 같거나 작다면?
            우선순위 큐에 넣기
        크다면?
            더 이상 넣을 수 있는 보석이 없으므로 빼야함
            poll하면 해당 가방에 넣을 수 있는 가치의 최대값을 알 수 있음.

이렇게 모든 가방과 보석을 순회하는데,
여기서 보석 순회할 떄는 가방마다 계속 순회하는 것이 아닌
이전 가방에서 순회할 때 넣었던 보석의 인덱스를 기억해야함.
 */
public class Main {

    static class Jewel {
        int weight;
        int value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public String toString() {
            return "w : "+weight+", v : "+value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int jewelSize = Integer.parseInt(st.nextToken());
        int bagSize = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[jewelSize];
        for (int i = 0; i < jewelSize; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(weight, value);
        }

        Arrays.sort(jewels, (o1, o2) -> o1.weight - o2.weight); //무게 기준 정렬

        int[] bags = new int[bagSize];
        for (int i = 0; i < bagSize; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bags); //오름차순 정렬

        PriorityQueue<Jewel> prique = new PriorityQueue<>((o1, o2) -> o2.value - o1.value); //가치기준 내림차순
        int jewelIndex = 0;
        long sum = 0;
        for (int weightCap : bags) { //모든 가방을 순회
//            System.out.printf("cap : %d \n", weightCap);
            boolean isGet = false; //우선순위큐에서 빼서 합계에 더했는지 여부

            while (jewelIndex < jewelSize) {
                Jewel j = jewels[jewelIndex];
//                System.out.print("  "+jewelIndex);
                if (j.weight <= weightCap) {
                    prique.offer(j);
                    jewelIndex++;
//                    System.out.println(prique.toString());
                } else {
//                    System.out.println(prique.toString()+"<< break");
                    if(!prique.isEmpty()){
                        int value = prique.poll().value;
                        sum += value;
                        isGet = true;
                    }
                    break;
                }
            }

            if (!isGet && !prique.isEmpty()) {
                int value = prique.poll().value;
                sum += value;
            }
        }

        System.out.println(sum);

    }
}

/*
4 3
1 65
2 99
4 150
5 23
10
4
5

4 3
100 100
101 200
103 300
104 400
5
6
7

4 3
100 100
101 200
103 300
104 400
500
600
700

4 3
100 100
101 200
103 300
104 400
101
101
101

4 3
1 200
2 99
4 150
5 23
10
4
5

 */