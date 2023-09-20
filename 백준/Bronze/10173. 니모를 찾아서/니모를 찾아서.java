import java.util.*;
import java.io.*;


public class Main
{

    static char[] nemo = {'n','e','m','o'};
    static char[] NEMO = {'N','E','M','O'};

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while(!input.equals("EOI")){
            st = new StringTokenizer(input);

            boolean isSame = true;
            while(st.hasMoreTokens()){
                char[] word = st.nextToken().toCharArray();
                // System.out.println(Arrays.toString(word));
                int size = word.length;
                if(size<4) {
                    isSame = false;
                    continue;
                }

                int streak = 0;
                for(int i=0; i<size; i++){
                    if(word[i] == nemo[streak] || word[i] == NEMO[streak]){
                        streak++;
                    } else {
                        streak = 0;
                    }

                    if(streak==4){
                        isSame = true;
                        break;
                    } else {
                        isSame = false;
                    }
                }

                if(isSame){
                    sb.append("Found\n");
                    break;
                }

            }

            if(!isSame){
                sb.append("Missing\n");
            }


            input = br.readLine();
        }

        System.out.println(sb.toString());
    }
}

/*
Marlin names this last egg Nemo, a name that Coral liked.
While attempting to save nemo, Marlin meets Dory,
a good-hearted and optimistic regal blue tang with short-term memory loss. 
Upon leaving the East Australian Current,(888*%$^&%0928375)Marlin and Dory
NEMO leaves for school and Marlin watches NeMo swim away.
EOI

Found
Found
Missing
Missing
Found
*/