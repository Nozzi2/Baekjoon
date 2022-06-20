import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = sc.nextInt();
        for (int K = N; K >= 0; K--) {
            if (K == 0) {
                if(N == 1){
                    bw.write("No more bottles of beer on the wall, no more bottles of beer.\n"
                            + "Go to the store and buy some more, " + N + " bottle of beer on the wall.\n");
                } else {
                    bw.write("No more bottles of beer on the wall, no more bottles of beer.\n"
                            + "Go to the store and buy some more, " + N + " bottles of beer on the wall.\n");
                }
            } else if (K == 1) {
                bw.write("1 bottle of beer on the wall, 1 bottle of beer.\n"
                        + "Take one down and pass it around, no more bottles of beer on the wall.\n");
            } else if (K == 2) {
                bw.write("2 bottles of beer on the wall, 2 bottles of beer.\n"
                        + "Take one down and pass it around, 1 bottle of beer on the wall.\n");
            } else {
                bw.write(K + " bottles of beer on the wall, " + K + " bottles of beer.\n"
                        + "Take one down and pass it around, " + (K - 1) + " bottles of beer on the wall.\n");
            }
            bw.write("\n");
        }

        bw.flush();
        sc.close();
        bw.close();
    }
}