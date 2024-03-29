import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int R,C;
    static int ORIGINAL_BLIND_CNT;	//원래 입력된 map에서의 사각지대 갯수
    static int blindCnt;			//TC마다 계산할 사각지대 갯수
    static int minBlindCnt;			//정답으로 출력할 최소 사각지대 갯수
    static int combSize;
    static List<CCTV> ccs;
    static int[] dirComb;
    static int[][] matrix;
    static boolean[][] viewSpot; //볼 수 있는 칸들
    static boolean[][] copyViewSpot; //메소드에서 사용할 복사한 배열
    static int[] dr= {0,-1,0,1}; //우 상 좌 하하
    static int[] dc= {1,0,-1,0}; //우 상 좌 하하

    static class CCTV {
        int r;
        int c;
        int dir; //0우 1상 2좌 3하
        int type;

        //처음 만들떄 생성자
        CCTV(int r, int c, int type){
            this.r = r;
            this.c = c;
            this.type = type;
            this.dir = 0;
        }

        //객체 복사할때 방향도 정해줄 생성자
        CCTV(int r, int c, int type, int dir){
            this.r = r;
            this.c = c;
            this.type = type;
            this.dir = dir;
        }

        //cctv를 90도 회전하는 메소드
        public void rotate() {
            this.dir = (this.dir+1)%4;
        }

        //조합을 통해 cctv가 감시하는 첫번째 방향을 지정하는 메소드
        public void setDir(int num) {
            this.dir = num;
        }

        //cctv가 감시할 다음 타일로 좌표를 옮기는 메소드
        public void next() {
            this.r += dr[this.dir];
            this.c += dc[this.dir];
        }

        //현재 좌표가 배열을 벗어났는지 검사하는 메소드
        public boolean isOut() {
            if(r<=0 || c<=0 || r>R || c>C) {
                return true;
            }
            return false;
        }

        //CCTV의 상태를 확인하기 위한 테스트 메소드
        public void print() {
            System.out.print(type+"타입 / ");
            System.out.print("좌표 : "+r+", "+c+" ");
            switch(dir) {
                case 0:
                    System.out.println("우");
                    break;
                case 1:
                    System.out.println("상");
                    break;
                case 2:
                    System.out.println("좌");
                    break;
                case 3:
                    System.out.println("하");
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("res/15683boj.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R= Integer.parseInt(st.nextToken());
        C= Integer.parseInt(st.nextToken());

        ORIGINAL_BLIND_CNT=R*C;
        minBlindCnt = Integer.MAX_VALUE;
        matrix = new int[R+2][C+2];
        viewSpot = new boolean[R+2][C+2];
        ccs = new ArrayList<>();

        for(int i=1, endi=R; i<=endi; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1, endj=C; j<=endj; j++) {
                int input = Integer.parseInt(st.nextToken());

                //1,3,4,5번 CCTV는 각각 0,1,2,3으로 입력받고, 2번 CCTV만 -1로 입력받는다.
                //이렇게 저장하면 type이 곧 회전할 방향의 갯수와도 같게 된다.
                switch(input) {
                    case 1:
                        input = input-1;
                        ccs.add(new CCTV(i,j,input));
                        break;
                    case 3:
                    case 4:
                    case 5:
                        input = input-2;
                        ccs.add(new CCTV(i,j,input));
                        break;
                    case 2:
                        input = -1;
                        ccs.add(new CCTV(i,j,input));
                        break;
                    case 6:
                        viewSpot[i][j] = true; //결과 출력할 때 사각지대 칸만 출력해야하므로 벽은 세면 안됨
                        ORIGINAL_BLIND_CNT--;
                        break;
                }
                matrix[i][j] = input;

            }//for j
        }//for i
        dirComb = new int[ccs.size()]; //CCTV들의 방향을 저장
        combSize = ccs.size();

        perm(0);
        System.out.println(minBlindCnt);
    }

    //cctv별로 방향을 정해줄 중복순열 메소드
    private static void perm(int cnt) {
        if(cnt==combSize) {
            copyViewSpot = copyArray(viewSpot); //사각지대 배열을 복사
            blindCnt = ORIGINAL_BLIND_CNT;   //원래 사각지대값을 복사
            setCCTVsDir(dirComb);			//cctv들의 방향 설정
            for(CCTV cc : ccs) {
                view(cc);
            }
            minBlindCnt = Math.min(minBlindCnt, blindCnt);
            return;
        }

        if(ccs.get(cnt).type == 3) { //5번 CCTV(type3)는 4방향이므로 방향을 선택할 필요 없음
            perm(cnt+1);
            return;
        }

        if (ccs.get(cnt).type == -1) { //2번 CCTV는 양방향이므로 방향을 우 상 중에만 선택하면 됨
            for(int i=0, endi=1; i<=endi; i++) {
                dirComb[cnt] = i;
                perm(cnt+1);
            }//for i
            return;
        }

        for(int i=0, endi=3; i<=endi; i++) {
            dirComb[cnt] = i;
            perm(cnt+1);
        }//for i
    }


    private static void view(CCTV origin) {
        if(origin.type == -1) {//원래의 2번 CCTV(type:-1)는 두방향만 비추면 됨
            setViewSpot(origin);
            origin.rotate(); //상하, 또는 좌우 이기 때문에 2번 돌아야한다.
            origin.rotate();
            setViewSpot(origin);
            return;
        }

        for(int i=0, endi=origin.type; i<=endi; i++) { //cctv의 타입별로 몇번 회전할지 결정됨
            setViewSpot(origin);
            origin.rotate();
        }
    }

    //벽을 만나거나 끝에 도달할때까지 볼 수 있는 곳을 표시해주는 메소드
    private static void setViewSpot(CCTV origin) {
        CCTV cctvViewRange = new CCTV(origin.r, origin.c, origin.type, origin.dir);
        while(!cctvViewRange.isOut() && matrix[cctvViewRange.r][cctvViewRange.c] !=6) {
            if(!copyViewSpot[cctvViewRange.r][cctvViewRange.c]) { //사각지대 였던 곳만
                copyViewSpot[cctvViewRange.r][cctvViewRange.c] = true;
                blindCnt--; //사각지대 카운트 빼준다
            }
            cctvViewRange.next();
        }
    }


    public static void setCCTVsDir(int[] comb) {
        for(int i=0, endi=comb.length; i<endi; i++) {
            ccs.get(i).setDir(comb[i]);
        }
    }

    //배열을 깊은 복사해준다.
    private static boolean[][] copyArray(boolean[][] arr){
        boolean[][] carr = new boolean[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, carr[i], 0, carr[i].length);
        }
        return carr;
    }
}