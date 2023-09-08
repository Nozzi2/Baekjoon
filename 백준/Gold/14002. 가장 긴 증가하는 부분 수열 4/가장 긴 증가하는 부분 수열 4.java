import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        List<Integer>[] lists = new List[N];
        int[] dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            lists[i] = new ArrayList<>();
            lists[i].add(arr[i]);
            dp[i] = arr[i];
        }

        int maxSize = 0;
        int maxIndex = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if(lists[i].size() <= lists[j].size() ){
                    if (arr[i] > arr[j]) {
                        lists[i] = new ArrayList<>(lists[j]);
                        lists[i].add(arr[i]);
                    }
                }
            }

            if (maxSize < lists[i].size()) {
                maxSize = lists[i].size();
                maxIndex = i;
            }
        }

//        for (List<Integer> list : lists) {
//            for (int n : list) {
//                System.out.print(n+" ");
//            }
//            System.out.println();
//        }

        StringBuilder sb = new StringBuilder();
        sb.append(maxSize).append("\n");
        for (int n : lists[maxIndex]) {
            sb.append(n).append(' ');
        }

        System.out.println(sb.toString());
    }
}

/*
9
5 7 8 1 2 3 6 9 10

11
5 7 8 1 2 3 6 4 5 9 10

7
6 7 8 9 2 3 4

6
1 2 4 5 3 6
[1, 2, 3, 5, 6, 0]
//가장 잘 나오는 반례

13
7 8 10 11 9 12 1 2 3 4 5 6 7

10
7 8 10 11 9 12 1 2 13 4

9
7 8 10 1 2 3 4 9 12

11
20 21 22 10 11 12 1 2 3 23 24
 */