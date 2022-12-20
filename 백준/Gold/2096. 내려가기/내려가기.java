import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] input;
    static int[][] minMemo; //현재까지의 최소값 합계를 저장할 배열
    static int[][] maxMemo; //현재까지의 최대값 합계를 저장할 배열

    //큐에 하나씩 담을 클래스
    static class Value {
        int no; //직전에 선택한 숫자의 번호 (0,1,2)
        int sum; //선택해왔던 숫자들의 합계

        public Value(int no, int sum) {
            this.no = no;
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "Value{" +
                    "no=" + no +
                    ", sum=" + sum +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        int minVal, maxVal;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N=Integer.parseInt(br.readLine()); //줄 수 입력
//        input = new int[N][3];
//        minMemo = new int[N][3];
//        maxMemo = new int[N][3];

        StringTokenizer st;
        int[] input = new int[3];
        int[][] minArr = new int[2][3];
        int[][] maxArr = new int[2][3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            int val = Integer.parseInt(st.nextToken());
            input[i] = minArr[0][i] = maxArr[0][i] = val;
        }

//        System.out.println();
//        for (int a = 0; a < 2; a++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.print(minArr[a][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//
//        //출력
//        for (int a = 0; a < 2; a++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.print(maxArr[a][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println("끝");

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                input[j] = Integer.parseInt(st.nextToken());
            }

            minArr[i%2][0] = Math.min(minArr[(i-1)%2][0],minArr[(i-1)%2][1])+input[0];
//            System.out.printf("minArr[i/2][0] = %d%n",minArr[i%2][0]);
//            System.out.printf("minArr[(i-1)/2][0] = %d%n",minArr[(i-1)%2][0]);
//            System.out.printf("minArr[(i-1)/2][1] = %d%n",minArr[(i-1)%2][1]);
//            System.out.printf("input[0] = %d%n",input[0]);
            minArr[i%2][2] = Math.min(minArr[(i-1)%2][2],minArr[(i-1)%2][1])+input[2];
            minArr[i%2][1] = Math.min(Math.min(minArr[(i-1)%2][0],minArr[(i-1)%2][1]),minArr[(i-1)%2][2])+input[1];

            maxArr[i%2][0] = Math.max(maxArr[(i-1)%2][0],maxArr[(i-1)%2][1])+input[0];
            maxArr[i%2][2] = Math.max(maxArr[(i-1)%2][2],maxArr[(i-1)%2][1])+input[2];
            maxArr[i%2][1] = Math.max(Math.max(maxArr[(i-1)%2][0],maxArr[(i-1)%2][1]),maxArr[(i-1)%2][2])+input[1];

//            for(int j=0; j<N; j++){
//                System.out.print(minArr[i%2][j]+" ");
//            }
//            System.out.println();
//
//            for(int j=0; j<N; j++){
//                System.out.print(maxArr[i%2][j]+" ");
//            }
//            System.out.println();

//            System.out.println();
//            for (int a = 0; a < 2; a++) {
//                for (int j = 0; j < 3; j++) {
//                    System.out.print(minArr[a][j]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//
//            //출력
//            for (int a = 0; a < 2; a++) {
//                for (int j = 0; j < 3; j++) {
//                    System.out.print(maxArr[a][j]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println("끝");
        }
//        for (int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < 3; j++) {
//                input[i][j] = Integer.parseInt(st.nextToken());
//                minMemo[i][j] = Integer.MAX_VALUE; //모든 저장값 최대값으로 초기화
//                maxMemo[i][j] = Integer.MIN_VALUE; //모든 저장값 최소값으로 초기화
//            }
//        }

//        for (int i = 0; i < 3; i++) {
//            Value start = new Value(i, input[0][i]);
//            minBfs(start); //시간초과 나면 bfs를 1번만 돌리는걸로 바꿔야한다!
//            maxBfs(start);
//        }

//        minBfs(); //시간초과 나면 bfs를 1번만 돌리는걸로 바꿔야한다!
//        maxBfs();
//
        int tmp = (N-1)%2;
        minVal = Math.min(Math.min(minArr[tmp][0],minArr[tmp][1]),minArr[tmp][2]);
        maxVal = Math.max(Math.max(maxArr[tmp][0],maxArr[tmp][1]),maxArr[tmp][2]);

        System.out.printf("%d %d",maxVal, minVal);

