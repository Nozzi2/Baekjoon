import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/* 1512
결과로 출력할 기준
나보다 작은 것 있으면 같거나 큰거 나올떄까지 pop하면서 +1씩 계속 하기
    같거나 큰거 나왔을때도 +1하기
    직전에 pop한 값이 몇개연속으로 나오는지(n개) 계산하고,
        다른 값이 나오면 +n-1
        (pop하면서 +1하는 거랑 동시에 하는거임)
        다른 값이 나오지 않으면 -n+1+nC2해주기
나보다 큰 것 있으면 그냥 +1하고 push

모두 끝났을 떄 스택에 남아있는 경우도 고려하기!

push하면 이전에 있는 것과 무조건 조합이 되므로 +1해준다.

 */
public class Main {

    static final int INIT = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        long result = 0;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
//        StringTokenizer st = new StringTokenizer(br.readLine()); //////////////////////////

        for (int i = 0; i < size; i++) {
//            int input = Integer.parseInt(st.nextToken());
            int input = Integer.parseInt(br.readLine()); //////////////////////////
//            System.out.println("> 입력 : "+input);

            if (stack.isEmpty()) {
                stack.push(input);
//                System.out.println("결과 : "+result+" / "+stack.toString()+"\n");
                continue;
            }

            if (stack.peek() >= input) {
//                System.out.printf("    ( %d, %d )\n",input, stack.peek());
                stack.push(input);
                result++;
//                System.out.println("결과 : "+result+" / "+stack.toString()+"\n");
                continue;
            }

            //아래는 스택의 peek()보다 입력이 더 큰 경우임

            int streak = INIT; //같은 숫자가 몇개 연속으로 나오는지 저장할 변수
            int preNum = INIT; //직전에 pop한 값
            boolean isCalced = false;
            while (!stack.isEmpty()) { //push할 수 있을떄까지 반복하기
//                System.out.println("  while "+stack.toString());
                if (stack.peek() < input) {
                    int pop = stack.poll();
                    result++;
//                    System.out.printf("    ( %d, %d )\n",input, pop);
                    if (preNum == INIT) {
                        preNum = pop;
                        streak = 1;
                        isCalced = false;
                    } else {
                        if (pop == preNum) {
                            streak++;
                            isCalced = false;
                        } else {
                            //이부분에서 result에 더해줄건데, 2가지를 계산해야함.
                            //1. 연속된 숫자들의 갯수-1만큼 결과값에 더해주기
//                        result += streak - 1; // n+1
                            //2. 연속된 숫자들 간의 조합 갯수를 결과값에 더해주기
//                        result += streak * (streak - 1) / 2 - streak + 1; // nC2 -(n+1)

                            //1+2를 합치면 아래와 같이 nC2만 남는다.
                            result += (long) streak * (long) (streak - 1) / 2;
                            isCalced = true;

//                            System.out.println((streak * (streak - 1) / 2)+"만큼 확 더해줘");


                            streak = 1;
                            preNum = pop;
                        }
                    }
                } else if (stack.peek() >= input) {
                    preNum = INIT;
                    //이부분에서 result에 더해줄건데, 2가지를 계산해야함.
                    //1. 연속된 숫자들의 갯수-1만큼 결과값에 더해주기
//                        result += streak - 1; // n+1
                    //2. 연속된 숫자들 간의 조합 갯수를 결과값에 더해주기
//                        result += streak * (streak - 1) / 2 - streak + 1; // nC2 -(n+1)

                    //1+2를 합치면 아래와 같이 nC2만 남는다.
                    result++;
                    result += (long) streak * (long) (streak - 1) / 2;
                    isCalced = true;
//                    System.out.println((streak * (streak - 1) / 2)+"만큼 확 더해줘");
//                    System.out.printf("    ( %d, %d )\n",input, stack.peek());
                    break;
                }
            }

            if (!isCalced) {
                result += (long) streak * (long) (streak - 1) / 2 - streak + 1; // nC2 -(n+1)
//                System.out.println("이거 나오면 안된다;; +"+(streak * (streak - 1) / 2 - streak + 1));
            }
            stack.push(input);

//            System.out.println("결과 : "+result+" / "+stack.toString()+"\n");
        }//for i end

//        System.out.println(stack.toString());

        //스택에 남아있는 것 중에서 연속으로 된 것들 처리
        while (!stack.isEmpty()) {
            int streak = 1; //같은 숫자가 몇개 연속으로 나오는지 저장할 변수
            int preNum = stack.pop(); //직전에 pop한 값
            boolean isCalced = false;

            while (!stack.isEmpty()) {
                int peek = stack.peek();
                if (peek == preNum) {
                    stack.pop();
                    streak++;
                } else { //연속된 숫자가 나오지 않는다면 끝내도 됨
                    result += (long) streak * (long) (streak - 1) / 2;
                    isCalced = true;
                    break;
                }
            }

            if (!isCalced) {
                result += (long) streak * (long) (streak - 1) / 2 - streak + 1;
            }
//            System.out.println(stack.toString());
        }

//        System.out.println("결과 : "+result);
        System.out.println(result);

    }
}
/*
7
2
4
1
2
2
5
1

ans : 10
out : 10

11
3
6
4
3
1
2
4
4
7
7
6

ans : 19
out : 19

5
1
1
1
1
1

ans : 10
out : 10

5
3
1
1
1
1

ans : 10
out : 10

5
1
1
1
1
3

ans : 10
out : 10


14
7
7
8
6
5
3
7
4
7
7
10
6
1
2

[o] 25

10
10
9
1
3
8
6
7
8
5
8

[o] 17

5
4
1
1
3
2

ans 7
out 6

3
1
1
2

ans 3
out 3

// 입력 1
5
5
5
2
2
5

// 출력 1
8

// 입력 2
5
5
5
2
1
5

// 출력 2
7

// 입력 3
5
5
3
1
3
7


// 출력 3
8


// 입력 8
5
4 1 2 2 1

// 출력 8
6
out : 5


// 입력 11
6
6 6 6 5 2 5

// 출력 11
8
out : 7


// 입력 17
5
1 2 2 2 1

// 출력 17
5
out : 4



 */