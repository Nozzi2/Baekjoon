import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
클래스를 하나 만들어서
순위, 값, 순서 이렇게 저장한다.
입력 받으면서 순서와 값을 저장하고
클래스 배열을 만든 후
값을 기준으로 오름차순 정렬한다.
클래스 배열을 순회하면서 순위를 0부터 매기고
이전 값보다 더 커진다면 ++순위 한 값을 저장한다.

배열 순회가 끝나면
다시 순서 기준으로 정렬하고
하나씩 순위를 출력해준다.
 */
public class Main {

    static class Pos {
        int value;
        int rank;
        int index;

        public Pos(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public String toString() {
            return "index: "+index+", value: "+value+", rank: "+rank+"\n";
        }

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        Pos[] positions = new Pos[size];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            int value = Integer.parseInt(st.nextToken());
            positions[i] = new Pos(i, value);
        }

        Arrays.sort(positions, (o1, o2) -> o1.value - o2.value); //값 기준 정렬

        int curValue = positions[0].value;
        int curRank = 0;
        for (int i = 0; i < size; i++) {
            if (curValue == positions[i].value) {
                positions[i].rank = curRank;
            } else {
                curValue = positions[i].value;
                positions[i].rank = ++curRank;
            }
        }

        Arrays.sort(positions, (o1, o2) -> o1.index - o2.index);

        StringBuilder sb = new StringBuilder();
        for (Pos p : positions) {
            sb.append(p.rank).append(' ');
        }
        System.out.println(sb.toString());

    }
}

/*
5
2 4 -10 4 -9
 */