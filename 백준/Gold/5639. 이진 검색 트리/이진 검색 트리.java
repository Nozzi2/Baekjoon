/*
주어진 입력을 트리로 만들어야만 한다.
전위 순회 (루트, 왼쪽, 오른쪽)으로 입력된 것을 트리로 바꿔야 하므로
입력을 받으면 가장 먼저 루트로 등록하고,
루트보다 숫자가 작으면 왼쪽 트리, 루트보다 숫자가 크면 오른쪽 트리로 등록한다.
모든 트리는 단 한개의 수만 가지고 있으며,
왼쪽트리, 오른쪽트리로 구성 되어있다.

숫자를 입력받을 땐 루트부터 입력을 시작해서,
루트와 숫자를 비교하여 자식 트리에 입력을 호출하면 재귀 호출되어 정렬된 위치에 저장된다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static class BinaryTree {
        int number;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(int number) {
            this.number = number;
            this.left = null;
            this.right = null;
        }

        public void setLR(int input) {
            if (this.number > input) {
                if (isLeft()) {
                    this.left.setLR(input);
                } else {
                    this.left = new BinaryTree(input);
                }
            } else {
                if (isRight()) {
                    this.right.setLR(input);
                } else {
                    this.right = new BinaryTree(input);
                }
            }
        }

        public boolean isLeft() {
            if (left == null) {
                return false;
            }
            return true;
        }

        public boolean isRight() {
            if (right == null) {
                return false;
            }
            return true;
        }

        public void print() {
            if (isLeft()) {
                left.print();
            }
            if (isRight()) {
                right.print();
            }
            System.out.println(this.number);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int rootNumber = Integer.parseInt(br.readLine());
        BinaryTree root = new BinaryTree(rootNumber);

        String input;
        while (true) {
            input = br.readLine();
            if (input == null || input.isEmpty()) break;
            root.setLR(Integer.parseInt(input));
        }

        root.print();
    }
}