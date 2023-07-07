/*
Disjoin set == 서로소 집합 == Union find 의 가장 대표적인 문제이다.
순전히 Union find 알고리즘 만으로 풀 수 있는 문제임.

먼저 union, find, isSameParent 메소드 부터 구현하여 실습해보고
문제에서 주어지는 요구사항대로 출력할 수 있게 바꿔보자
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        parents = new int[size+1];
        for (int i = 1; i <= size; i++) {
            parents[i] = i;
        }
        int[] clone = parents.clone();

        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int A = find(a);
            int B = find(b);

            if (command == 0) {
                union(A,B);
            } else if (command == 1) {
                System.out.println(isSameParent(A,B)?"YES":"NO");
            }
//            System.out.println(Arrays.toString(clone));
//            System.out.println(Arrays.toString(parents));
//            System.out.println("---------------------------");
        }
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if(parentA < parentB) {
            parents[b] = parentA;
        } else {
            parents[a] = parentB;
        }
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean isSameParent(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        return parentA == parentB;
    }
}