import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int R,C,cheeseCnt,hourCnt;
    static boolean[][] map;
    static int[][] visited;
    static Set<Integer> set;

    static class Pos{
        int r;
        int c;

        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }

        boolean isOut() {
            if(r>=0 && c>=0 && r<R && c<C){
                return false;
            }
            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        set = new HashSet<Integer>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new boolean[R][C];

        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine()) ;
            for(int j=0; j<C; j++){
                map[i][j] = Integer.parseInt(st.nextToken())==0?false:true;
                if(map[i][j]) cheeseCnt++;
            }
        }

        Pos start = new Pos(0, 0);

        while(cheeseCnt!=0){
            set.clear();
            visited = new int[R][C];

            //녹일 치즈 찾기위한 bfs탐색
            bfs(start);

            //공기와 닿는 면이 2개 이상인 값을 찾아서 녹여주기
            for(int a : set){
                int tmpR = a/100;
                int tmpC = a%100;
                if(visited[tmpR][tmpC]>=2){
                    map[tmpR][tmpC] = false;
                    cheeseCnt--;
                }
            }
            hourCnt++;

//            for(int i=0; i<R; i++){
//                for(int j=0; j<C; j++){
//                    System.out.print(map[i][j]?1:0);
//                }
//                System.out.println();
//            }
//            System.out.println();
        }
        System.out.println(hourCnt);

    }

    private static void bfs(Pos start) {
        Queue<Pos> que = new ArrayDeque<>();
        que.offer(start);
        visited[start.r][start.c]++;
        while(!que.isEmpty()){
            Pos cur = que.poll();
            for(int i=0; i<4; i++){
                Pos next = new Pos(cur.r+dr[i], cur.c+dc[i]);
                if(next.isOut()) continue;
                if(!map[next.r][next.c]){ //외부 공기일떄
                    if(visited[next.r][next.c]>0){ //방문 했었다면
                        continue;
                    } else {
                        visited[next.r][next.c]++; //방문 체크
                        que.offer(next);
                    }
                } else { //치즈일 때
                    visited[next.r][next.c]++; //방문 체크
                    set.add(next.r*100+next.c); //외부 공기와 닿았던 치즈리스트에 추가
                    //next.r*100+next.c 치즈의 범위가 5~100이기 때문에
                }
            }
        }
    }


}