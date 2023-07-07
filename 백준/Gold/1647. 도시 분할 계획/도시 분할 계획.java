/*
알아낸 것들
- 각 특정 집마다 2개 이상의 길이 있다면 2개까지 길을 줄일 수 있음.

길을 다 이어놓고 하나씩 지워나가는 것보다
적은 비용의 길끼리 이어서 2개의 집합이 나오는게 어떨까?

길들을 모두 배열에 저장해놓고,
비용이 낮은 순서로 정렬한다.

낮은 비용부터 이을지말지 결정해야하는데,
이미 같은 집합이라면 잇지 않아도 되고
다른 집합이라면 됐다면 총 집 갯수--를 해준다.
그래서 총 집 갯수가 2가 될때까지 길을 이어준다.



 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] towns;
    static List<Street> streets;

    static class Street {
        int a;
        int b;
        int cost;

        public Street(int a, int b, int cost) {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Street{" +
                    "a=" + a +
                    ", b=" + b +
                    ", cost=" + cost +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int houseSize = Integer.parseInt(st.nextToken());
        int streetSize = Integer.parseInt(st.nextToken());
        int totalCost = 0;
        int townCount = houseSize;

        towns = new int[houseSize+1];
        streets = new ArrayList<>();
        for (int i = 1; i <= houseSize; i++) {
            towns[i] = i;
        }

        for (int T = 0; T < streetSize; T++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            streets.add(new Street(a, b, cost));
        }

        //길 유지비가 낮은 순서대로 정렬
        Collections.sort(streets,(o1, o2) -> o1.cost - o2.cost);

        //비용이 낮은 길부터 이어주는 작업
        for (Street s : streets) {
            if(townCount==2) break; //마을 갯수가 2개가 되면 더이상 길을 이을 필요가 없음
            if (isSameTown(s)) continue; //이미 같은 마을이라면 길을 이을 필요가 없음
            union(s);
            totalCost += s.cost; //총 유지비 더하기
            townCount--;
        }

        System.out.println(totalCost);
    }

    static void union(Street street) {
        int a = find(street.a);
        int b = find(street.b);

        if(a<b){
            towns[b] = a;
        } else {
            towns[a] = b;
        }
    }

    static int find(int a) {
        if (towns[a] == a) return a;
        return towns[a] = find(towns[a]);
    }

    static boolean isSameTown(Street street) {
        int a = find(street.a);
        int b = find(street.b);

        return a == b;
    }
}