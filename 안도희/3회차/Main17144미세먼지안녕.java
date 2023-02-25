package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main17144�̼������ȳ� {
	static int r;
	static int c;
	static int[] m;
	static int[][] arr, copy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		m = new int[2];// ���� û���� row ��
		int mcnt = 0;
		int result=0;

		arr = new int[r][c];
		copy = new int[r][c];
		for (int i = 0; i < r; i++) {// map
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == -1) {// ����û����
					m[mcnt] = i;
					mcnt++;
				}
			}
			copy[i] = Arrays.copyOf(arr[i], arr[i].length);
		}

		for (int tc = 0; tc < t; tc++) {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (arr[i][j] != 0 && arr[i][j] != -1) // Ȯ��
						sp(i, j);
				}
			}

			on();// ����û���� �۵�
			// tc1ȸ ��
			for (int i = 0; i < r; i++) {
				arr[i] = Arrays.copyOf(copy[i], copy[i].length);
			}
			
		}
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(arr[i][j]!=-1) result+=arr[i][j];
			}
		}
		System.out.println(result);

	}

	private static void on() {

		for (int t = 0; t < 2; t++) {
			int mr = m[t];// ����û���� �ప

			if (t == 0) {// �ݽð� ����
				int[] di = { -1, 0, 1, 0 };
				int[] dj = { 0, 1, 0, -1 };
				int i = mr - 1;
				int j = 0;
				int d = 0;

				while (true) {
					copy[i][j] = copy[i + di[d]][j + dj[d]];
					i += di[d];
					j += dj[d];
					if ((j == 0 && i == 0) || (j == c - 1 && i == 0) || (j == c - 1 && i == mr))
						d++;
					if (i == mr && j == 1) {
						copy[i][j]=0;
						break;
					}
						
				}

			}

			else {// �ð����
				int[] di = { 1, 0, -1, 0 };
				int[] dj = { 0, 1, 0, -1 };
				int i = mr + 1;
				int j = 0;
				int d = 0;

				while (true) {
					copy[i][j] = copy[i + di[d]][j + dj[d]];
					i += di[d];
					j += dj[d];
					if ((j == 0 && i == r - 1) || (j == c - 1 && i == r - 1) || (j == c - 1 && i == mr))
						d++;
					if(i==mr && j==1) {
						copy[i][j]=0;
						break;
					}
				}

			}

		}

	}

	private static void sp(int i, int j) {
		int[] di = { -1, 0, 1, 0 };
		int[] dj = { 0, 1, 0, -1 };
		int s = arr[i][j] / 5;// Ȯ��� �̼�����
		int n = 0;// � �������� Ȯ�� �Ǿ�����

		for (int d = 0; d < 4; d++) {
			int rd = i + di[d];
			int cd = j + dj[d];
			if (!(cd == 0 && (rd == m[0] || rd == m[1])) && rd >= 0 && rd < r && cd >= 0 && cd < c) {
				copy[rd][cd] += s;
				n++;
			}
		}
		copy[i][j] -= s * n;

	}
}
