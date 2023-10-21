import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Cow {
    int arrivedTime;
    int spendTime;

    public Cow(int arrivedTime, int spendTime) {
        this.arrivedTime = arrivedTime;
        this.spendTime = spendTime;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        StringTokenizer st;

        Cow[] cows = new Cow[size];
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            int arrivedTime = Integer.parseInt(st.nextToken());
            int spendTime = Integer.parseInt(st.nextToken());
            cows[i] = new Cow(arrivedTime, spendTime);
        }

        Arrays.sort(cows, (o1, o2) -> o1.arrivedTime - o2.arrivedTime);

        int curTime = 0;
        for (Cow cow : cows) {
            if (cow.arrivedTime > curTime) {
                curTime = cow.arrivedTime;
            }
            curTime += cow.spendTime;
        }

        System.out.println(curTime);
    }
}
/*
3
2 1
8 3
5 7

 */