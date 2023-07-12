import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
입력받은 문자열을 toCharArray로 변환해서
하나씩 탐색을 진행하여
문자가 '_'일경우 다음 문자를 대문자로 변환시키고
문자가 대문자일 경우 '_'를 출력에 넣어주고 다음 문자는 소문자로 변환시킨다.

!! 4번 연속 틀렸습니다. !!
이거 문제를 아주 꼼꼼히 읽어야한다.
Java, C++이 아니라면 에러를 출력해야함.

Java는 첫문자가 소문자여야만 한다.
    C++도 소문자로 이루어져 있기 떄문에
C++은 '_'를 단어 구분할 때만 사용한다.
 */
public class Main {

    static final int UPPERCASE = 'A'-'a';
    static final int LOWERCASE = 'a'-'A';

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] input = br.readLine().toCharArray();

        boolean isNextUpper = false;
        boolean isJava = false;
        boolean isCpp = false;

        if (input[0] >= 'A' && input[0] <= 'Z') { //첫문자부터 대문자라면
            //에러 출력해야함
            returnError();
        }

        if(input[0] == '_'){ //첫문자부터 '_'라면
            //에러 출력해야함
            returnError();
        }

        for (char c : input) {
            if (isNextUpper) {
                if (c == '_') {
                    returnError();
                } else if (c >= 'A' && c <= 'Z') {
                    returnError();
                } else {
                    sb.append( (char)(c+UPPERCASE) );
                    isNextUpper = false;
                    continue;
                }
            }

            if (c >= 'A' && c <= 'Z') { //자바라면
                sb.append('_');
                sb.append( (char)(c+LOWERCASE) );
                isJava = true;
            } else if (c == '_') { //C++이라면
                isNextUpper = true;
                isCpp = true;
            } else {
                sb.append(c);
            }

            if (isJava && isCpp) {
                returnError();
            }
        }

        if(isNextUpper) { //마지막 글자가 '_'라면
            returnError();
        }

        System.out.println(sb.toString());

    }

    static void returnError() {
        System.out.println("Error!");
        System.exit(0);
    }
}

/*
직접 입력해본 반례
모두 에러가 나와야한다.

___

AAA

a_A

a____b

aaa_

 */