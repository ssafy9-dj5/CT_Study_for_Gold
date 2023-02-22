package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main17281�߱� {
	static int[] s = new int[9];
	static boolean[] issel = new boolean[9];
	static int inning;
	static int[][] arr;
	static int max=Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		inning = Integer.parseInt(br.readLine());
		arr = new int[inning][9];
		for (int i = 0; i < inning; i++) { //������ ����
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		perm(0);
		System.out.println(max);
	}

	private static void perm(int cnt) {//Ÿ�� ���� ����
		if (cnt == 9) {
			score();
			return;
		}

		if (cnt == 3) {//4��Ÿ�ڴ�
			s[cnt] = 0;// 1�� ����
			issel[0] = true;
			perm(cnt+1);
		}
		
		for (int i = 1; i < 9; i++) { // i�� ���� 1�� ������ 4�� Ÿ�� �����̿��� ��

			if (issel[i])
				continue;
			s[cnt] = i;
			issel[i] = true;
			perm(cnt + 1);
			issel[i] = false;
		}
	}

	private static void score() {
		int player = 0; // ���� Ÿ�� ��ȣ
		int total = 0;
		for (int i = 0; i < inning; i++) {
			int out = 0;
			boolean[] play = new boolean[4]; //��� ��Ȳ
			while (true) {
				switch (arr[i][s[player]]) {
				case 0:
					out++;
					break;
				case 1:
					if(play[3]) {
						total++;
						play[3]=false;
					}
					if (play[2]) {
						play[3]=true;
						play[2]=false;
					}
					if(play[1]) {
						play[2]=true;
						play[1]=false;
					}
					play[1]=true;

					break;
				case 2:
					if(play[3]) {
						total++;
						play[3]=false;
					}
					if (play[2]) {
						total++;
						play[2]=false;
					}
					if(play[1]) {
						play[3]=true;
						play[1]=false;
					}
					play[2]=true;

					break;
				case 3:
					if(play[3]) {
						total++;
						play[3]=false;
					}
					if (play[2]) {
						total++;
						play[2]=false;
					}
					if(play[1]) {
						total++;
						play[1]=false;
					}
					play[3]=true;

					break;

				case 4:
					if(play[3]) {
						total++;
						play[3]=false;
					}
					if (play[2]) {
						total++;
						play[2]=false;
					}
					if(play[1]) {
						total++;
						play[1]=false;
					}
					total++;
					break;
				}

				player = (player+1)%9;
				if (out == 3)
					break;
			}
		}
		if(total>max) max=total;
	}
}
