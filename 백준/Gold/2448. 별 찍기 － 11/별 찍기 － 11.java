import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
반칸 띄어쓰기 그리기 반칸 띄어쓰기
그리기         그리기

그리기를 2개로 나눈다면

띄  그1 그2 띄
그1 그2 그1 그2

이렇게 나눌 수 있음.

3
그1 그2

6
띄  그1 그2 띄
그1 그2 그1 그2

12
띄  띄  띄  그1 그2 띄  띄  띄
띄  띄  그1 그2 그1 그2 띄  띄
띄  그1 그2 띄  띄  그1 그2 띄
그1 그2 그1 그2 그1 그2 그1 그2
 */
public class Main {
    static int[][] pattern = {{0,1,2,0},{1,2,1,2}}; //0:띄, 1:그1, 2:그2
    static StringBuilder sb;
    static boolean[][] isStar = { {false, false, true, false, false, false},//__*___
                                  {false, true, false, true, false, false}, //_*_*__
                                  {true, true, true, true, true, false}};   //*****_

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int size = Integer.parseInt(br.readLine());

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size * 2; c++) {
//                System.out.printf("(%d, %d) 검사\n",r,c);
                if (isPrintStar(size, r, c)) {
                    sb.append('*');
//                    System.out.println("      여기서 별찍힘!");
                } else {
                    sb.append(' ');
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static boolean isPrintStar(int size, int r, int c){
//        System.out.printf("  isPrintStart(%d, %d, %d)\n", size, r, c);
        if (size == 3) {
            return isStar[r][c];
        }

        int divideR=r/(size/2);
        int divideC=c/(size/2);
//        System.out.printf("  나눴을 때 %d, %d \n", divideR, divideC);


        size /= 2;
        switch(pattern[divideR][divideC]) {
            case 1 :
                return isPrintStar(size, r-(divideR*size), c-(divideC*size));
            case 2 :
                return isPrintStar(size, r-(divideR*size), c+size-(divideC*size));
        }
        return false;
    }
}
/*
3
  *
 * *
*****

6
     *
    * *
   *****
  *     *
 * *   * *
***** *****

12
           *
          * *
         *****
        *     *
       * *   * *
      ***** *****
     *           *
    * *         * *
   *****       *****
  *     *     *     *
 * *   * *   * *   * *
***** ***** ***** *****

24
                        *
                       * *
                      *****
                     *     *
                    * *   * *
                   ***** *****
                  *           *
                 * *         * *
                *****       *****
               *     *     *     *
              * *   * *   * *   * *
             ***** ***** ***** *****
            *                       *
           * *                     * *
          *****                   *****
         *     *                 *     *
        * *   * *               * *   * *
       ***** *****             ***** *****
      *           *           *           *
     * *         * *         * *         * *
    *****       *****       *****       *****
   *     *     *     *     *     *     *     *
  * *   * *   * *   * *   * *   * *   * *   * *
 ***** ***** ***** ***** ***** ***** ***** *****
*/