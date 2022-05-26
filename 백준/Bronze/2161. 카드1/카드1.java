import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class Main {
 
    private static Scanner sc = new Scanner(System.in);
 
    public static void main(String[] args) {
 
        Queue queue = new LinkedList(); // queue 형태로 선언
 
        int N = sc.nextInt();
 
        for (int i = 1; i <= N; i++) {
 
            queue.offer(i); // offer = 순차보관
 
        }
 
        while (!queue.isEmpty()) {
 
            Object out = queue.poll(); // poll = 가장먼저 보관한 값 꺼내고 반환
 
            System.out.print(out + " ");
 
            if (queue.isEmpty()) // 비어있는지 판별
 
                break;
 
            Object in = queue.poll(); // 두번째 값 꺼낸뒤
 
            queue.offer(in);    // 뒤에 다시 넣음.
 
        }
 
    }
 
}