import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
DP로 풀어야하나 생각했는데,
문제 범위 자체가 엄청 크지 않기 때문에 그냥 DFS로도 풀릴것같은데?
재귀 호출의 depth가 최대 15까지밖에 가지 않으므로
    -9, -3, -1을 번갈아가면서 때리면 총 -13인데, 13*5 = 65이므로
    15번정도면 모든 SCV가 죽을 것 같음.

재귀 호출을 하면서
    1번째꺼 때리고 재귀호출
    2번쨰꺼 때리고 재귀호출
    3번쨰꺼 때리고 재귀호출

재귀호출할 파라미터
    남은 SCV 대수
    SCV 체력이 저장된 배열
    현재 카운트

!!! 시간 초과 !!!
로직 자체는 괜찮은 것 같은데,
하나의 재귀마다 9개의 분기가 있기 때문에
최대 depth가 15라면
9^15이기 때문에 시간 초과가 나는 것 같음.
그렇기 때문에 DFS는 사용하면 안되는 로직임

예를 들어 떄리는 순서가 123이라면 123을 여러개로 할 수도 있지만,
굳이 깊은 depth에서 123을 여러번 때릴 필요는 없음
그렇기 때문에 DFS보다는 BFS를 사용하면
딱 최소 depth까지만 호출이 될 것임.

bfs 탐색마다 que에 담겨있어야할 정보들
    SCV 체력이 저장된 배열
 */
public class Main {

    static int minHitCount;
    static final int NO_HIT = -1;
    static final int FIRST_ORDER = 0;
    static final int SECOND_ORDER = 1;
    static final int THIRD_ORDER = 2;
    static boolean[][][] minCounts;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int curCount = Integer.parseInt(br.readLine()); //살아있는 SCV 갯수
        int[] scvs = new int[3];
        minCounts = new boolean[61+9][61+9][61+9];
        minHitCount = Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int index = 0;
        while (st.hasMoreTokens()) {
            scvs[index++] = Integer.parseInt(st.nextToken());
        }

//        long start = System.currentTimeMillis();

//        int hit = 0;
//        dfs(scvs, curCount, hit);
        int count = bfs(scvs);


//        long end = System.currentTimeMillis();

