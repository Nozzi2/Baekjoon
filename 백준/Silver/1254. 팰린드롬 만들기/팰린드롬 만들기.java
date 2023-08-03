import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
팰린드롬을 만드는데 최소한의 조건만 만족하도록 문자를 추가해주면 됨

먼저 문자열을 입력받고
알파뱃 등장횟수를 저장해준다.

팰린드롬은 앞과 뒤가 항상 문자가 1개씩 나와야하므로 등장횟수가 짝수여야만 한다.
하지만 문자열의 길이가 홀수일 때는 정가운데 하나만 추가하면 되므로
단 하나의 문자만 등장횟수가 홀수여야만한다.

등장횟수를 검사해서 홀수인 문자갯수를 구하고
홀수인 문자 갯수를 1개까지 줄여준 후
해당 문자를 출력했을 때 문자열의 길이가 홀수라면
    그대로 출력하고
그렇지 않다면
    해당 문자열 길이+1한 값을 출력한다.

아 위 로직 다틀렸음
문자열 뒤에 출력해야되는거임..

문자열의 맨 뒤에서부터 몇번 인덱스까지 팰린드롬이 되는지 검사한다.
그리고 size + 팰린드롬 인덱스 출력하면 끝


 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();
        int size = input.length();

        int endi = size % 2 == 0 ? size / 2 : size / 2 + 1;
        int pelIdx = size - 1;
        for (int i = 2; i <= endi; i++) {
            int forE = size;
            int forS = forE - i + 1;

            int revOddE = forS - 1;
            int revOddS = revOddE - i + 1;

            int revEvenE = revOddE + 1;
            int revEvenS = revOddS + 1;

            String forward = input.substring(forS, forE);
            sb.append(input.substring(revEvenS, revEvenE));
//            System.out.println("forward : "+forward);
//            System.out.println(" revEven : "+sb.reverse().toString());
            if (forward.equals(sb.reverse().toString())) {
//                System.out.println("  여기서 갱신!");
                pelIdx = revEvenS;
            }
            sb.setLength(0);


            sb.append(input.substring(revOddS, revOddE));
//            System.out.println(input.substring(forS-1, forS)+" 기준");
//            System.out.println(" reveOdd : "+sb.reverse().toString());
            if (forward.equals(sb.reverse().toString())) {
//                System.out.println("  여기서 갱신!");
                pelIdx = revOddS;
            }

            sb.setLength(0);

//            System.out.println();
        }

        if (size % 2 == 0) {
            int forE = size;
            int forS = size/2;

            int revE = forS;
            int revS = 0;

            String forward = input.substring(forS, forE);
            sb.append(input.substring(revS, revE));
//            System.out.println("forward : "+forward);
//            System.out.println(" revEven : "+sb.reverse().toString());
            if (forward.equals(sb.reverse().toString())) {
//                System.out.println("  여기서 갱신!");
                pelIdx = revS;
            }
        }

//        System.out.println();
//        System.out.println("pelIdx : "+pelIdx);

        if (size == 1) {
            System.out.println(1);
        } else {
            System.out.println(size+pelIdx);
        }

//        for (int i = 1; i <= size/2; i=i+2) {
//            int forE = size;
//            int forS = forE-i;
//
//            int revE = forS;
//            int revS = revE-i;
//
//            String forward = input.substring(forS, forE);
//            sb.append(input.substring(revS, revE));
//            System.out.println("forward : "+forward);
//            System.out.println("reverse : "+sb.reverse().toString());
//
//            sb.setLength(0);
//        }



    }
}

/*
abdfhdyrbdbsdfghjkllkjhgfds
ans : 38
out : 33

abcdfiif

abcdfiifdcba

abbag


a
1

ab
3

aa
2

aba

abb

abc

 */