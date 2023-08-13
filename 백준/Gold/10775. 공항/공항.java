import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
G개의 게이트와 A개의 비행기가 들어온다고 했을 때
각 비행기는 1부터 g 게이트에만 도킹할 수 있다.
비행기가 들어오는 순서가 주어졌을 때
도킹한 게이트의 갯수의 최대값을 구해야함.

비행기가 들어왔을 떄 무조건 가장 높은 값의 게이트에 도킹하는게 맞음.
그럼 비행기가 이미 들어와서 게이트가 도킹이 되어있을 때
다음 비행기가 어느 게이트에 도킹해야하는지 최대한 빨리 찾아낼 수 있어야함.

최악의 값은 G 100_000, A 100_000
G*A만큼 해서 100억이 넘어간다.

이진 트리구조로 만들어서
해당 트리마다 범위와 해당 범위내에 도킹 가능한 게이트의 갯수를 저장한다.
그래서 게이트 번호가 들어왔을 때
트리를 탐색하면서 도킹 가능한 게이트를 최대한 빨리 찾는것임

트리 클래스
    게이트번호 min값, max값
    게이트 개수
    왼쪽트리, 오른쪽트리

 */
public class Main {

    static class Tree {
        int min;
        int max;
        int count;
        Tree left;
        Tree right;

        public Tree(int min, int max) {
            this.min = min;
            this.max = max;
            this.count = max - min + 1;
            if (min != max) {
                int mid = (min + max - 1)/2;
                this.left = new Tree(min, mid);
                this.right = new Tree(mid + 1, max);
            }
        }

        @Override
        public String toString() {
            return "[ "+min+" ~ "+max+" ] > "+count+"\n"
                    +"left "+(this.left!=null?this.left.toString():"null\n")
                    +"right "+(this.right!=null?this.right.toString():"null\n");
        }

        boolean doking(int num) {
            if (this.count <= 0) {
                return false;
            }

            if (min == max) { //루트 노드라면
                if (this.count == 1) { //도킹할 수 있다면
                    this.count--;
                    return true;
                } else {
                    return false;
                }
            } else {
                int mid = (min + max - 1)/2;
                if (num > mid) { //오른쪽트리부터 순회할지?
                    if (right.doking(num)) { //오른쪽 트리 먼저 순회
                        this.count--;
                        return true;
                    } else {
                        if (left.doking(num)) { //왼쪽트리 순회
                            this.count--;
                            return true;
                        }
                        return false;
                    }
                } else {
                    if (left.doking(num)) { //왼쪽트리만 순회
                        this.count--;
                        return true;
                    }
                    return false;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        Tree root = new Tree(1,size);
        int T = Integer.parseInt(br.readLine());
        int count = 0;
        for (int t = 1; t <= T; t++) {
            int gateNum = Integer.parseInt(br.readLine());
            if (root.doking(gateNum)) {
                count++;
            } else {
                break;
            }
        }
//        System.out.println(root);

        System.out.println(count);
    }
}

/*
4
6
2
2
3
3
4
4

 */