        System.out.println(count);
//        System.out.println("소요시간 : "+(end-start)+"ms");
    }

    static int bfs(int[] scvs) {
        Queue<int[]> que = new ArrayDeque<>();
        que.offer(scvs);
        int count = 0;
        setVisited(scvs);

        while (!que.isEmpty()) {
            count++;
//            System.out.println(count);
            for (int q = 0, endq = que.size(); q < endq; q++) {
                int[] curScvs = que.poll();

//                System.out.println(Arrays.toString(curScvs));
                for (int first = 0; first < 3; first++) {
                    if(curScvs[first] <= 0) continue;

                    int[] hitOrders = new int[3];
                    hit(curScvs, FIRST_ORDER, first);

                    int second = (first + 1) % 3;
                    int third = (first + 2) % 3;

                    Arrays.fill(hitOrders, NO_HIT);
                    hitOrders[first] = FIRST_ORDER; //0,1,2번째 SCV를 때린 순서 저장

                    if (curScvs[second] > 0) { //두번째 SCV가 살아있다면
                        hit(curScvs, SECOND_ORDER, second);
                        hitOrders[second] = SECOND_ORDER; //2번째로 때린 SCV의 인덱스에 2번쨰로 때렸다고 저장
                        if (curScvs[third] > 0) { //세번째 SCV가 살아있다면
                            hit(curScvs, THIRD_ORDER, third);
                            hitOrders[third] = THIRD_ORDER;
                        }
                    }

//                    System.out.println("때린 후 "+Arrays.toString(curScvs));
                    if(isAllDied(curScvs)) return count;
                    if(!isVisited(curScvs)) { //해당 HP리스트로 이미 때린 적이 없다면
                        int[] nextScvs = deepCopyArray(curScvs);
                        que.offer(nextScvs);
                        setVisited(curScvs); //현재 체력들로 때려봤다고 저장
                    }
                    for (int i = 0; i < 3; i++) {
                        if(hitOrders[i] == NO_HIT) continue;
                        undo(curScvs, hitOrders[i], i);
                    }//for i end

                    hit(curScvs, FIRST_ORDER, first);

                    second = (first + 2) % 3;
                    third = (first + 1) % 3;

                    Arrays.fill(hitOrders, NO_HIT);
                    hitOrders[first] = FIRST_ORDER; //0,1,2번째 SCV를 때린 순서 저장

                    if (curScvs[second] > 0) { //두번째 SCV가 살아있다면
                        hit(curScvs, SECOND_ORDER, second);
                        hitOrders[second] = SECOND_ORDER; //2번째로 때린 SCV의 인덱스에 2번쨰로 때렸다고 저장
                        if (curScvs[third] > 0) { //세번째 SCV가 살아있다면
                            hit(curScvs, THIRD_ORDER, third);
                            hitOrders[third] = THIRD_ORDER;
                        }
                    }

//                    System.out.println("때린 후 "+Arrays.toString(curScvs));
                    if(isAllDied(curScvs)) return count;
                    if(!isVisited(curScvs)) { //해당 HP리스트로 이미 때린 적이 없다면
                        int[] nextScvs = deepCopyArray(curScvs);
                        que.offer(nextScvs);
                        setVisited(curScvs); //현재 체력들로 때려봤다고 저장
                    }
                    for (int i = 0; i < 3; i++) {
                        if(hitOrders[i] == NO_HIT) continue;
                        undo(curScvs, hitOrders[i], i);
                    }//for i end


                }//for first end
            }//for q end
        }//while end

        return count;

    }

    static int[] deepCopyArray(int[] arr) {
        int[] output = new int[3];
        for (int i = 0; i < 3; i++) {
            output[i] = arr[i];
        }
        return output;
    }

    static boolean isAllDied(int[] scvs) {
        for (int i = 0; i < 3; i++) {
            if (scvs[i] > 0) {
                return false;
            }
        }
        return true;
    }

    static boolean isVisited(int[] scvs) {
        return minCounts[scvs[0]+9][scvs[1]+9][scvs[2]+9];
    }

    static void setVisited(int[] scvs) {
        minCounts[scvs[0]+9][scvs[1]+9][scvs[2]+9] = true; //-9가 나올 수 있으므로
    }

    static void dfs(int[] scvs, int scvCount, int hitCount) {
        System.out.println("hitCount : "+hitCount+" "+Arrays.toString(scvs));


        if (scvCount == 0) { //남아있는 SCV가 한마리도 없다면
            minHitCount = Math.min(minHitCount, hitCount);
            return;
        }

        for (int i = 0; i < scvCount; i++) {
            if (scvs[i] <= 0) continue; //죽어있다면 넘어가도됨

            int[] hitOrders = new int[3];
            Arrays.fill(hitOrders, NO_HIT);
            hit(scvs, FIRST_ORDER, i);
            hitOrders[i] = FIRST_ORDER;

            //ex) 2,3번 순서대로 떄리기
            int secondIndex = (i + 1) % 3;
            if (scvs[secondIndex] > 0) {
                hit(scvs, SECOND_ORDER, secondIndex);
                hitOrders[secondIndex] = SECOND_ORDER;
                int thirdIndex = (i + 2) % 3;
                if (thirdIndex > 0) {
                    hit(scvs, THIRD_ORDER, thirdIndex);
                    hitOrders[thirdIndex] = THIRD_ORDER;
                }
            }
            int curCount = getCurrentCount(scvs);
            dfs(scvs, curCount, hitCount + 1);

            for (int h = 0; h < 3; h++) {
                if (hitOrders[h] == NO_HIT) continue; //2,3번째 때린 것만 되돌리기
                undo(scvs, hitOrders[h], h); // 때린거 다시 원상복귀
            }


            Arrays.fill(hitOrders, NO_HIT);
            hit(scvs, FIRST_ORDER, i);
            hitOrders[i] = FIRST_ORDER;

            // ex) 3,2번 순서대로 때리기
            secondIndex = (i + 2) % 3;
            if (scvs[secondIndex] > 0) {
                hit(scvs, SECOND_ORDER, secondIndex);
                hitOrders[secondIndex] = SECOND_ORDER;
                int thirdIndex = (i + 1) % 3;
                if (thirdIndex > 0) {
                    hit(scvs, THIRD_ORDER, thirdIndex);
                    hitOrders[thirdIndex] = THIRD_ORDER;
                }
            }
            curCount = getCurrentCount(scvs);
            dfs(scvs, curCount, hitCount + 1);

            for (int h = 0; h < 3; h++) {
                if (hitOrders[h] == NO_HIT) continue;
                undo(scvs, hitOrders[h], h); // 때린거 다시 원상복귀
            }
        }
    }

    static void hit(int[] scvs, int order, int index) {
        int damage = 9;
        for (int i = 0; i < order; i++) {
            damage /= 3;
        }
        scvs[index] -= damage;
    }

    static void undo(int[] scvs, int order, int index) {
        int damage = 9;
        for (int i = 0; i < order; i++) {
            damage /= 3;
        }
        scvs[index] += damage;
    }

    static int getCurrentCount(int[] scvs) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (scvs[i] > 0) {
                count++;
            }
        }

        return count;
    }
}
/*
3
12 10 4

3
60 60 60

 */