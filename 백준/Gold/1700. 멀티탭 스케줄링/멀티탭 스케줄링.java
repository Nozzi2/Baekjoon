import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
멀티탭 구 갯수와 꽂을 전자기기 갯수 및 꽂을 횟수가 주어졌을 떄
최소한으로 뽑는 횟수를 구하는 문제임

그리디로 접근하면 어떨까?
만약 기존것을 뽑아야 하는 상황이 왔을 때
다음 것을 꼽혀져있는 것중에서 가장 꽂을 회차가 뒤에있는 것을 뽑는 것이다.

예를들어 입력이
3 6
1 2 3 4 1 2
이렇게 된다면
4번을 꽂을 때
    1번은 5번째에 꽂을 예정이고
    2번은 6번째에 꽂을 예정
    3번은 꽂을 예정이 없으므로
3번을 뽑아야 한다.

우선순위 큐 쓰면 좋겠지만, 새로 꽂을 때 다음 꽂을 거를 갱신해줘야 하는데, 이게 좀 까다로울거같음
문제에서 주어지는 범위가 100이기 때문에 100번의 회차마다 100개를 탐색해줘도 문제될 것 같진 않음.

그럼 각 번호별로 꽂을 순서를 어떻게 저장하고, 어떻게 검사할까

먼저 저장하는법.
1번부터 N번까지 que 배열을 선언한다.
그리고 각 배열의 인덱스별로 꽂을 횟수를 넣어준다.

검사하는 법
멀티탭에 꽂혀져 있는 거 확인해서
현재 회차수보다 적다면 다 poll 해준다.
현재 회차수보다 큰 값이 있다면, 해당 값을 해당 번호의 회차수로 반환하고,
회차수가 가장 큰값이라면 해당 번호를 뽑을 번호로 저장한다.

 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int multitapSize = Integer.parseInt(st.nextToken());
        List<Integer> multitap = new ArrayList<>();
        int productSize = Integer.parseInt(st.nextToken());
        Queue<Integer>[] products = new ArrayDeque[productSize + 1];
        int[] orders = new int[productSize+1];

        for (int i = 0; i <= productSize; i++) {
            products[i] = new ArrayDeque<Integer>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= productSize; i++) {
            int productNumber = Integer.parseInt(st.nextToken());
            orders[i] = productNumber;
            products[productNumber].offer(i);
        }

        int count=0;
        for (int o = 1; o <= productSize; o++) {
            int product = orders[o];

            if(multitap.contains(product)) continue; //이미 꼽혀있다면 생략

            if (multitap.size() < multitapSize) { //꽂을 자리가 남았다면
                multitap.add(product);
            } else { //꽂을 자리가 없다면
                count++;

                int maxNextOrder = 0;
                int unplugMultitap = 0;

                //어떤걸 뽑을지 검사하고
                for (int m = 0, endm = multitap.size(); m < endm; m++) {
                    int checkProduct = multitap.get(m);

                    while(!products[checkProduct].isEmpty()){
                        if (products[checkProduct].peek() < o) {
                            products[checkProduct].poll();
                        } else {
                            break;
                        }
                    }

                    if (products[checkProduct].isEmpty()) {
                        unplugMultitap = m;
                        maxNextOrder = 101;
                        continue;
                    }
                    if(products[checkProduct].peek() > maxNextOrder){
                        unplugMultitap = m;
                        maxNextOrder = products[checkProduct].peek();
                    }
                }

//                System.out.printf("[ %d ]번 플러그에 꽂혀있는 [ %d ]가 [ %d ]번째에서 뽑을 겁니다.\n", unplugMultitap, multitap.get(unplugMultitap),maxNextOrder);

                //뽑아줘야함.
                multitap.remove(unplugMultitap);
                multitap.add(product);
            }

//            System.out.println(multitap.toString());
        }

        System.out.println(count);
    }
}

/*
3 6
1 2 3 4 1 2

2 7
2 3 2 3 1 2 7
 */