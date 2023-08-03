import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
일단 문자열을 입력받고
배열을 선언해서 A부터 Z까지 등장한 횟수를 저장함.

만약 문자열의 길이가 홀수인데,
    등장횟수가 모두 짝수이거나,
    등장횟수가 홀수인게 2개 이상이라면
        출력불가
만약 문자열의 길이가 짝수인데,
    등장횟수가 하나라도 홀수라면
        출력불가

위 조건을 만족한다면 펠린드롬으로 출력 가능함.
사전에서 가장 앞에있는 단어를 출력해야하므로
A부터 Z까지 등장횟수의 절반만큼 출력하고
다시 Z부터 A까지 절반만큼 출력한다.

단, 문자열 길이가 홀수인 경우일 떄는
등장횟수가 홀수인 알파벳을 Z까지 출력을 마친 후에 출력해야함 (가장 중앙에 있어야함)
 */
public class Main {

    static final String IMPOSSIBLE = "I'm Sorry Hansoo";
    static final int NONE = 987654321;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        int size = input.length;
        int[] charCounts = new int[26]; //A부터 Z까지의 등장 횟수

        for (int i = 0; i < size; i++) {
            int idx = input[i]-'A';
            charCounts[idx]++;
        }

        int oddIdx = NONE;

        if (size % 2 == 0) { // 문자열 길이가 짝수일 경우
            for (int c : charCounts) {
                if (c % 2 == 1) {
                    System.out.println(IMPOSSIBLE);
                    System.exit(0);
                }
            }
        } else { //홀수인 경우
            int oddCount = 0; //등장횟수가 홀수인 알파벳 갯수 저장

            for (int i=0, endi=charCounts.length; i<endi; i++) {
                if (charCounts[i] % 2 == 1) {
                    oddCount++;
                    oddIdx = i;
                }
            }

            if (oddCount != 1) {
                System.out.println(IMPOSSIBLE);
                System.exit(0);
            }
        }

        //A-Z 순으로 절반의 문자열 출력
        for (int i = 0, endi = charCounts.length; i < endi; i++) {
            for (int j = 0, endj = charCounts[i] / 2; j < endj; j++) {
                sb.append((char) ('A' + i));
            }
        }

        char[] reverse = sb.toString().toCharArray();

        if (oddIdx != NONE) {
            sb.append((char) ('A' + oddIdx));
        }

        for (int i = reverse.length - 1; i >= 0; i--) {
            sb.append(reverse[i]);
        }

        System.out.println(sb.toString());
    }
}