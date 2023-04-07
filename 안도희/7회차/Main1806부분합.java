package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1806�κ��� {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int sum = 0;
		int len = 100001;
		int[] arr = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			arr[i] = arr[i-1]+Integer.parseInt(st.nextToken());
		}
		int start=0;
		int e=0;

		while (true) {
			if (sum < s) {// sum�� s���� ������
				if (e==n)
					break;
				e++;

			} else { // sum�� s���� ũ�ų� ������
				start++;
				if (e==start)
					break;
			}
			sum=arr[e]-arr[start];
			if (sum >= s) // ���ӵ� ���� s���� ũ�ٸ� �ּ� ���� �������ֱ�!
				len = Math.min(len, e-start);

			
		}

		if (len > 100000)// s�̻� �Ұ���
			len = 0;
		System.out.println(len);

	}
}
