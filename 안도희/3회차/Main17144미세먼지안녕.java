package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main17144미세먼지안녕 {
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
		m = new int[2];// 공기 청정기 row 값
		int mcnt = 0;
		int result=0;

		arr = new int[r][c];
		copy = new int[r][c];
		for (int i = 0; i < r; i++) {// map
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == -1) {// 공기청정기
					m[mcnt] = i;
					mcnt++;
				}
			}
			copy[i] = Arrays.copyOf(arr[i], arr[i].length);
		}

		for (int tc = 0; tc < t; tc++) {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (arr[i][j] != 0 && arr[i][j] != -1) // 확산
						sp(i, j);
				}
			}

			on();// 공기청정기 작동
			// tc1회 끝
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
			int mr = m[t];// 공기청정기 행값

			if (t == 0) {// 반시계 방향
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

			else {// 시계방향
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
		int s = arr[i][j] / 5;// 확산될 미세먼지
		int n = 0;// 몇개 방향으로 확산 되었는지

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
