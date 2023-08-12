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
        int productSize = Integer.parseInt(st.nextToken());
        
        List<Integer> multitap = new ArrayList<>();
        Queue<Integer>[] products = new ArrayDeque[productSize + 1];    //물건별 뽑을 회차 저장
        int[] orders = new int[productSize+1];  //꽂을 물건 순서

        for (int i = 0; i <= productSize; i++) {
            products[i] = new ArrayDeque<Integer>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= productSize; i++) {
            int productNumber = Integer.parseInt(st.nextToken());
            orders[i] = productNumber;          //뽑을 번호 저장
            products[productNumber].offer(i);   //해당 물건에 몇번쨰 회차에 뽑을지 저장해놓기
        }

        int count=0;
        for (int o = 1; o <= productSize; o++) {
            int product = orders[o];

            if(multitap.contains(product)) continue; //이미 꼽혀있다면 생략
            if (multitap.size() < multitapSize) {    //꽂을 자리가 남았다면
                multitap.add(product);
                continue;
            }
            
            
            //꽂을 자리가 없다면
            count++;

            int maxNextOrder = 0;   //다음 뽑을 회차의 최댓값
            int unplugMultitap = 0; //뽑을 플러그 번호 (물건 번호랑 다름)

            //몇번째 플러그를 뽑을지 검사
            for (int m = 0, endm = multitap.size(); m < endm; m++) { //모든 멀티탭을 순회하면서
                int checkProduct = multitap.get(m);

                //해당 물건의 다음 뽑을 횟수를 저장하기 위해 현재 회차보다 적은거 지우기
                while(!products[checkProduct].isEmpty()     //다음 뽑을 회차가 비어있으면 안됨
                        && products[checkProduct].peek() < o){  //현재 회차보다 적으면
                    products[checkProduct].poll(); //지워버리기
                }
                /*
                현재 회차 5
                검사할 물건의 뽑을 회차 [2,4,6,8]
                위 while문을 통해서 2,4는 poll해줌.
                 */

                //다음 뽑을 회차가 없는 경우
                if (products[checkProduct].isEmpty()) {
                    unplugMultitap = m;
                    break;
                }

                //현재 물건의 다음 뽑을 회차가 기존 최대회차보다 크다면
                int checkNextOrder = products[checkProduct].peek(); 
                if(checkNextOrder > maxNextOrder){
                    unplugMultitap = m; //해당 멀티탭을 뽑을 것임
                    maxNextOrder = checkNextOrder; //최대회차 갱신
                }
            }//end for m

            //뽑아줘야함.
            multitap.remove(unplugMultitap);
            multitap.add(product);
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