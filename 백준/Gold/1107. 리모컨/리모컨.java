import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
가고싶은 채널 번호로 가기 위해서
특정 숫자가 고장난 리모컨을 가지고
최소한의 조작을 하여 해당 채널로 간다고 했을 때 가장 적게 조작한 횟수를 구해야한다.

세 가지 경우를 고려하면 될 듯.
1. 100에서 +, -를 눌러서 타겟 숫자로 가는 경우 (타겟숫자 - 100)
2. 타겟 숫자보다 크지만 가장 타겟 숫자와 가까운 경우 (|타겟숫자 - 크면서 가까운 숫자|)
3. 타겟 숫자보다 작지만 가장 타겟 숫자와 가까운 경우 (타겟숫자 - 작으면서 가까운 숫자)

위 세가지 케이스를 구하면 문제에서 요구하는 모든 경우를 고려해볼 수 있을 것임.

그렇다면 2. 3.을 구하는 방법은?
리모컨에서 사용할 수 있는 숫자를 조합해서 만들어야 하므로
타겟 숫자에서 1씩 더하거나 빼주면서 만들 수 있는 숫자인지 여부를 검사한다.

시간 복잡도는?
채널은 최대 50만까지 주어짐.
2.를 구하기 위해선 50만부터 100만까지 50만번의 연산이 필요하고,
3.를 구하기 위해선 50만부터 0까지 50만번의 연산이 필요하다.
합쳐봤자 100만이므로 문제될 건 없어보임.
 */
public class Main {

    static int START_CH = 100;
    static int LIMIT_CH = 1_000_000;
    static boolean[] canUseNumbers;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TARGET_CH = Integer.parseInt(br.readLine());
        int size = Integer.parseInt(br.readLine());
        canUseNumbers = new boolean[10];
        Arrays.fill(canUseNumbers, true);
        StringTokenizer st = size>0? new StringTokenizer(br.readLine()) : null;
        for (int i = 0; i < size; i++) {
            int num = Integer.parseInt(st.nextToken());
            canUseNumbers[num] = false;
        }
//        System.out.println(Arrays.toString(canUseNumbers));

        //1. 100에서 +, -를 눌러서 타겟 숫자로 가는 경우 (타겟숫자 - 100)
        int case1 = Math.abs(TARGET_CH - START_CH);

        //2. 타겟 숫자보다 크지만 가장 타겟 숫자와 가까운 경우 (|타겟숫자 - 크면서 가까운 숫자|)
        int case2 = Integer.MAX_VALUE;
        boolean isNum = false; //아래 반복문으로 숫자를 만들었는지 여부 저장
        for (int i = TARGET_CH+1; i <= LIMIT_CH; i++) { //5555 > LIMIT_CH//////////////////////////////
            if (checkNumber(i)) {
                isNum = true;
                case2 = i;
                break;
            }
        }
        if (isNum) { //숫자가 만들어진 경우
            //자릿수만큼 버튼을 조작해야함.
            int jarisu = getJarisu(case2);
//            System.out.println("case2의 자릿수 : "+jarisu);
            case2 = case2 - TARGET_CH + jarisu;
        }

        //3. 타겟 숫자보다 작지만 가장 타겟 숫자와 가까운 경우 (타겟숫자 - 작으면서 가까운 숫자)
        int case3 = Integer.MAX_VALUE;
        isNum = false;
        for (int i = TARGET_CH; i >= 0; i--) {
            if (checkNumber(i)) {
                isNum = true;
                case3 = i;
                break;
            }
        }
        if (isNum) { //숫자가 만들어진 경우
            //자릿수만큼 버튼을 조작해야함.
            int jarisu = getJarisu(case3);
//            System.out.println("case2의 자릿수 : "+jarisu);
            case3 = TARGET_CH - case3 + jarisu;
        }

        int min = Math.min(case1, case2);
        min = Math.min(min, case3);

//        System.out.printf("case1 : %d\ncase2 : %d\ncase3 : %d\n",case1, case2, case3);
//        System.out.printf("case1 : %d\ncase2 : %d\n",case1, case2);

        System.out.println(min);
    }

    private static int getJarisu(int num) {
        if(num==0) return 1;

        int jarisu=0;
        while (num > 0) {
            num/=10;
            jarisu++;
        }
        return jarisu;
    }

    //각 자릿수별로 사용할 수 있는 버튼만 있는 숫자인지 검사하는 메소드
    private static boolean checkNumber(int num) {
//        System.out.println(num+"번 검사 시작");
        if (num == 0 && !canUseNumbers[num]) {
            return false;
        }

        while (num!=0) {
            int btnNum = num % 10;
//            System.out.print(btnNum); ////////////////////////
            if (!canUseNumbers[btnNum]) {
//                System.out.println(" > 사용 불가능 XXXXXXXX");
                return false;
            }
//            System.out.println(" > 사용 가능");

//            if(num==0) break;

            num /= 10;
        }
//        System.out.println("사용 가능한 숫자임!!!!!!!!!!!!!!!!!!!!!");
        return true; ////////////////////////////////////////
    }
}