import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            set.add(input);
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String input = br.readLine();
            if (set.contains(input)) {
                list.add(input);
            }
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        for (String s : list) {
            sb.append(s).append("\n");
        }

        System.out.print(sb.toString());
    }
}

/*
3 4
ohhenrie
charlie
baesangwook
obama
baesangwook
ohhenrie
clinton

//out
2
baesangwook
ohhenrie

 */