//        //출력
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.print(minArr[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//
//        //출력
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.print(maxArr[i][j]+" ");
//            }
//            System.out.println();
//        }
    }

    private static void maxBfs() {
        Queue<Value> que = new ArrayDeque<>();
        for (int i = 0; i < 3; i++) {
            Value start = new Value(i, input[0][i]);
            que.offer(start);
            maxMemo[0][start.no] = start.sum;
        }
        int level=1;

        while (!que.isEmpty()) {
//            System.out.println("단계 : "+level);
            for(int q=0, endq=que.size(); q<endq; q++){
//                System.out.println("q사이즈 : "+endq);
                Value cur = que.poll();
//                System.out.println(cur);
                for (int i = 0; i < 3; i++) {
                    if(Math.abs(cur.no - i)>1){ //1>3 또든 3>1는 탐색할 수 없음
//                        System.out.printf("cur.no : %d / i : %d%n",cur.no,i);
                        continue;
                    }
                    int nextSum = cur.sum+input[level][i];
                    if(maxMemo[level][i] >= nextSum) { //이전에 탐색하여 저장된 값이 더 크다면 탐색 종료
                        continue;
                    }
                    Value next = new Value(i,nextSum);
                    maxMemo[level][i] = nextSum;
                    que.offer(next);
                }
//                System.out.println(cur+"종료");
//                for (int i = 0; i < 3; i++) {
//                    System.out.print(minMemo[level][i]+" ");
//                }
//                System.out.println();
//                System.out.println(que.size());
//                System.out.println();
//                System.out.println();

            }
            if(++level== input.length) {
                return;
            }
        }
    }

    private static void minBfs() {
        Queue<Value> que = new ArrayDeque<>();
        for (int i = 0; i < 3; i++) {
            Value start = new Value(i, input[0][i]);
            que.offer(start);
            minMemo[0][start.no] = start.sum;
        }
        int level=1;

        while (!que.isEmpty()) {
//            System.out.println("단계 : "+level);
            for(int q=0, endq=que.size(); q<endq; q++){
//                System.out.println("q사이즈 : "+endq);
                Value cur = que.poll();
//                System.out.println(cur);
                for (int i = 0; i < 3; i++) {
                    if(Math.abs(cur.no - i)>1){ //1>3 또든 3>1는 탐색할 수 없음
//                        System.out.printf("cur.no : %d / i : %d%n",cur.no,i);
                        continue;
                    }
                    int nextSum = cur.sum+input[level][i];
                    if(minMemo[level][i] <= nextSum) { //이전에 탐색하여 저장된 값이 더 크다면 탐색 종료
                        continue;
                    }
                    Value next = new Value(i,nextSum);
                    minMemo[level][i] = nextSum;
                    que.offer(next);
                }
//                System.out.println(cur+"종료");
//                for (int i = 0; i < 3; i++) {
//                    System.out.print(minMemo[level][i]+" ");
//                }
//                System.out.println();
//                System.out.println(que.size());
//                System.out.println();
//                System.out.println();

            }
            if(++level== input.length) {
                return;
            }
        }
    }

    private static void maxBfs(Value start) {
        Queue<Value> que = new ArrayDeque<>();
        que.offer(start);
        maxMemo[0][start.no] = start.sum;
        int level=1;

        while (!que.isEmpty()) {
//            System.out.println("단계 : "+level);
            for(int q=0, endq=que.size(); q<endq; q++){
//                System.out.println("q사이즈 : "+endq);
                Value cur = que.poll();
//                System.out.println(cur);
                for (int i = 0; i < 3; i++) {
                    if(Math.abs(cur.no - i)>1){ //1>3 또든 3>1는 탐색할 수 없음
//                        System.out.printf("cur.no : %d / i : %d%n",cur.no,i);
                        continue;
                    }
                    int nextSum = cur.sum+input[level][i];
                    if(maxMemo[level][i] >= nextSum) { //이전에 탐색하여 저장된 값이 더 크다면 탐색 종료
                        continue;
                    }
                    Value next = new Value(i,nextSum);
                    maxMemo[level][i] = nextSum;
                    que.offer(next);
                }
//                System.out.println(cur+"종료");
//                for (int i = 0; i < 3; i++) {
//                    System.out.print(minMemo[level][i]+" ");
//                }
//                System.out.println();
//                System.out.println(que.size());
//                System.out.println();
//                System.out.println();

            }
            if(++level== input.length) {
                return;
            }
        }
    }

    private static void minBfs(Value start) {
        Queue<Value> que = new ArrayDeque<>();
        que.offer(start);
        minMemo[0][start.no] = start.sum;
        int level=1;

        while (!que.isEmpty()) {
//            System.out.println("단계 : "+level);
            for(int q=0, endq=que.size(); q<endq; q++){
//                System.out.println("q사이즈 : "+endq);
                Value cur = que.poll();
//                System.out.println(cur);
                for (int i = 0; i < 3; i++) {
                    if(Math.abs(cur.no - i)>1){ //1>3 또든 3>1는 탐색할 수 없음
//                        System.out.printf("cur.no : %d / i : %d%n",cur.no,i);
                        continue;
                    }
                    int nextSum = cur.sum+input[level][i];
                    if(minMemo[level][i] <= nextSum) { //이전에 탐색하여 저장된 값이 더 크다면 탐색 종료
                        continue;
                    }
                    Value next = new Value(i,nextSum);
                    minMemo[level][i] = nextSum;
                    que.offer(next);
                }
//                System.out.println(cur+"종료");
//                for (int i = 0; i < 3; i++) {
//                    System.out.print(minMemo[level][i]+" ");
//                }
//                System.out.println();
//                System.out.println(que.size());
//                System.out.println();
//                System.out.println();

            }
            if(++level== input.length) {
                return;
            }
        }
    }

}