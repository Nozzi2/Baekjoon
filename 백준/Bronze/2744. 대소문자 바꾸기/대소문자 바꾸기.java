import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();

        StringBuilder sb = new StringBuilder();
        int upperCaseCount = 'A'-'a';
        int lowerCaseCount = 'a'-'A';
        for(char c : input){
            if(c >= 'A' && c <= 'Z') {
                sb.append((char)(c+lowerCaseCount));
            } else {
                sb.append((char)(c+upperCaseCount));
            }
        }
        System.out.println(sb.toString());
    }
}
/*
WrongAnswer
*/