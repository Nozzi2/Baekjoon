/*
주어진 문자열에서 폭발할문자열을 전부 폭발하고 나서 남은 문자열을 출력하는 문제이다.
약간 뿌요뿌요 같은? 그런 느낌
하나 폭발하고 난 후 문자열에서 또 폭발할 문자열이 있을 수 있다는 의미임

최악 문자열의 길이는 100만이고,
폭발 문자열의 길이는 36이다.
O(NlogN)이나 O(N)으로만 풀 수 있는 문제임

재귀로 검사하는 방식을 생각해봤는데,
100만까지 계속 재귀호출할 수도 있어서 이건 아닌거같다 싶은데,
딱히 풀이가 떠오르지 않음.

혹시 StringTokenizer나 아니면 split을 쓰면 어떨까?
궁금하니까 한번 해봐야게따

!!! 46%에서 메모리 초과 !!!
왜 메모리 초과가 날까?
너무 많은 데이터를 배열에 담을 때 생기는데,
혹시 while문을 방문하면서 String input이 계속 constant pool 영역에 계속 새로 저장이 돼서
너무 많은 메모리를 할당하는게 아닐까?

String input에 계속 저장하지 않고 그냥 sb자체의 길이를 반환하는 식으로 해야겠다.
그래야 string constant pool 영역에 저장하는게 아니라 heap 영역에 저장하니까
(깨알 상식 StringBuilder.toString()은 heap 영역에 저장됩니다.)

아니다.. 계속 메모리 초과가 나온다.
아까도 계속 input = sb.toString()이렇게 저장했었는데, 계속 힙영역에 저장되어있어도 초과가 나오는 거였음..
그냥 split을 쓰는 로직 자체를 쓰면 안될거같음

질문게시판 보니까 StringBuffer 쓰면 메모리초과가 안난다는 얘기가 있는데, 한번 해보겠음!
=> 똑같이 메모리 초과남. 내가 구현한거랑 관련없었음..

스택을 쓰면 된다는 얘기가 있는데, 한번 고민해보자..

입력된 문자열을 char배열로 받고
하나씩 stack에 넣어주고,
만약 새로 입력된 문자열이 폭발문자열 맨끝 문자와 동일하다면
    기존에 stack에 있는 문자열을 하나씩 pop해가면서 검사하기
    검사할 때는 다시 넣어줘야 하니 다른 스택에 넣어주면서 검사
    (검사하면서 더이상 pop할 경우가 없는 경우도 고려해야함.)
    검사하여 폭발문자열과 동일하다면ㄴ
        삭제할 stack은 전부 삭제하기
    동일하지 않다면
        삭제할 stack에 있는 것 전부 원래 스택에 다시 집어넣기
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Main {

    static char target;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] inputs = br.readLine().toCharArray();
        char[] deletes = br.readLine().toCharArray();
        int delSize = deletes.length;
        target = deletes[delSize - 1]; //검사를 시작할 맨 마지막 문자 저장
        Deque<Character> resultStack = new ArrayDeque<>(); //출력할 것만 남길 스택
        Deque<Character> tmpStack = new ArrayDeque<>();

        for (char c : inputs) {
            resultStack.push(c);

            if (c == target && resultStack.size() >= delSize) { //지워야할 문자열의 마지막 문자와 동일하다면 && 스택에 저장된 문자의 갯수가 검사할 문자열보다 많이 저장되어있다면

                //검사해보기
                boolean isSame = true;
                for (int i = delSize - 1; i >= 0; i--) {
                    char checkChar = resultStack.pop();
                    tmpStack.push(checkChar); //임시 스택에 저장

                    if(checkChar != deletes[i]) { //동일하지 않다면
                        isSame = false;
                        break;
                    }
                }

                if (isSame) { //지울 문자열과 동일하다면
                    tmpStack.clear();  //임시스택 삭제
                } else {
                    //결과 스택에 다시 집어넣기
                    while (!tmpStack.isEmpty()) {
                        resultStack.push( tmpStack.pop() );
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!resultStack.isEmpty()) {
            sb.append(resultStack.pollLast());
        }

        String result = sb.toString();
        System.out.println(result.length()==0?"FRULA":result);
    }
}

/*
mirkovC4nizCC44
C4

m i r k o v n i z

AAABABCCBC
ABC
//검사가 다 끝나기 전에 문자열이 먼저 끝나는 경우

출력할 스택
A A A B C

1234567890
aaaaaabbab
aaab

a a a b

b a a a

 */