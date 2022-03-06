package Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//1026��. ���� (�ǹ�4)
public class Treasure {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int num1 = Integer.parseInt(br.readLine());

		//string���� �ѹ��� �Է¹ޱ�
		String[] s1 = br.readLine().split(" "); //���� ����
		String[] s2 = br.readLine().split(" "); //���� ����

		//ArrayList ����
		ArrayList<Integer> numsA = new ArrayList<Integer>();
		ArrayList<Integer> numsB = new ArrayList<>();

		//string�迭 > ArrayList�� ����
		for (int i = 0; i < num1; i++) {
			numsA.add(Integer.parseInt(s1[i]));
			numsB.add(Integer.parseInt(s2[i]));
		}
		
		// ������������ ����
		Collections.sort(numsA);
		// ������������ ����
		Collections.sort(numsB, Collections.reverseOrder());
		
		//�հ� ���
		int sum=0;
		for (int i = 0; i < numsA.size(); i++) {
			sum = sum+(numsA.get(i)*numsB.get(i));
		}
		
		System.out.println(sum);
	}

}
