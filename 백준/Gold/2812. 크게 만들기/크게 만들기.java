import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
문자열의 길이가 500_000이기 때문에
O(n^2)의 풀이는 먼저 배제해야함

큰 숫자를 만들기 위해서는 가장 앞의 번호가 큰게 중요함.
그래서 숫자들을 stack에 집어넣으면서
스택에 최상단의 숫자와 넣을 숫자를 비교해서 넣을 숫자가 더 크다면 pop
넣을 숫자가 작다면 push를 진행하면서
pop한 숫자가 문제에서 주어진 갯수만큼 다 지웠다면
스택을 뒤집어서 출력하면 됨.

 */

public class Main {

    static String fullNumber;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        fullNumber = br.readLine();
        Stack<Integer> stack = new Stack<>();
        stack.push(getNumber(0));
        for (int i = 1; i < size; i++) {
            int curNumber = getNumber(i);

            //스택이 비어있지 않고, 지울 숫자가 남아있고, 스택의 상단에 있는 숫자가 현재 숫자보다 작다면
            while (!stack.empty() && k > 0 && stack.peek() < curNumber) {
                stack.pop();
                k--;
            }
            stack.push(curNumber);
//            System.out.println(stack.toString());
        }
//        System.out.println("-----------탐색 끝------------");

        //숫자 지울게 남아있다면 맨 아랫자리 부터 지워주기
        while(k>0 && !stack.isEmpty()){
            stack.pop();
            k--;
//            System.out.println("pop() > "+stack.toString());
        }

        String maxNumStack = stack.toString(); //[7, 7, 5, 8, 4, 1]
        maxNumStack = maxNumStack.substring(1,maxNumStack.length()-1); //7, 7, 5, 8, 4, 1

        String maxNum = maxNumStack.replaceAll(", ", ""); //775841
        System.out.println(maxNum);
    }

    public static int getNumber(int index) {
        return fullNumber.charAt(index)-'0';
    }
}

/*
2 1
21

 */