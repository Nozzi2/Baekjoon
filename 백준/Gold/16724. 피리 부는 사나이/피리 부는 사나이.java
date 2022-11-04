import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int R,C;
    static List<Pos> list;
    static int[][] map;
    static int[][] visited;

    static class Pos {
        int r;
        int c;

        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        list = new ArrayList<Pos>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int szCnt=0;
        map = new int[R+2][C+2];
        visited = new int[R + 2][C + 2]; //1일경우 방문O, 2일경우 방문O & 안전지대 카운트 O
        for(int i=1; i<=R; i++){
            char[] chars = br.readLine().toCharArray();
            for(int j=1; j<=C; j++){
                map[i][j] = getDir(chars[j - 1]);
            }
        }

        //1,1부터 r,c까지 탐색
        for(int i=1; i<=R; i++){
            for(int j=1; j<=C; j++){
                if(visited[i][j]==0){//탐색하지 않았었으면 탐색 진행
                    Pos safeZone = checkMap(new Pos(i, j));
                    //탐색 마친 후 도착한 곳이 이전에 방문하지 않았다면
                    if(visited[safeZone.r][safeZone.c]==1){
                        szCnt++; //안전지대 갯수 ++
//                        System.out.println(safeZone);
                    }
//                    for(int a=0; a<=R+1; a++){
//                        for(int b=0; b<=C+1; b++){
//                            System.out.print(visited[a][b]+" ");
//                        }
//                        System.out.println();
//                    }
//                    System.out.println(list.size());
//                    System.out.println();
                    //이번 탐색에 방문한 모든 칸 값 갱신
                    for(Pos p : list){
                        visited[p.r][p.c]=2;
                    }
                    list.clear();
                }
            }
        }

//        for(int i=0; i<=R+1; i++){
//            for(int j=0; j<=C+1; j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }

        System.out.println(szCnt);
    }

    private static Pos checkMap(Pos cur) {
//        System.out.println("  탐색 시작했다~");
        //방문한 칸이 나올때까지 방향대로 탐색 진행
        while(visited[cur.r][cur.c]==0){
            list.add(new Pos(cur.r,cur.c));
            visited[cur.r][cur.c]=1;
            int dir = map[cur.r][cur.c];
//            System.out.println("    "+dir+"방향 / "+cur.r+","+cur.c);
            cur.r += dr[dir];
            cur.c += dc[dir];
        }
//        System.out.println("  탐색 끝났다~");
        return cur;
    }

    private static int getDir(char c){
        switch (c){
            case 'U':
                return 0;
            case 'D':
                return 1;
            case 'L':
                return 2;
            case 'R':
                return 3;
        }
        return -1;
    }
}