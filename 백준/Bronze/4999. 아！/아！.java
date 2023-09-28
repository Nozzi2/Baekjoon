import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] patSays = br.readLine().toCharArray();
        char[] docSays = br.readLine().toCharArray();

        System.out.println(docSays.length <= patSays.length ?"go":"no");
    }
}
/*
2
500 550 4
450 500 7

 */