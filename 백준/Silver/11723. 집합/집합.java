import java.util.*;
import java.io.*;

/*
비트마스킹으로 풀면 쉬울듯??
1~20까지이므로 int로 32승까지니까 비트마스킹 가능
*/
public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int reqCount = Integer.parseInt(br.readLine());
        int bit=0;
        int num=0;

        StringTokenizer st;
        for(int i=0; i<reqCount; i++){
            String input = br.readLine();
            st = new StringTokenizer(input);
            String command = st.nextToken();
            // System.out.println(input);

            switch(command){
                case "add":
                    num = Integer.parseInt(st.nextToken());
                    bit |= 1<<num;
                    break;
                case "remove":
                    num = Integer.parseInt(st.nextToken());
                    bit &= ~(1<<num);
                    break;
                case "check":
                    num = Integer.parseInt(st.nextToken());
                    if((bit & (1<<num)) == 1<<num){
                        sb.append(1);
                    } else {
                        sb.append(0);
                    }
                    sb.append("\n");
                    break;
                case "toggle":
                    num = Integer.parseInt(st.nextToken());
                    bit ^= 1<<num;
                    break;
                case "all":
                    for(int b=1; b<=20; b++){
                        bit |= 1<<b;
                    }
                    break;
                case "empty":
                    for(int b=1; b<=20; b++){
                        bit &= ~(1<<b);
                    }
                    break;
            }
            // print(bit);
            
        }
        System.out.println(sb.toString());
    }

    static void print(int bit){
        for(int i=20; i>0; i--){
            System.out.print((bit & (1<<i))==1<<i?1:0);
        }
        System.out.println();
        System.out.println();
    }
}