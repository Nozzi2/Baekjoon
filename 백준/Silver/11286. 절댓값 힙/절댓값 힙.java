import java.io.*;
import java.util.*;
/*
절대값이 작은 값을 먼저 출력해야한다.
절대값이 서로 같다면 둘중 작은 값을 먼저 출력한다.
 */
public class Main {

    public static class AbsNumber implements Comparable<AbsNumber> {
        int origin;
        int abs;

        public AbsNumber(int origin){
            this.origin = origin;
            this.abs = Math.abs(origin);
        }

        @Override
        public int compareTo(AbsNumber o) {
            if(this.abs == o.abs){ //두 값이 절대값이 같다면
                return this.origin-o.origin;
            } else {
                return this.abs-o.abs;
            }
        }

        @Override
        public String toString(){
            return origin+"";
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        PriorityQueue<AbsNumber> prique = new PriorityQueue<>();

        for(int i=0; i<T; i++){
            int input = Integer.parseInt(br.readLine());

            if(input == 0){
                if(prique.size() == 0){
                    sb.append(0).append("\n");
//                    System.out.println(prique.toString()+" : "+prique.size());
                } else {
                    int tmp = prique.poll().origin;
                    sb.append(tmp).append("\n");
//                    System.out.println(tmp+" < "+prique.toString()+" : "+prique.size());
                }
                continue;
            }

            prique.add(new AbsNumber(input));
//            System.out.println(prique.toString()+" : "+prique.size());
        }

        System.out.print(sb.toString());
    }
}

/*

10
2
-3
1
3
-1
0
0
0
0
0

20
2
[2] : 1
2
[2, 2] : 2
2
[2, 2, 2] : 3
2
[2, 2, 2, 2] : 4
-2
[-2, 2, 2, 2, 2] : 5
-2
[-2, 2, -2, 2, 2, 2] : 6
-2
[-2, 2, -2, 2, 2, 2, -2] : 7
0
-2 < [-2, 2, -2, 2, 2, 2] : 6
0
-2 < [-2, 2, 2, 2, 2] : 5
0
-2 < [2, 2, 2, 2] : 4
-2
[-2, 2, 2, 2, 2] : 5
-2
[-2, 2, -2, 2, 2, 2] : 6
0
-2 < [-2, 2, 2, 2, 2] : 5
0
-2 < [2, 2, 2, 2] : 4

 */