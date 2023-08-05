import java.util.*;
import java.io.*;


public class Main
{
    static StringBuilder sb;
    static Node[] tree;

    static class Node {
        char alpha;
        Node left;
        Node right;

        public Node(char alpha){
            this.alpha = alpha;
            left = null;
            right = null;
        }

        void addLeft(Node n){
            this.left = n;
        }

        void addRight(Node n){
            this.right = n;
        }

        Node getLeft() {
            return left;
        }

        Node getRight() {
            return right;
        }
    }

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        // StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(br.readLine());
        tree = new Node[size];
        for(int i=0; i<size; i++){
            tree[i] = new Node((char)(i+'A'));
        }

        StringTokenizer st;
        for(int i=0; i<size; i++){
            st = new StringTokenizer(br.readLine());
            int parent = (char)st.nextToken().charAt(0)-'A';
            char left = (char)st.nextToken().charAt(0);
            char right = (char)st.nextToken().charAt(0);

            if(left != '.'){
                tree[parent].addLeft(tree[left-'A']);
            }

            if(right != '.'){
                tree[parent].addRight(tree[right-'A']);
            }
        }

        // for(Node n : tree){
        //     System.out.println(n.alpha);
        //     if(n.left != null){
        //         System.out.print(n.left.alpha);
        //     }
        //     if(n.right != null){
        //         System.out.print(n.right.alpha);
        //     }
        //     System.out.println();
        //     System.out.println();
        // }


        jun(tree[0]);
        sb.append("\n");
        jung(tree[0]);
        sb.append("\n");
        who(tree[0]);
        sb.append("\n");

        System.out.print(sb.toString());
    }

    static void jun(Node n){
        if(n==null) return;

        sb.append(n.alpha);
        jun(n.left);
        jun(n.right);
    }

    static void jung(Node n){
        if(n==null) return;

        jung(n.left);
        sb.append(n.alpha);
        jung(n.right);
    }

    static void who(Node n){
        if(n==null) return;

        who(n.left);
        who(n.right);
        sb.append(n.alpha);
    }

}